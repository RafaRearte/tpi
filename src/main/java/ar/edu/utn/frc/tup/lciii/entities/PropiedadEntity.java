package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Setter
@Getter
public class PropiedadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPropiedad;

    @Column
    private String nombre;

    @Column
    private int precio;

    @Column
    private int renta;

    @ManyToOne
    @JoinColumn(name = "fk_jugador")
    private JugadorEntity jugador;

    @ManyToOne
    @JoinColumn(name = "fk_tipoPropiedad")
    private TipoPropiedadEntity fk_tipoPropiedad;
}
