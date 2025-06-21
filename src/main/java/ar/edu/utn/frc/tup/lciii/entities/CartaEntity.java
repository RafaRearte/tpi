package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Cartas", schema = "dbo")
public class CartaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCarta", nullable = false)
    private Integer id;

    @Column(name = "descripcion", length = 100)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoDeCarta")
    private TiposDeCartaEntity idTipoDeCarta;

    @Column(name = "condicion")
    private Integer condicion;

    @Column(name = "precio")
    private Integer precio;

}