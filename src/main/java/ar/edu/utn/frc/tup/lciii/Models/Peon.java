package ar.edu.utn.frc.tup.lciii.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Random;
//TODO: Revisar relacion con juego para poder definir el orden de juego
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Peon {
    private int id;
    private int saldo;
    private List<Escritura> propiedades;
    private Color color;
    private boolean bancarrota;
    private int posicionActual;
    private boolean esBot;
    private int ordenDeJuego;
}
