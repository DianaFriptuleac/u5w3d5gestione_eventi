package dianafriptuleac.u5w3d5gestione_eventi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prenotazione {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    private int nrPostiPrenotati;
    private LocalDate dataPrenotazione;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    @JsonBackReference //evito i loop
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    @JsonBackReference
    private Evento evento;


}
