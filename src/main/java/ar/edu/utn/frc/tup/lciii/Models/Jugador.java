package ar.edu.utn.frc.tup.lciii.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Random;
//TODO: Agregar la lista de escrituras en la base de datos. Cambiar la relacion peon-escritura-jugador
//TODO: Combinar con peon
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Jugador {
    private int id;
//    private Peon peon;//1 jugador tiene un peon
    private int saldo;
    private List<Escritura> propiedades;
    private String nombre;
    private Color color;
    private boolean bancarrota;
    private int posicionActual;
    private boolean esBot;
    private int ordenDeJuego;
//    private int anioNacimiento;
    private TipoDeJugador tipoDeJugador;

    protected int lanzarDado(){
        return new Random().nextInt(7);
    }

    protected void avanzar(int primerDado, int segundoDado){
        this.setPosicionActual(this.getPosicionActual() + primerDado + segundoDado);
    }

    protected void comprarPropiedad(Escritura escritura){

    }

    protected void venderPropiedad(Escritura escritura){

    }

    //TODO: Se pueden tener hasta 4 chacras, solo cuando se tiene 4 se pueden mejorar a una estancia
    protected void mejorarPropiedad(Escritura escritura, Mejora mejora){

    }

    //FIXME: Revizar esos tres metodos, dan error cuando se los llama con parametros
    public final void jugar(){
        int primerDado = lanzarDado();
        int segundoDado = lanzarDado();
        avanzar(primerDado, segundoDado);
//        comprarPropiedad();
//        venderPropiedad();
//        mejorarPropiedad();
    }
}
