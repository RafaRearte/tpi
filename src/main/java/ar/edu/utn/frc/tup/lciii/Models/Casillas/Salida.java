package ar.edu.utn.frc.tup.lciii.Models.Casillas;

import ar.edu.utn.frc.tup.lciii.handlers.EstancieroMatchHandler;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import ar.edu.utn.frc.tup.lciii.services.CasillaService;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Salida extends Casilla implements CasillaService {

    private TipoCasilla tipoCasilla;
    private String nombre;

    public Salida(String nombre, TipoCasilla tipoCasilla) {
        this.nombre = nombre;
        this.tipoCasilla = tipoCasilla;
    }

    public String obtenerDescripcion(){
        return "Cuando pases por aquí, cobra $5000 del banco.";
    }

    @Override
    public void ejecutarAccion(Jugador jugador, EstancieroMatchHandler estancieroMatchHandler) {
        System.out.println("Al pasar por la casilla de salida, cobre $5000");
        estancieroMatchHandler.getBanco().distribuirSaldo(jugador, 5000);
        System.out.println(jugador.getNombre() + " Ha recibido $5000 por pasar por Salida.");
    }
}
