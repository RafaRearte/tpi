package ar.edu.utn.frc.tup.lciii.Models.Casillas;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Destino extends Casilla {

    private String nombre;
    private TipoCasilla tipoCasilla;

    public Destino(String nombre, TipoCasilla tipoCasilla) {
        this.nombre = nombre;
        this.tipoCasilla = tipoCasilla;
    }
}
