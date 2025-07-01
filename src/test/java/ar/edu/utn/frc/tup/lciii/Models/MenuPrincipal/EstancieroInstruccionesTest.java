package ar.edu.utn.frc.tup.lciii.Models.MenuPrincipal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class EstancieroInstruccionesTest {

    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private ByteArrayOutputStream outputStream;
    private EstancieroInstrucciones estancieroInstrucciones;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        estancieroInstrucciones = new EstancieroInstrucciones();
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void testAjustarMenuPrincipalConEnterVacio() {
        // Arrange
        String simulatedInput = "\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        // Act
        estancieroInstrucciones.ajustarMenuPrincipal();

        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("Instrucciones del juego de mesa El Estanciero."), 
                  "Debería mostrar el título de las instrucciones");
        assertTrue(output.contains("Onjetivo del juego:"), 
                  "Debería mostrar el objetivo del juego");
        assertTrue(output.contains("Presione enter para volver al menu principal"), 
                  "Debería mostrar el mensaje para volver al menú");
    }

    @Test
    void testMuestraObjetivoDelJuego() {
        // Arrange
        String simulatedInput = "\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        // Act
        estancieroInstrucciones.ajustarMenuPrincipal();

        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("Sé el último jugador en pie después de que todos los demás hayan quedado en bancarrota"),
                  "Debería mostrar el objetivo del juego");
    }

    @Test
    void testMuestraSeccionSetup() {
        // Arrange
        String simulatedInput = "\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        // Act
        estancieroInstrucciones.ajustarMenuPrincipal();

        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("Setup:"), "Debería mostrar la sección Setup");
        assertTrue(output.contains("Cada jugador elige una ficha"), 
                  "Debería mostrar instrucciones de setup");
    }

    @Test
    void testMuestraDesarrolloDelJuego() {
        // Arrange
        String simulatedInput = "\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        // Act
        estancieroInstrucciones.ajustarMenuPrincipal();

        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("Desarrollo del juego:"), 
                  "Debería mostrar la sección de desarrollo del juego");
        assertTrue(output.contains("Los jugadores tiran los dados"), 
                  "Debería mostrar reglas del desarrollo");
    }

    @Test
    void testMuestraPropiedades() {
        // Arrange
        String simulatedInput = "\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        // Act
        estancieroInstrucciones.ajustarMenuPrincipal();

        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("Propiedades:"), "Debería mostrar la sección de propiedades");
        assertTrue(output.contains("Los jugadores pueden comprar propiedades"), 
                  "Debería mostrar reglas de propiedades");
    }

    @Test
    void testMuestraAlquiler() {
        // Arrange
        String simulatedInput = "\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        // Act
        estancieroInstrucciones.ajustarMenuPrincipal();

        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("Alquiler:"), "Debería mostrar la sección de alquiler");
        assertTrue(output.contains("Los jugadores deben pagar alquiler"), 
                  "Debería mostrar reglas de alquiler");
    }

    @Test
    void testMuestraBancarrota() {
        // Arrange
        String simulatedInput = "\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        // Act
        estancieroInstrucciones.ajustarMenuPrincipal();

        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("Bancarrota:"), "Debería mostrar la sección de bancarrota");
        assertTrue(output.contains("Un jugador entra en bancarrota"), 
                  "Debería mostrar reglas de bancarrota");
    }

    @Test
    void testMuestraFinDelJuego() {
        // Arrange
        String simulatedInput = "\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        // Act
        estancieroInstrucciones.ajustarMenuPrincipal();

        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("Fin del juego:"), "Debería mostrar la sección fin del juego");
        assertTrue(output.contains("El juego termina cuando solo queda un jugador"), 
                  "Debería mostrar condición de fin del juego");
    }

    @Test
    void testMuestraConsejos() {
        // Arrange
        String simulatedInput = "\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        // Act
        estancieroInstrucciones.ajustarMenuPrincipal();

        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("Consejos:"), "Debería mostrar la sección de consejos");
        assertTrue(output.contains("Administrá bien tu dinero"), 
                  "Debería mostrar consejos del juego");
    }

    @Test
    void testMuestraMensajeFinal() {
        // Arrange
        String simulatedInput = "\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        // Act
        estancieroInstrucciones.ajustarMenuPrincipal();

        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("¡Divertite jugando a El Estanciero!"), 
                  "Debería mostrar el mensaje final");
        assertTrue(output.contains("-------------------------------------------------------------------------------"), 
                  "Debería mostrar el separador");
    }

    @Test
    void testAjustarMenuPrincipalSinExcepciones() {
        // Arrange
        String simulatedInput = "\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        // Act & Assert
        assertDoesNotThrow(() -> {
            estancieroInstrucciones.ajustarMenuPrincipal();
        }, "El método no debería lanzar excepciones");
    }

    @Test
    void testEstancieroInstruccionesImplementaStrategy() {
        // Test básico para verificar que implementa la interfaz correcta
        assertTrue(estancieroInstrucciones instanceof ar.edu.utn.frc.tup.lciii.services.strategy.MenuPrincipalStrategy,
                "EstancieroInstrucciones debe implementar MenuPrincipalStrategy");
    }

    @Test
    void testEstancieroInstruccionesClassExists() {
        // Test básico para verificar que la clase existe
        assertNotNull(EstancieroInstrucciones.class, "La clase EstancieroInstrucciones debe existir");
    }
}