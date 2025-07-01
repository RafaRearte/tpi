package ar.edu.utn.frc.tup.lciii.Models.Casillas;

import ar.edu.utn.frc.tup.lciii.handlers.EstancieroMatchHandler;
import ar.edu.utn.frc.tup.lciii.Models.Dado;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import ar.edu.utn.frc.tup.lciii.services.CasillaService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Descanso extends Casilla implements CasillaService {

    private String nombre;
    private TipoCasilla tipoCasilla;
    private Dado dado;

    public Descanso(String nombre, TipoCasilla tipoCasilla) {
        this.nombre = nombre;
        this.tipoCasilla = tipoCasilla;
    }

    @Override
    public void ejecutarAccion(Jugador jugador, EstancieroMatchHandler estancieroMatchHandler) {

    }
}
