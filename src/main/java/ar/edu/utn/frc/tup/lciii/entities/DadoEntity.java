package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "Dado")
public class DadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDado;

    @Column
    private int numeroCara;
}
