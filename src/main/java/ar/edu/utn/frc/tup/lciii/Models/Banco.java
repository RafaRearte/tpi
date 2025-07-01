package ar.edu.utn.frc.tup.lciii.Models;

import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Banco {

    private int saldo = 334200;
    private List<Propiedad> propiedades;
    public Banco() {
        this.propiedades = new ArrayList<>();
    }

    public Banco(int saldo) {
        this.saldo = saldo;
        this.propiedades = new ArrayList<>();
    }

    public void recolectarRenta(Propiedad propiedad, Jugador jugador1, Jugador jugador2) {
        int price = propiedad.getPrecio();
        jugador1.setSaldo(jugador1.getSaldo() - price);
        jugador2.setSaldo(jugador2.getSaldo() + price);
    }

    public void recolectarImpuestos(Jugador jugador, int impuesto) {
        jugador.setSaldo(jugador.getSaldo() - impuesto);
        saldo += impuesto;
    }

    public void aniadirPropiedad(Propiedad propiedad) {
        propiedades.add(propiedad);
    }

//    public void venderPropiedad(Propiedad propiedad, Jugador jugador) {
//        int precio = propiedad.getPrecio();
//        int saldoJugador = jugador.getSaldo();
//        if (saldoJugador >= precio) {
//            jugador.setSaldo(jugador.getSaldo() - precio);
//            this.saldo += precio;
//            jugador.getPropiedades().remove(propiedad);
//            propiedad.setDuenio(null);
//        } else System.out.println("el jugador no tiene el saldo suficiente para comprar la propiedad");
//    }

    public void venderPropiedad(Propiedad propiedad, Jugador jugador) { //Fixme ver si se puede cambiar por ferrocarril y provincia
        int precio = propiedad.obtenerPrecio();//Devuelve precio 0
        int saldoJugador = jugador.getSaldo();
        if (saldoJugador >= precio && propiedad.getDuenio() == null) {
            jugador.setSaldo(saldoJugador - precio);
            this.saldo += precio;
            if (!jugador.getPropiedades().contains(propiedad)) {
                jugador.getPropiedades().add(propiedad);
            }
            propiedad.setDuenio(jugador);
        } else {
            System.out.println("El jugador no tiene el saldo suficiente o la propiedad ya tiene dueño.");
        }
    }

    public void distribuirSaldo(Jugador jugador, int valor) {
        if (saldo >= valor) {
            jugador.saldoRecibido(valor);
            this.saldo -= valor;
        } else {
            System.out.println("Fondos insuficientes.");
        }
    }

    public void volverASalida(Jugador p) {
        p.setSaldo(p.getSaldo() + 5000);
    }

    public int saldoDisponibleDelBanco(int numeroDeJugadores) {
        int saldoDistribuido = numeroDeJugadores * 35000;
        int saldoDisponible = this.saldo - saldoDistribuido;

        if (saldoDisponible >= 0) {
            return saldoDisponible;
        } else {
            System.out.println("Fondos insuficientes.");
            return 0;
        }
    }
}
