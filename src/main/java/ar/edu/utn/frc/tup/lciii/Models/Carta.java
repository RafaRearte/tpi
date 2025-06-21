package ar.edu.utn.frc.tup.lciii.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//Quite tablero
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carta {
    private int id;
    private String descripcion;
    private TipoDeCarta tipo;
    private int condicion; //FIXME cambiar de string a int o cambiar los valores numericos de condicion a palabras
    private int precio;

    public boolean esCobrar() {
        return condicion == 1;
    }

    public boolean esPagar() {
        return condicion == 0;
    }

    public boolean esIrCarcel() {
        return condicion == 2;
    }

    public boolean esSalirCarcel() {
        return condicion == 3;
    }

    public boolean esRetroceder() {
        return condicion == 4;
    }

    public boolean esPagarPorMejoras() {
        return condicion == 5;
    }

    public boolean esAvanzarLugar() {
        return condicion == 6;
    }

    public boolean esCobrarDeJugadores() {
        return condicion == 7;
    }

    public boolean esAvanzarFerrocarril() {
        return condicion == 8;
    }
}
