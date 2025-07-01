package ar.edu.utn.frc.tup.lciii.Models;

import ar.edu.utn.frc.tup.lciii.Models.Casillas.Carcel;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Setter
@Getter
public class Dado {

    private int id;
    private int valorCara;
    private int contador;
    private int dado1;
    private int dado2;
    private Jugador jugador;
    private Carcel carcel = new Carcel();

    public Dado(Jugador jugador) {
        this.jugador = jugador;
    }

    public Dado(int id, int valorCara) {
        this.id = id;
        this.valorCara = valorCara;
        this.contador = 0;
    }

    public Dado() {
    }

    public void lanzarDado() {
        Random random = new Random();

        for (int i = 0; i < 2; i++) {
            int numeroRandom = random.nextInt(6) + 1;
            System.out.println("Dado " + (i + 1) + ": " + numeroRandom);
            if (i == 0) {
                dado1 = numeroRandom;
            }
            if (i == 1) {
                dado2 = numeroRandom;
            }
        }
        dadoDoble(dado2, dado2);
    }

    public void dadoDoble(int dado1, int dado2) {
        if (dado1 == dado2) {
            contador += 1;
        } else {
            contador = 0;
        }
        if (contador == 3) {
            carcel.enviadoALaCarcel(jugador);
            contador = 0;
        }
    }

    public boolean esDoble(){
        return dado1 == dado2;
    }

    public int obtenerResultado() {
        return dado1 + dado2;
    }

    //todo traer metodo de estancieroMatch para dar el orden de turnos aca y llamarlo dsp en estancieroMatch
}
