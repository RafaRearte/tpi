package ar.edu.utn.frc.tup.lciii.Models.MenuPrincipal;

import ar.edu.utn.frc.tup.lciii.handlers.DataJuego;
import ar.edu.utn.frc.tup.lciii.services.GameReadyListener;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuTest {

    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private ByteArrayOutputStream outputStream;
    private Menu menu;
    
    @Mock
    private GameReadyListener gameReadyListenerMock;
    
    @Mock
    private JugadorPrincipal jugadorPrincipalMock;
    
    @Mock
    private DataJuego dataJuegoMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        menu = new Menu();
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void testCorrerMenuPrincipalConSalida() {
        String simulatedInput = "0\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        menu.correrMenuPrincipal();

        String output = outputStream.toString();
        assertTrue(output.contains("1. Cargar Juego"), "Debe mostrar opción cargar juego");
        assertTrue(output.contains("2. Nueva partida"), "Debe mostrar opción nueva partida");
        assertTrue(output.contains("3. Ver instrucciones"), "Debe mostrar opción ver instrucciones");
        assertTrue(output.contains("0. Salir"), "Debe mostrar opción salir");
        assertTrue(output.contains("Seleccionar una opcion:"), "Debe mostrar prompt de selección");
        assertTrue(output.contains("Saliendo del juego..."), "Debe mostrar mensaje de salida");
    }

    @Test
    void testCorrerMenuPrincipalConOpcionInvalida() {
        String simulatedInput = "9\n0\n"; // Opción inválida seguida de salir
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        menu.correrMenuPrincipal();

        String output = outputStream.toString();
        assertTrue(output.contains("Opcion no valida, por favor elija una opcion valida (1, 2, 3 o 0)."),
                  "Debe mostrar mensaje de opción inválida");
    }

    @Test
    void testCorrerMenuPrincipalConEntradaNoNumerica() {
        String simulatedInput = "abc\n0\n"; // Entrada no numérica seguida de salir
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        menu.correrMenuPrincipal();

        String output = outputStream.toString();
        assertTrue(output.contains("Opcion no valida. Por favor ingrese un numero."),
                  "Debe mostrar mensaje de entrada inválida");
    }

    @Test
    void testCorrerMenuPrincipalConInstrucciones() {
        String simulatedInput = "3\n\n0\n"; // Ver instrucciones, enter, salir
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        menu.correrMenuPrincipal();

        String output = outputStream.toString();
        assertTrue(output.contains("Instrucciones del juego de mesa El Estanciero."),
                  "Debe mostrar las instrucciones del juego");
    }

    @Test
    void testEmpezarNuevoJuego() {
        menu.setGameReadyListener(gameReadyListenerMock);
        
        menu.empezarNuevoJuego(jugadorPrincipalMock);


        assertTrue(menu.isJuegoListo(), "El juego debe estar marcado como listo");
        verify(jugadorPrincipalMock).cambiarEstrategia(any(EstancieroNuevoJuego.class));
    }

    @Test
    void testEmpezarNuevoJuegoSinListener() {

        assertDoesNotThrow(() -> {
            menu.empezarNuevoJuego(jugadorPrincipalMock);
        }, "No debe lanzar excepción cuando no hay listener");
        
        assertTrue(menu.isJuegoListo(), "El juego debe estar marcado como listo");
    }

    @Test
    void testMensajeBienvenida() {
        menu.mensajeBienvenida();

        String output = outputStream.toString();
        assertTrue(output.contains("Bienvenido a 'El Estanciero'!"),
                  "Debe mostrar el mensaje de bienvenida correcto");
    }

    @Test
    void testGetGameReadyListener() {
        menu.setGameReadyListener(gameReadyListenerMock);
        
        assertEquals(gameReadyListenerMock, menu.getGameReadyListener(),
                    "El getter debe retornar el listener correcto");
    }

    @Test
    void testIsJuegoListo() {
        assertFalse(menu.isJuegoListo(), "Inicialmente el juego no debe estar listo");
        
        menu.setJuegoListo(true);
        
        assertTrue(menu.isJuegoListo(), "El juego debe estar marcado como listo");
    }

    @Test
    void testSetGameReadyListener() {
        menu.setGameReadyListener(gameReadyListenerMock);
        
        assertEquals(gameReadyListenerMock, menu.getGameReadyListener(),
                    "El setter debe establecer el listener correctamente");
    }

    @Test
    void testSetJuegoListo() {
        assertFalse(menu.isJuegoListo(), "Inicialmente debe ser false");
        
        menu.setJuegoListo(true);
        
        assertTrue(menu.isJuegoListo(), "Debe cambiar a true");
        
        menu.setJuegoListo(false);
        
        assertFalse(menu.isJuegoListo(), "Debe cambiar a false nuevamente");
    }

    @Test
    void testCorrerMenuPrincipalSinExcepciones() {
        String simulatedInput = "0\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        assertDoesNotThrow(() -> {
            menu.correrMenuPrincipal();
        }, "El método no debería lanzar excepciones");
    }

    @Test
    void testMenuClassExists() {
        assertNotNull(Menu.class, "La clase Menu debe existir");
    }

    @Test
    void testEstadoInicialMenu() {
        assertNull(menu.getGameReadyListener(), "El listener inicial debe ser null");
        assertFalse(menu.isJuegoListo(), "El juego inicialmente no debe estar listo");
    }

    @Test
    void testConstantesMenu() {
        String simulatedInput = "1\nx\n0\n"; // Cargar juego, x para salir, luego salir del menú
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        menu.correrMenuPrincipal();

        String output = outputStream.toString();
        assertTrue(output.contains("Juegos guardados."), "Debe ejecutar la opción cargar juego");
    }
}