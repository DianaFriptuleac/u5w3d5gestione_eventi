package dianafriptuleac.u5w3d5gestione_eventi.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EventoDTO(
        @NotEmpty(message = "Il titolo è un campo obbligatorio!")
        @Size(min = 2, max = 50, message = "Il titolo deve essere compresa tra 2 e 50 caratteri!")
        String titolo,

        @NotEmpty(message = "La descrizione evento è un campo obbligatorio!")
        @Size(min = 5, max = 100, message = "Fornire una descrizione  compresa tra 5 e 100 caratteri!")
        String descrizione,

        @NotEmpty(message = "Fornire una data evento")
        LocalDate dataEvento,

        @NotEmpty(message = "Il luodo dell'evento è un campo obbligatorio!")
        @Size(min = 2, max = 50, message = "Fornire una luogo dell'evento compresa tra 2 e 50 caratteri!")
        String luogo,

        @Min(value = 2, message = "Fornire almeno due posti per evento")
        int postiDisponibili
) {
}
