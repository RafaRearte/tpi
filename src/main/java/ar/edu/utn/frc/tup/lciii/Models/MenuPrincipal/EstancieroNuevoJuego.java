package ar.edu.utn.frc.tup.lciii.Models.MenuPrincipal;

import ar.edu.utn.frc.tup.lciii.handlers.DataJuego;
import ar.edu.utn.frc.tup.lciii.Models.Banco;
import ar.edu.utn.frc.tup.lciii.Models.Casillas.Casilla;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.EstadoJugador;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import ar.edu.utn.frc.tup.lciii.Models.JugadorFactory;
import ar.edu.utn.frc.tup.lciii.Models.Propiedad;
import ar.edu.utn.frc.tup.lciii.Models.Tablero;
import ar.edu.utn.frc.tup.lciii.Services.GameReadyListener;
import ar.edu.utn.frc.tup.lciii.services.strategy.MenuPrincipalStrategy;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

@Setter
@Getter
public class EstancieroNuevoJuego implements MenuPrincipalStrategy {

    private Jugador jugador;
    private String tipoDeObjetivo;
    private int montoGanador;
    private String dificultad;

    private Scanner scanner;

    private static final String YES_NO_REGEX = "[yYnN]";

    private GameReadyListener gameReadyListener;

    public EstancieroNuevoJuego(GameReadyListener gameReadyListener) {
        jugador = new Jugador() {
            @Override
            public String obtenerNombre() {
                return "";
            }

            @Override
            public int obtenerSaldo() {
                return 0;
            }

            @Override
            public void actualizarSaldo(int cantidad) {

            }

            @Override
            public List<Propiedad> obtenerPropiedades() {
                return List.of();
            }

            @Override
            public List<Casilla> obtenerCasillas() {
                return List.of();
            }

            @Override
            public void pagarAlquiler(int alquiler, Jugador duenio) {

            }

            @Override
            public void tomarTurno(Tablero tablero, Banco banco) {
            }
        };
        scanner = new Scanner(System.in);
        this.gameReadyListener = gameReadyListener;
    }

    @Override
    public void ajustarMenuPrincipal() {
        obtenerInformacionDelJugador();
        obtenerObjetivoParaGanar();
        obtenerDificultad();
        imprimirDatosDelJugador();

        if (listoParaEmpezar()) {
            DataJuego dataJuego = new DataJuego(jugador, tipoDeObjetivo, montoGanador, dificultad);
            gameReadyListener.juegoListo(dataJuego);
        } else {
            limpiarConsola();
        }
    }

    private enum Color {
        ROJO, AZUL, VERDE, AMARILLO, NEGRO, VIOLETA
    }

    private enum Dificultad {
        FACIL, MEDIO, DIFICL
    }

    private void imprimirDatosDelJugador() {
        // Imprimir los datos ingresados por el jugador
        System.out.println("\nDatos ingresados:");
        System.out.println("Nombre del jugador: " + jugador.getNombre());
        System.out.println("Tipo de persona: " + jugador.getTipoPersona());
        System.out.println("Color: " + jugador.getColor());
        System.out.println("Objetivo para terminar el juego: " + tipoDeObjetivo);
        System.out.println("Dificultad seleccionada: " + dificultad);
    }

    public Boolean listoParaEmpezar() {
        Boolean respuesta = null;
        do {
            System.out.println("Esta listo para empezar a jugar? (y/n)");
            String input = scanner.nextLine();
            respuesta = getYesNoAnswer(input);
        } while (respuesta == null);
        return respuesta;
    }

    private static Boolean getYesNoAnswer(String input) {
        Pattern pattern = Pattern.compile(YES_NO_REGEX);
        Boolean answer = null;
        if (pattern.matcher(input).matches()) {
            answer = input.equalsIgnoreCase("y");
        } else {
            System.out.println("Respuesta no valida. Por favor ingrese 'y' o 'n'");
        }
        return answer;
    }

    private void obtenerObjetivoParaGanar() {
        boolean opcionValida;
        do {
            opcionValida = true;
            System.out.println("Objetivo para ganar:");
            System.out.println("1. Numero de propiedades adquiridas");
            System.out.println("2. Monto de saldo acumulado");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    tipoDeObjetivo = "Numero de propiedades adquiridas";
                    break;
                case 2:
                    tipoDeObjetivo = "Monto de saldo acumulado";
                    System.out.println("Ingrese el monto ganador (entre 37.000 y 334.200): ");
                    montoGanador = scanner.nextInt();
                    if (montoGanador <= 37000 || montoGanador > 342000) {
                        System.out.println(" monto invalido. Por favor ingrese un valor entre 37.000 y 334.200");
                        opcionValida = false;
                    }
                    break;
                default:
                    System.out.println("Opcion no valida. Por favor ingrese '1' o '2'");
                    opcionValida = false;
                    break;
            }
        } while (!opcionValida);

    }

    private void obtenerDificultad() {
        int eleccion;
        do {
            System.out.println("Dificultad:");
            for (int i = 0; i < Dificultad.values().length; i++) {
                System.out.println((i + 1) + ". " + Dificultad.values()[i]);
            }
            eleccion = scanner.nextInt();
            scanner.nextLine();
            if (!(eleccion >= 1 && eleccion <= Dificultad.values().length)) {
                System.out.println("Eleccion no valida. Por favor seleccione un numero entre 1 y " + Dificultad.values().length);
            }
        }while (!(eleccion >= 1 && eleccion <= Dificultad.values().length));

        dificultad = Dificultad.values()[eleccion - 1].name();

    }

    private void obtenerInformacionDelJugador() {
        System.out.println("\n--Configura tu juego--");
        String nombreJugador = nombreValido();
        // Solicitar al jugador que seleccione un color mediante el número correspondiente
        Color colorElegido;
        do {
            System.out.println("Elige tu color:");
            for (int i = 0; i < Color.values().length; i++) {
                System.out.println((i + 1) + ". " + Color.values()[i]);
            }
            int eleccionColor = scanner.nextInt();
            if (eleccionColor >= 1 && eleccionColor <= Color.values().length) {
                colorElegido = Color.values()[eleccionColor - 1];
            } else {
                colorElegido = null;
                System.out.println("Eleccion no valida. Por favor seleccione un numero entre 1 y " + Color.values().length);
            }
        } while (colorElegido == null);

        jugador = JugadorFactory.crearJugador(nombreJugador, 0, new ArrayList<>(), colorElegido.name(), 0, 0, EstadoJugador.HABILITADO, new ArrayList<>());
    }

    private String nombreValido() {
        String nombreJugador;
        do {
            System.out.println("Ingresa tu nombre:");
            nombreJugador = scanner.nextLine().trim();

            if (!nombreValido(nombreJugador)) {
                System.out.println("Nombre no valido. Por favor ingresa un nombre valido. Tiene que empezar con al menos una letra.");
            }
        } while (!nombreValido(nombreJugador));
        return nombreJugador;
    }

    private boolean nombreValido(String nombreJugador) {
        return Pattern.matches("^[a-zA-Z]{3,}.*", nombreJugador);
    }

    private void limpiarConsola() {
        try {
            for (int i = 0; i < 50; ++i) System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
