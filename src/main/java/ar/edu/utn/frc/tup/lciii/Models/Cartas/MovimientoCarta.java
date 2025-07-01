package ar.edu.utn.frc.tup.lciii.Models.Cartas;

import ar.edu.utn.frc.tup.lciii.handlers.EstancieroMatchHandler;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import ar.edu.utn.frc.tup.lciii.Models.Tablero;

public class MovimientoCarta extends Carta {

    public MovimientoCarta(int id, CartaTipo cartaTipo, String description) {
        super(id, cartaTipo, TipoCarta.CARTA_MOVIMIENTO, description);

        if (!verTipoDeCarta(TipoCarta.CARTA_MOVIMIENTO)) {
            throw new IllegalArgumentException("El tipo de carta no es CARTA_MOVIMIENTO");
        }
    }

    @Override
    public void ejecutarAccion(Jugador jugador) {

        String descripcion = getDescription();
        descripcionCartaDeMovimiento(descripcion, jugador);

    }

    public void descripcionCartaDeMovimiento(String description, Jugador jugador) {
        if (getTypeCard() == TipoCarta.CARTA_MOVIMIENTO) {

            // Normalizar la descripción: trim, lowercase, sin puntos, sin comas al final
            String normalizedDescription = description.trim()
                    .toLowerCase()
                    .replaceAll("\\.$", "")  // Remover punto final
                    .replaceAll(",\\s*$", "") // Remover coma final
                    .replaceAll("\\.", "")    // Remover puntos de números (5.000 -> 5000)
                    .replaceAll("\\s+", " "); // Normalizar espacios múltiples

            switch (normalizedDescription) {
                case "vuelva tres pasos atrás":
                case "vuelve tres pasos atrás":
                case "vuelve 3 paso atrás":
                    cartaRetroceder(jugador, 3);
                    break;

                case "siga hasta santa fe, zona norte si pasa por la salida cobre 5000":
                    cartaAvanzar(jugador, 26);
                    break;

                case "siga hasta salta, zona norte si pasa por la salida cobre 5000":
                    cartaAvanzar(jugador, 13);
                    break;

                case "siga hasta buenos aires, zona norte":
                    cartaAvanzar(jugador, 40);
                    break;

                case "siga hasta la salida":
                    cartaAvanzar(jugador, 0); // Ir a la posición 0 (salida)
                    break;

                case "haga un paseo hasta la bodega si pasa por la salida cobre 5000":
                    cartaAvanzar(jugador, 16);
                    break;

                case "vuelve hasta formosa zona sur":
                case "vuelva atrás hasta formosa zona sur":
                    cartaAvanzar(jugador, 1);
                    break;

                default:
                    throw new IllegalArgumentException("Descripcion de la carta no valida: '" + description + "' (normalizada: '" + normalizedDescription + "')");
            }
        } else {
            throw new IllegalArgumentException("El tipo de carta no es CARTA_MOVIMIENTO");
        }
    }

//    public void descripcionCartaDeMovimiento(String description, Jugador jugador) {
//        if (getTypeCard() == TipoCarta.CARTA_MOVIMIENTO) {
//
//            switch (description) {
//                case "Vuelve 3 paso atrás":
//                    cartaRetroceder(jugador, 3);
//                    break;
//                case "Siga hasta Santa Fe, zona norte si pasa por la salida cobre 5000":
//                    cartaAvanzar(jugador,26);
//                    break;
//                case "Siga hasta Salta, zona norte si pasa por la salida cobre 5000":
//                    cartaAvanzar(jugador,13);
//                    break;
//                case "Siga hasta Buenos Aires, zona norte":
//                    cartaAvanzar(jugador,40);
//                    break;
//                case "Siga hasta la salida":
//                    cartaRetroceder(jugador, 0);
//                    break;
//                case "Haga un paseo hasta la bodega si pasa por la salida cobre 5000":
//                    cartaAvanzar(jugador,16);
//                    break;
//                case "Vuelve hasta Formosa zona sur.":
//                    cartaAvanzar(jugador,1);
//                    break;
//                default:
//                    throw new IllegalArgumentException("Descripcion de la carta no valida: " + description);
//
//            }
//        } else {
//            throw new IllegalArgumentException("El tipo de carta no es MOVEMENTSCARDS");
//        }
//    }

    private void cartaRetroceder(Jugador jugador, int casillasAtras) {
        int posicionActual = jugador.getPosicion();
        int nuevaPosicion = posicionActual - casillasAtras;

        if (nuevaPosicion < 0) {
            nuevaPosicion = 42 + nuevaPosicion; // Dar la vuelta hacia atrás
        }

        jugador.setPosicion(nuevaPosicion);
    }

//    private void cartaRetroceder(Jugador jugador, int pasos) {
//        if (getTypeCard() == TipoCarta.CARTA_MOVIMIENTO) {
//            int posicionActual = jugador.getPosicion();
//            int nuevaPosicion = posicionActual - pasos;
//            jugador.setPosicion(nuevaPosicion);
//
//        }
//    }

    private void cartaAvanzar(Jugador jugador, int posicionDestino) {
        int posicionActual = jugador.getPosicion();

        // Si la posición destino es menor que la actual, significa que debe dar la vuelta
        if (posicionDestino < posicionActual) {
            // Dar la vuelta al tablero
            int casillasHastaSalida = 42 - posicionActual; // Asumiendo 42 casillas en total
            int casillasDesdesSalida = posicionDestino;
            int totalCasillas = casillasHastaSalida + casillasDesdesSalida;

            // Verificar si pasa por la salida y darle dinero
            System.out.println("Pasó por la salida! El banco le da $5000.");
            // Aquí deberías agregar la lógica para dar dinero al jugador
            // banco.distribuirSaldo(jugador, 5000);

            jugador.mover(totalCasillas);
        } else {
            // Movimiento normal
            int casillasAMover = posicionDestino - posicionActual;
            jugador.mover(casillasAMover);
        }
    }

//    private void cartaAvanzar(Jugador jugador, int posicionDeDestino) {
//        try {
//            Tablero tablero = EstancieroMatchHandler.getTablero();
//            if (getTypeCard() == TipoCarta.CARTA_MOVIMIENTO) {
//                // obtiene la posicion actual del jugador
//                int posicionActual = jugador.getPosicion();
//                System.out.println("Posicion actual: " + posicionActual);
//                //calcular los pasos para llegar a salida
//                int pasosHaciaSalida = (41 - posicionActual) % 41;
//                System.out.println("Pasos hacia la salida: " + pasosHaciaSalida);
//                //calular los pasos desde la salida hasta el destino deseado
//                int pasosDesdeSalida = (posicionDeDestino - posicionActual + 41) % 41;
//                System.out.println("Pasos desde la salida: " + pasosDesdeSalida);
//                //calular la nueva posicion
//                int nuevaPosicion = (posicionActual + pasosHaciaSalida + pasosDesdeSalida) % 41;
//                System.out.println("Nueva posicion: " + nuevaPosicion);
//
//                //actualizar la nueva posicion del jugador
//                jugador.setPosicion(nuevaPosicion + jugador.getPosicion());
//                System.out.println("Posicion del jugador actualizada: " + jugador.getPosicion());
//
//                if (pasosDesdeSalida != 0) {
//                    jugador.saldoRecibido(5000);
//                    jugador.contadorJuegos();
//                }
//            }
//        } catch (Exception e) {
//            System.err.println("Ocurrio un error al mover la carta: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
}