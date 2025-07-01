package ar.edu.utn.frc.tup.lciii.Models.Jugador.strategy;

import ar.edu.utn.frc.tup.lciii.Models.Banco;
import ar.edu.utn.frc.tup.lciii.Models.Casillas.Casilla;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.EstadoJugador;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import ar.edu.utn.frc.tup.lciii.Models.Propiedad;
import ar.edu.utn.frc.tup.lciii.Models.Tablero;

import java.util.List;

public class JugadorHumano extends Jugador {
    public JugadorHumano(String nombre, int saldo, List<Propiedad> propiedades, String color, int posicion, String bot, int valorDado, EstadoJugador estadoJugador, List<Casilla> casillas) {
        super(nombre, saldo, propiedades, color, posicion, "user", valorDado, estadoJugador, casillas);
    }
    public JugadorHumano(){
        super();
    }

    @Override
    public void tomarTurno(Tablero tablero, Banco banco) {
//        //TODO prueba
//        List<Propiedad> propiedadesDisponibles = tablero.obtenerPropiedadesDisponibles();
//        if (comprarPrioridad(propiedadesDisponibles, banco)) return;
    }

//    //TODO prueba
//    private boolean comprarPrioridad(List<Propiedad> propiedadesDisponibles, Banco banco) {
//        for (Propiedad propiedad : propiedadesDisponibles) {
//            if (propiedad.getDuenio() == null && propiedad.getPrecio() <= this.getSaldo()) {
//                banco.venderPropiedad(propiedad, this);
//                return true;
//            }
//        }
//        return false;
//    }

    @Override
    public String obtenerNombre() {
        return "";
    }

    @Override
    public int obtenerSaldo() {
        return 0;
    }

    @Override
    public void actualizarSaldo(int cantidad) {

    }

    @Override
    public List<Propiedad> obtenerPropiedades() {
        return List.of();
    }

    @Override
    public List<Casilla> obtenerCasillas() {
        return List.of();
    }

    @Override
    public void pagarAlquiler(int alquiler, Jugador duenio) {

    }
}
