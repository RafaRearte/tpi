package ar.edu.utn.frc.tup.lciii.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tablero {
    private int id;
    private List<Casilla> casillas;
//    private List<Carta> mazo;//Voy a usar dos mazos como hizo rafa
    private MazoCartas mazoDestino;//Fixme cambie los list por una clase mazocartas
    private MazoCartas mazoSuerte;//Fixme cambie los list por una clase mazocartas

    //Cambios de rafa

//    public static final int TOTAL_CASILLAS = 40;
//    public static final int POSICION_SALIDA = 0;
//    public static final int POSICION_CARCEL = 10;
//    public static final int POSICION_PARKING = 20;
//    public static final int POSICION_VAYA_CARCEL = 30;
//
//    /**
//     * Obtiene una casilla por su posición
//     * @param posicion Posición en el tablero (0-39)
//     * @return Casilla en esa posición
//     */
//    public Casilla getCasilla(int posicion) {
//        if (posicion < 0 || posicion >= TOTAL_CASILLAS || casillas == null) {
//            return null;
//        }
//        return casillas.get(posicion);
//    }
//
//    /**
//     * Busca la posición de una casilla por nombre
//     * @param nombre Nombre de la casilla
//     * @return Posición de la casilla (-1 si no se encuentra)
//     */
//    public int buscarPosicionCasilla(String nombre) {
//        if (casillas == null || nombre == null) {
//            return -1;
//        }
//
//        for (int i = 0; i < casillas.size(); i++) {
//            Casilla casilla = casillas.get(i);
//            if (casilla.getDescripcion().toLowerCase().contains(nombre.toLowerCase())) {
//                return i;
//            }
//        }
//        return -1;
//    }
//
//    /**
//     * Calcula la nueva posición después de avanzar
//     * @param posicionActual Posición actual
//     * @param espacios Espacios a avanzar
//     * @return Nueva posición
//     */
//    public int calcularNuevaPosicion(int posicionActual, int espacios) {
//        return (posicionActual + espacios) % TOTAL_CASILLAS;
//    }
//
//    /**
//     * Verifica si al moverse se pasó por la salida
//     * @param posicionAnterior Posición anterior
//     * @param posicionNueva Nueva posición
//     * @return true si pasó por la salida
//     */
//    public boolean pasoPorSalida(int posicionAnterior, int posicionNueva) {
//        return posicionNueva < posicionAnterior;
//    }
//
//    /**
//     * Encuentra el ferrocarril más cercano hacia adelante
//     * @param posicionActual Posición actual del jugador
//     * @return Posición del ferrocarril más cercano
//     */
//    public int buscarFerrocarrilMasCercano(int posicionActual) {
//        // Posiciones típicas de ferrocarriles en el Estanciero
//        int[] posicionesFerrocarriles = {5, 15, 25, 35};
//
//        for (int pos : posicionesFerrocarriles) {
//            if (pos > posicionActual) {
//                return pos;
//            }
//        }
//        // Si no hay ninguno adelante, devolver el primero
//        return posicionesFerrocarriles[0];
//    }
//
//    /**
//     * Encuentra el servicio público más cercano hacia adelante
//     * @param posicionActual Posición actual del jugador
//     * @return Posición del servicio público más cercano
//     */
//    public int buscarServicioPublicoMasCercano(int posicionActual) {
//        // Posiciones típicas de servicios públicos
//        int[] posicionesServicios = {12, 28};
//
//        for (int pos : posicionesServicios) {
//            if (pos > posicionActual) {
//                return pos;
//            }
//        }
//        return posicionesServicios[0];
//    }
//
//    /**
//     * Inicializa los mazos de cartas
//     * @param cartasSuerte Cartas de suerte
//     * @param cartasDestino Cartas de destino
//     */
//    public void inicializarMazos(List<Carta> cartasSuerte, List<Carta> cartasDestino) {
//        this.mazoSuerte = new MazoCartas(TipoDeCarta.SUERTE);
//        this.mazoSuerte.agregarCartas(cartasSuerte);
//        this.mazoSuerte.mezclar();
//
//        this.mazoDestino = new MazoCartas(TipoDeCarta.DESTINO);
//        this.mazoDestino.agregarCartas(cartasDestino);
//        this.mazoDestino.mezclar();
//    }
//
//    /**
//     * Saca una carta de suerte
//     * @return Carta de suerte
//     */
//    public Carta sacarCartaSuerte() {
//        if (mazoSuerte == null) {
//            throw new IllegalStateException("Mazo de suerte no inicializado");
//        }
//        return mazoSuerte.sacarCarta();
//    }
//
//    /**
//     * Saca una carta de destino
//     * @return Carta de destino
//     */
//    public Carta sacarCartaDestino() {
//        if (mazoDestino == null) {
//            throw new IllegalStateException("Mazo de destino no inicializado");
//        }
//        return mazoDestino.sacarCarta();
//    }
//
//    /**
//     * Devuelve una carta al mazo correspondiente
//     * @param carta Carta a devolver
//     */
//    public void devolverCarta(Carta carta) {
//        if (carta == null) return;
//
//        if (carta.getTipo() == TipoDeCarta.SUERTE) {
//            mazoSuerte.devolverCarta(carta);
//        } else if (carta.getTipo() == TipoDeCarta.DESTINO) {
//            mazoDestino.devolverCarta(carta);
//        }
//    }
}
