package ar.edu.utn.frc.tup.lciii.Models.Casillas;

import ar.edu.utn.frc.tup.lciii.handlers.EstancieroMatchHandler;
import ar.edu.utn.frc.tup.lciii.handlers.ImpresoLetraPorLetra;
import ar.edu.utn.frc.tup.lciii.Models.Dado;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.EstadoJugador;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import ar.edu.utn.frc.tup.lciii.services.CasillaService;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Setter
@Getter
public class Carcel extends Casilla implements CasillaService {
    private String nombre;
    private TipoCasilla tipoCasilla;
    private Dado dado;

    public Carcel(String name, TipoCasilla tipoCasilla) {
        this.nombre = name;
        this.tipoCasilla = tipoCasilla;
    }

    public Carcel() {

    }

    public void enviadoALaCarcel(Jugador jugador) {
        ImpresoLetraPorLetra.println(jugador.getNombre() + " Ha sacado dobles tres veces. Va a la carcel.");

        enviarALaCarcel(jugador);
    }

    public void enviarALaCarcel(Jugador jugador) {
        int posicionCarcel = 14;
        jugador.setPosicion(posicionCarcel);
        jugador.setEstado(EstadoJugador.PRESO);
        jugador.setTurnosEnCasilla(1);
    }

    @Override
    public void ejecutarAccion(Jugador jugador, EstancieroMatchHandler estancieroMatchHandler) {
        int posicionDelJugador = jugador.getPosicion();

        // verificamos si esta en la comisaria
        if (posicionDelJugador == 14) {
            ImpresoLetraPorLetra.println("Comisaria");
            //sendToJail(player);
        } else if (posicionDelJugador == 35) {
            // enviar a la carcel
            ImpresoLetraPorLetra.println(jugador.getNombre() + " cayo en Marche Preso. Va a la carcel.!");
            enviadoALaCarcel(jugador);
        }
    }

    public boolean lanzarDadosParaSalirDeCarcel() {
        dado.lanzarDado();
        System.out.println("Has sacado los dados "+ dado.getDado1() + ", " + dado.getDado2());
        return dado.esDoble();
    }

    public boolean preguntarSiQuiereLanzarDados(Jugador jugador) {
        System.out.println(jugador.getNombre()+ ", ¿quieres tirar los dados para salir intentar salid de la cárcel? (y/n)");
        Scanner scanner= new Scanner(System.in);
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("y")) {
            dado.lanzarDado();
            return true;
        } else if (respuesta.equalsIgnoreCase("n")) {
            return false;
        }else {
            System.out.println("Respuesta invalida,ingrese Y o N");
            return preguntarSiQuiereLanzarDados(jugador);
        }
    }

    public void dadosDobles(Jugador jugador, EstancieroMatchHandler estancieroMatchHandler) {
        enviadoALaCarcel(jugador);
    }
}
