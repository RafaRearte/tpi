package ar.edu.utn.frc.tup.lciii.Models.MenuPrincipal;

import ar.edu.utn.frc.tup.lciii.services.strategy.MenuPrincipalStrategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JugadorPrincipalTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputStream;
    private JugadorPrincipal jugadorPrincipal;
    
    @Mock
    private MenuPrincipalStrategy estrategiaMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testConstructorVacio() {
        jugadorPrincipal = new JugadorPrincipal();
        
        assertNotNull(jugadorPrincipal, "El jugador principal debe ser creado");
        assertNull(jugadorPrincipal.getMenuPrincipalStrategy(), 
                  "La estrategia debe ser null cuando se usa el constructor vacío");
    }

    @Test
    void testConstructorConEstrategia() {
        jugadorPrincipal = new JugadorPrincipal(estrategiaMock);
        
        assertNotNull(jugadorPrincipal, "El jugador principal debe ser creado");
        assertEquals(estrategiaMock, jugadorPrincipal.getMenuPrincipalStrategy(),
                    "La estrategia debe ser la pasada en el constructor");
    }

    @Test
    void testCambiarEstrategia() {
        jugadorPrincipal = new JugadorPrincipal();
        
        jugadorPrincipal.cambiarEstrategia(estrategiaMock);
        
        assertEquals(estrategiaMock, jugadorPrincipal.getMenuPrincipalStrategy(),
                    "La estrategia debe cambiar correctamente");
    }

    @Test
    void testCambiarEstrategiaNulaANoNula() {
        jugadorPrincipal = new JugadorPrincipal();
        assertNull(jugadorPrincipal.getMenuPrincipalStrategy());
        
        jugadorPrincipal.cambiarEstrategia(estrategiaMock);
        
        assertEquals(estrategiaMock, jugadorPrincipal.getMenuPrincipalStrategy(),
                    "Debe poder cambiar de estrategia null a una estrategia válida");
    }

    @Test
    void testAdjustMainEstancieroConEstrategiaValida() {
        jugadorPrincipal = new JugadorPrincipal(estrategiaMock);
        
        jugadorPrincipal.adjustMainEstanciero();
        
        verify(estrategiaMock, times(1)).ajustarMenuPrincipal();
    }

    @Test
    void testAdjustMainEstancieroConEstrategiaNula() {
        jugadorPrincipal = new JugadorPrincipal();
        
        jugadorPrincipal.adjustMainEstanciero();
        
        String output = outputStream.toString();
        assertTrue(output.contains("No se ha definido una estrategia para el menu principal."),
                  "Debe mostrar mensaje cuando no hay estrategia definida");
    }

    @Test
    void testAdjustMainEstancieroImprimeSaltoDeLinea() {
        jugadorPrincipal = new JugadorPrincipal(estrategiaMock);
        
        jugadorPrincipal.adjustMainEstanciero();
        
        String output = outputStream.toString();
        assertTrue(output.startsWith(System.lineSeparator()) || output.startsWith("\n"),
                  "Debe imprimir un salto de línea antes de ejecutar la estrategia");
        verify(estrategiaMock, times(1)).ajustarMenuPrincipal();
    }

    @Test
    void testGetMenuPrincipalStrategy() {
        jugadorPrincipal = new JugadorPrincipal(estrategiaMock);
        
        assertEquals(estrategiaMock, jugadorPrincipal.getMenuPrincipalStrategy(),
                    "El getter debe retornar la estrategia correcta");
    }

    @Test
    void testSetMenuPrincipalStrategy() {
        jugadorPrincipal = new JugadorPrincipal();
        
        jugadorPrincipal.setMenuPrincipalStrategy(estrategiaMock);
        
        assertEquals(estrategiaMock, jugadorPrincipal.getMenuPrincipalStrategy(),
                    "El setter debe establecer la estrategia correctamente");
    }

    @Test
    void testSetMenuPrincipalStrategyConNull() {
        jugadorPrincipal = new JugadorPrincipal(estrategiaMock);
        assertNotNull(jugadorPrincipal.getMenuPrincipalStrategy());
        
        jugadorPrincipal.setMenuPrincipalStrategy(null);
        
        assertNull(jugadorPrincipal.getMenuPrincipalStrategy(),
                  "Debe permitir establecer la estrategia como null");
    }

    @Test
    void testAdjustMainEstancieroSinExcepciones() {
        jugadorPrincipal = new JugadorPrincipal(estrategiaMock);
        
        assertDoesNotThrow(() -> {
            jugadorPrincipal.adjustMainEstanciero();
        }, "adjustMainEstanciero no debería lanzar excepciones");
    }

    @Test
    void testJugadorPrincipalClassExists() {
        assertNotNull(JugadorPrincipal.class, "La clase JugadorPrincipal debe existir");
    }

    @Test
    void testCambiarEstrategiaMultiplesVeces() {
        jugadorPrincipal = new JugadorPrincipal();
        MenuPrincipalStrategy segundaEstrategia = mock(MenuPrincipalStrategy.class);
        
        jugadorPrincipal.cambiarEstrategia(estrategiaMock);
        assertEquals(estrategiaMock, jugadorPrincipal.getMenuPrincipalStrategy());
        
        jugadorPrincipal.cambiarEstrategia(segundaEstrategia);
        
        assertEquals(segundaEstrategia, jugadorPrincipal.getMenuPrincipalStrategy(),
                    "Debe permitir cambiar la estrategia múltiples veces");
    }
}