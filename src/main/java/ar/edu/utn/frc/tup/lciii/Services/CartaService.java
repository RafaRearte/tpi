package ar.edu.utn.frc.tup.lciii.Services;

import ar.edu.utn.frc.tup.lciii.Models.Carta;
import ar.edu.utn.frc.tup.lciii.Models.MazoCartas;
import ar.edu.utn.frc.tup.lciii.Models.TipoDeCarta;

import java.util.List;

public interface CartaService {
    MazoCartas crearMazo(TipoDeCarta tipo);
    void mezclar(List<Carta> cartas);
    Carta sacarCarta(List<Carta> cartas, List<Carta> cartasUsadas);
    void devolverCarta(Carta carta, List<Carta> cartasUsadas);
    void agregarCartas(List<Carta> nuevasCartas, List<Carta> cartas);
    boolean estaVacio(List<Carta> cartasUsadas, List<Carta> cartas);
    int getTotalCartas(List<Carta> cartasUsadas, List<Carta> cartas);
}
