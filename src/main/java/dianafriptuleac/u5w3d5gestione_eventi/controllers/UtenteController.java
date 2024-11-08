package dianafriptuleac.u5w3d5gestione_eventi.controllers;

import dianafriptuleac.u5w3d5gestione_eventi.entities.Utente;
import dianafriptuleac.u5w3d5gestione_eventi.payloads.UtenteDTO;
import dianafriptuleac.u5w3d5gestione_eventi.services.UtenteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utente")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public Page<Utente> findAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "8") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        return this.utenteService.findAll(page, size, sortBy);
    }


    @GetMapping("/{utenteId}")
    public Utente findById(@PathVariable Long utenteId) {
        return this.utenteService.findById(utenteId);
    }


    //me
    @GetMapping("/me")
    @Transactional
    public Utente getMyProfile(@AuthenticationPrincipal Utente currentAuthenticateUtente) {
        return currentAuthenticateUtente;
    }

    @PutMapping("/me")
    public Utente updateMyProfile(@AuthenticationPrincipal Utente currentAuthenticateUtente,
                                  @RequestBody @Validated UtenteDTO body) {
        return this.utenteService.findByIdAndUpdate(currentAuthenticateUtente.getId(), body);
    }

}
