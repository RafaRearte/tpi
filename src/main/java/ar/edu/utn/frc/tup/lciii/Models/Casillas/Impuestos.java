package ar.edu.utn.frc.tup.lciii.Models.Casillas;

import ar.edu.utn.frc.tup.lciii.handlers.EstancieroMatchHandler;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import ar.edu.utn.frc.tup.lciii.services.CasillaService;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Impuestos extends Casilla implements CasillaService {

    private String nombre;
    private TipoCasilla tipoCasilla;

    public Impuestos(String nombre, TipoCasilla tipoCasilla) {
        this.nombre = nombre;
        this.tipoCasilla = tipoCasilla;
    }

    @Override
    public void ejecutarAccion(Jugador jugador, EstancieroMatchHandler estancieroMatchHandler) {
        int cantidad = determinarCantidad(tipoCasilla);

        estancieroMatchHandler.getBanco().recolectarImpuestos(jugador, cantidad);
    }

    public int determinarCantidad(TipoCasilla tipoCasilla) {
        if (tipoCasilla == TipoCasilla.RENTAS){
            return 5000;
        } else if (tipoCasilla == TipoCasilla.IMPUESTO_VENTAS) {
            return 2000;
        } else {
            return 0;
        }
    }
}
