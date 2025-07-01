package ar.edu.utn.frc.tup.lciii.Models;

import ar.edu.utn.frc.tup.lciii.Models.Casillas.Casilla;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.*;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.strategy.JugadorAgresivo;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.strategy.JugadorBalanceado;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.strategy.JugadorConservador;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.strategy.JugadorHumano;

import java.util.List;
import java.util.Objects;

public class JugadorFactory {

    public static Jugador crearJugador(String nombre, int saldo, List<Propiedad> propiedades, String color, int posicion, int valorDado, EstadoJugador estadoJugador, List<Casilla> casillas){
        return new JugadorHumano(nombre, saldo, propiedades, color, posicion, "bot", valorDado, estadoJugador, casillas);
    }


    public static Jugador crearBot(String tipo, String nombre, int saldo, List<Propiedad> propiedades, String color, int posicion, int valorDado, EstadoJugador estado, List<Casilla> casillas){
        if (tipo == null) {
            System.out.println("Ingreso no valido. Por favor ingrese un tipo de jugador ('conservador', 'equilibrado' o 'agresivo').");
            return null;
        }
        try {
            switch (Objects.requireNonNull(tipo).toLowerCase()){
                case "conservador":
                    return new JugadorConservador(nombre, saldo, propiedades, color, posicion,"bot", valorDado, estado, casillas);
                case "equilibrado":
                    return new JugadorBalanceado(nombre, saldo, propiedades, color, posicion, "bot", valorDado, estado, casillas);
                case "agresivo":
                    return new JugadorAgresivo(nombre, saldo, propiedades, color, posicion, "bot", valorDado, estado, casillas);
                default:
                    throw new IllegalArgumentException("Tipo de jugador desconocido: " + tipo);
            }
        } catch (IllegalArgumentException e){
            System.out.println("Ingreso no valido. Por favor ingrese un tipo de jugador ('conservador', 'equilibrado' o 'agresivo').");
            return null;
        }
    }
}