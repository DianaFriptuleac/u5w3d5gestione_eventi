package dianafriptuleac.u5w3d5gestione_eventi.controllers;

import dianafriptuleac.u5w3d5gestione_eventi.entities.Prenotazione;
import dianafriptuleac.u5w3d5gestione_eventi.entities.Utente;
import dianafriptuleac.u5w3d5gestione_eventi.payloads.PrenotazioneDTO;
import dianafriptuleac.u5w3d5gestione_eventi.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping
    @PreAuthorize("hasAuthority('UTENTE')")
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione salvaPrenotazione(@RequestBody PrenotazioneDTO prenotazioneDTO,
                                          @AuthenticationPrincipal Utente utente) {
        return prenotazioneService.salvaPrenotazione(prenotazioneDTO.evento_id(), prenotazioneDTO.postiDisponibili(),
                utente);
    }
}
