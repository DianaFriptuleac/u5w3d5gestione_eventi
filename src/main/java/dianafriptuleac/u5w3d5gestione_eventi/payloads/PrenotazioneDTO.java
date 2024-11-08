package dianafriptuleac.u5w3d5gestione_eventi.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PrenotazioneDTO(

        @NotEmpty(message = "Inserire il numero di posti da prenotare!")
        @Min(value = 1, message = "È richiesta almeno una prenotazione.")
        int postiDisponibili,

        LocalDate dataPrenotazione,

        @NotNull(message = "Inserire un id dell'evento!")
        Long evento_id

) {
}
