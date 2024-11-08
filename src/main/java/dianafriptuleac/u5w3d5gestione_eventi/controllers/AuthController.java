package dianafriptuleac.u5w3d5gestione_eventi.controllers;

import dianafriptuleac.u5w3d5gestione_eventi.entities.Utente;
import dianafriptuleac.u5w3d5gestione_eventi.exceptions.BadRequestException;
import dianafriptuleac.u5w3d5gestione_eventi.payloads.UtenteDTO;
import dianafriptuleac.u5w3d5gestione_eventi.payloads.UtenteLoginDTO;
import dianafriptuleac.u5w3d5gestione_eventi.payloads.UtenteLoginResponseDTO;
import dianafriptuleac.u5w3d5gestione_eventi.services.AuthService;
import dianafriptuleac.u5w3d5gestione_eventi.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UtenteService utenteService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente register(@RequestBody @Validated UtenteDTO body, BindingResult validatioResult) {

        if (validatioResult.hasErrors()) {
            String msg = validatioResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Errori nel payload!" + msg);
        }
        return this.utenteService.save(body);

    }

    @PostMapping("/login")
    public UtenteLoginResponseDTO login(@RequestBody UtenteLoginDTO body) {
        return new UtenteLoginResponseDTO(this.authService.checkAllCredentialsAndToken(body));
    }

}
