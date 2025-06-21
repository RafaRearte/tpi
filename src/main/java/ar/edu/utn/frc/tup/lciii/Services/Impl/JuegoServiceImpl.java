package ar.edu.utn.frc.tup.lciii.Services.Impl;

import ar.edu.utn.frc.tup.lciii.Models.*;
import ar.edu.utn.frc.tup.lciii.Services.CartaService;
import ar.edu.utn.frc.tup.lciii.Services.EscrituraService;
import ar.edu.utn.frc.tup.lciii.Services.JuegoService;
import ar.edu.utn.frc.tup.lciii.Services.MovimientoService;
import ar.edu.utn.frc.tup.lciii.TemplateJugadores.JugadorBotAgresivo;
import ar.edu.utn.frc.tup.lciii.TemplateJugadores.JugadorBotConservador;
import ar.edu.utn.frc.tup.lciii.TemplateJugadores.JugadorBotModerado;
import ar.edu.utn.frc.tup.lciii.TemplateJugadores.JugadorHumano;
import ar.edu.utn.frc.tup.lciii.entities.CasillaEntity;
import ar.edu.utn.frc.tup.lciii.entities.EscrituraEntity;
import ar.edu.utn.frc.tup.lciii.repositories.CasillaRepository;
import ar.edu.utn.frc.tup.lciii.repositories.EscrituraRepository;
import ar.edu.utn.frc.tup.lciii.repositories.Impl.CasillaRepositoryImpl;
import ar.edu.utn.frc.tup.lciii.repositories.Impl.EscrituraRepositoryImpl;
import ar.edu.utn.frc.tup.lciii.repositories.JuegoRepository;
import ar.edu.utn.frc.tup.lciii.repositories.JugadorRepository;
import org.modelmapper.ModelMapper;

import org.modelmapper.TypeToken;
import java.lang.reflect.Type;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TODO: Implementar la logica
public class JuegoServiceImpl implements JuegoService {
    private static final int MAX_DOBLES = 3;
    private Juego juego;
    private MovimientoService movimientoService;

    private EscrituraService escrituraService;
    private EscrituraRepository escrituraRepository;

    private CartaService cartaService;

    private CasillaRepository casillaRepository;
    private JugadorRepository jugadorRepository;
    private JuegoRepository juegoRepository;
    private ModelMapper modelMapper;
    private List<Jugador> jugadoresEnQuiebra;

    public JuegoServiceImpl(Juego juego) {
        this.juego = juego;
    }
    public JuegoServiceImpl() {}
    public void setJuego(Juego juego) { this.juego = juego; }

    public void iniciarPartida() {
        Scanner scanner = new Scanner(System.in);
        juego.setScanner(scanner);
        juego.setFechaInicial(LocalDateTime.now());
        juego.setAjuste(configurarDificultad());
        juego.setJugadores(new ArrayList<>());
        agregarJugadores();
        inicializarTablero();
        inicializarBanco();
        int montoVictoria = elegirMontoVictoria();
        juego.getAjuste().setMontoGanador(montoVictoria);
        while (!hayGanador()) {
            for (Jugador jugador : juego.getJugadores()) {
                if (!jugador.isBancarrota()) {
                    ejecutarTurno(jugador);
                }
            }
        }
        juego.setFechaFinal(LocalDateTime.now());
        juego.setGanador(obtenerGanador());
        finalizarPartida();
        scanner.close();
    }

    public Ajuste configurarDificultad() {
        Scanner scanner = juego.getScanner();
        System.out.println("\nSeleccione la dificultad:");
        System.out.println("1. Fácil");
        System.out.println("2. Medio");
        System.out.println("3. Difícil");
        int opcion;
        do {
            System.out.print("Ingrese una opción (1-3): ");
            opcion = scanner.nextInt();
        } while (opcion < 1 || opcion > 3);
        Dificultad dificultad;
        switch (opcion) {
            case 1:
                dificultad = Dificultad.FACIL;
                break;
            case 2:
                dificultad = Dificultad.MEDIO;
                break;
            default:
                dificultad = Dificultad.DIFICIL;
        }
        return new Ajuste(0, dificultad, 0);
    }

