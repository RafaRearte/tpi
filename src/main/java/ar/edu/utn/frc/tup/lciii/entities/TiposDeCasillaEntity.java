package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TiposDeCasillas", schema = "dbo")
public class TiposDeCasillaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipoDeCasilla", nullable = false)
    private Integer id;

    @Column(name = "descripcion", length = 50)
    private String descripcion;

    @Column(name = "cantidad")
    private Integer cantidad;

}