package ar.edu.utn.frc.tup.lciii.handlers;

import ar.edu.utn.frc.tup.lciii.Models.*;
import ar.edu.utn.frc.tup.lciii.Models.Cartas.MazoCartas;
import ar.edu.utn.frc.tup.lciii.Models.Casillas.Carcel;
import ar.edu.utn.frc.tup.lciii.Models.Casillas.Casilla;
import ar.edu.utn.frc.tup.lciii.Models.Casillas.TipoCasilla;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.*;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.strategy.JugadorAgresivo;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.strategy.JugadorBalanceado;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.strategy.JugadorConservador;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.strategy.JugadorHumano;
import ar.edu.utn.frc.tup.lciii.services.CasillaService;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.regex.Pattern;

@Getter
@Setter
public class EstancieroMatchHandler {
    private Scanner scanner = new Scanner(System.in);

    private JugadorHumano jugadorHumano;
    private JugadorBalanceado bot;
    private Integer puntosUsuario;
    private Integer puntosBot;
    private Jugador ganador; //TODO: Ver tema de jugador humano y varios bot

    private static final String YES_NO_REGEX = "[yYnN]";

    private Set<colorBot> coloresUsados = new HashSet<>();
    private Banco banco = new Banco();
    private MazoCartas mazo = new MazoCartas();

    @Getter
    @Setter
    private static Tablero tablero = new Tablero();
    private int montoGanador = -1; // variable para almacenar el monto del objetivo.
    private Dado dado;

    @Getter
    private Carcel carcel;
    private List<Jugador> jugadors = new ArrayList<>();
    boolean lanzarNuevamente;

    public EstancieroMatchHandler(JugadorHumano jugadorHumano, JugadorBalanceado bot, Set<colorBot> coloresUsados, Banco banco, MazoCartas mazo, int montoGanador) {
        this.jugadorHumano = jugadorHumano;
        this.bot = bot;
        this.puntosUsuario = 0;
        this.puntosBot = 0;
        this.ganador = null;
        this.coloresUsados = coloresUsados;
        this.banco = banco;
        this.mazo = mazo;
        this.montoGanador = montoGanador;
        this.dado = new Dado()  ;
        this.carcel = new Carcel("Comisaria", TipoCasilla.COMISARIA);

    }

    public EstancieroMatchHandler() {
        mazo = new MazoCartas();
        this.dado = new Dado();
        verCarta();
    }

    public  void verCarta() {
        mazo.inicializarMazo();
    }


    //Pasa por aca la compra
    public void ejecutarAccionCarta(Jugador jugador){
        int posicion = jugador.getPosicion();
        if (posicion==4||posicion==15||posicion==36){
            mazo.ejecutarAccionSuerte(jugador);
        }
        if (posicion==10||posicion==25||posicion==38){
            mazo.ejecutarAccionDestino(jugador);
        }
        //todo: no trae bien al jugador y el dinero.
        //pasa por aca para comprar
    }

    public void determinarTurnos(List<Jugador> jugadors) {
        Turno turno = new Turno();
        turno.asignarTurnos(jugadors);
        lanzandoDados(jugadors);
    }

