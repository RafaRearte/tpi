package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "TipoCasilla")
public class TipoCasillaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoCasilla;

    @Column
    private String descripcion;

}