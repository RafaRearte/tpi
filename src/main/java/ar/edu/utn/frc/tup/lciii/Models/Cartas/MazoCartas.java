package ar.edu.utn.frc.tup.lciii.Models.Cartas;

import ar.edu.utn.frc.tup.lciii.Models.Banco;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.Stack;

@Setter
@Getter
public class MazoCartas {

    private final Stack<Carta> mazoSuerte;
    private final Stack<Carta> mazoDestino;
    private Jugador jugador;private Banco banco;


    public MazoCartas() {
        this.mazoSuerte = new Stack<>();
        this.mazoDestino = new Stack<>();
    }

    public void aniadirCarta(Carta carta) {
        if (carta.getCartaTipo() == CartaTipo.SUERTE) {
            mazoSuerte.add(carta);
        } else if (carta.getCartaTipo() == CartaTipo.DESTINO) {
            mazoDestino.add(carta);
        }
    }

    public Carta sacarCartaDeSuerte() {
        return mazoVacio(mazoSuerte) ? null : mazoSuerte.pop();
    }

    public Carta sacarCartaDeDestino() {
        return mazoVacio(mazoDestino) ? null : mazoDestino.pop();
    }

    public void mezclarMazoDeSuerteDestino(Stack<Carta> tipoCarta) {
        Collections.shuffle(tipoCarta);
    }

    public void mezclarMazoCompleto() {
        Collections.shuffle(mazoSuerte);
        Collections.shuffle(mazoDestino);
    }

    public boolean mazoVacio(Stack<Carta> tipoCarta) {
        return tipoCarta.isEmpty();
    }

    public boolean mazoCompletoVacio() {
        return mazoSuerte.isEmpty() && mazoDestino.isEmpty();
    }

    public void ejecutarAccionSuerte(Jugador jugador) {
        Carta carta = sacarCartaDeSuerte();
        if (carta != null) {
            carta.verCarta();
            carta.ejecutarAccion(jugador);
        }
        else {
            System.out.println("No hay cartas de suerte");
        }
    }

    public void ejecutarAccionDestino(Jugador jugador) {
        Carta carta = sacarCartaDeDestino();
        if (carta != null) {
            carta.verCarta();
            carta.ejecutarAccion(jugador);
        } else {
            System.out.println("No hay cartas de destino");
        }
    }

