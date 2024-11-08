package dianafriptuleac.u5w3d5gestione_eventi.services;


import dianafriptuleac.u5w3d5gestione_eventi.entities.Evento;
import dianafriptuleac.u5w3d5gestione_eventi.entities.Prenotazione;
import dianafriptuleac.u5w3d5gestione_eventi.entities.Utente;
import dianafriptuleac.u5w3d5gestione_eventi.exceptions.BadRequestException;
import dianafriptuleac.u5w3d5gestione_eventi.exceptions.NotFoundException;
import dianafriptuleac.u5w3d5gestione_eventi.repositories.EventoReposiitory;
import dianafriptuleac.u5w3d5gestione_eventi.repositories.PrenotazioneRepository;
import dianafriptuleac.u5w3d5gestione_eventi.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PrenotazioneService {

    @Autowired
    private EventoReposiitory eventoReposiitory;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    public Prenotazione salvaPrenotazione(Long eventoId, int numeroPosti, Utente utente) {
        Evento ev = eventoReposiitory.findById(eventoId).orElseThrow(() ->
                new NotFoundException("Nessun evento trovato con l'id: " + eventoId));
        if (ev.getPostiDisponibili() < numeroPosti) {
            throw new BadRequestException("Noon ci sono posti disponobili per la tua richiesta!");
        }
        Prenotazione pr = new Prenotazione();
        pr.setNrPostiPrenotati(numeroPosti);
        pr.setDataPrenotazione(LocalDate.now());
        pr.setUtente(utente);
        pr.setEvento(ev);

        pr = prenotazioneRepository.save(pr);

        //Aggiorno i posto disponobili
        ev.setPostiDisponibili(ev.getPostiDisponibili() - numeroPosti);
        eventoReposiitory.save(ev);

        return pr;
    }
}
