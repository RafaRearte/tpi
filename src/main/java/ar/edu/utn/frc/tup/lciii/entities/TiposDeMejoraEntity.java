package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TiposDeMejoras", schema = "dbo")
public class TiposDeMejoraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipoDeMejora", nullable = false)
    private Integer id;

    @Column(name = "tipoDeCarta", length = 50)
    private String tipoDeMejora;
}
