package ar.edu.utn.frc.tup.lciii.Models.MenuPrincipal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EstancieroCargandoJuegoTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputStream;
    private EstancieroCargandoJuego estancieroCargandoJuego;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testConstructorVacio() {
        estancieroCargandoJuego = new EstancieroCargandoJuego();
        
        assertNotNull(estancieroCargandoJuego, "El objeto debe ser creado");
        assertEquals(0, estancieroCargandoJuego.getNumero(), "El número debe ser 0 por defecto");
        assertNull(estancieroCargandoJuego.getNombre(), "El nombre debe ser null por defecto");
        assertNull(estancieroCargandoJuego.getFecha(), "La fecha debe ser null por defecto");
    }

    @Test
    void testConstructorConParametros() {
        int numero = 1;
        String nombre = "Test Player";
        String fecha = "26/06/24";
        
        estancieroCargandoJuego = new EstancieroCargandoJuego(numero, nombre, fecha);
        
        assertEquals(numero, estancieroCargandoJuego.getNumero(), "El número debe coincidir");
        assertEquals(nombre, estancieroCargandoJuego.getNombre(), "El nombre debe coincidir");
        assertEquals(fecha, estancieroCargandoJuego.getFecha(), "La fecha debe coincidir");
    }

    @Test
    void testToString() {
        estancieroCargandoJuego = new EstancieroCargandoJuego(1, "Fran", "26/06/24");
        
        String resultado = estancieroCargandoJuego.toString();
        
        String esperado = "Numero de juego:1, Nombre:Fran, Fecha:26/06/24";
        assertEquals(esperado, resultado, "El toString debe formatear correctamente");
    }

    @Test
    void testToStringConValoresNulos() {
        estancieroCargandoJuego = new EstancieroCargandoJuego(0, null, null);
        
        String resultado = estancieroCargandoJuego.toString();
        
        String esperado = "Numero de juego:0, Nombre:null, Fecha:null";
        assertEquals(esperado, resultado, "El toString debe manejar valores null");
    }

    @Test
    void testCargarPartidas() {
        estancieroCargandoJuego = new EstancieroCargandoJuego();
        String nombrePartida = "Test Game";
        
        estancieroCargandoJuego.cargarPartidas(nombrePartida);
        
        String output = outputStream.toString();
        assertTrue(output.contains(nombrePartida), "Debe imprimir el nombre de la partida");
    }

    @Test
    void testCargarPartidasConNombreVacio() {
        estancieroCargandoJuego = new EstancieroCargandoJuego();
        String nombrePartida = "";
        
        estancieroCargandoJuego.cargarPartidas(nombrePartida);
        
        String output = outputStream.toString();
        assertTrue(output.trim().isEmpty() || output.equals(nombrePartida + System.lineSeparator()),
                  "Debe manejar nombres vacíos");
    }

    @Test
    void testMostrarPartidasGuardadas() {
        estancieroCargandoJuego = new EstancieroCargandoJuego();
        
        List<EstancieroCargandoJuego> partidas = estancieroCargandoJuego.mostrarPartidasGuardadas();
        
        assertNotNull(partidas, "La lista no debe ser null");
        assertEquals(7, partidas.size(), "Debe retornar 7 partidas predefinidas");
        
        EstancieroCargandoJuego primeraPartida = partidas.get(0);
        assertEquals(1, primeraPartida.getNumero(), "Primera partida debe tener número 1");
        assertEquals("Fran", primeraPartida.getNombre(), "Primera partida debe ser de Fran");
        assertEquals("26/06/24", primeraPartida.getFecha(), "Primera partida debe tener fecha 26/06/24");
    }

    @Test
    void testMostrarPartidasGuardadasContieneTodosLosDatos() {
        estancieroCargandoJuego = new EstancieroCargandoJuego();
        
        List<EstancieroCargandoJuego> partidas = estancieroCargandoJuego.mostrarPartidasGuardadas();
        
        String[] nombresEsperados = {"Fran", "Luciano", "Rafa", "Jonas", "Agus", "Gino", "Ismael"};
        
        for (int i = 0; i < nombresEsperados.length; i++) {
            EstancieroCargandoJuego partida = partidas.get(i);
            assertEquals(nombresEsperados[i], partida.getNombre(),
                        "El nombre en la posición " + i + " debe ser " + nombresEsperados[i]);
            assertEquals("26/06/24", partida.getFecha(),
                        "Todas las partidas deben tener la misma fecha");
        }
    }

    @Test
    void testGetNumero() {
        estancieroCargandoJuego = new EstancieroCargandoJuego(42, "Test", "01/01/25");
        
        assertEquals(42, estancieroCargandoJuego.getNumero(), "El getter debe retornar el número correcto");
    }

    @Test
    void testGetNombre() {
        estancieroCargandoJuego = new EstancieroCargandoJuego(1, "TestPlayer", "01/01/25");
        
        assertEquals("TestPlayer", estancieroCargandoJuego.getNombre(), "El getter debe retornar el nombre correcto");
    }

    @Test
    void testGetFecha() {
        estancieroCargandoJuego = new EstancieroCargandoJuego(1, "Test", "01/01/25");
        
        assertEquals("01/01/25", estancieroCargandoJuego.getFecha(), "El getter debe retornar la fecha correcta");
    }

    @Test
    void testSetNumero() {
        estancieroCargandoJuego = new EstancieroCargandoJuego();
        
        estancieroCargandoJuego.setNumero(100);
        
        assertEquals(100, estancieroCargandoJuego.getNumero(), "El setter debe establecer el número correctamente");
    }

    @Test
    void testSetNombre() {
        estancieroCargandoJuego = new EstancieroCargandoJuego();
        
        estancieroCargandoJuego.setNombre("NuevoNombre");
        
        assertEquals("NuevoNombre", estancieroCargandoJuego.getNombre(), "El setter debe establecer el nombre correctamente");
    }

    @Test
    void testSetFecha() {
        estancieroCargandoJuego = new EstancieroCargandoJuego();
        
        estancieroCargandoJuego.setFecha("31/12/25");
        
        assertEquals("31/12/25", estancieroCargandoJuego.getFecha(), "El setter debe establecer la fecha correctamente");
    }

    @Test
    void testSettersConValoresNull() {
        estancieroCargandoJuego = new EstancieroCargandoJuego(1, "Test", "01/01/25");
        
        estancieroCargandoJuego.setNombre(null);
        estancieroCargandoJuego.setFecha(null);
        
        assertNull(estancieroCargandoJuego.getNombre(), "Debe permitir establecer nombre como null");
        assertNull(estancieroCargandoJuego.getFecha(), "Debe permitir establecer fecha como null");
    }

    @Test
    void testEstancieroCargandoJuegoClassExists() {
        assertNotNull(EstancieroCargandoJuego.class, "La clase EstancieroCargandoJuego debe existir");
    }
}