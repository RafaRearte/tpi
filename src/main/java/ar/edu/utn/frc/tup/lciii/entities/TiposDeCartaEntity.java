package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TiposDeCartas", schema = "dbo")
public class TiposDeCartaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipoDeCarta", nullable = false)
    private Integer id;

    @Column(name = "tipoDeCarta", length = 50)
    private String tipoDeCarta;

}