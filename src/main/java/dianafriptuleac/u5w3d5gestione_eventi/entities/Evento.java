package dianafriptuleac.u5w3d5gestione_eventi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "eventi")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Evento {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;
    private String titolo;
    private String descrizione;
    private LocalDate dataEvento;
    private String luogo;
    private int postiDisponibili;
    @ManyToOne
    @JoinColumn(name = "organizzatore_id", nullable = false)
    private Utente organizzatore;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<Prenotazione> prenotazionesPerEvento;

}