    public void inicializarMazo() {
        Stack<Carta> listaSuerte = new Stack<>();
        Stack<Carta> listaDestino = new Stack<>();

        //Cartas suerte de movimientos
        listaSuerte.push(new MovimientoCarta(1, CartaTipo.SUERTE, "Vuelve 3 paso atrás"));
        listaSuerte.push(new MovimientoCarta(2, CartaTipo.SUERTE, "Siga hasta Santa Fe, zona norte si pasa por la salida cobre 5000 "));
        listaSuerte.push(new MovimientoCarta(3, CartaTipo.SUERTE, "Siga hasta Salta, zona norte si pasa por la salida cobre 5000"));
        listaSuerte.push(new MovimientoCarta(4, CartaTipo.SUERTE, "Siga hasta Salta, zona norte si pasa por la salida cobre 5000"));//Todo verificar que si este 2 veces en el mazo de cartas reales
        listaSuerte.push(new MovimientoCarta(5, CartaTipo.SUERTE, "Siga hasta Buenos Aires, zona norte "));
        listaSuerte.push(new MovimientoCarta(6, CartaTipo.SUERTE, "Siga hasta la salida "));
        listaSuerte.push(new MovimientoCarta(7, CartaTipo.SUERTE, "Haga un paseo hasta la bodega si pasa por la salida cobre 5000"));

        //cartas suerte de recibir dinero
        listaSuerte.push(new CartaRecibeDinero(8, CartaTipo.SUERTE, "Cobre $1000 por intereses bancarios"));
        listaSuerte.push(new CartaRecibeDinero(9, CartaTipo.SUERTE, "Ha ganado lo grande cobre $10000"));
        listaSuerte.push(new CartaRecibeDinero(10, CartaTipo.SUERTE, "Gano en las carreras cobre $3000"));

        //tod*o agregar el metodo de cardReceivemoney a en penaltyCards modificando para su uso
        //cartas de suerte para descontar dinero
        listaSuerte.push(new PenalidadCarta(11, CartaTipo.SUERTE, "Pague $3000 por los gastos colegiales "));
        listaSuerte.push(new PenalidadCarta(12, CartaTipo.SUERTE, "Por multa de exceso de velocidad pague $3000 "));
        listaSuerte.push(new PenalidadCarta(13, CartaTipo.SUERTE, "Multa caminera pague $400 "));
        listaSuerte.push(new PenalidadCarta(14, CartaTipo.SUERTE, "Por compra de semillas pague al Banco $800 por cada chakra y $400 por cada estancia "));
        listaSuerte.push(new PenalidadCarta(15, CartaTipo.SUERTE, "Sus propiedades tienen que ser reparadas pague al Banco $500 por cada chacra $2500 por cada estancia  "));

        // Cartas especiales de suerte

        listaSuerte.push(new CartaEspecial(16, CartaTipo.SUERTE, "Habeas Corpus concedido. Con esta tarjeta sale usted gratuitamente de la comisaría. consérvela o véndala." ));

        listaSuerte.push(new CartaEspecial(17, CartaTipo.SUERTE, "Marché preso directamente."));

        // Cartas destino de movimientos

        listaDestino.push(new MovimientoCarta(18, CartaTipo.DESTINO, "vuelve hasta Formosa zona sur."));
        listaDestino.push(new MovimientoCarta(19, CartaTipo.DESTINO, "siga hasta la salida."));

        // Cartas destino de recibir dinero

        listaDestino.push(new CartaRecibeDinero(20, CartaTipo.DESTINO, "Ha ganado un concurso agrícola cobre $2.000."));
        listaDestino.push(new CartaRecibeDinero(21, CartaTipo.DESTINO, "Hereda $2000."));
        listaDestino.push(new CartaRecibeDinero(22, CartaTipo.DESTINO, "Error en cálculos del Banco cobre $4000."));
        listaDestino.push(new CartaRecibeDinero(23, CartaTipo.DESTINO, "Por venta de acciones cobre $1000."));
        listaDestino.push(new CartaRecibeDinero(24, CartaTipo.DESTINO, "Ha obtenido un segundo premio de belleza cobre $2000."));
        listaDestino.push(new CartaRecibeDinero(25, CartaTipo.DESTINO, "5% de intereses sobre cédula hipotecarias cobre $2000."));
        listaDestino.push(new CartaRecibeDinero(26, CartaTipo.DESTINO, "Ha ganado un concurso agrícola cobre $2000."));

        listaDestino.push(new CartaRecibeDinero(27, CartaTipo.DESTINO, "En su cumpleaños cobre $200 de cada los jugadores."));

        // Cartas de penalización de destino
        listaDestino.push(new PenalidadCarta(28, CartaTipo.DESTINO, "Devolución de impuestos cobre $400." ));
        listaDestino.push(new PenalidadCarta(29, CartaTipo.DESTINO, "Gastos de farmacia pague $1000." ));
        listaDestino.push(new PenalidadCarta(30, CartaTipo.DESTINO, "Pague y su póliza de seguro en $1000."));

        // Cartas especiales de destino
        listaDestino.push(new CartaEspecial(31, CartaTipo.DESTINO, "Pague 200 más y Levante una tarjeta de suerte." ));
        listaDestino.push(new CartaEspecial(32, CartaTipo.DESTINO, "Marché preso directamente." ));
        listaDestino.push(new CartaEspecial(33, CartaTipo.DESTINO, "Con esta tarjeta sale usted de la comisaría. consérvela hasta utilizarla o véndala." ));


        for (Carta card : listaSuerte) {
            aniadirCarta(card);

        }
        for (Carta card2 : listaDestino) {
            aniadirCarta(card2);
        }

        mezclarMazoCompleto();
    }
}
