package ar.edu.utn.frc.tup.lciii.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Jugador {
    private int id;
    private String nombre;
    private int saldo;
    private Color color;
    private boolean bancarrota;
    private TipoDeJugador tipoDeJugador;
    private Casilla casillaActual;
    private List<Escritura> propiedades;
    private boolean esBot;
    private int ordenDeJuego;


    public int lanzarDado(){
        return new Random().nextInt(7);
    }

    public void listaDePropiedades(){
        for(Escritura escritura : propiedades){
            System.out.println(escritura.toString());
        }
    }

    protected void comprarPropiedad(Escritura escritura){

    }

    protected void venderPropiedad(Escritura escritura){

    }

    //TODO: Se pueden tener hasta 4 chacras, solo cuando se tiene 4 se pueden mejorar a una estancia
    protected void mejorarPropiedad(Escritura escritura, Mejora mejora){

    }

    //FIXME: Revizar esos tres metodos, dan error cuando se los llama con parametros
    public final void accion(int option, Escritura escritura, Mejora mejora){
        switch (option){
            case 1:
                comprarPropiedad(escritura);
                break;
            case 2:
                venderPropiedad(escritura);
                break;
            case 3:
                mejorarPropiedad(escritura, mejora);
                break;
        }
    }
}
