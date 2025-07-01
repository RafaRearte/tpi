package ar.edu.utn.frc.tup.lciii.Models.MenuPrincipal;

import ar.edu.utn.frc.tup.lciii.Services.GameReadyListener;
import ar.edu.utn.frc.tup.lciii.services.strategy.MenuPrincipalStrategy;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Setter
@Getter
public class Menu {

    private static final int OPCION_CARGAR_JUEGO = 1;
    private static final int OPCION_NUEVO_JUEGO = 2;
    private static final int OPCION_VER_INSTRUCCIONES = 3;
    private static final int OPCION_SALIDA = 0;

    private GameReadyListener gameReadyListener;

    private boolean juegoListo = false;

    public void correrMenuPrincipal() {
        MenuPrincipalStrategy mainMenuStrategy = new MenuPrincipalStrategy() {
            @Override
            public void ajustarMenuPrincipal() {
            }
        };

        JugadorPrincipal jugadorPrincipal = new JugadorPrincipal(mainMenuStrategy);
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;
        do {
            try {

                System.out.println("1. Cargar Juego");
                System.out.println("2. Nueva partida");
                System.out.println("3. Ver instrucciones");
                System.out.println("0. Salir");
                System.out.print("Seleccionar una opcion: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case OPCION_CARGAR_JUEGO:
                        jugadorPrincipal.cambiarEstrategia(new CargandoJuego());
                        break;
                    case OPCION_NUEVO_JUEGO:
                        empezarNuevoJuego(jugadorPrincipal);
                        break;
                    case OPCION_VER_INSTRUCCIONES:
                        jugadorPrincipal.cambiarEstrategia(new EstancieroInstrucciones());
                        break;
                    case OPCION_SALIDA:
                        System.out.println("Saliendo del juego...");
                        break;
                    default:
                        System.out.println("Opcion no valida, por favor elija una opcion valida (1, 2, 3 o 0).");
                        break;
                }

                if (opcion != OPCION_SALIDA && !juegoListo) {
                    jugadorPrincipal.adjustMainEstanciero();
                }
            } catch (Exception e) {
                System.out.println("Opcion no valida. Por favor ingrese un numero.");
                scanner.nextLine();  // limpia el buffer en caso de excepcion
            }

        } while (opcion != OPCION_SALIDA && !juegoListo);

    }
    public void empezarNuevoJuego(JugadorPrincipal jugadorPrincipal) {
        MenuPrincipalStrategy nuevo = new EstancieroNuevoJuego(gameData -> {
            if (gameReadyListener != null) {
                gameReadyListener.juegoListo(gameData);
            }
            juegoListo = true;  // Señala que el juego esta listo
        });
        jugadorPrincipal.cambiarEstrategia(nuevo);
        nuevo.ajustarMenuPrincipal();
    }

    public void mensajeBienvenida() {
        System.out.println("Bienvenido a 'El Estanciero'!");
    }
}
