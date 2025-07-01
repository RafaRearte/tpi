package ar.edu.utn.frc.tup.lciii.Models.Casillas;

import ar.edu.utn.frc.tup.lciii.Models.Banco;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class Impuesto extends Casilla {
    private Map<Integer, List<String>> map;
    private Banco banco;

    public Impuesto(Map<Integer, List<String>> map) {
        this.map = map;
    }

    public void crearImpuesto(){
        List<String> valor = new ArrayList<>();
        valor.add("Impuesto a los Reditos\n");
        valor.add("5000");
        map.put(4, valor);

        valor.add("Impuesto a las Ventas\n");
        valor.add("2000");
        map.put(41, valor);
    }

    public void obtenerDescripcion(int posicion){
        crearImpuesto();
        List<String> detallesDelImpuesto = map.get(posicion);
        if (detallesDelImpuesto != null){
            for (int i = 0; i < detallesDelImpuesto.size(); i += 2){
                System.out.println("[" + detallesDelImpuesto.get(i) + ", " + detallesDelImpuesto.get(i + 1) + "]");
            }
        }
    }
}
