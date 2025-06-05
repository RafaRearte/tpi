package ar.edu.utn.frc.tup.lciii.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//TODO: Revisar como se relacion con las otras tablas
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movimiento {
    private int id;
    private Peon peon;
    private Juego juego;
    private int posicionPrevia;
    private int nuevaPosicion;
}
