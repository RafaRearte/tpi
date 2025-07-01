package ar.edu.utn.frc.tup.lciii.Models.Jugador.strategy;

import ar.edu.utn.frc.tup.lciii.Models.Banco;
import ar.edu.utn.frc.tup.lciii.Models.Casillas.Casilla;
import ar.edu.utn.frc.tup.lciii.Models.Chacra;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.EstadoJugador;
import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import ar.edu.utn.frc.tup.lciii.Models.Propiedad;
import ar.edu.utn.frc.tup.lciii.Models.Tablero;

import java.util.ArrayList;
import java.util.List;

public class JugadorBalanceado extends Jugador {

    private static final List<String> PROVINCIAS_CON_PRIORIDAD = List.of("MENDOZA Zona Sur","MENDOZA Zona Centro","MENDOZA Zona Norte", "SANTA FE Zona Sur", "SANTA FE Zona Centro", "SANTA FE Zona Norte", "TUCUMAN Zona Sur", "TUCUMAN Zona Centro","TUCUMAN Zona Norte");

    private static final List<String> FERROCARRIL = List.of("FERROCARRIL");

    public JugadorBalanceado(String nombre, int saldo, List<Propiedad> propiedades, String color, int posicion, String tipo, int valorDado, EstadoJugador estado, List<Casilla> casillas) {
        super(nombre, saldo, propiedades, color, posicion, tipo, valorDado, estado, casillas);
    }

    public JugadorBalanceado(){
        super();
    }

    @Override
    public void tomarTurno(Tablero tablero, Banco banco) {
        List<Propiedad> propiedadesDisponibles = tablero.obtenerPropiedadesDisponibles();
        List<Propiedad> propiedadesJugador = this.getPropiedades();

        // Verificar si se deben construir mejoras
        boolean deberiaConstruir = (propiedadesJugador.size() > 3 && this.getSaldo() > (this.getSaldo() * 0.50)) ||
                (32 - tablero.obtenerPropiedadesDisponibles().size()) / (double) 32 > 0.75;

        if (deberiaConstruir) {
            construirMejora(propiedadesJugador);
            return;
        }

        // Comprar propiedades de ferrocarriles
        for (Propiedad propiedad : propiedadesDisponibles) {
            if (FERROCARRIL.contains(propiedad.getNombre()) && this.getSaldo() >= propiedad.getPrecio()) {
//                comprarPropiedad(propiedad);
                banco.venderPropiedad(propiedad, this);
                return;
            }
        }

        // Comprar propiedades prioritarias
        for (Propiedad propiedad : propiedadesDisponibles) {
            if (PROVINCIAS_CON_PRIORIDAD.contains(propiedad.getNombre()) && this.getSaldo() >= propiedad.getPrecio()) {
                banco.venderPropiedad(propiedad, this);
                return;
            }
        }

        // Comprar propiedades fuera de las provincias de preferencia
        List<Propiedad> propiedadesDeBajaPrioridad = new ArrayList<>();
        for (Propiedad propiedad : propiedadesDisponibles) {
            if (!PROVINCIAS_CON_PRIORIDAD.contains(propiedad.getNombre())) {
                propiedadesDeBajaPrioridad.add(propiedad);
            }
        }

        if (!propiedadesDeBajaPrioridad.isEmpty() && this.getSaldo() >= propiedadesDeBajaPrioridad.get(0).getPrecio() && propiedadesDeBajaPrioridad.size() % 3 == 0) {
//            comprarPropiedad(propiedadesDeBajaPrioridad.get(0));
            banco.venderPropiedad(propiedadesDeBajaPrioridad.get(0), this);
            return;
        }
    }

    private void construirMejora(List<Propiedad> propiedadesDelJugador) {
        for (Propiedad propiedad : propiedadesDelJugador) {
            if (propiedad instanceof Chacra) {
                Chacra chacra = (Chacra) propiedad;
                if (chacra.sePuedeMejorar() && this.getSaldo() > chacra.obtenerCostoDeMejora() * 0.50) {
                    chacra.construirMejora();
                    this.setSaldo(this.getSaldo() - chacra.obtenerCostoDeMejora());
                }
            }
        }
    }

    public boolean deberiaComprar(Propiedad propiedad) {
        if (PROVINCIAS_CON_PRIORIDAD.contains(propiedad.obtenerInfoPropiedad())) {
            return this.getSaldo() >= propiedad.getPrecio();
        } else if (FERROCARRIL.contains(propiedad.obtenerInfoPropiedad())) {
            return this.getSaldo() >= propiedad.getPrecio();
        } else {
            List<Propiedad> propiedadesDeBajaPrioridad = new ArrayList<>();
            for (Propiedad p : this.getPropiedades()) {
                if (!PROVINCIAS_CON_PRIORIDAD.contains(p.getNombre())) {
                    propiedadesDeBajaPrioridad.add(p);
                }
            }
            return !propiedadesDeBajaPrioridad.isEmpty() && this.getSaldo() >= propiedad.getPrecio() && propiedadesDeBajaPrioridad.size() % 3 == 0;
        }
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
