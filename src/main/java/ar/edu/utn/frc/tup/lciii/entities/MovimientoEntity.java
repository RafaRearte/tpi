package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Movimientos", schema = "dbo")
public class MovimientoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMovimiento", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idJugador")
    private JugadorEntity idJugador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idJuego")
    private JuegoEntity idJuego;

    @Column(name = "posicionPrevia")
    private Integer posicionPrevia;

    @Column(name = "nuevaPosicion")
    private Integer nuevaPosicion;

}