    //Fixme Modificar para que acepte mas de un bot
    public void agregarJugadores() {
        Scanner scanner = juego.getScanner();
        List<Jugador> jugadores = juego.getJugadores();
        Ajuste ajuste = juego.getAjuste();
        System.out.println("\nIngrese su nombre:");
        scanner.nextLine();
        String nombreHumano = scanner.nextLine();
        JugadorHumano jugadorHumano = new JugadorHumano();
        jugadorHumano.setNombre(nombreHumano);
        jugadorHumano.setEsBot(false);
        jugadorHumano.setSaldo(100000);
        jugadorHumano.setBancarrota(false);

        //Todo: Revisar
        casillaRepository = new CasillaRepositoryImpl();
        modelMapper = new ModelMapper();
        CasillaEntity casillaEntity = casillaRepository.getById(1);//Posicion provisional
        Casilla casilla = modelMapper.map(casillaEntity, Casilla.class);
        jugadorHumano.setCasillaActual(casilla);

        jugadorHumano.setOrdenDeJuego(1);
        jugadorHumano.setTipoDeJugador(TipoDeJugador.HUMANO);
        jugadorHumano.setColor(Color.ROJO);
        jugadores.add(jugadorHumano);
        int cantidadBots;
        switch (ajuste.getDificultad()) {
            case FACIL:
                cantidadBots = 2;
                break;
            case MEDIO:
                cantidadBots = 3;
                break;
            case DIFICIL:
                cantidadBots = 4;
                break;
            default:
                cantidadBots = 2;
        }
        Color[] colores = {Color.AZUL, Color.VERDE, Color.NARANJA, Color.BLANCO};
        for (int i = 0; i < cantidadBots; i++) {
            Jugador bot;
            switch (i % 3) {
                case 0:
                    bot = new JugadorBotConservador();
                    bot.setTipoDeJugador(TipoDeJugador.BOT_CONSERVADOR);
                    bot.setNombre("Bot Conservador " + (i + 1));
                    break;
                case 1:
                    bot = new JugadorBotModerado();
                    bot.setTipoDeJugador(TipoDeJugador.BOT_MODERADO);
                    bot.setNombre("Bot Moderado " + (i + 1));
                    break;
                default:
                    bot = new JugadorBotAgresivo();
                    bot.setTipoDeJugador(TipoDeJugador.BOT_AGRESIVO);
                    bot.setNombre("Bot Agresivo " + (i + 1));
            }
            bot.setEsBot(true);
            bot.setSaldo(100000);
            bot.setBancarrota(false);
            bot.setCasillaActual(new Casilla());// configurar
            bot.setOrdenDeJuego(i + 2);
            bot.setColor(colores[i]);
            jugadores.add(bot);
        }
        System.out.println("\nJugadores en la partida:");
        for (Jugador jugador : jugadores) {
            System.out.println("- " + jugador.getNombre() + (jugador.isEsBot() ? " (Bot - " + jugador.getTipoDeJugador() + ")" : "") + " - Color: " + jugador.getColor());
        }
    }

    public void inicializarTablero() {
        //TODO: Aquí va la lógica de inicialización del tablero
        //Fixme Revisar
        cartaService = new CartaServiceImpl();
        Tablero tablero = new Tablero();
        tablero.setMazoSuerte(cartaService.crearMazo(TipoDeCarta.SUERTE));
        tablero.setMazoDestino(cartaService.crearMazo(TipoDeCarta.DESTINO));
        casillaRepository = new CasillaRepositoryImpl();
        List<CasillaEntity> casillasEntites = casillaRepository.getAll();
        Type listType = new TypeToken<List<Casilla>>() {}.getType();
        List<Casilla> casillas = modelMapper.map(casillasEntites, listType);
        tablero.setCasillas(casillas);
        tablero.setId(1);
        juego.setTablero(tablero);
    }

    public void inicializarBanco() {
        Banco banco = new Banco();
        banco.setId(1);
        banco.setSaldo(1000000);
        banco.setEscriturasDisponibles(new ArrayList<>());
        juego.setBanco(banco);
    }

