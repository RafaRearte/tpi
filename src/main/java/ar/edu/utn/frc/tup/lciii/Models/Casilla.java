package ar.edu.utn.frc.tup.lciii.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//Voy a poner lo de aceptar mejoras en escritura
//Precio creo que es redundante, igual que disponibilidad
//TODO: Habria que definir la relacion casilla-escritura. Ahora tiene una relacion 1 a 1 con escritura
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Casilla {
    private int id;
    private String descripcion;
    private TipoDeCasilla tipoDeCasilla;
    private boolean cobrarPagar;
    private Carta carta;

    public boolean esSalida() {
        return descripcion != null && descripcion.equalsIgnoreCase("SALIDA");
    }

    public boolean esCarcel() {
        return descripcion != null && descripcion.toLowerCase().contains("carcel");
    }

    public boolean esVayaACarcel() {
        return descripcion != null && descripcion.toLowerCase().contains("vaya a la carcel");
    }

    public boolean esParkingGratis() {
        return descripcion != null && descripcion.toLowerCase().contains("parking");
    }

    public boolean esImpuesto() {
        return descripcion != null && descripcion.toLowerCase().contains("impuesto");
    }

    public boolean esSuerte() {
        return descripcion != null && descripcion.equalsIgnoreCase("SUERTE");
    }

    public boolean esDestino() {
        return descripcion != null && descripcion.equalsIgnoreCase("DESTINO");
    }

    public boolean esPropiedad() {
        return tipoDeCasilla == TipoDeCasilla.CAMPO ||
                tipoDeCasilla == TipoDeCasilla.ESTACION ||
                tipoDeCasilla == TipoDeCasilla.SERVICIOS_PUBLICOS;
    }

    //Fixme
//    public boolean tieneEscritura() {
//        return escritura != null;
//    }

    public boolean tieneCarta() {
        return carta != null;
    }
}
