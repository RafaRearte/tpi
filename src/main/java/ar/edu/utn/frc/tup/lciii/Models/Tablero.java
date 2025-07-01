package ar.edu.utn.frc.tup.lciii.Models;

import ar.edu.utn.frc.tup.lciii.Models.Casillas.*;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

import static java.lang.System.exit;

@Setter
@Getter
public class Tablero {

    private Scanner scanner = new Scanner(System.in);

    private Map<Integer, Casilla> map = new HashMap<>();
    private Jugador userJugador;
    private Jugador[] jugadores;
    private int position;
    private Casilla casilla;

    @Getter
    private Carcel carcel;

    private static Tablero instancia;

    public Tablero(Jugador[] jugadores, int position, Map<Integer, Casilla> boxMap) {
        this.jugadores = jugadores;
        this.position = position;
        this.map = boxMap;
    }

    public Tablero() {
        inicializarTablero();
    }

    private void inicializarTablero() {
        map.put(0,new Salida("Salida", TipoCasilla.SALIDA));
        map.put(1, new Provincia("FORMOSA Zona Sur", 1000, TipoCasilla.PROVINCIA, true));
        map.put(2, new Provincia("FORMOSA Zona Centro", 1000, TipoCasilla.PROVINCIA, true));
        map.put(3, new Provincia("FORMOSA Zona Norte", 1200, TipoCasilla.PROVINCIA, true));
        map.put(4, new Suerte("Suerte", TipoCasilla.CARTA_SUERTE));
        map.put(5, new Provincia("RIO NEGRO Zona Sur", 2000, TipoCasilla.PROVINCIA, true));
        map.put(6, new Provincia("RIO NEGRO Zona Norte", 2200, TipoCasilla.PROVINCIA, true));
        map.put(7,new PremioGanadero("Premio Ganadero", TipoCasilla.PREMIO_GANADERO));
        map.put(8, new CompaniaPetrolera("COMPAÑÍA PETROLERA", 3600, TipoCasilla.COMPANIA_PETROLERA, true));
        map.put(9, new Provincia("SALTA Zona Sur", 2600, TipoCasilla.PROVINCIA, true));
        map.put(10,new Destino("Destino", TipoCasilla.CARTA_DESTINO));
        map.put(11, new Provincia("SALTA Zona Centro", 2600, TipoCasilla.PROVINCIA, true));
        map.put(12, new Ferrocarril("General BELGRANO", 3600, TipoCasilla.FERROCARRIL, true));
        map.put(13, new Provincia("SALTA Zona Norte", 3000, TipoCasilla.PROVINCIA, true));
        map.put(14, new Carcel("Comisaria", TipoCasilla.COMISARIA));
        map.put(15, new Suerte("Suerte", TipoCasilla.CARTA_SUERTE));
        map.put(16, new Bodega("BODEGA", 3600, TipoCasilla.BODEGA, true));
        map.put(17, new Provincia("MENDOZA Zona Sur", 3400, TipoCasilla.PROVINCIA, true));
        map.put(18, new Ferrocarril("General S. MARTIN", 3600, TipoCasilla.FERROCARRIL, true));
        map.put(19, new Provincia("MENDOZA Zona Centro", 3400, TipoCasilla.PROVINCIA, true));
        map.put(20, new Provincia("MENDOZA Zona Norte", 3600, TipoCasilla.PROVINCIA, true));
        map.put(21, new Descanso("Descanso", TipoCasilla.DESCANSO));
        map.put(22, new Ferrocarril("General B. MITRE", 3600, TipoCasilla.FERROCARRIL, true));
        map.put(23, new Provincia("SANTA FE Zona Sur", 4200, TipoCasilla.PROVINCIA, true));
        map.put(24, new Provincia("SANTA FE Zona Centro", 4200, TipoCasilla.PROVINCIA, true));
        map.put(25,new Destino("Destino", TipoCasilla.CARTA_DESTINO));
        map.put(26, new Provincia("SANTA FE Zona Norte", 4600, TipoCasilla.PROVINCIA, true));
        map.put(27, new Ferrocarril("General URQUIZA", 3600, TipoCasilla.FERROCARRIL, true));
        map.put(28, new EstacionamientoLibre("Libre Estacionamiento", TipoCasilla.ESTACIONAMIENTO_LIBRE));
        map.put(29, new Provincia("TUCUMAN Zona Sur", 5000, TipoCasilla.PROVINCIA, true));
        map.put(30, new Provincia("TUCUMAN Zona Norte", 5400, TipoCasilla.PROVINCIA, true));
        map.put(31, new Impuestos("Impuesto a las Ganancias. Pague $5000", TipoCasilla.RENTAS));
        map.put(32, new Provincia("CORDOBA Zona Sur", 6000, TipoCasilla.PROVINCIA, true));
        map.put(33, new Provincia("CORDOBA Zona Centro", 6000, TipoCasilla.PROVINCIA, true));
        map.put(34, new Provincia("CORDOBA Zona Norte", 6400, TipoCasilla.PROVINCIA, true));
        map.put(35, new Carcel("Marche Preso", TipoCasilla.MARCHE_PRESO));
        map.put(36, new Suerte("Suerte", TipoCasilla.CARTA_SUERTE));
        map.put(37, new Provincia("BUENOS AIRES Zona Sur", 7000, TipoCasilla.PROVINCIA, true));
        map.put(38,new Destino("Destino", TipoCasilla.CARTA_DESTINO));
        map.put(39, new Provincia("BUENOS AIRES Zona Centro", 7000, TipoCasilla.PROVINCIA, true));
        map.put(40, new Provincia("BUENOS AIRES Zona Norte", 7400, TipoCasilla.PROVINCIA, true));
        map.put(41, new Impuestos("Impuesto a las ventas. Pague $2000", TipoCasilla.IMPUESTO_VENTAS));
    }

