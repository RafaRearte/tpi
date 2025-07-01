package ar.edu.utn.frc.tup.lciii.Models.Cartas;

import ar.edu.utn.frc.tup.lciii.handlers.ImpresoLetraPorLetra;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PenalidadCarta extends Carta {

    public PenalidadCarta(int id, CartaTipo tipo, String descripcion) {
        super(id, tipo, TipoCarta.CARTA_PENALIDAD, descripcion);

        if (!verTipoDeCarta(TipoCarta.CARTA_PENALIDAD)) {
            throw new IllegalArgumentException("El tipo de carta no es CARTA_PENALIDAD");
        }
    }

    @Override
    public void ejecutarAccion(Jugador jugador) {
        int monto = extraerSaldo(getDescription());
        retirarSaldo(monto, jugador);
    }

    private int extraerSaldo(String descripcion) {

        if (descripcion == null) {
            throw new IllegalArgumentException("Error: Descripción de carta es nula.");
        }
        int total = 0;
        String[] parts = descripcion.split("\\$");

        for (int i = 1; i < parts.length; i++) {
            String part = parts[i].split(" ")[0];
            try {
                int amount = Integer.parseInt(part.replaceAll("[^0-9]", "").trim());
                total += amount;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException ("Error: No se pudo obtener ninguna cantidad de dinero a descontar.");
            }
        }
        return total;
    }

    private void retirarSaldo(int saldo, Jugador jugador) {

        if (saldo > 0) {
            int saldoActual = jugador.getSaldo();
            if (saldoActual >= saldo) {
                jugador.pagarSaldo(saldo);
            } else {
                jugador.pagarSaldo(saldoActual);
                saldoInsuficiente(saldoActual, saldo);
                throw new IllegalArgumentException("el jugador no tiene el monto necesario para llevar a cabo la accion. ");
            }
        } else {
            throw new IllegalArgumentException("el monto a descontar no puede ser 0");
        }
    }

    private void saldoInsuficiente(int saldoActual, int requerido) {
        ImpresoLetraPorLetra.println("El jugador no tiene el saldo suficiente. Se requiere: " + requerido + " , disponible: " + saldoActual);
    }
}
