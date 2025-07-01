package ar.edu.utn.frc.tup.lciii.Models.Casillas;

import ar.edu.utn.frc.tup.lciii.handlers.EstancieroMatchHandler;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import ar.edu.utn.frc.tup.lciii.services.CasillaService;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Suerte extends Casilla implements CasillaService {

    private String nombre;
    private String descripcion;
    private TipoCasilla tipoCasilla;

    public Suerte(String nombre, TipoCasilla tipoCasilla) {
        this.nombre = nombre;
        this.tipoCasilla = tipoCasilla;
    }

    @Override
    public void ejecutarAccion(Jugador jugador, EstancieroMatchHandler estancieroMatchHandler) {

    }
}
