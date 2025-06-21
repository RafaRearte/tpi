package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Dificultades", schema = "dbo")
public class DificultadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDificultad", nullable = false)
    private Integer id;

    @Column(name = "dificultad", length = 50)
    private String dificultad;

}