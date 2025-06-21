package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Juegos_Jugadores", schema = "dbo")
public class JuegosJugadorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idJuegoPeon", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idJugador")
    private JugadorEntity idJugador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idJuego")
    private JuegoEntity idJuego;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEstado")
    private EstadoEntity idEstado;

}