    public int elegirMontoVictoria() {
        Scanner scanner = juego.getScanner();
        System.out.println("\nIngrese el monto de dinero para ganar la partida:");
        int monto;
        do {
            System.out.print("Monto (mínimo 100000): ");
            monto = scanner.nextInt();
        } while (monto < 100000);
        return monto;
    }

    public boolean hayGanador() {
        int jugadoresActivos = 0;
        for (Jugador jugador : juego.getJugadores()) {
            if (!jugador.isBancarrota()) {
                jugadoresActivos++;
            }
        }
        return jugadoresActivos <= 1;
    }

    public void ejecutarTurno(Jugador jugador) {
        int contadorDobles = 0;
        boolean seguirTirando = true;
        while (seguirTirando && contadorDobles < MAX_DOBLES) {
            int[] dados = new int[2];
            dados[0] = jugador.lanzarDado();
            dados[1] = jugador.lanzarDado();
            boolean dadosIguales = dados[0] == dados[1];
            int pasos = dados[0] + dados[1];
            System.out.println("\nTurno de " + jugador.getNombre());
            System.out.println("Dados: " + dados[0] + " y " + dados[1]);
            //TODO verificar luego si funciona
            Movimiento movimiento = new Movimiento();
            movimiento.setJuego(juego);
            movimiento.setJugador(jugador);
            movimientoService = new MovimientoServiceImpl();
            movimientoService.avanzarCasilla(movimiento);
//            moverJugador(jugador, pasos);
            Casilla casillaActual = juego.getTablero().getCasillas().get(jugador.getCasillaActual().getId());//
            aplicarEfectoCasilla(jugador, casillaActual);
            if (puedeMejorarPropiedad(jugador)) {
                ofrecerMejora(jugador);
            }
            if (jugador.getSaldo() < 0) {
                jugador.setBancarrota(true);
                actualizarListaQuiebras(jugador);
                seguirTirando = false;
            }
            if (dadosIguales) {
                contadorDobles++;
                if (contadorDobles == MAX_DOBLES) {
                    System.out.println("¡Tres dobles! " + jugador.getNombre() + " va a la cárcel.");
                    enviarALaCarcel(jugador);
                    seguirTirando = false;
                } else {
                    System.out.println("¡Dobles! Tira nuevamente.");
                }
            } else {
                seguirTirando = false;
            }
        }
    }

//    public int[] lanzarDados() {
//        Random random = new Random();
//        return new int[]{random.nextInt(6) + 1, random.nextInt(6) + 1};
//    }

//    public void moverJugador(Jugador jugador, int pasos) {
//        int nuevaPosicion = (jugador.getCasillaActual().getId() + pasos) % juego.getTablero().getCasillas().size();
//        //TODO: Usar el service de movimiento
////        jugador.setPosicionActual(nuevaPosicion);
//    }


