package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Setter
@Getter
@Table (name = "Casilla")
public class CasillaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCasilla;

    @ManyToOne
    @JoinColumn(name = "fk_tablero")
    @Column
    private TableroEntity fk_tablero;

    @ManyToOne
    @JoinColumn(name = "fk_propiedad")
    @Column
    private PropiedadEntity fk_propiedad;

    @ManyToOne
    @JoinColumn(name = "fk_tipoCasilla")
    @Column
    private TipoCasillaEntity fk_tipoCasilla;
}
