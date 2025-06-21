package ar.edu.utn.frc.tup.lciii.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banco {
    private int id;
    private int saldo;

    //Lucho
    private List<Escritura> escriturasDisponibles;

    public void transferirDinero(Jugador origen, Jugador destino, int monto) {
        if (origen.getSaldo() >= monto) {
            origen.setSaldo(origen.getSaldo() - monto);
            destino.setSaldo(destino.getSaldo() + monto);
        }
    }

    public void cobrarAlquiler(Jugador inquilino, Jugador propietario, int monto) {
        if (inquilino.getSaldo() >= monto) {
            inquilino.setSaldo(inquilino.getSaldo() - monto);
            propietario.setSaldo(propietario.getSaldo() + monto);
        } else {
            inquilino.setBancarrota(true);
        }
    }

    public void venderEscritura(Jugador comprador, Escritura escritura) {
        if (comprador.getSaldo() >= escritura.getPrecio()) {
            comprador.setSaldo(comprador.getSaldo() - escritura.getPrecio());
            this.saldo += escritura.getPrecio();
            escritura.setDisponibilidad(false);
            comprador.getPropiedades().add(escritura);
            this.escriturasDisponibles.remove(escritura);
        }
    }

    public void comprarEscritura(Jugador vendedor, Escritura escritura) {
        this.saldo -= escritura.getPrecio();
        vendedor.setSaldo(vendedor.getSaldo() + escritura.getPrecio());
        escritura.setDisponibilidad(true);
        vendedor.getPropiedades().remove(escritura);
        this.escriturasDisponibles.add(escritura);
    }
}
