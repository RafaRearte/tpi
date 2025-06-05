package ar.edu.utn.frc.tup.lciii.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carta {
    private int id;
    private Tablero tablero;
    private String descripcion;
    private TipoDeCarta tipo;
    private String condicion;
    private int precio;

    // sacar ultima carta
    // mezclar cartas
    // tipo carta
    // sacar carta con id tanto sale de prision

}
