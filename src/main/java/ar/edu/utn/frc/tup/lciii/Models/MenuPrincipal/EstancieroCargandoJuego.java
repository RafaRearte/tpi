package ar.edu.utn.frc.tup.lciii.Models.MenuPrincipal;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class EstancieroCargandoJuego {

    private int numero;
    private String nombre;
    private String fecha;

    public EstancieroCargandoJuego(int numero, String nombre, String fecha) {
        this.numero = numero;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public EstancieroCargandoJuego() {

    }

    @Override
    public String toString() {
        return "Numero de juego:" + numero + ", Nombre:" + nombre + ", Fecha:" + fecha;
    }

    // TODO : implementar con la base de datos las partidas guardas y cargarlas
    public void cargarPartidas(String nombre) {
        System.out.println(nombre);
        // TODO: implementar logica para cargar la partida
    }

    public List<EstancieroCargandoJuego> mostrarPartidasGuardadas() {
        List<EstancieroCargandoJuego> partidas = new ArrayList<>();
        partidas.add(new EstancieroCargandoJuego(1, "Fran", "26/06/24"));
        partidas.add(new EstancieroCargandoJuego(2, "Luciano", "26/06/24"));
        partidas.add(new EstancieroCargandoJuego(3, "Rafa", "26/06/24"));
        partidas.add(new EstancieroCargandoJuego(4, "Jonas", "26/06/24"));
        partidas.add(new EstancieroCargandoJuego(5, "Agus", "26/06/24"));
        partidas.add(new EstancieroCargandoJuego(5, "Gino", "26/06/24"));
        partidas.add(new EstancieroCargandoJuego(5, "Ismael", "26/06/24"));
        return partidas;
    }
}
