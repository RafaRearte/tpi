package ar.edu.utn.frc.tup.lciii.Models.Casillas;

import ar.edu.utn.frc.tup.lciii.handlers.EstancieroMatchHandler;
import ar.edu.utn.frc.tup.lciii.handlers.ImpresoLetraPorLetra;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import ar.edu.utn.frc.tup.lciii.Models.Propiedad;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Setter
@Getter
public class Casilla {

    private int id;
    private List<Propiedad> propiedades;
    private Jugador jugador;
    private int posicion;
    private Scanner scanner = new Scanner(System.in);
    private TipoCasilla tipoCasilla;

    public Casilla(){
        this.propiedades = new ArrayList<>();
    }

    public Casilla(int id, Jugador jugador) {
        this.id = id;
        this.propiedades = new ArrayList<>();
        this.jugador = jugador;
    }

    public void agregarPropiedad(Propiedad propiedad){
        propiedades.add(propiedad);
    }

    public void realizarAccion(Jugador Jugador, EstancieroMatchHandler estancieroMatchHandler) {
        int posicionDelJugador = Jugador.getPosicion();
        Casilla casillaActual = estancieroMatchHandler.getTablero().obtenerCasilla(Jugador.getPosicion());

        if (casillaActual == null){
            ImpresoLetraPorLetra.println("Error: La casilla actual no existe");
            return;
        }

        ImpresoLetraPorLetra.println("Jugador " + Jugador.getNombre() + " esta en la posicion " + posicionDelJugador + " " +
                casillaActual.getClass().getSimpleName());

    }

}
