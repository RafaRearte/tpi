package ar.edu.utn.frc.tup.lciii.Services;

import ar.edu.utn.frc.tup.lciii.Models.Escritura;
import ar.edu.utn.frc.tup.lciii.Models.Jugador;

public interface EscrituraService {
    void cobrarAlquiler(Escritura escritura, Jugador jugador);
}
