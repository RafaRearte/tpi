package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "Juegos", schema = "dbo")
public class JuegoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idJuego", nullable = false)
    private Integer id;

    @Column(name = "fechaInicial")
    private Instant fechaInicial;

    @Column(name = "fechaFinal")
    private Instant fechaFinal;

    @Column(name = "idGanador")
    private Integer idGanador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idJugadores")
    private JugadorEntity idJugadores;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAjuste")
    private AjusteEntity idAjuste;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTablero")
    private TableroEntity idTablero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idBanco")
    private BancoEntity idBanco;

}