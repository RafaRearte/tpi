package ar.edu.utn.frc.tup.lciii.Models;

import ar.edu.utn.frc.tup.lciii.Services.JuegoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

//TODO: Voy a agregar una lista de jugadores. Despues cambiar en la base
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Juego {
    private int id;
    private LocalDateTime fechaInicial;
    private LocalDateTime fechaFinal;
    private Jugador ganador;
    private List<Jugador> jugadores;
    private Ajuste ajuste;
    private Tablero tablero;
    private Banco banco;

    //Lucho
    private Scanner scanner;
    private static final int MAX_DOBLES = 3;
    private JuegoService juegoService;
}
