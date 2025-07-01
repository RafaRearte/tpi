package ar.edu.utn.frc.tup.lciii.Models.Cartas;

import ar.edu.utn.frc.tup.lciii.Models.Casillas.Casilla;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.EstadoJugador;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.strategy.JugadorHumano;
import ar.edu.utn.frc.tup.lciii.Models.Propiedad;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovimientoCartaTest {

    private MovimientoCarta carta;
    private JugadorHumano jugador;

    @BeforeEach
    void setUp() {
        carta = new MovimientoCarta(1, CartaTipo.MOVIMIENTO, "vuelve tres pasos atrás");
        List<Casilla> casillas = new ArrayList<>();
        List<Propiedad> propiedades = new ArrayList<>();
        jugador = new JugadorHumano("TestPlayer", 10000, propiedades, "Rojo", 5, "Bot", 0, EstadoJugador.HABILITADO, casillas);
    }

    @Test
    void ejecutarAccion() {
        // Arrange
        MovimientoCarta carta = new MovimientoCarta(1, CartaTipo.MOVIMIENTO, "vuelve tres pasos atrás");
        JugadorHumano jugador = new JugadorHumano("TestPlayer", 10000, new ArrayList<>(), "Rojo", 5, "Bot", 0, EstadoJugador.HABILITADO, new ArrayList<>());

        // Act
        carta.ejecutarAccion(jugador);

        // Assert
        assertEquals(2, jugador.getPosicion(), "El jugador debería haber retrocedido tres pasos.");
    }

    @Test
    void descripcionCartaDeMovimiento() {
        // Arrange
        MovimientoCarta carta = new MovimientoCarta(2, CartaTipo.MOVIMIENTO, "siga hasta la salida");
        JugadorHumano jugador = new JugadorHumano("TestPlayer", 10000, new ArrayList<>(), "Rojo", 5, "Bot", 0, EstadoJugador.HABILITADO, new ArrayList<>());

        // Act
        carta.descripcionCartaDeMovimiento("vuelve tres pasos atrás", jugador);

        // Assert
        assertEquals(2, jugador.getPosicion(), "El jugador debería haber retrocedido tres pasos.");
    }

    @Test
    void descripcionCartaDeMovimientoDescripcionInvalida() {
        // Arrange
        MovimientoCarta carta = new MovimientoCarta(3, CartaTipo.MOVIMIENTO, "descripcion invalida");
        JugadorHumano jugador = new JugadorHumano("TestPlayer", 10000, new ArrayList<>(), "Rojo", 5, "Bot", 0, EstadoJugador.HABILITADO, new ArrayList<>());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            carta.descripcionCartaDeMovimiento("descripcion invalida", jugador);
        });

        String expectedMessage = "Descripcion de la carta no valida";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "Debería lanzar una excepción con mensaje de descripción inválida.");
    }

    @Test
    void ejecutarAccionTipoCartaInvalido() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new MovimientoCarta(4, CartaTipo.SUERTE, "vuelve tres pasos atrás");
        });

        String expectedMessage = "El tipo de carta no es CARTA_MOVIMIENTO";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "Debería lanzar una excepción con mensaje de tipo de carta inválido.");
    }

    @Test
    void ejecutarAccionJugadorHumano() {
        // Act
        carta.ejecutarAccion(jugador);

        // Assert
        assertEquals(2, jugador.getPosicion(), "El jugador debería haber retrocedido tres pasos.");
    }

    @Test
    void descripcionCartaDeMovimientoJugadorHumano() {
        // Act
        carta.descripcionCartaDeMovimiento("vuelve tres pasos atrás", jugador);

        // Assert
        assertEquals(2, jugador.getPosicion(), "El jugador debería haber retrocedido tres pasos.");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            carta.descripcionCartaDeMovimiento("descripcion invalida", jugador);
        });
        assertTrue(exception.getMessage().contains("Descripcion de la carta no valida"), "Debería lanzar una excepción para descripciones inválidas.");
    }
}