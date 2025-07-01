package ar.edu.utn.frc.tup.lciii.Models;

import ar.edu.utn.frc.tup.lciii.Models.Casillas.Casilla;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Propiedad extends Casilla {

    private int id;
    private String nombre;
    private int precio;
    private Jugador duenio;
    private TipoPropiedad tipoPropiedad;
    private int alquiler;

    public Propiedad(int id, String nombre, int precio, Jugador duenio, TipoPropiedad tipoPropiedad, int alquiler) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.duenio = duenio;
        this.tipoPropiedad = tipoPropiedad;
        this.alquiler = alquiler;
    }

    public Propiedad() {
    }

    public String obtenerInfoPropiedad() {
        StringBuilder info = new StringBuilder();
        info.append("ID: ").append(id).append("\n");
        info.append("Nombre: ").append(nombre).append("\n");
        info.append("Precio: ").append(precio).append("\n");
        info.append("Tipo: ").append(tipoPropiedad).append("\n");
        info.append("Costo de alquiler: ").append(alquiler).append("\n");
        if (duenio != null) {
            info.append("Dueño: ").append(duenio.getNombre()).append("\n");
        } else {
            info.append("Dueño: Ninguno").append("\n");
        }
        return info.toString();
    }
    public abstract int obtenerPrecio();
    public abstract void construirMejora();

    public abstract int obtenerCostoDeMejora();

    public int calcularCostoDeAlquiler() {
        return precio;
    }

}

