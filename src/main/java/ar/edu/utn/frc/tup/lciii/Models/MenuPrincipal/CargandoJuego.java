package ar.edu.utn.frc.tup.lciii.Models.MenuPrincipal;

import ar.edu.utn.frc.tup.lciii.services.strategy.MenuPrincipalStrategy;

import java.util.List;
import java.util.Scanner;

public class CargandoJuego implements MenuPrincipalStrategy {

    @Override
    public void ajustarMenuPrincipal() {
        System.out.println("Juegos guardados.");
        System.out.println("-------------------");
        Scanner sc = new Scanner(System.in);

        EstancieroCargandoJuego partidaCargada = new EstancieroCargandoJuego();
        List<EstancieroCargandoJuego> partidasGuardadas = partidaCargada.mostrarPartidasGuardadas();
        for (EstancieroCargandoJuego partida : partidasGuardadas) {
            System.out.println(partida);
        }
        System.out.println("-------------------");
        System.out.println("Seleciona un juego (1 -" + partidasGuardadas.size() + ") o \"x\" para ir al menu principal.");

        try {
            String opcion = sc.nextLine();
            if (opcion.equalsIgnoreCase("x")) {
                System.out.println("Regresando al menu principal...");
                System.out.println("-------------------");
                return;
            }
            int selectGame = Integer.parseInt(opcion);
            if (selectGame >= 1 && selectGame <= partidasGuardadas.size()) {
                int casePartida = selectGame - 1;
                partidaCargada.cargarPartidas("Load game: " + partidasGuardadas.get(casePartida));
            } else
                System.out.println("Opcion no valida. Por favor ingrese un numero entre 1 y " + partidasGuardadas.size());

        } catch (NumberFormatException e) {
            System.out.println("Ingreso no valido. Por favor ingrese un numero o 'x'.");
        }
    }
}
