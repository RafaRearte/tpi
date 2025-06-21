package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Bancos", schema = "dbo")
public class BancoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBanco", nullable = false)
    private Integer id;

    @Column(name = "saldo")
    private Integer saldo;

}