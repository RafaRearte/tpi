package ar.edu.utn.frc.tup.lciii.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//Voy a poner lo de aceptar mejoras en escritura
//Precio creo que es redundante, igual que disponibilidad
//TODO: Habria que definir la relacion casilla-escritura. Ahora tiene una relacion 1 a 1 con escritura
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Casilla {
    private int id;
    private String descripcion;
    private TipoDeCasilla tipoDeCasilla;
    private Escritura escritura;
    private boolean cobrarPagar;
    private Carta carta;
}
