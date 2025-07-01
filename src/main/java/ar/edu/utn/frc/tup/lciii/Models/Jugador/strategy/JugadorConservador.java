package ar.edu.utn.frc.tup.lciii.Models.Jugador.strategy;

import ar.edu.utn.frc.tup.lciii.Models.Banco;
import ar.edu.utn.frc.tup.lciii.Models.Casillas.Casilla;
import ar.edu.utn.frc.tup.lciii.Models.Chacra;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.EstadoJugador;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import ar.edu.utn.frc.tup.lciii.Models.Propiedad;
import ar.edu.utn.frc.tup.lciii.Models.Tablero;
import ar.edu.utn.frc.tup.lciii.services.strategy.CompraStrategy;

import java.util.List;

public class JugadorConservador extends Jugador implements CompraStrategy {

    private static final List<String> PROVINCIAS_BARATAS = List.of("FORMOZA Zona Sur", "FORMOZA Zona Centro", "FORMOZA Zona Norte", "RIO NEGRO Zona Sur", "RIO NEGRO Zona Centro", "RIO NEGRO Zona Norte", "SALTA Zona Sur", "SALTA Zona Centro", "SALTA Zona Norte");
    private int comprarDeBajaPrioridad = 0; //variable que se utiliza para realizar un seguimiento de la cantidad de propiedades no prioritarias que el jugador conservador ha comprado.

    public JugadorConservador(String nombre, int saldo, List<Propiedad> propiedades, String color, int posicion, String tipo, int valorDado, EstadoJugador estado, List<Casilla> casillas) {
        super(nombre, saldo, propiedades, color, posicion, tipo, valorDado, estado, casillas);
    }

    @Override
    public void tomarTurno(Tablero tablero, Banco banco) {
        List<Propiedad> propiedadesDisponibles = tablero.obtenerPropiedadesDisponibles();

        if (comprarPrioridad(propiedadesDisponibles, banco)) return;
        if (comprarBajaPrioridad(propiedadesDisponibles, banco)) return;

        construirMejoras();
    }

    private void construirMejoras() {
        List<Propiedad> propiedadesDelJugador = this.getPropiedades();
        for (Propiedad propiedad :
                propiedadesDelJugador) {
            if (propiedad instanceof Chacra){
                Chacra chacra = (Chacra) propiedad;
                if (chacra.sePuedeMejorar() && this.getSaldo() >= chacra.obtenerCostoDeMejora()) {
                    chacra.construirMejora();
                    this.setSaldo(this.getSaldo() - chacra.obtenerCostoDeMejora());
                }
            }
        }
    }

    private boolean comprarBajaPrioridad(List<Propiedad> propiedadesDisponibles, Banco banco) {
        for (Propiedad propiedad : propiedadesDisponibles) {
            if (!PROVINCIAS_BARATAS.contains(propiedad.getNombre()) &&
                    this.getSaldo() >= propiedad.getPrecio()) {
                if (comprarDeBajaPrioridad % 5 == 0) {
//                    comprarPropiedad(propiedad);
                    banco.venderPropiedad(propiedad, this);
                    comprarDeBajaPrioridad++;
                    return true;
                } else {
                    comprarDeBajaPrioridad++;
                    return false;
                }
            }
        }
        return false;
    }

    private boolean comprarPrioridad(List<Propiedad> propiedadesDisponibles, Banco banco) {
        for (Propiedad propiedad : propiedadesDisponibles) {
            if (deberiaComprar(propiedad)) {
//                comprarPropiedad(propiedad);
                banco.venderPropiedad(propiedad, this);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deberiaComprar(Propiedad propiedad) {
        if (PROVINCIAS_BARATAS.contains(propiedad.getNombre())) {
            return this.getSaldo() >= propiedad.getPrecio();
        } else if (comprarDeBajaPrioridad % 5 == 0) {
            return this.getSaldo() >= propiedad.getPrecio();
        }
        return false;
    }

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
    public void pagarAlquiler(int rentalCost, Jugador owner) {

    }
}
