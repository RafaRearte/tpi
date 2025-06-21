package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Escrituras", schema = "dbo")
public class EscrituraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEscritura", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCasilla", unique = true)
    private CasillaEntity casilla;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "precio")
    private Integer precio;

    @Column(name = "valorAlquiler")
    private Integer valorAlquiler;

    @Column(name = "valorHipotecario")
    private Integer valorHipotecario;

    @Column(name = "disponibilidad")
    private Boolean disponibilidad;

    @Column(name = "sePuedeMejorar")
    private Boolean sePuedeMejorar;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "escritura")
    private List<MejoraEntity> mejoras;
}