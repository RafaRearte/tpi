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

public class JugadorAgresivo extends Jugador {

    private static final List<String> PROVINCIAS_CON_PRIORIDAD = List.of("TUCUMAN Zona Sur","TUCUMAN Zona Centro","TUCUMAN Zona Norte", "CORDOBA Zona Sur", "CORDOBA Zona Centro", "CORDOBA Zona Norte","BUENOS AIRES Zona Sur","BUENOS AIRES Zona Centro", "BUENOS AIRES Zona Norte");

    private static final List<String> PROPIEDADES_CON_PRIORIDAD = List.of("FERROCARRIL", "COMPANIA");

    public JugadorAgresivo(String nombre, int saldo, List<Propiedad> propiedades, String color, int posicion, String tipo, int valorDado, EstadoJugador estadoJugador, List<Casilla> casillas) {
        super(nombre, saldo, propiedades, color, posicion, tipo, valorDado, estadoJugador, casillas);
    }

    @Override
    public void tomarTurno(Tablero tablero, Banco banco) {
        List<Propiedad> propiedadesDisponibles = tablero.obtenerPropiedadesDisponibles();

        if (intentarComprarProvinciaPrioritaria(propiedadesDisponibles, banco)) return;
        if (intentarComprarPropiedadPrioritaria(propiedadesDisponibles, banco)) return;
        if (comprarOtrasPropiedades(propiedadesDisponibles, banco)) return;

        construirMejoras();
    }

    private boolean intentarComprarProvinciaPrioritaria(List<Propiedad> propiedadesDisponibles, Banco banco) {
        for (Propiedad propiedad : propiedadesDisponibles) {
            if (PROVINCIAS_CON_PRIORIDAD.contains(propiedad.getNombre())) {
                banco.venderPropiedad(propiedad, this);
                return true;
            }
        }
        return false;
    }

    private boolean intentarComprarPropiedadPrioritaria(List<Propiedad> propiedadesDisponibles, Banco banco) {
        for (Propiedad propiedad : propiedadesDisponibles) {
            System.out.println(propiedad.getNombre());
            if (PROPIEDADES_CON_PRIORIDAD.contains(propiedad.getNombre())) {
                banco.venderPropiedad(propiedad, this);
                return true;
            }
        }
        return false;
    }

    private boolean prioridadesVendidas(List<Propiedad> propiedadesDisponibles) {
        for (String provincia : PROVINCIAS_CON_PRIORIDAD) {
            boolean vendido = false;
            for (Propiedad propiedad : propiedadesDisponibles) {
                if (propiedad.getNombre().contains(provincia)) {
                    vendido = true;
                    break;
                }
            }
            if (!vendido) {
                return false;
            }
        }
        return true;
    }

    private boolean prioridadesCompradas(List<Propiedad> propiedadesDisponibles) {
        for (String provincia : PROVINCIAS_CON_PRIORIDAD) {
            boolean comprado = this.getPropiedades().stream().anyMatch(property -> property.getNombre().equalsIgnoreCase(provincia)) ||
                    propiedadesDisponibles.stream().anyMatch(property -> property.getNombre().equalsIgnoreCase(provincia) && property.getDuenio() != null);

            if (!comprado) {
                return false;
            }
        }
        return true;
    }

    private boolean comprarOtrasPropiedades(List<Propiedad> propiedadesDisponibles, Banco banco) {
        if (prioridadesVendidas(propiedadesDisponibles) || prioridadesCompradas(propiedadesDisponibles)) {
            comprarTodasLasPosibles(propiedadesDisponibles, banco);
            return true;
        }
        return false;
    }

    private void comprarTodasLasPosibles(List<Propiedad> propiedadesDisponibles, Banco banco) {
        for (Propiedad propiedad : propiedadesDisponibles) {
            if (this.getSaldo() >= propiedad.getPrecio()){
                banco.venderPropiedad(propiedad, this);
                this.setSaldo(this.getSaldo() - propiedad.getPrecio());
            } else if (this.getSaldo() < propiedad.getPrecio()){
                System.out.println("No tiene dinero suficiente para comprar una propiedad.");
                break;
            }
        }
    }

    private void construirMejoras() {
        List<Propiedad> propiedadesDelJugador = this.getPropiedades();
        for (Propiedad propiedad : propiedadesDelJugador) {
            if (propiedad instanceof Chacra) {
                Chacra chacra = (Chacra) propiedad;
                if (chacra.sePuedeMejorar() && this.getSaldo() >= chacra.obtenerCostoDeMejora()) {
                    chacra.construirMejora();
                    this.setSaldo(this.getSaldo() - chacra.obtenerCostoDeMejora());
                }
            }
        }
    }

    public boolean deberiaComprar(Propiedad propiedad) {
        List<Propiedad> availableProperties = new ArrayList<>(); // lista de propiedades disponibles
        if (PROVINCIAS_CON_PRIORIDAD.contains(propiedad.getNombre())) {
            return this.getSaldo() >= propiedad.getPrecio();
        }
        else if (PROPIEDADES_CON_PRIORIDAD.contains(propiedad.getNombre())) {
            return this.getSaldo() >= propiedad.getPrecio();
        } else {
            return (prioridadesVendidas(availableProperties) || prioridadesCompradas(availableProperties)) && this.getSaldo() >= propiedad.getPrecio();
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
