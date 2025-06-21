package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TiposDeJugadores", schema = "dbo")
public class TiposDeJugadorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipoDeJugadores", nullable = false)
    private Integer id;

    @Column(name = "tipo", length = 50)
    private String tipo;
}