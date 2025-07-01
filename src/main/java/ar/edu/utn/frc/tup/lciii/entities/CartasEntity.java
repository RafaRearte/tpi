package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Setter
@Getter
@Table (name = "cartas")
public class CartasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarta;

    @ManyToOne
    @JoinColumn(name = "fk_tipoCarta")
    @Column
    private TipoCartaEntity fk_tipoCarta;

    @ManyToOne
    @JoinColumn(name = "fk_cartaTipo")
    @Column
    private CartaTipoEntity fk_cartaTipo;

    @Column
    private String descripcion;
}
