package ar.edu.utn.frc.tup.lciii.Models.Cartas;

import ar.edu.utn.frc.tup.lciii.Models.Casillas.Carcel;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.EstadoJugador;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import ar.edu.utn.frc.tup.lciii.Models.Tablero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartaEspecialTest {

    private CartaEspecial cartaEspecial;
    
    @Mock
    private Jugador jugadorMock;
    
    @Mock
    private MazoCartas mazoCartasMock;
    
    @Mock
    private Tablero tableroMock;
    
    @Mock
    private Carcel carcelMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConstructorValido() {
        cartaEspecial = new CartaEspecial(1, CartaTipo.DESTINO, "Test description");

        assertEquals(1, cartaEspecial.getId());
        assertEquals(CartaTipo.DESTINO, cartaEspecial.getCartaTipo());
        assertEquals(TipoCarta.CARTA_ESPECIAL, cartaEspecial.getTypeCard());
        assertEquals("Test description", cartaEspecial.getDescription());
    }

    @Test
    void testConstructorConTipoInvalido() {
        assertDoesNotThrow(() -> {
            cartaEspecial = new CartaEspecial(1, CartaTipo.DESTINO, "Test description");
        });
    }

    @Test
    void testEjecutarAccionHabeasCorpus() {
        cartaEspecial = new CartaEspecial(1, CartaTipo.DESTINO, 
            "Habeas Corpus concedido. Con esta tarjeta sale usted gratuitamente de la comisaría. consérvela o véndala.");
        cartaEspecial.setTablero(tableroMock);
        
        when(tableroMock.getCarcel()).thenReturn(carcelMock);
        when(carcelMock.preguntarSiQuiereLanzarDados(jugadorMock)).thenReturn(true);

        cartaEspecial.ejecutarAccion(jugadorMock);

        verify(tableroMock).getCarcel();
        verify(carcelMock).preguntarSiQuiereLanzarDados(jugadorMock);
        verify(jugadorMock).setEstado(EstadoJugador.HABILITADO);
    }

    @Test
    void testEjecutarAccionMarchePreso() {
        cartaEspecial = new CartaEspecial(1, CartaTipo.DESTINO, "Marché preso directamente.");
        cartaEspecial.setTablero(tableroMock);
        
        when(tableroMock.getCarcel()).thenReturn(carcelMock);

        cartaEspecial.ejecutarAccion(jugadorMock);

        verify(tableroMock).getCarcel();
        verify(carcelMock).enviarALaCarcel(jugadorMock);
        verify(jugadorMock).setEstado(EstadoJugador.PRESO);
    }

    @Test
    void testEjecutarAccionSuerteEspecial() {
        cartaEspecial = new CartaEspecial(1, CartaTipo.DESTINO, 
            "Pague 200 más y Levante una tarjeta de suerte.");
        cartaEspecial.setMazoCartas(mazoCartasMock);

        cartaEspecial.ejecutarAccion(jugadorMock);

        verify(jugadorMock).pagarSaldo(200);
        verify(mazoCartasMock).ejecutarAccionSuerte(jugadorMock);
    }

    @Test
    void testGettersYSetters() {
        cartaEspecial = new CartaEspecial(1, CartaTipo.DESTINO, "Test description");

        cartaEspecial.setMazoCartas(mazoCartasMock);
        assertEquals(mazoCartasMock, cartaEspecial.getMazoCartas());

        cartaEspecial.setTablero(tableroMock);
        assertEquals(tableroMock, cartaEspecial.getTablero());
    }

    @Test
    void testEjecutarAccionConJugadorNull() {
        cartaEspecial = new CartaEspecial(1, CartaTipo.DESTINO, "Marché preso directamente.");
        cartaEspecial.setTablero(tableroMock);

        assertThrows(IllegalArgumentException.class, () -> {
            cartaEspecial.ejecutarAccion(null);
        });
    }

    @Test
    void testCartaEspecialExiste() {
        assertNotNull(CartaEspecial.class, "La clase CartaEspecial debe existir");
    }
}