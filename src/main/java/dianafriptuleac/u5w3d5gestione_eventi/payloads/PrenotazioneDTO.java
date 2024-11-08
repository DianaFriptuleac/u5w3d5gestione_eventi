package dianafriptuleac.u5w3d5gestione_eventi.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PrenotazioneDTO(

        @NotEmpty(message = "Inserire il numero di posti da prenotare!")
        @Size(min = 1, message = "È richiesta almeno una prenotazione.")
        int postiDisponibili,

        LocalDate dataPrenotazione,

        @NotEmpty(message = "Inserire un id utente!")
        LocalDate utente_id,

        @NotEmpty(message = "Inserire un id dell'evento!")
        LocalDate evento_id

) {
}
