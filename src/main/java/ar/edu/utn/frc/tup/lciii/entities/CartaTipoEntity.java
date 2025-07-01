package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "cardType")
public class CartaTipoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCartaTipo;

    @Column
    private String tipoNombre;

}
