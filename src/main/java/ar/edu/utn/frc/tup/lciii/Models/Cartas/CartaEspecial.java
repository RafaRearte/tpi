package ar.edu.utn.frc.tup.lciii.Models.Cartas;

import ar.edu.utn.frc.tup.lciii.Models.Casillas.Carcel;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.EstadoJugador;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import ar.edu.utn.frc.tup.lciii.Models.Tablero;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartaEspecial extends Carta {
    private MazoCartas mazoCartas;
    private Tablero tablero;

    public CartaEspecial(int id, CartaTipo cartaTipo, String description) {
        super(id, cartaTipo, TipoCarta.CARTA_ESPECIAL, description);
        if (!verTipoDeCarta(TipoCarta.CARTA_ESPECIAL)) {
            throw new IllegalArgumentException("El tipo de carta no es CARTA_ESPECIAL");
        }
    }

    @Override
    public void ejecutarAccion(Jugador jugador) {
        cartaEspecial(getDescription(), jugador);}

    private void cartaEspecial(String descripcion, Jugador jugador) {
        try {
            if (getTypeCard() == TipoCarta.CARTA_ESPECIAL) {
                switch (descripcion) {
                    case "Habeas Corpus concedido. Con esta tarjeta sale usted gratuitamente de la comisaría. consérvela o véndala.":
                        salidaGratisDeCarcel(jugador);
                        break;
                    case "Marché preso directamente.":
                        marchePresoDirectamente(jugador);
                        break;
                    case "Pague 200 más y Levante una tarjeta de suerte.":
                        suerteEspecial(jugador);
                        break;
                    case "Con esta tarjeta sale usted de la comisaría. consérvela hasta utilizarla o véndala.":
                        marchePresoDirectamente(jugador);
                        break;
                    default:
                        throw new IllegalArgumentException("El tipo de carta no es SPECIALCARDS o no se puede ejecutar");
                }
            }
        } catch (IllegalArgumentException e) {
            // Re-lanzar IllegalArgumentException para que los tests puedan detectarlas
            throw e;
        } catch (Exception e) {
            System.err.println("An error occurred while moving the card: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void salidaGratisDeCarcel(Jugador jugador) {
        Carcel carcel = tablero.getCarcel();
        if (jugador != null) {
            boolean roll = carcel.preguntarSiQuiereLanzarDados(jugador);
            if (roll) {
                jugador.setEstado(EstadoJugador.HABILITADO);
                System.out.println(jugador.getNombre() + " ha salido de la cárcel");
            } else {
                System.out.println(jugador.getNombre() + " ha decidido no salir de la cárcel");
            }
        } else {
            throw new IllegalArgumentException("No se encuentra al jugador.");
        }
    }

    private void marchePresoDirectamente(Jugador jugador) {
        Carcel carcel = tablero.getCarcel();
        if (jugador != null) {
            carcel.enviarALaCarcel(jugador);
            jugador.setEstado(EstadoJugador.PRESO);
        } else {
            throw new IllegalArgumentException("No se encuentra al jugador.");
        }
    }

    private void suerteEspecial(Jugador jugador) {
        if (jugador != null) {
            jugador.pagarSaldo(200);
            mazoCartas.ejecutarAccionSuerte(jugador);
        } else {
            throw new IllegalArgumentException("No se encuentra al jugador.");
        }
    }
}
