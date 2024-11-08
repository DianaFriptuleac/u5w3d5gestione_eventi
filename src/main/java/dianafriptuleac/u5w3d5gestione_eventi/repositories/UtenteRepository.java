package dianafriptuleac.u5w3d5gestione_eventi.repositories;

import dianafriptuleac.u5w3d5gestione_eventi.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Long> {

    Optional<Utente> findByEmail(String email);
}
