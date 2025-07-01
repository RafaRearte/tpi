package ar.edu.utn.frc.tup.lciii.Services;

import ar.edu.utn.frc.tup.lciii.Models.Cartas.TipoCarta;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;

public interface CartaService {
    void ejecutarAccion(Jugador jugador);
    void verCarta();
    boolean verTipoDeCarta(TipoCarta tipoCarta);
}
