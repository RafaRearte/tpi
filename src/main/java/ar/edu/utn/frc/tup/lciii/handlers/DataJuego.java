package ar.edu.utn.frc.tup.lciii.handlers;

import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import ar.edu.utn.frc.tup.lciii.Services.GameReadyListener;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataJuego implements GameReadyListener {
    private Jugador jugador;
    private String objetivo;
    private int montoGanador;
    private String dificultad;

    public DataJuego(Jugador jugador, String objetivo, int montoGanador, String dificultad) {
        this.jugador = jugador;
        this.objetivo = objetivo;
        this.montoGanador = montoGanador;
        this.dificultad = dificultad;
    }

    @Override
    public void juegoListo(DataJuego dataJuego) {

    }
}
