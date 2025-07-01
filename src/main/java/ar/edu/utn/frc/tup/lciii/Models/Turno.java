package ar.edu.utn.frc.tup.lciii.Models;

import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;

import java.util.List;
import java.util.Random;

public class Turno {

    public void asignarTurnos(List<Jugador> jugadores) {
        Random r = new Random();
        // Asigna a cada jugador el valor de los dados
        for (Jugador p : jugadores) {
            p.setDadoNumero((r.nextInt(6) + 1));
        }
        // Ordenar los jugadores por el resultado de los dados (de mayor a menor)
        jugadores.sort((p1, p2) -> Integer.compare(p2.getDadoNumero(), p1.getDadoNumero()));
    }
}
