package ar.edu.utn.frc.tup.lciii.Models.Jugador;

import ar.edu.utn.frc.tup.lciii.handlers.EstancieroMatchHandler;
import ar.edu.utn.frc.tup.lciii.Models.Banco;
import ar.edu.utn.frc.tup.lciii.Models.Casillas.*;
import ar.edu.utn.frc.tup.lciii.Models.Propiedad;
import ar.edu.utn.frc.tup.lciii.Models.Tablero;
import ar.edu.utn.frc.tup.lciii.services.JugadorService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public abstract class Jugador implements JugadorService {

    private int id;
    private String nombre;
    private int saldo;
    private List<Propiedad> propiedades;
    private List<Casilla> casillas;
    private String color;
    private int dadoNumero;
    private EstadoJugador estado;
    private int posicion;
    private String tipoPersona; // TODO: definir tipo persona, bot
    private int contarJuegos;
    private int turnosEnCasilla;
    private String razonDescanso;
    private boolean enPrision;

    private EstancieroMatchHandler estancieroMatchHandler;

    private int contadoDoble;

    public Jugador(String nombre, int saldo, List<Propiedad> propiedades, String color, int posicion,
                   String tipoPersona, int dadoNumero, EstadoJugador estado, List<Casilla> casillas) {
        this.nombre = nombre;
        this.saldo = saldo;
        this.propiedades = propiedades != null ? propiedades : new ArrayList<>();
        this.color = color;
        this.posicion = posicion;
        this.dadoNumero = dadoNumero;
        this.tipoPersona = tipoPersona;
        this.estado = EstadoJugador.HABILITADO;
        this.casillas = casillas != null ? casillas : new ArrayList<>();
        this.turnosEnCasilla = 0;
        this.razonDescanso = "";
        this.enPrision = false;
        this.estancieroMatchHandler = new EstancieroMatchHandler();
        this.contadoDoble = 0;
    }

    public Jugador() {
        this.propiedades = new ArrayList<>();
        this.casillas = new ArrayList<>();
    }

    public String getNombre(){
        return nombre;
    }

    public int getSaldo(){
        return saldo;
    }

    public void setSaldo(int amount){
        this.saldo = amount;
    }

    public List<Propiedad> getPropiedades(){
        return propiedades;
    }

    public List<Casilla> getCasillas(){
        return casillas;
    }

    @Override
    public void comprarPropiedad(Propiedad propiedad){
        int precio;

        if (propiedad instanceof Provincia){
            precio = ((Provincia) propiedad).getPrecio();
        } else if (propiedad instanceof CompaniaPetrolera){
            precio = ((CompaniaPetrolera) propiedad).getPrecio();
        } else if (propiedad instanceof Ferrocarril) {
            precio = ((Ferrocarril) propiedad).getPrecio();
        } else if (propiedad instanceof Bodega) {
            precio = ((Bodega) propiedad).getPrecio();
        } else {
            precio = propiedad.getPrecio();
        }

        if(saldo >= precio){
            propiedades.add(propiedad);
//            estancieroMatchHandler.getBanco().distribuirSaldo(this, precio);
            estancieroMatchHandler.getBanco().venderPropiedad(propiedad, this);
            propiedad.setDuenio(this);
        } else {
            throw new IllegalArgumentException("El jugador no puede comprar la propiedad.");
        }
    }

    @Override
    public String obtenerComportamiento(String dificultad){
        return dificultad;
    }

    public String obtenerInformacionJugador() {
        StringBuilder info = new StringBuilder();
        info.append("Jugador ID: ").append(id).append("\n");
        info.append("Nombre: ").append(nombre).append("\n");
        info.append("Saldo: ").append(saldo).append("\n");
        info.append("Color: ").append(color).append("\n");
        info.append("Posicion: ").append(posicion).append("\n");
        info.append("Tipo: ").append(tipoPersona).append("\n");

        info.append("Propiedades: \n");
        for (Propiedad propiedad : propiedades) {
            info.append("\t- ").append(propiedad.getNombre()).append("\n");
        }

        return info.toString();
    }

    public void venderPropiedad(Jugador jugador, Propiedad propiedad) {
        if (propiedades.contains(propiedad)) {
            propiedades.remove(propiedad);
            jugador.saldoRecibido(propiedad.getPrecio());
            propiedad.setDuenio(null);
        } else {
            throw new IllegalArgumentException("The player does not own the property he is trying to sell.");
        }
    }

    public void
    saldoRecibido(int amount) {
        saldo += amount;
    }

    public void pagarSaldo(int amount) {
        if (amount > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente para pagar.");
        }
        saldo -= amount;
    }

    public void mover(int pasos) {
        posicion += pasos;
    }

    public void pagarAlquiler(int alquiler, Jugador jugador, Jugador duenio){
        try {
            jugador.pagarSaldo(alquiler);
            duenio.saldoRecibido(alquiler);
        } catch (ArithmeticException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public abstract void tomarTurno(Tablero tablero, Banco banco);

    public void contadorJuegos() {
        contarJuegos++;
    }

    public void incrementarContadorDoble() {
        contadoDoble++;
    }

    public void resetearContadorDoble() {
        contadoDoble = 0;
    }
}
