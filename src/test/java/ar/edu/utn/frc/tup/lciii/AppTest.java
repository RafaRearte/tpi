package ar.edu.utn.frc.tup.lciii;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void testMainSinExcepciones() {
        String simulatedInput = "0\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        assertDoesNotThrow(() -> {
            App.main(new String[]{});
        });
    }

    @Test
    void testSalidaMenu() {
        String simulatedInput = "0\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        App.main(new String[]{});

        String output = outputStream.toString();
        assertFalse(output.isEmpty(), "La aplicación debería producir alguna salida");
        assertTrue(output.contains("--------------------------------------------------------------"), 
                  "La salida debería contener el separador de bienvenida");
    }

    @Test
    void testAppExiste() {
        assertNotNull(App.class, "La clase App debe existir");
    }
}