    //Fixme combinar con los metodos de la clase jugador
    public void aplicarEfectoCasilla(Jugador jugador, Casilla casilla) {
        Banco banco = juego.getBanco();
        List<Jugador> jugadores = juego.getJugadores();
        Scanner scanner = juego.getScanner();

        escrituraRepository = new EscrituraRepositoryImpl();
        escrituraService = new EscrituraServiceImpl();

        EscrituraEntity escrituraEntity = escrituraRepository.getByIdCasilla(casilla.getId());
        Escritura escritura = modelMapper.map(escrituraEntity, Escritura.class);

        switch (casilla.getTipoDeCasilla()) {
            case CAMPO:
                Escritura escrituraDisponible = null;//Todo revisar
//                for (Escritura escritura : banco.getEscriturasDisponibles()) {
//                    if (escritura.getNombre().contains(casilla.getDescripcion())) {
//                        escrituraDisponible = escritura;
//                        break;
//                    }
//                }
                if(escritura.isDisponibilidad()){
                    escrituraDisponible = escritura;
                }
                if (escrituraDisponible != null) {
                    if (!jugador.isEsBot()) {
                        System.out.println("\n¿Desea comprar esta propiedad?");
                        System.out.println("Nombre: " + escrituraDisponible.getNombre());
                        System.out.println("Precio: $" + escrituraDisponible.getPrecio());
                        System.out.println("1. Sí");
                        System.out.println("2. No");
                        int opcion = scanner.nextInt();
                        if (opcion == 1) {
//                            banco.venderEscritura(jugador, escrituraDisponible);
                            jugador.accion(1, escrituraDisponible, null);//Fixme: cambiar cuando saque el parametro de mejora
                        }
                    } else {
                        switch (jugador.getTipoDeJugador()) { //Todo: Revisarlos a todos. Se puede simplificar
                            case BOT_CONSERVADOR:
//                                if (jugador.getSaldo() > escrituraDisponible.getPrecio() * 2) {
//                                    banco.venderEscritura(jugador, escrituraDisponible);
//                                }
                                jugador.accion(1, escrituraDisponible, null);
                                break;
                            case BOT_MODERADO:
//                                if (jugador.getSaldo() > escrituraDisponible.getPrecio() * 1.5) {
//                                    banco.venderEscritura(jugador, escrituraDisponible);
//                                }
                                jugador.accion(1, escrituraDisponible, null);
                                break;
                            case BOT_AGRESIVO:
//                                if (jugador.getSaldo() > escrituraDisponible.getPrecio()) {
//                                    banco.venderEscritura(jugador, escrituraDisponible);
//                                }
                                jugador.accion(1, escrituraDisponible, null);
                                break;
                        }
                    }
                } else {
                    for (Jugador propietario : jugadores) {
                        for (Escritura propiedad : propietario.getPropiedades()) {
                            if (propiedad.getNombre().contains(casilla.getDescripcion())) {
                                if (propietario != jugador) {
                                    banco.cobrarAlquiler(jugador, propietario, propiedad.getValorAlquiler());
                                }
                                break;
                            }
                        }
                    }
                }
                break;
            case ESPECIAL:
                if (casilla.getCarta() != null) {
                    switch (casilla.getCarta().getTipo()) {
                        case SUERTE:
                            jugador.setSaldo(jugador.getSaldo() + casilla.getCarta().getPrecio());
                            break;
                        case DESTINO:
                            jugador.setSaldo(jugador.getSaldo() - casilla.getCarta().getPrecio());
                            break;
                    }
                }
                break;
            case SERVICIOS_PUBLICOS:
            case ESTACION:
                Escritura escrituraServicio = null;//TODO revisar
//                for (Escritura escritura : banco.getEscriturasDisponibles()) {
//                    if (escritura.getNombre().contains(casilla.getDescripcion())) {
//                        escrituraServicio = escritura;
//                        break;
//                    }
//                }
                if(escritura.getCasilla().getTipoDeCasilla().equals(TipoDeCasilla.ESTACION)){
                    escrituraServicio = escritura;
                }
                if (escrituraServicio != null) {
                    if (!jugador.isEsBot()) {
                        System.out.println("\n¿Desea comprar este servicio?");
                        System.out.println("Nombre: " + escrituraServicio.getNombre());
                        System.out.println("Precio: $" + escrituraServicio.getPrecio());
                        System.out.println("1. Sí");
                        System.out.println("2. No");
                        int opcion = scanner.nextInt();
                        if (opcion == 1) {
//                            banco.venderEscritura(jugador, escrituraServicio);
                            jugador.accion(1, escrituraServicio, null);
                        }
                    } else {
                        switch (jugador.getTipoDeJugador()) { //TODO revisar
                            case BOT_CONSERVADOR:
//                                if (jugador.getSaldo() > escrituraServicio.getPrecio() * 2) {
//                                    banco.venderEscritura(jugador, escrituraServicio);
//                                }
                                jugador.accion(1, escrituraServicio, null);
                                break;
                            case BOT_MODERADO:
//                                if (jugador.getSaldo() > escrituraServicio.getPrecio() * 1.5) {
//                                    banco.venderEscritura(jugador, escrituraServicio);
//                                }
                                jugador.accion(1, escrituraServicio, null);
                                break;
                            case BOT_AGRESIVO:
//                                if (jugador.getSaldo() > escrituraServicio.getPrecio()) {
//                                    banco.venderEscritura(jugador, escrituraServicio);
//                                }
                                jugador.accion(1, escrituraServicio, null);
                                break;
                        }
                    }
                } else {
                    for (Jugador propietario : jugadores) {
                        for (Escritura propiedad : propietario.getPropiedades()) {
                            if (propiedad.getNombre().contains(casilla.getDescripcion())) {
                                if (propietario != jugador) {
                                    escrituraService.cobrarAlquiler(propiedad,jugador);//Fixme revisar
                                }
                                break;
                            }
                        }
                    }
                }
                break;
        }
    }

