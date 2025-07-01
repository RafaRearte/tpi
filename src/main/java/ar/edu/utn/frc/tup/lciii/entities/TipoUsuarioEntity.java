package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "TipoUsuario")
public class TipoUsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private Long idTipoUsuario;

    @Column
    private String tipo;
}
