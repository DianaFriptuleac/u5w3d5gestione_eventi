package dianafriptuleac.u5w3d5gestione_eventi.services;


import dianafriptuleac.u5w3d5gestione_eventi.entities.Evento;
import dianafriptuleac.u5w3d5gestione_eventi.entities.Utente;
import dianafriptuleac.u5w3d5gestione_eventi.enums.Role;
import dianafriptuleac.u5w3d5gestione_eventi.exceptions.UnauthorizedException;
import dianafriptuleac.u5w3d5gestione_eventi.payloads.EventoDTO;
import dianafriptuleac.u5w3d5gestione_eventi.repositories.EventoReposiitory;
import dianafriptuleac.u5w3d5gestione_eventi.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    @Autowired
    private EventoReposiitory eventoReposiitory;

    private UtenteRepository utenteRepository;

    public Evento creaEvento(EventoDTO eventoDTO, Utente organizzatore) {
        if (organizzatore.getRole() != Role.ORGANIZZATORE) {
            throw new UnauthorizedException("Solo gli organizzatori possono creare eventi!");
        }
        Evento ev = new Evento();
        ev.setTitolo(eventoDTO.titolo());
        ev.setDescrizione(eventoDTO.descrizione());
        ev.setDataEvento(eventoDTO.dataEvento());
        ev.setLuogo(eventoDTO.luogo());
        ev.setPostiDisponibili(eventoDTO.postiDisponibili());
        ev.setOrganizzatore(organizzatore);

        return eventoReposiitory.save(ev);
    }
}
