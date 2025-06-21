package ar.edu.utn.frc.tup.lciii.Services;

import ar.edu.utn.frc.tup.lciii.Models.Carta;
import ar.edu.utn.frc.tup.lciii.Models.Casilla;
import ar.edu.utn.frc.tup.lciii.Models.TipoDeCarta;

import java.util.List;

public interface TableroService {
    Casilla getCasilla(int posicion);

    int buscarPosicionCasilla(String nombre);

    int calcularNuevaPosicion(int posicionActual, int espacios);

    boolean pasoPorSalida(int posicionAnterior, int posicionNueva);

    int buscarFerrocarrilMasCercano(int posicionActual);

    int buscarServicioPublicoMasCercano(int posicionActual);

    void inicializarMazos(List<Carta> cartasSuerte, List<Carta> cartasDestino);

    Carta sacarCartaSuerte();

    Carta sacarCartaDestino();

    void devolverCarta(Carta carta);
}
