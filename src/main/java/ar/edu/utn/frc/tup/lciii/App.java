package ar.edu.utn.frc.tup.lciii;

import ar.edu.utn.frc.tup.lciii.Models.Juego;
import ar.edu.utn.frc.tup.lciii.Services.Impl.JuegoServiceImpl;

/**
 * Hello to TPI Estanciero
 */
public class App {

    /**
     * This is the main program
     */
    public static void main(String[] args) {
//         Crear e iniciar una nueva partida
        Juego juego = new Juego();
        JuegoServiceImpl juegoService = new JuegoServiceImpl(juego);
        juegoService.iniciarPartida();

    }
}
