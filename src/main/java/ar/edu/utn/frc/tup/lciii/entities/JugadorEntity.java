package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Jugadores", schema = "dbo")
public class JugadorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idJugador", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 200)
    private String nombre;

    @Column(name = "saldo")
    private Integer saldo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idColor")
    private ColorEntity idColor;

    @Column(name = "bancarrota")
    private Boolean bancarrota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoDeJugador")
    private TiposDeJugadorEntity idTipoDeJugador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCasillaActual")
    private CasillaEntity idCasillaActual;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEscrituras")
    private EscrituraEntity idEscrituras;

    @Column(name = "esBot")
    private Boolean esBot;

    @Column(name = "ordenDeJuego")
    private Integer ordenDeJuego;

}