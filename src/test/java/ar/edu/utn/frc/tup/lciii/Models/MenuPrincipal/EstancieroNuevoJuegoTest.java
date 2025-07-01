package ar.edu.utn.frc.tup.lciii.Models.MenuPrincipal;

import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
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
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EstancieroNuevoJuegoTest {

    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private ByteArrayOutputStream outputStream;
    private EstancieroNuevoJuego estancieroNuevoJuego;
    
    @Mock
    private GameReadyListener gameReadyListenerMock;
    
    @Mock
    private Jugador jugadorMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void testConstructorConGameReadyListener() {
        estancieroNuevoJuego = new EstancieroNuevoJuego(gameReadyListenerMock);
        
        assertNotNull(estancieroNuevoJuego, "El objeto debe ser creado");
        assertEquals(gameReadyListenerMock, estancieroNuevoJuego.getGameReadyListener(), 
                    "El GameReadyListener debe coincidir");
        assertNotNull(estancieroNuevoJuego.getJugador(), "El jugador debe ser inicializado");
        assertNotNull(estancieroNuevoJuego.getScanner(), "El scanner debe ser inicializado");
    }

    @Test
    void testListoParaEmpezarConRespuestaAfirmativa() {
        estancieroNuevoJuego = new EstancieroNuevoJuego(gameReadyListenerMock);
        String simulatedInput = "y\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        estancieroNuevoJuego.setScanner(new Scanner(System.in));

        Boolean resultado = estancieroNuevoJuego.listoParaEmpezar();

        assertTrue(resultado, "Debe retornar true cuando la respuesta es 'y'");
        String output = outputStream.toString();
        assertTrue(output.contains("Esta listo para empezar a jugar? (y/n)"), 
                  "Debe mostrar la pregunta");
    }

    @Test
    void testListoParaEmpezarConRespuestaNegativa() {
        estancieroNuevoJuego = new EstancieroNuevoJuego(gameReadyListenerMock);
        String simulatedInput = "n\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        estancieroNuevoJuego.setScanner(new Scanner(System.in));

        Boolean resultado = estancieroNuevoJuego.listoParaEmpezar();

        assertFalse(resultado, "Debe retornar false cuando la respuesta es 'n'");
    }

    @Test
    void testListoParaEmpezarConRespuestaInvalidaYLuegoValida() {
        estancieroNuevoJuego = new EstancieroNuevoJuego(gameReadyListenerMock);
        String simulatedInput = "x\ny\n"; // Primera respuesta inválida, segunda válida
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        estancieroNuevoJuego.setScanner(new Scanner(System.in));

        Boolean resultado = estancieroNuevoJuego.listoParaEmpezar();

        assertTrue(resultado, "Debe retornar true después de respuesta válida");
        String output = outputStream.toString();
        assertTrue(output.contains("Respuesta no valida"), 
                  "Debe mostrar mensaje de error para respuesta inválida");
    }

    @Test
    void testGetJugador() {
        estancieroNuevoJuego = new EstancieroNuevoJuego(gameReadyListenerMock);
        
        assertNotNull(estancieroNuevoJuego.getJugador(), "El getter debe retornar el jugador");
    }

    @Test
    void testGetTipoDeObjetivo() {
        estancieroNuevoJuego = new EstancieroNuevoJuego(gameReadyListenerMock);
        estancieroNuevoJuego.setTipoDeObjetivo("Test Objetivo");
        
        assertEquals("Test Objetivo", estancieroNuevoJuego.getTipoDeObjetivo(), 
                    "El getter debe retornar el tipo de objetivo correcto");
    }

    @Test
    void testGetMontoGanador() {
        estancieroNuevoJuego = new EstancieroNuevoJuego(gameReadyListenerMock);
        estancieroNuevoJuego.setMontoGanador(100000);
        
        assertEquals(100000, estancieroNuevoJuego.getMontoGanador(), 
                    "El getter debe retornar el monto ganador correcto");
    }

    @Test
    void testGetDificultad() {
        estancieroNuevoJuego = new EstancieroNuevoJuego(gameReadyListenerMock);
        estancieroNuevoJuego.setDificultad("FACIL");
        
        assertEquals("FACIL", estancieroNuevoJuego.getDificultad(), 
                    "El getter debe retornar la dificultad correcta");
    }

    @Test
    void testGetScanner() {
        estancieroNuevoJuego = new EstancieroNuevoJuego(gameReadyListenerMock);
        
        assertNotNull(estancieroNuevoJuego.getScanner(), 
                     "El getter debe retornar el scanner");
    }

    @Test
    void testGetGameReadyListener() {
        estancieroNuevoJuego = new EstancieroNuevoJuego(gameReadyListenerMock);
        
        assertEquals(gameReadyListenerMock, estancieroNuevoJuego.getGameReadyListener(), 
                    "El getter debe retornar el GameReadyListener correcto");
    }

    @Test
    void testSetJugador() {
        estancieroNuevoJuego = new EstancieroNuevoJuego(gameReadyListenerMock);
        
        estancieroNuevoJuego.setJugador(jugadorMock);
        
        assertEquals(jugadorMock, estancieroNuevoJuego.getJugador(), 
                    "El setter debe establecer el jugador correctamente");
    }

    @Test
    void testSetTipoDeObjetivo() {
        estancieroNuevoJuego = new EstancieroNuevoJuego(gameReadyListenerMock);
        
        estancieroNuevoJuego.setTipoDeObjetivo("Nuevo Objetivo");
        
        assertEquals("Nuevo Objetivo", estancieroNuevoJuego.getTipoDeObjetivo(), 
                    "El setter debe establecer el tipo de objetivo correctamente");
    }

    @Test
    void testSetMontoGanador() {
        estancieroNuevoJuego = new EstancieroNuevoJuego(gameReadyListenerMock);
        
        estancieroNuevoJuego.setMontoGanador(200000);
        
        assertEquals(200000, estancieroNuevoJuego.getMontoGanador(), 
                    "El setter debe establecer el monto ganador correctamente");
    }

    @Test
    void testSetDificultad() {
        estancieroNuevoJuego = new EstancieroNuevoJuego(gameReadyListenerMock);
        
        estancieroNuevoJuego.setDificultad("DIFICIL");
        
        assertEquals("DIFICIL", estancieroNuevoJuego.getDificultad(), 
                    "El setter debe establecer la dificultad correctamente");
    }

    @Test
    void testSetScanner() {
        estancieroNuevoJuego = new EstancieroNuevoJuego(gameReadyListenerMock);
        Scanner nuevoScanner = new Scanner("test");
        
        estancieroNuevoJuego.setScanner(nuevoScanner);
        
        assertEquals(nuevoScanner, estancieroNuevoJuego.getScanner(), 
                    "El setter debe establecer el scanner correctamente");
    }

    @Test
    void testSetGameReadyListener() {
        estancieroNuevoJuego = new EstancieroNuevoJuego(gameReadyListenerMock);
        GameReadyListener nuevoListener = mock(GameReadyListener.class);
        
        estancieroNuevoJuego.setGameReadyListener(nuevoListener);
        
        assertEquals(nuevoListener, estancieroNuevoJuego.getGameReadyListener(), 
                    "El setter debe establecer el GameReadyListener correctamente");
    }

    @Test
    void testEstancieroNuevoJuegoImplementaStrategy() {
        estancieroNuevoJuego = new EstancieroNuevoJuego(gameReadyListenerMock);
        
        assertTrue(estancieroNuevoJuego instanceof ar.edu.utn.frc.tup.lciii.services.strategy.MenuPrincipalStrategy,
                "EstancieroNuevoJuego debe implementar MenuPrincipalStrategy");
    }

    @Test
    void testEstancieroNuevoJuegoClassExists() {
        assertNotNull(EstancieroNuevoJuego.class, "La clase EstancieroNuevoJuego debe existir");
    }

    @Test
    void testListoParaEmpezarSinExcepciones() {
        estancieroNuevoJuego = new EstancieroNuevoJuego(gameReadyListenerMock);
        String simulatedInput = "y\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        estancieroNuevoJuego.setScanner(new Scanner(System.in));

        assertDoesNotThrow(() -> {
            estancieroNuevoJuego.listoParaEmpezar();
        }, "listoParaEmpezar no debería lanzar excepciones");
    }
}