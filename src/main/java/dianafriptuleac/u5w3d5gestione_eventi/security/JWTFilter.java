package dianafriptuleac.u5w3d5gestione_eventi.security;

import dianafriptuleac.u5w3d5gestione_eventi.entities.Utente;
import dianafriptuleac.u5w3d5gestione_eventi.exceptions.UnauthorizedException;
import dianafriptuleac.u5w3d5gestione_eventi.services.UtenteService;
import dianafriptuleac.u5w3d5gestione_eventi.tools.JWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWT jwt;

    @Autowired
    private UtenteService utenteService;

    //Controllo il token
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new UnauthorizedException("Inserire token nell'Authorization Header nel formato corretto!");
        String accessToken = authHeader.substring(7);
        jwt.verifyToken(accessToken);
        String utenteId = jwt.getIdFromToken(accessToken);
        Utente currentUtente = this.utenteService.findById(Long.parseLong(utenteId));
        Authentication authentication = new UsernamePasswordAuthenticationToken(currentUtente, null, currentUtente.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}

