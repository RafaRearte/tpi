package ar.edu.utn.frc.tup.lciii.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.internal.bytebuddy.pool.TypePool;

import java.util.List;
//TODO: Agregar valorAlquiler
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Escritura {
    private int id;
    private Casilla casilla;
    private String nombre;
    private int precio;
    private int valorAlquiler;
    private int valorHipotecario;
    private boolean disponibilidad;
    private boolean sePuedeMejorar;
    private List<Mejora> mejoras;

    public String toString(){
        int estancias = 0;
        int chacras = 0;
        String totalMejoras = null;
        for(Mejora m : mejoras){
            if(m.getTipoDeMejora().equals(TipoDeMejora.ESTANCIA)){
                estancias++;
            } else{
                chacras++;
            }
        }

        if(this.getCasilla().getTipoDeCasilla().equals(TipoDeCasilla.CAMPO)){
            totalMejoras = "Chacras: " + chacras + System.lineSeparator() + "Estancias: " + estancias;
        }

        return "Nombre: " + nombre + System.lineSeparator() +
                "Valor de Alquiler: " + valorAlquiler + System.lineSeparator() +
                "Valor hipotecario: " +  valorHipotecario + System.lineSeparator() +
                totalMejoras;
    }
}
