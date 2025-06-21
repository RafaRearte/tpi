package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Casillas", schema = "dbo")
public class CasillaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCasilla", nullable = false)
    private Integer id;

    @Column(name = "descripcion", length = 50)
    private String descripcion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idTipoDeCasilla")
    private TiposDeCasillaEntity idTipoDeCasilla;

    @Column(name = "cobrarPagar")
    private Boolean cobrarPagar;

}