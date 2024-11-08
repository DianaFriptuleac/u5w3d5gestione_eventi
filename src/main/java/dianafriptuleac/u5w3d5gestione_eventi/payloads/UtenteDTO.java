package dianafriptuleac.u5w3d5gestione_eventi.payloads;

import dianafriptuleac.u5w3d5gestione_eventi.entities.Evento;
import dianafriptuleac.u5w3d5gestione_eventi.entities.Prenotazione;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;


public record UtenteDTO(

        @NotEmpty(message = "Il nome è obbligatorio!")
        @Size(min = 3, max = 30, message = "Il nome deve essere compreso tra 3 e 30 caratteri!")
        String nome,

        @NotEmpty(message = "Il cognome è obbligatorio!")
        @Size(min = 2, max = 30, message = "Il cognome deve essere compreso tra 2 e 30 caratteri!")
        String cognome,

        @NotEmpty(message = "L'email è un campo obbligatorio!")
        @Email(message = "L'email inserita non è un'email valida")
        String email,

        @NotEmpty(message = "La password è un campo obbligatorio!")
        @Size(min = 4, message = "La password deve avere almeno 4 caratteri!")
        String password,

        List<Prenotazione> prenotazioniUtente,
        List<Evento> eventiOrganizzatore

) {

}
