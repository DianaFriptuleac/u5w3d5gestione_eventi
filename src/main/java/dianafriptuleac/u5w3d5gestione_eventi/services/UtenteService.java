package dianafriptuleac.u5w3d5gestione_eventi.services;

import dianafriptuleac.u5w3d5gestione_eventi.entities.Utente;
import dianafriptuleac.u5w3d5gestione_eventi.exceptions.BadRequestException;
import dianafriptuleac.u5w3d5gestione_eventi.exceptions.NotFoundException;
import dianafriptuleac.u5w3d5gestione_eventi.payloads.UtenteDTO;
import dianafriptuleac.u5w3d5gestione_eventi.repositories.PrenotazioneRepository;
import dianafriptuleac.u5w3d5gestione_eventi.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
