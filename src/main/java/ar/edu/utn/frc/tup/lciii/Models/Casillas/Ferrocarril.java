package ar.edu.utn.frc.tup.lciii.Models.Casillas;

import ar.edu.utn.frc.tup.lciii.Models.Propiedad;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ferrocarril extends Propiedad {

    private String nombre;
    private int valor;
    private TipoCasilla tipoCasilla;
    private boolean disponibilidad;

    public Ferrocarril(String nombre, int valor, TipoCasilla tipoCasilla, boolean disponibilidad) {
        this.nombre = nombre;
        this.valor = valor;
        this.tipoCasilla = tipoCasilla;
        this.disponibilidad = disponibilidad;
    }

    public int obtenerPrecio(){
        return valor * 1;
    }

    @Override
    public void construirMejora() {

    }

    @Override
    public int obtenerCostoDeMejora() {
        return 0;
    }

    @Override
    public int calcularCostoDeAlquiler() {
        return 0;
    }
}
