package ar.edu.utn.frc.tup.lciii.Models.Casillas;

import ar.edu.utn.frc.tup.lciii.handlers.EstancieroMatchHandler;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import ar.edu.utn.frc.tup.lciii.services.CasillaService;
import lombok.Getter;

@Getter
public class PremioGanadero extends Casilla implements CasillaService {

    private String nombre;
    private TipoCasilla tipoCasilla;

    public PremioGanadero(String nombre, TipoCasilla tipoCasilla) {
        this.nombre = nombre;
        this.tipoCasilla = tipoCasilla;
    }

    @Override
    public void ejecutarAccion(Jugador jugador, EstancieroMatchHandler estancieroMatchHandler) {
        System.out.println("Premio ganadero, cobre $2500");
        estancieroMatchHandler.getBanco().distribuirSaldo(jugador, 2500);

        System.out.println(jugador.getNombre() + " ha recibido $2500 por el precio de la ganaderia.");

    }
}
