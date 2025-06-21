package ar.edu.utn.frc.tup.lciii.Services.Impl;

import ar.edu.utn.frc.tup.lciii.Models.Casilla;
import ar.edu.utn.frc.tup.lciii.Models.Movimiento;
import ar.edu.utn.frc.tup.lciii.Services.MovimientoService;

//Todo: put para Jugador
public class MovimientoServiceImpl implements MovimientoService {
    @Override
    public void avanzarCasilla(Movimiento movimiento) {
        Casilla previa = movimiento.getJugador().getCasillaActual();

        int avance = movimiento.getJugador().lanzarDado() + movimiento.getJugador().lanzarDado();

        movimiento.getJugador().setCasillaActual(movimiento.getJuego().getTablero().getCasillas().
                get(previa.getId() + avance));
    }
}
