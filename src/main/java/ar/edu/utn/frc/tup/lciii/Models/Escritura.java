package ar.edu.utn.frc.tup.lciii.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
//TODO: Cambiar en la base de datos  ahora tiene una relacion 1 a 1 con casilla
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Escritura {
    private int id;
    private Jugador jugador;
    private String nombre;
    private Casilla casilla;
    private int precio;
    private int valorHipotecario;
    private boolean disponibilidad;
    private boolean aceptaMejoras;
    private List<Mejora> mejoras;
}
