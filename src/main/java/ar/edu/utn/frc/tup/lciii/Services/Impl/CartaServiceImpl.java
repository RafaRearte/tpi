package ar.edu.utn.frc.tup.lciii.Services.Impl;

import ar.edu.utn.frc.tup.lciii.Models.Carta;
import ar.edu.utn.frc.tup.lciii.Models.MazoCartas;
import ar.edu.utn.frc.tup.lciii.Models.TipoDeCarta;
import ar.edu.utn.frc.tup.lciii.Services.CartaService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Fixme Revisar si los cambios estan bien

public class CartaServiceImpl implements CartaService {

    @Override
    public MazoCartas crearMazo(TipoDeCarta tipo) {
        MazoCartas mazo = new MazoCartas();
        mazo.setTipo(tipo);
        mazo.setCartas(new ArrayList<>());
        mazo.setCartasUsadas(new ArrayList<>());

        return mazo;
    }

    /**
     * Mezcla las cartas del mazo
     */
    @Override
    public void mezclar(List<Carta> cartas) {
        Collections.shuffle(cartas);
    }

    /**
     * Saca una carta del mazo
     * @return Carta sacada
     */
    @Override
    public Carta sacarCarta(List<Carta> cartas, List<Carta> cartasUsadas) {
        if (cartas.isEmpty()) {
            // Si no hay cartas, volver a mezclar las usadas
            cartas.addAll(cartasUsadas);
            cartasUsadas.clear();
            mezclar(cartas);
        }

        if (cartas.isEmpty()) {
            throw new IllegalStateException("No hay cartas disponibles en el mazo");
        }

        Carta carta = cartas.remove(0);
        return carta;
    }

    /**
     * Devuelve una carta al mazo (al final de las usadas)
     * @param carta Carta a devolver
     */
    @Override
    public void devolverCarta(Carta carta, List<Carta> cartasUsadas) {
        if (carta != null && !carta.esSalirCarcel()) {
            // Las cartas normales van a usadas
            cartasUsadas.add(carta);
        } else if (carta != null && carta.esSalirCarcel()) {
            // Las cartas de salir de cárcel se pueden guardar
            // No las devolvemos automáticamente
        }
    }

    /**
     * Agrega cartas al mazo
     * @param nuevasCartas Cartas a agregar
     */
    @Override
    public void agregarCartas(List<Carta> nuevasCartas, List<Carta> cartas) {
        if (nuevasCartas != null) {
            cartas.addAll(nuevasCartas);
        }
    }

    /**
     * Verifica si el mazo está vacío
     * @return true si está vacío
     */
    @Override
    public boolean estaVacio(List<Carta> cartasUsadas, List<Carta> cartas) {
        return cartas.isEmpty() && cartasUsadas.isEmpty();
    }

    /**
     * Obtiene el número total de cartas
     * @return Total de cartas
     */
    @Override
    public int getTotalCartas(List<Carta> cartasUsadas, List<Carta> cartas) {
        return cartas.size() + cartasUsadas.size();
    }
}
