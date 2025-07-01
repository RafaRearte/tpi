package ar.edu.utn.frc.tup.lciii.Models.Casillas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MarchePreso extends Carcel {
    private String nombre;
    private TipoCasilla tipoCasilla;
}
