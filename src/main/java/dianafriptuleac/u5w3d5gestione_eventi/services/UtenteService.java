package dianafriptuleac.u5w3d5gestione_eventi.services;

import dianafriptuleac.u5w3d5gestione_eventi.entities.Utente;
import dianafriptuleac.u5w3d5gestione_eventi.exceptions.BadRequestException;
import dianafriptuleac.u5w3d5gestione_eventi.exceptions.NotFoundException;
import dianafriptuleac.u5w3d5gestione_eventi.payloads.UtenteDTO;
import dianafriptuleac.u5w3d5gestione_eventi.repositories.PrenotazioneRepository;
import dianafriptuleac.u5w3d5gestione_eventi.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    public Utente save(UtenteDTO body) {
        this.utenteRepository.findByEmail(body.email()).ifPresent(utente -> {
            throw new BadRequestException("Email " + body.email() + " già in uso!");
        });
        Utente newUtente = new Utente(body.nome(), body.cognome(), body.email(), bcrypt.encode(body.password()));
        return this.utenteRepository.save(newUtente);
    }

    public Utente findByEmail(String email) {
        return this.utenteRepository.findByEmail(email).orElseThrow(() ->
                new NotFoundException("L'utente con email " + email + " non è stato trovato"));
    }

    public Utente findById(Long utenteId) {
        return this.utenteRepository.findById(utenteId).orElseThrow(() -> new NotFoundException(utenteId));
    }

    public Utente findByIdAndUpdate(Long utenteId, UtenteDTO body) {
        Utente foundUtente = this.findById(utenteId);
        if (!foundUtente.getEmail().equals(body.email())) {
            this.utenteRepository.findByEmail(body.email()).ifPresent(
                    utente -> {
                        throw new BadRequestException("Email " + body.email() + " è già in uso!");
                    }
            );
        }
        foundUtente.setNome(body.nome());
        foundUtente.setCognome(body.cognome());
        foundUtente.setEmail(body.email());
        foundUtente.setPassword(bcrypt.encode(body.password()));
        return this.utenteRepository.save(foundUtente);
    }

    public Page<Utente> findAll(int page, int size, String sortBy) {
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.utenteRepository.findAll(pageable);
    }
}
