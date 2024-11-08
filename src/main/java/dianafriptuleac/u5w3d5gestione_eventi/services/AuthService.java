package dianafriptuleac.u5w3d5gestione_eventi.services;

import dianafriptuleac.u5w3d5gestione_eventi.entities.Utente;
import dianafriptuleac.u5w3d5gestione_eventi.exceptions.UnauthorizedException;
import dianafriptuleac.u5w3d5gestione_eventi.payloads.UtenteLoginDTO;
import dianafriptuleac.u5w3d5gestione_eventi.tools.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JWT jwt;

    @Autowired
    private PasswordEncoder bcrypt;

    public String checkAllCredentialsAndToken(UtenteLoginDTO body) {
        Utente utenteFound = this.utenteService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), utenteFound.getPassword())) {
            String accessToken = jwt.createToken(utenteFound);
            return accessToken;
        } else {
            throw new UnauthorizedException("Credenziali utente errate!");
        }
    }
}
