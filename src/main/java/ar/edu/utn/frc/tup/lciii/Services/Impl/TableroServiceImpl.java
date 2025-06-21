package ar.edu.utn.frc.tup.lciii.Services.Impl;

import ar.edu.utn.frc.tup.lciii.Models.Carta;
import ar.edu.utn.frc.tup.lciii.Models.Casilla;
import ar.edu.utn.frc.tup.lciii.Models.Tablero;
import ar.edu.utn.frc.tup.lciii.Models.TipoDeCarta;
import ar.edu.utn.frc.tup.lciii.Services.CartaService;
import ar.edu.utn.frc.tup.lciii.Services.TableroService;

import java.util.List;

//Fixme Revisar si los cambios estan bien

public class TableroServiceImpl implements TableroService {

    Tablero tablero;
    CartaService cartaService;

    public TableroServiceImpl(Tablero tablero, CartaService cartaService) {
        this.tablero = tablero;
        this.cartaService = cartaService;
    }


    public static final int TOTAL_CASILLAS = 40;
    public static final int POSICION_SALIDA = 0;
    public static final int POSICION_CARCEL = 10;
    public static final int POSICION_PARKING = 20;
    public static final int POSICION_VAYA_CARCEL = 30;


    /**
     * Obtiene una casilla por su posición
     * @param posicion Posición en el tablero (0-39)
     * @return Casilla en esa posición
     */
    @Override
    public Casilla getCasilla(int posicion) {
        if (posicion < 0 || posicion >= TOTAL_CASILLAS || tablero.getCasillas() == null) {
            return null;
        }
        return tablero.getCasillas().get(posicion);
    }

    /**
     * Busca la posición de una casilla por nombre
     * @param nombre Nombre de la casilla
     * @return Posición de la casilla (-1 si no se encuentra)
     */
    @Override
    public int buscarPosicionCasilla(String nombre) {
        if (tablero.getCasillas() == null || nombre == null) {
            return -1;
        }

        for (int i = 0; i < tablero.getCasillas().size(); i++) {
            Casilla casilla = tablero.getCasillas().get(i);
            if (casilla.getDescripcion().toLowerCase().contains(nombre.toLowerCase())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Calcula la nueva posición después de avanzar
     * @param posicionActual Posición actual
     * @param espacios Espacios a avanzar
     * @return Nueva posición
     */
    @Override
    public int calcularNuevaPosicion(int posicionActual, int espacios) {
        return (posicionActual + espacios) % TOTAL_CASILLAS;
    }

    /**
     * Verifica si al moverse se pasó por la salida
     * @param posicionAnterior Posición anterior
     * @param posicionNueva Nueva posición
     * @return true si pasó por la salida
     */
    @Override
    public boolean pasoPorSalida(int posicionAnterior, int posicionNueva) {
        return posicionNueva < posicionAnterior;
    }

    /**
     * Encuentra el ferrocarril más cercano hacia adelante
     * @param posicionActual Posición actual del jugador
     * @return Posición del ferrocarril más cercano
     */
    @Override
    public int buscarFerrocarrilMasCercano(int posicionActual) {
        // Posiciones típicas de ferrocarriles en el Estanciero
        int[] posicionesFerrocarriles = {5, 15, 25, 35};

        for (int pos : posicionesFerrocarriles) {
            if (pos > posicionActual) {
                return pos;
            }
        }
        // Si no hay ninguno adelante, devolver el primero
        return posicionesFerrocarriles[0];
    }

    /**
     * Encuentra el servicio público más cercano hacia adelante
     * @param posicionActual Posición actual del jugador
     * @return Posición del servicio público más cercano
     */
    @Override
    public int buscarServicioPublicoMasCercano(int posicionActual) {
        // Posiciones típicas de servicios públicos
        int[] posicionesServicios = {12, 28};

        for (int pos : posicionesServicios) {
            if (pos > posicionActual) {
                return pos;
            }
        }
        return posicionesServicios[0];
    }

    /**
     * Inicializa los mazos de cartas
     * @param cartasSuerte Cartas de suerte
     * @param cartasDestino Cartas de destino
     */
    @Override
    public void inicializarMazos(List<Carta> cartasSuerte, List<Carta> cartasDestino) {
        this.tablero.setMazoSuerte(cartaService.crearMazo(TipoDeCarta.SUERTE));
        cartaService.agregarCartas(cartasSuerte, tablero.getMazoSuerte().getCartas());
        cartaService.mezclar(tablero.getMazoSuerte().getCartas());

        this.tablero.setMazoDestino(cartaService.crearMazo(TipoDeCarta.DESTINO));
        cartaService.agregarCartas(cartasDestino, tablero.getMazoDestino().getCartas());
        cartaService.mezclar(tablero.getMazoDestino().getCartas());
    }

    /**
     * Saca una carta de suerte
     * @return Carta de suerte
     */
    @Override
    public Carta sacarCartaSuerte() {
        if (tablero.getMazoSuerte() == null) {
            throw new IllegalStateException("Mazo de suerte no inicializado");
        }
        return cartaService.sacarCarta(tablero.getMazoSuerte().getCartas(), tablero.getMazoSuerte().getCartasUsadas());
    }

    /**
     * Saca una carta de destino
     * @return Carta de destino
     */
    @Override
    public Carta sacarCartaDestino() {
        if (tablero.getMazoDestino() == null) {
            throw new IllegalStateException("Mazo de destino no inicializado");
        }
        return cartaService.sacarCarta(tablero.getMazoDestino().getCartas(), tablero.getMazoDestino().getCartasUsadas());
    }


    /**
     * Devuelve una carta al mazo correspondiente
     * @param carta Carta a devolver
     */
    @Override
    public void devolverCarta(Carta carta) {
        if (carta == null) return;

        if (carta.getTipo() == TipoDeCarta.SUERTE) {
            cartaService.devolverCarta(carta, tablero.getMazoSuerte().getCartasUsadas());
//            mazoSuerte.devolverCarta(carta);
        } else if (carta.getTipo() == TipoDeCarta.DESTINO) {
            cartaService.devolverCarta(carta, tablero.getMazoDestino().getCartasUsadas());
//            mazoDestino.devolverCarta(carta);
        }
    }
}
