package ar.edu.utn.frc.tup.lciii.TemplateJugadores;

import ar.edu.utn.frc.tup.lciii.Models.*;

import java.util.Iterator;
import java.util.List;

public class JugadorHumano extends Jugador {

    protected void comprarPropiedad(Escritura escritura){
        escritura.setDisponibilidad(false);
        this.setSaldo(this.getSaldo() - escritura.getPrecio());
        this.getPropiedades().add(escritura);
    }

    protected void venderPropiedad(Escritura escritura){
        this.getPropiedades().get(escritura.getId()).setDisponibilidad(true);
        this.setSaldo(this.getSaldo() + escritura.getPrecio());
    }

    /**
     * Le agregue la funcion para que tenga que comprar primero 4 chacras y luego las reemplaze por una estancia
     */
    protected void mejorarPropiedad(Escritura escritura, Mejora mejora){
        int cantidadDeChacras = 0;
        for (int i = 0; i < 4; i++){
            if(escritura.getMejoras().get(i).equals(TipoDeMejora.CHACRA)){
                cantidadDeChacras++;
            }
        }
        if(cantidadDeChacras < 4 && mejora.getTipoDeMejora().equals(TipoDeMejora.CHACRA)){
            this.getPropiedades().get(escritura.getId()).getMejoras().add(mejora);
        } else if (cantidadDeChacras == 4 && mejora.getTipoDeMejora().equals(TipoDeMejora.ESTANCIA)) {
            this.getPropiedades().get(escritura.getId()).getMejoras().add(mejora);
            List<Mejora> mejoras = this.getPropiedades().get(escritura.getId()).getMejoras();
            Iterator<Mejora> iterator = mejoras.iterator();
            while (iterator.hasNext()) {
                Mejora m = iterator.next();
                if (m.getTipoDeMejora().equals(TipoDeMejora.CHACRA)) {
                    iterator.remove();
                }
            }

        }
    }
}
