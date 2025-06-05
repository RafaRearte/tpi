package ar.edu.utn.frc.tup.lciii.TemplateJugadores;

import ar.edu.utn.frc.tup.lciii.Models.*;

import java.util.Iterator;
import java.util.List;

public class JugadorBotAgresivo extends Jugador {

    @Override
    protected void comprarPropiedad(Escritura escritura){
        if(escritura.isDisponibilidad() && this.getSaldo() > escritura.getPrecio()){
            if(escritura.getNombre().contains("Tucuman") || escritura.getNombre().contains("Buenos Aires") ||
                    escritura.getNombre().contains("Cordoba") || escritura.getNombre().contains("Ferrocarril") || escritura.getNombre().contains("Compañia")){
                escritura.setDisponibilidad(false);
                this.setSaldo(this.getSaldo() - escritura.getPrecio());
                this.getPropiedades().add(escritura);
            } else if (this.getPropiedades().size() > 3) {
                escritura.setDisponibilidad(false);
                this.setSaldo(this.getSaldo() - escritura.getPrecio());
                this.getPropiedades().add(escritura);
            }
        }
    }

    //TODO: Implementar la logica de este metodo
    @Override
    protected void venderPropiedad(Escritura escritura){

    }

    /**
     * Le agregue la funcion para que tenga que comprar primero 4 chacras y luego las reemplaze por una estancia
     */
    @Override
    protected void mejorarPropiedad(Escritura escritura, Mejora mejora){
        int cantidadDeChacras = 0;
        for (int i = 0; i < 4; i++){
            if(escritura.getMejoras().get(i).equals(TipoDeMejora.CHACRA)){
                cantidadDeChacras++;
            }
        }
        if(mejora.getTipoDeMejora().equals(TipoDeMejora.CHACRA) && cantidadDeChacras < 4
                && mejora.getValor() < getSaldo()){
            this.getPropiedades().get(escritura.getId()).getMejoras().add(mejora);
        } else if (mejora.getValor() < getSaldo() && mejora.getTipoDeMejora().equals(TipoDeMejora.ESTANCIA) && cantidadDeChacras == 4 ) {
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
