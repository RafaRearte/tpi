package ar.edu.utn.frc.tup.lciii.Services;

import ar.edu.utn.frc.tup.lciii.Models.Ajuste;
import ar.edu.utn.frc.tup.lciii.Models.Casilla;
import ar.edu.utn.frc.tup.lciii.Models.Jugador;

public interface JuegoService {
    void iniciarPartida();
    Ajuste configurarDificultad();
    void agregarJugadores();
    void inicializarTablero();
    void inicializarBanco();
    int elegirMontoVictoria();
    boolean hayGanador();
    void ejecutarTurno(Jugador jugador);
//    int[] lanzarDados();//
//    void moverJugador(Jugador jugador, int pasos);//
    void aplicarEfectoCasilla(Jugador jugador, Casilla casilla);
    boolean puedeMejorarPropiedad(Jugador jugador);
    void ofrecerMejora(Jugador jugador);
    void actualizarListaQuiebras(Jugador jugador);
    void enviarALaCarcel(Jugador jugador);
    Jugador obtenerGanador();
    void finalizarPartida();
    void setJuego(ar.edu.utn.frc.tup.lciii.Models.Juego juego);
}