    public Tablero(Map<Integer, Casilla> boxMap, Jugador[] jugadores) {
        this.jugadores = jugadores;
        this.map = boxMap;
    }


    public int obtenerPosicionDeSalida() {
        for (Map.Entry<Integer, Casilla> entry : map.entrySet()) {
            if (entry.getValue().getTipoCasilla() == TipoCasilla.SALIDA) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public boolean esDuenioDeLaCasilla(int index){
        return this.jugadores[index].getCasillas().contains(map.get(this.position));
    }

    public void setJugador(int playerIndex, Jugador realJugador) {
        this.jugadores[playerIndex] = realJugador;
    }

    public List<Propiedad> obtenerPropiedadesDisponibles(){
        List<Propiedad> propiedadesDisponibles = new ArrayList<>();
        for (Casilla casilla : map.values()){
            List<Propiedad> properties = casilla.getPropiedades();
            for (Propiedad propiedad : properties){
                if (propiedad.getDuenio() == null){
                    propiedadesDisponibles.add(propiedad);
                }
            }
        }
        return propiedadesDisponibles;
    }

    public int obtenerPosicionCasilla(){
        return position;
    }

    public void avanzarPosicionDelJugador(int index, int nuevaPosicion){
        if(nuevaPosicion >= 0 && nuevaPosicion < map.size()){
            int posicionActual = jugadores[index].getPosicion();
            Casilla casillaActual = map.get(posicionActual);
            Casilla nuevaCasilla = map.get(nuevaPosicion);

            if (casillaActual != null){
                casillaActual.setJugador(null);
            }

            if (nuevaCasilla != null){
                nuevaCasilla.setJugador(jugadores[index]);
            }

            nuevaCasilla.setJugador(jugadores[index]);
            System.out.println("El jugador ha sido movido a la posicion " + nuevaPosicion);

        } else {
            System.out.println("Posicion no valida!!");
        }
    }

    public boolean movimientoValido(int index, int pasos){
        int nuevaPosicion = jugadores[index].getPosicion() + pasos;

        return nuevaPosicion >= 0 && nuevaPosicion < map.size();
    }

    public void moverJugador(int index, int pasos){
        if (jugadores != null && movimientoValido(index, pasos)){
            jugadores[index].mover(pasos);
            position += pasos;
        } else {
            System.out.println("Movimiento no valido!!");
        }

    }

    public int buscarPosicionDeLaCasilla(int numero){
        for (Map.Entry<Integer, Casilla> boxEntry : map.entrySet()){
            if (boxEntry.getKey()==numero){
                return boxEntry.getValue().getPosicion();
            }
        }
        return -1;
    }

    public int obtenerTamanio() {
        return map.size();
    }

    public Casilla obtenerCasilla(int nuevaPosicion) {
        return map.get(nuevaPosicion);
    }

    public Jugador obtenerDuenio(){
        return userJugador;
    }

    public boolean casillaDisponible(int posicion){
        // TODO: completar metodo
        Provincia provincia;
        Ferrocarril ferrocarril;
        CompaniaPetrolera companiaPetrolera;
        for (Integer i:
                map.keySet()) {
            if (i == posicion) {
                map.get(posicion);
            }
        }
        return false;
    }

    // TODO: Mover los metodos siguientes a la clase Tablero
    public void mostrarMenu() {
        while (true) {
            try {
                System.out.println("1. Continuar");
                System.out.println("2. Guardar");
                System.out.println("3. Menu principal");
                System.out.print("Elija una opcion: ");

                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("Regresando al juego...");
                        return;
                    case 2:
                        guardarPatida();
                        break;
                    case 3:
                        salirDelJuego();
                        break;
                    default:
                        System.out.println("Opción inválida, intente de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Opcion invalida. Por favor elija una opcion valida.");
            }
        }
    }

    public void guardarPatida() {
        // TODO: Implementar la lógica para guardar la partida
        System.out.println("Partida guardada.");
    }

    public void salirDelJuego() {
        // TODO Implementar la lógica para salir de la partida EN OTRO LADO
        System.out.println("Saliendo de la partida.");
        exit(0);
    }

}
