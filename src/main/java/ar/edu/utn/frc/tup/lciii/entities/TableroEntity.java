package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "board")
@NoArgsConstructor
public class TableroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_board;

    @Column
    private Long fk_jugador;

    @Column
    private int position;

    @Column
    private Long fk_position;
}
