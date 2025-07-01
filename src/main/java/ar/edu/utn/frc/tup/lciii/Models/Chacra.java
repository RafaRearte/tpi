package ar.edu.utn.frc.tup.lciii.Models;

import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Chacra extends Propiedad {
    private int nivelDeMejora;
    private static final int NIVEL_MAXIMO_MEJORA = 5; // Maximo de mejoras permitidas.

    public Chacra(int id, String nombre, int precio, Jugador duenio, int alquiler) {
        super(id, nombre, precio, duenio, TipoPropiedad.CHACRA, alquiler);
        this.nivelDeMejora = 0;
    }

    public Chacra() {

    }

    public boolean sePuedeMejorar() {
        // Verifica si la propiedad tiene un propietario.
        if (getDuenio() == null) {
            return false;
        }

        // Verifica si no se ha alcanzado el nivel maximo de mejoras.
        if (nivelDeMejora >= NIVEL_MAXIMO_MEJORA) {
            return false;
        }

        return true;
    }

    @Override
    public void construirMejora() {
        if (sePuedeMejorar()) {
            nivelDeMejora++;
            // TODO: Actualizar el valor de alquiler de la propiedad.
        } else {
            throw new IllegalStateException("La propiedad alcanzo el limite maximo de mejoras.");
        }
    }

    @Override
    public int obtenerCostoDeMejora() {
        // TODO: Definir calculo del costo de la mejora con cada nivel.
        return 0;
    }

    public int getNivelDeMejora() {
        return nivelDeMejora;
    }

    public void setNivelDeMejora(int nivelDeMejora) {
        this.nivelDeMejora = nivelDeMejora;
    }

    @Override
    public int calcularCostoDeAlquiler() {
        // TODO: calcular calculateRentalCost
        return 0;
    }

    //Agregado
    @Override
    public int obtenerPrecio() {
        return getPrecio();
    }
}
