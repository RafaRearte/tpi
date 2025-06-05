package ar.edu.utn.frc.tup.lciii.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
//TODO: Voy a agregar una lista de jugadores. Despues cambiar en la base
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Juego {
    private int id;
    private LocalDateTime fechaInicial;
    private LocalDateTime fechaFinal;
    private List<Jugador> jugadores;
    private Jugador ganador;
    private Ajuste ajuste;
    private Tablero tablero;
    private Banco banco;
    private List<Carta> mazo; //esta podria ir en tablero

}
