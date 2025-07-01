package ar.edu.utn.frc.tup.lciii;

import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import ar.edu.utn.frc.tup.lciii.Models.MenuPrincipal.Menu;
import ar.edu.utn.frc.tup.lciii.handlers.DataJuego;
import ar.edu.utn.frc.tup.lciii.handlers.EstancieroMatchHandler;
import ar.edu.utn.frc.tup.lciii.handlers.ImpresoLetraPorLetra;

import java.util.List;
import java.util.Scanner;

/**
 * Hello to TPI Estanciero
 */
public class App {

    private static Menu menuGame = new Menu();
    private static DataJuego appDataJuego;

    public static void main(String[] args) {

        menuGame.mensajeBienvenida();
        System.out.println("--------------------------------------------------------------");
        menuGame.setGameReadyListener(dataJuego -> {
            empezarJuego(dataJuego);
        });
        menuGame.correrMenuPrincipal();
        
        // Verificar si el juego fue inicializado (el usuario seleccionó "Nueva partida")
        if (appDataJuego == null) {
            System.out.println("Gracias por jugar!");
            return;
        }
        
        Scanner scanner = new Scanner(System.in);
        Boolean jugarDeNuevo = true;

        do {
            Jugador jugadorHumano = appDataJuego.getJugador();

            EstancieroMatchHandler estancieroMatchHandler = new EstancieroMatchHandler();
            List<Jugador> jugadores = estancieroMatchHandler.addBots(appDataJuego.getDificultad(), jugadorHumano.getColor());
            jugadores.add(jugadorHumano);

            estancieroMatchHandler.determinarTurnos(jugadores);
            estancieroMatchHandler.distribuirSaldoJugadores(jugadores);
            ImpresoLetraPorLetra.println(System.lineSeparator() + "Listos para jugar!!!");

            while (!estancieroMatchHandler.juegoTerminado(jugadores)) {
                for (Jugador jugador : jugadores) {
                    estancieroMatchHandler.jugarTurno(jugador, estancieroMatchHandler);
                    if (estancieroMatchHandler.juegoTerminado(jugadores)) {
                        break;
                    }
                }
            }
            jugarDeNuevo = estancieroMatchHandler.jugarDeNuevo();
        } while (jugarDeNuevo);

        System.out.println("Gracias por jugar!");
        scanner.close();
    }

    private static void empezarJuego(DataJuego dataJuego) {
        System.out.println("\nPreparando todo para comenzar...");
        appDataJuego = dataJuego;
    }
}

