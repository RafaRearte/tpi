package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Mejoras", schema = "dbo")
public class MejoraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMejora", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoDeMejora", nullable = false)
    private TiposDeJugadorEntity tipoDeMejora;

    @Column(name = "descripcion", length = 50)
    private String descripcion;

    @Column(name = "valor")
    private Integer valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEscritura", nullable = false)
    private EscrituraEntity escritura;
}