    public void lanzandoDados(List<Jugador> jugadors) {
        try {
            System.out.print("\nLanzando dado");
            for (int i = 0; i < 3; i++) {
                Thread.sleep(500); // Espera 0.5 segundos
                System.out.print(".");
            }
            Thread.sleep(500); // Espera 0.5 seg
            System.out.println(); // Salto de línea

            System.out.println("El orden de los turnos se determina por el lanzamiento de dados:");
            for (Jugador p : jugadors){
                System.out.println(p.getNombre() + " obtuvo " + p.getDadoNumero());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean juegoTerminado(List<Jugador> jugadors) {
        // miplementar la logica para determinar si el juego ha terminado
        for (Jugador jugador : jugadors){
            // si se establecio un monto objetivo, verificar si algun jugador lo alcanzo
            if (montoGanador > 0 && jugador.getSaldo() >= montoGanador){
                System.out.println(jugador.getNombre() + " gano el juego al alcanzar el monto ganador " + montoGanador);
                return true;
            }

            // verificar si todos los demas jugadores estan en bancarrota
            long jugadoresEnBancaRota = jugadors.stream().filter(p -> p.getSaldo() > 0).count();

            if (jugadoresEnBancaRota <= 1){
                ganador = jugadors.stream().filter(p -> p.getSaldo() > 0).findFirst().orElse(null);
                if (ganador != null){
                    System.out.println(ganador.getNombre() + " gano el juego ya que los otros jugadores estan en bancarrota");
                }
                return true;
            }
        }
        return false;

    }

    public Boolean jugarDeNuevo() {
        Boolean respuesta = null;
        do {
            ImpresoLetraPorLetra.println("¿Quieres jugar de nuevo? (y/n)");
            String input = scanner.nextLine();
            respuesta = getYesNoAnswer(input);
        } while (respuesta == null);
        return respuesta;
    }

    public static Boolean getYesNoAnswer(String input) {
        Pattern pattern = Pattern.compile(YES_NO_REGEX);
        Boolean answer = null;
        if (pattern.matcher(input).matches()) {
            answer = input.equalsIgnoreCase("y");
        } else {
            ImpresoLetraPorLetra.println("Respuesta no valida. Por favor ingrese 'y' o 'n'");
        }
        return answer;
    }

    public void distribuirSaldoJugadores(List<Jugador> jugadors) {
        for (Jugador jugador : jugadors) {
            banco.distribuirSaldo(jugador, 35000);
        }
    }

    public void jugarTurno(Jugador jugador, EstancieroMatchHandler estancieroMatchHandler){
        do {
            lanzarNuevamente = false;
            System.out.println("\n---------------------------------------");
            ImpresoLetraPorLetra.println("Turn " + jugador.getNombre());

            Dado dado = new Dado();
            int dice1 = 0;
            int dice2 = 0;
            if(!EstadoJugador.PRESO.equals(jugador.getEstado())){
                dado.lanzarDado();
                dice1 = dado.getDado1();
                dice2 = dado.getDado2();
            } // sino los dados quedan seteados en 0,0 perdiendo 1 turno y setendo el status a ENABLED para la proxima ronda
            else {
                jugador.setEstado(EstadoJugador.HABILITADO);}

            recibirDado(jugador, dice1, dice2, estancieroMatchHandler);
            System.out.println("---------------------------------------");
            int resultadoDado = dado.obtenerResultado();

            verificarSiPasoPorSalida(jugador, resultadoDado);

            accionNuevaCasilla(jugador);
            //Aca hace la compra
            estancieroMatchHandler.ejecutarAccionCarta(jugador);
            // Pasa por aca para comprar
            verEstadoDelJugador(jugador);

            verificarSiTermino(estancieroMatchHandler);


        } while (lanzarNuevamente);
    }

    private boolean verificarSiTermino(EstancieroMatchHandler estancieroMatchHandler) {
        // verifica si el juego termino
        if (estancieroMatchHandler.juegoTerminado(estancieroMatchHandler.getJugadors())){
            return true; // termina el juego si se cumple
        }
        return false;
    }

    private void accionNuevaCasilla(Jugador jugador) {
        int nuevaPosicion = jugador.getPosicion() % tablero.obtenerTamanio(); // Suponiendo que `obtenerTamanio` devuelve el número total de casillas
        jugador.setPosicion(nuevaPosicion);

        Casilla casillaActual = tablero.obtenerCasilla(nuevaPosicion); // Devuelve la casilla solo con nombre, valor, y tipo de casilla en el resto es nulo
        if (casillaActual != null){
            casillaActual.realizarAccion(jugador, this);//pasa por aca la compra
            verificarTipoDeCasilla(jugador, casillaActual, this);//
        } else {
            ImpresoLetraPorLetra.println("Error: La casilla actual esta vacia");
        }
    }

    private void verificarSiPasoPorSalida(Jugador jugador, int resultadoDado) {
        if (jugador.getPosicion() + resultadoDado >= tablero.obtenerTamanio()) {
            System.out.println("\uD83C\uDFE6 paso por salida! El banco le da $5000.");
            //entregar dinero desde el banco
            banco.distribuirSaldo(jugador, 5000);
        }

        jugador.mover(resultadoDado);
    }

    private void recibirDado(Jugador jugador, int dado1, int dado2, EstancieroMatchHandler estancieroMatchHandler) {
        if (dado1 == 0 && dado2 == 0){ return; }
        if (dado1 == dado2){
            jugador.incrementarContadorDoble();
            if (jugador.getContadoDoble() >= 3){
                carcel = estancieroMatchHandler.getCarcel();
                if (carcel != null){
                    carcel.dadosDobles(jugador, estancieroMatchHandler);
                }
                return;
            } else {
                ImpresoLetraPorLetra.println("Salieron dobles, " + jugador.getNombre() + " tiene otro turno.");
                lanzarNuevamente = true;
            }
        } else {
            jugador.resetearContadorDoble();
        }
    }

    private void verEstadoDelJugador(Jugador jugador) {

        if (jugador instanceof Jugador){
            System.out.println("Informacion de: " + jugador.getNombre());
            System.out.println("Saldo: " + jugador.getSaldo());
            System.out.println("Posicion actual: " + jugador.getPosicion());
            System.out.println("Color:"+ jugador.getColor());
            System.out.println("Estado "+ jugador.getEstado());
            System.out.println("Propiedades compradas:");
            for (int i = 0; i < jugador.getPropiedades().size(); i++) {
                System.out.println("\n " + (i+1) + ": " + jugador.getPropiedades().get(i).getNombre());
            }

            System.out.println("--------------------------------------");
        }

    }

    public enum colorBot {
        ROJO, AZUL, VERDE, AMARILLO, NEGRO, VIOLETA
    }

    public List<Jugador> addBots(String dificultad, String colorJugador) {
        int cantidadBots;
        String[] perfiles;
        List<Jugador> jugadores = new ArrayList<>();
        perfiles = switch (dificultad) {
            case "FACIL" -> {
                cantidadBots = 2;
                yield new String[]{"conservador", "equilibrado"};
            }
            case "MEDIO" -> {
                cantidadBots = 3;
                yield new String[]{"conservador", "equilibrado", "agresivo"};
            }
            case "DIFICIL" -> {
                cantidadBots = 4;
                yield new String[]{"conservador", "conservador", "equilibrado", "agresivo"};
            }
            default -> throw new IllegalArgumentException("Dificultad no existente: " + dificultad);
        };

        coloresUsados.add(colorBot.valueOf(colorJugador)); // Agregar el color del jugador a los colores usados

        //TODO GUARDAR ORDEN DE DATOS PARA LOS TURNOS
        for (int i = 0; i < cantidadBots; i++) {
            String nombreBot = "Bot " + (i + 1);
            colorBot colorBot = obtenerSiguienteColorDisponible(); // Obtener el siguiente color disponible
            Jugador jugadorBot = JugadorFactory.crearBot(perfiles[i], nombreBot, 0, new ArrayList<>(),
                    colorBot.name(), 0, 0, EstadoJugador.HABILITADO, new ArrayList<>());
            jugadores.add(jugadorBot);
            coloresUsados.add(colorBot); // Agregar el color del bot a los colores usados
        }
        return jugadores;
    }

    private colorBot obtenerSiguienteColorDisponible() {
        for (colorBot color : colorBot.values()) {
            if (!coloresUsados.contains(color)) {
                return color;
            }
        }
        throw new IllegalStateException("No hay mas colores disponibles.");
    }

    public void verificarTipoDeCasilla(Jugador jugador, Casilla casillaActual, EstancieroMatchHandler estancieroMatchHandler){
        if (jugador == null || casillaActual == null) {
            ImpresoLetraPorLetra.println("Error: jugador o casilla actual nulos");
            return;
        }
            //Por aca pasa la compra

        if (casillaActual instanceof CasillaService){
            // casilla de salida
            ((CasillaService) casillaActual).ejecutarAccion(jugador, estancieroMatchHandler);
        } else if (casillaActual instanceof Propiedad) {
            ejecutarAccionDePropiedad(jugador, (Propiedad) casillaActual, estancieroMatchHandler);
        } else {
            ImpresoLetraPorLetra.println("Error: tipo de casilla desconocido");
        }
    }

//    private void ejecutarAccionDePropiedad(Jugador jugador, Propiedad propiedad, EstancieroMatchHandler estancieroMatchHandler) {
//        if (propiedad.getDuenio() == null) {
//            if (jugadorDeberiaComprar(jugador, propiedad)) {
//                jugador.comprarPropiedad(propiedad);
//                ImpresoLetraPorLetra.println(jugador.getNombre() + " ha comprado la propiedad " + propiedad.getNombre());
//            } else {
//                ImpresoLetraPorLetra.println(jugador.getNombre() + " decidio no comprar la propiedad " + propiedad.getNombre());
//            }
//        } else if (propiedad.getDuenio() != jugador) {
//
//            int alquiler = propiedad.calcularCostoDeAlquiler();
//            jugador.pagarAlquiler(alquiler, jugador, propiedad.getDuenio());
//            System.out.println(jugador.getNombre() + " ha pagado " + rentAmount + " en renta a " + propiedad.getDuenio().getNombre());
//        }
//    }

    //Por aca pasa la compra
    //Fixme aca esta el problema del saldo
    public void ejecutarAccionDePropiedad(Jugador jugador, Propiedad propiedad, EstancieroMatchHandler estancieroMatchHandler) {
        if (propiedad.getDuenio() == null) {
            if (jugadorDeberiaComprar(jugador, propiedad)) {
                banco.venderPropiedad(propiedad, jugador);
                ImpresoLetraPorLetra.println(jugador.getNombre() + " ha comprado la propiedad " + propiedad.getNombre());
            } else {
                ImpresoLetraPorLetra.println(jugador.getNombre() + " decidio no comprar la propiedad " + propiedad.getNombre());
            }
        } else if (propiedad.getDuenio() != jugador) {
            int alquiler = propiedad.calcularCostoDeAlquiler();
            jugador.pagarAlquiler(alquiler, jugador, propiedad.getDuenio());
            System.out.println(jugador.getNombre() + " ha pagado " + alquiler + " en renta a " + propiedad.getDuenio().getNombre());
        }
    }

    private boolean jugadorDeberiaComprar(Jugador jugador, Propiedad propiedad) {
        if (jugador == null || propiedad == null){
            return false;
        }
        //Fixme aca devuelve falso
        Boolean deberiaComprar = false;

        if (jugador instanceof JugadorBalanceado) {
            System.out.println("Tipo de jugador balanceado");
            return ((JugadorBalanceado) jugador).deberiaComprar(propiedad);
        } else if (jugador instanceof JugadorAgresivo) {
            System.out.println("Tipo de jugador agresivo");
            return ((JugadorAgresivo) jugador).deberiaComprar(propiedad);
        } else if (jugador instanceof JugadorConservador) {
            System.out.println("Tipo de jugador conservador");
            return ((JugadorConservador) jugador).deberiaComprar(propiedad);
        } else {
            deberiaComprar = DesicionDelJugador(propiedad);
        }

        if (deberiaComprar == null){
            ImpresoLetraPorLetra.println("Advertencia: el metodo retorno nulo para el jugador " + jugador.getNombre());
            deberiaComprar = false;
        }
        return deberiaComprar;
    }

    private boolean DesicionDelJugador(Propiedad propiedad) {
        try {
            while (true){
                ImpresoLetraPorLetra.println("¿Quieres comprar la propiedad " + propiedad.getNombre() + "? (y/n)");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("y")){
                    return true;
                } else if (input.equalsIgnoreCase("n")){
                    return false;
                } else {
                    ImpresoLetraPorLetra.println("Error al ingresar la respuesta. Por favor ingrese 'y' o 'n'");
                }
            }

        } catch (Exception ex) {
            ImpresoLetraPorLetra.println("Ocurrio un error al intentar comprar la propiedad. Por favor intente de nuevo.");
            return false;
        }
    }

}
