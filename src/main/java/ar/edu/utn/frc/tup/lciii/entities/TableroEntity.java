package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Tableros", schema = "dbo")
public class TableroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTablero", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCasilla")
    private CasillaEntity idCasilla;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCarta")
    private CartaEntity idCarta;

}