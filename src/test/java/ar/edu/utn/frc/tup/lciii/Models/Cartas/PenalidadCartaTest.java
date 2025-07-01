package ar.edu.utn.frc.tup.lciii.Models.Cartas;

import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PenalidadCartaTest {

    @Test
    void ejecutarAccion() {
        Jugador jugadorMock = mock(Jugador.class);
        when(jugadorMock.getSaldo()).thenReturn(1000);

        PenalidadCarta penalidadCarta = new PenalidadCarta(1, CartaTipo.SUERTE, "Penalidad de $500 por incumplimiento");

        penalidadCarta.ejecutarAccion(jugadorMock);

        verify(jugadorMock).pagarSaldo(500);
    }

    @Test
    void ejecutarAccionSaldoInsuficiente() {
        Jugador jugadorMock = mock(Jugador.class);
        when(jugadorMock.getSaldo()).thenReturn(300);

        PenalidadCarta penalidadCarta = new PenalidadCarta(1, CartaTipo.SUERTE, "Penalidad de $500 por incumplimiento");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> penalidadCarta.ejecutarAccion(jugadorMock));
        assertEquals("el jugador no tiene el monto necesario para llevar a cabo la accion. ", exception.getMessage());
        verify(jugadorMock).pagarSaldo(300);
    }

    @Test
    void ejecutarAccionDescripcionNula() {
        Jugador jugadorMock = mock(Jugador.class);

        PenalidadCarta penalidadCarta = new PenalidadCarta(1, CartaTipo.SUERTE, null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> penalidadCarta.ejecutarAccion(jugadorMock));
        assertEquals("Error: Descripción de carta es nula.", exception.getMessage());
    }

    @Test
    void ejecutarAccionMontoCero() {
        Jugador jugadorMock = mock(Jugador.class);

        PenalidadCarta penalidadCarta = new PenalidadCarta(1, CartaTipo.SUERTE, "Penalidad de $0 por incumplimiento");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> penalidadCarta.ejecutarAccion(jugadorMock));
        assertEquals("el monto a descontar no puede ser 0", exception.getMessage());
    }
}