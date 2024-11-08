package dianafriptuleac.u5w3d5gestione_eventi.controllers;

import dianafriptuleac.u5w3d5gestione_eventi.entities.Evento;
import dianafriptuleac.u5w3d5gestione_eventi.entities.Utente;
import dianafriptuleac.u5w3d5gestione_eventi.exceptions.BadRequestException;
import dianafriptuleac.u5w3d5gestione_eventi.payloads.EventoDTO;
import dianafriptuleac.u5w3d5gestione_eventi.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("api/eventi")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.CREATED)
    public Evento save(@RequestBody @Validated EventoDTO body, BindingResult validationResult,
                       @AuthenticationPrincipal Utente organizzatore) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        return this.eventoService.creaEvento(body, organizzatore);
    }
}
