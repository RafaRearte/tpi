package ar.edu.utn.frc.tup.lciii.Models.Casillas;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EstacionamientoLibre extends Casilla {

    private String nombre;
    private TipoCasilla tipoCasilla;

    public EstacionamientoLibre(String nombre, TipoCasilla tipoCasilla) {
        this.nombre = nombre;
        this.tipoCasilla = tipoCasilla;
    }
}
