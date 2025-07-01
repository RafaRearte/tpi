package ar.edu.utn.frc.tup.lciii.services;
import ar.edu.utn.frc.tup.lciii.Models.Casillas.Casilla;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import ar.edu.utn.frc.tup.lciii.Models.Propiedad;

import java.util.List;

public interface JugadorService {
    String obtenerNombre();
    int obtenerSaldo();
    void actualizarSaldo(int cantidad);
    List<Propiedad> obtenerPropiedades();
    List<Casilla> obtenerCasillas();
    void comprarPropiedad(Propiedad propiedad);
    String obtenerComportamiento(String dificultad);
    void pagarAlquiler(int alquiler, Jugador duenio);
}
