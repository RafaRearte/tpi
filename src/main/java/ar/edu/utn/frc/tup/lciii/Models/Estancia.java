package ar.edu.utn.frc.tup.lciii.Models;

import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Estancia extends Propiedad {

    public Estancia(int id, String nombre, int precio, Jugador duenio, int alquiler) {
        super(id, nombre, precio, duenio, TipoPropiedad.ESTANCIA, alquiler);
    }

    @Override
    public int obtenerPrecio() {
        return getPrecio();
    }

    @Override
    public void construirMejora() {
        throw new UnsupportedOperationException("No es posible realizar mejoras en una estancia.");
    }

    @Override
    public int obtenerCostoDeMejora() {
        throw new UnsupportedOperationException("Una estancia sin mejoras.");
    }

    @Override
    public int calcularCostoDeAlquiler() {
        // TODO: calcular calcularCostoDeAlquiler
        return 0;
    }

}
