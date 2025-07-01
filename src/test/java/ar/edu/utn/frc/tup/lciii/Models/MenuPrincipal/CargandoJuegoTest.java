package ar.edu.utn.frc.tup.lciii.Models.MenuPrincipal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CargandoJuegoTest {

    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private ByteArrayOutputStream outputStream;
    private CargandoJuego cargandoJuego;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        cargandoJuego = new CargandoJuego();
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void testAjustarMenuPrincipalConSalidaX() {
        String simulatedInput = "x\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        cargandoJuego.ajustarMenuPrincipal();

        String output = outputStream.toString();
        assertTrue(output.contains("Juegos guardados."), "Debería mostrar el título");
        assertTrue(output.contains("-------------------"), "Debería mostrar separadores");
        assertTrue(output.contains("Regresando al menu principal..."), "Debería mostrar mensaje de regreso");
    }

    @Test
    void testAjustarMenuPrincipalConSeleccionValida() {
        String simulatedInput = "1\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        cargandoJuego.ajustarMenuPrincipal();

        String output = outputStream.toString();
        assertTrue(output.contains("Juegos guardados."), "Debería mostrar el título");
        assertTrue(output.contains("Fran"), "Debería mostrar las partidas guardadas");
        assertTrue(output.contains("Load game:"), "Debería mostrar mensaje de carga");
    }

    @Test
    void testAjustarMenuPrincipalConNumeroInvalido() {
        String simulatedInput = "10\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        cargandoJuego.ajustarMenuPrincipal();

        String output = outputStream.toString();
        assertTrue(output.contains("Opcion no valida"), "Debería mostrar mensaje de opción inválida");
    }

    @Test
    void testAjustarMenuPrincipalConEntradaNoNumerica() {
        String simulatedInput = "abc\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        cargandoJuego.ajustarMenuPrincipal();

        String output = outputStream.toString();
        assertTrue(output.contains("Ingreso no valido"), "Debería mostrar mensaje de ingreso inválido");
    }

    @Test
    void testAjustarMenuPrincipalMuestraPartidasGuardadas() {
        String simulatedInput = "x\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        cargandoJuego.ajustarMenuPrincipal();

        String output = outputStream.toString();
        assertTrue(output.contains("Fran"), "Debería mostrar partida de Fran");
        assertTrue(output.contains("Luciano"), "Debería mostrar partida de Luciano");
        assertTrue(output.contains("26/06/24"), "Debería mostrar las fechas");
    }

    @Test
    void testAjustarMenuPrincipalSinExcepciones() {
        String simulatedInput = "x\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        assertDoesNotThrow(() -> {
            cargandoJuego.ajustarMenuPrincipal();
        }, "El método no debería lanzar excepciones");
    }

    @Test
    void testCargandoJuegoImplementaStrategy() {
        assertTrue(cargandoJuego instanceof ar.edu.utn.frc.tup.lciii.services.strategy.MenuPrincipalStrategy,
                "CargandoJuego debe implementar MenuPrincipalStrategy");
    }

    @Test
    void testCargandoJuegoClassExists() {
        assertNotNull(CargandoJuego.class, "La clase CargandoJuego debe existir");
    }
}