    //Fixme combinar con los metodos de la clase jugador
    public boolean puedeMejorarPropiedad(Jugador jugador) {
        for (Escritura propiedad : jugador.getPropiedades()) {
            if (propiedad.isSePuedeMejorar()) {
                int chacras = 0;
                for (Mejora mejora : propiedad.getMejoras()) {
                    if (mejora.getTipoDeMejora() == TipoDeMejora.CHACRA) {
                        chacras++;
                    }
                }
                if (chacras < 4) {
                    return true;
                }
            }
        }
        return false;
    }

    //Fixme combinar con los metodos de la clase jugador
    public void ofrecerMejora(Jugador jugador) {
        Scanner scanner = juego.getScanner();
        if (!jugador.isEsBot()) {
            System.out.println("\n¿Desea mejorar alguna propiedad?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            int opcion = scanner.nextInt();
            if (opcion == 1) {
                //Todo: Revisar
                jugador.listaDePropiedades();
                System.out.println("\n¿Que propiedad desea mejorar?");
                for(int i = 0; i < jugador.getPropiedades().size(); i++){
                    System.out.println(i + "- " + jugador.getPropiedades().get(i).getNombre());
                }
                int opcionMejora = scanner.nextInt();
                String nombreDeLaOpcion = jugador.getPropiedades().get(opcionMejora).getNombre();
                EscrituraEntity escrituraEntity = escrituraRepository.GetIdByNombre(nombreDeLaOpcion);
                Escritura escritura = modelMapper.map(escrituraEntity, Escritura.class);
                jugador.accion(3,escritura,null);
            }
        } else {
            switch (jugador.getTipoDeJugador()) {
                case BOT_CONSERVADOR:
                    if (jugador.getSaldo() > 50000) {
                        jugador.accion(3,elegirEscrituraBot(jugador),null);
                        // TODO: Revisar
                    }
                    break;
                case BOT_MODERADO:
                    if (jugador.getSaldo() > 30000) {
                        // TODO: Revisar
                        jugador.accion(3,elegirEscrituraBot(jugador),null);
                    }
                    break;
                case BOT_AGRESIVO:
                    if (jugador.getSaldo() > 10000) {
                        // TODO: Revisar
                        jugador.accion(3,elegirEscrituraBot(jugador),null);
                    }
                    break;
            }
        }
    }

    //Todo revisar
    public Escritura elegirEscrituraBot(Jugador jugador){
        List<Escritura>propiedades = jugador.getPropiedades();
        Escritura eleccion = propiedades.get(0);
        for (Escritura propiedad : propiedades) {
            if(propiedad.getPrecio() < jugador.getSaldo() && propiedad.getPrecio() > eleccion.getPrecio()){
                eleccion = propiedad;
            }
        }
        return eleccion;
    }

    public void actualizarListaQuiebras(Jugador jugador) {
        // TODO: Revisar
        if(jugador.isBancarrota()){
            jugadoresEnQuiebra.add(jugador);
        }
    }

    public void enviarALaCarcel(Jugador jugador) {
        //Todo: Revisar
        CasillaEntity casillaEntity = casillaRepository.getById(10);//Posicion provisional
        Casilla casilla = modelMapper.map(casillaEntity, Casilla.class);

        jugador.setCasillaActual(casilla);
    }

    public Jugador obtenerGanador() {
        for (Jugador jugador : juego.getJugadores()) {
            if (!jugador.isBancarrota()) {
                return jugador;
            }
        }
        return null;
    }

    public void finalizarPartida() {
        System.out.println("\n=== FIN DE LA PARTIDA ===");
        System.out.println("Ganador: " + juego.getGanador().getNombre());
        System.out.println("Saldo final: $" + juego.getGanador().getSaldo());
        System.out.println("Duración: " + java.time.Duration.between(juego.getFechaInicial(), juego.getFechaFinal()).toMinutes() + " minutos");
    }
}
