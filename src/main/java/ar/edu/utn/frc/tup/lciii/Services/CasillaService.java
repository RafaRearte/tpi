package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.handlers.EstancieroMatchHandler;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;

public interface CasillaService {
    void ejecutarAccion(Jugador jugador, EstancieroMatchHandler estancieroMatchHandler);
}
