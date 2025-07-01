package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "TipoPropiedad")
public class TipoPropiedadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idTipoPropiedad;

    @Column
    private String nombre;

    @Column
    private String descripcion;
}
