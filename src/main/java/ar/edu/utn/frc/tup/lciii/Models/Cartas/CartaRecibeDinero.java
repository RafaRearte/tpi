package ar.edu.utn.frc.tup.lciii.Models.Cartas;

import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartaRecibeDinero extends Carta {

    public CartaRecibeDinero(int id, CartaTipo tipo, String descripcion) {
        super(id, tipo, TipoCarta.CARTA_RECIBIR_SALDO, descripcion);

        if (!verTipoDeCarta(TipoCarta.CARTA_RECIBIR_SALDO)) {
            throw new IllegalArgumentException("El tipo de carta no es CARTA_RECIBIR_SALDO");
        }
    }

    public CartaRecibeDinero() {

    }

    @Override
    public void ejecutarAccion(Jugador jugador) {
        try {
            String descripcion = getDescription();
            int cantidad = extraerSaldo(descripcion);
            recibirSaldo(cantidad, jugador);
        }catch (Exception e) {
            System.err.println("Ocurrio un error la mover la carta: " + e.getMessage());
            e.printStackTrace();
        }
    }

//    private int extraerSaldo(String description) {
//        int searchSign = description.indexOf('$');
//        if (searchSign != -1) {
//            String substringSaldo = description.substring(searchSign +1);
//            while (!substringSaldo.isEmpty() &&!Character.isDigit(substringSaldo.charAt(substringSaldo.length()-1))){
//                substringSaldo = substringSaldo.substring(0,substringSaldo.length()-1);
//            }
//            try {
//                return Integer.parseInt(substringSaldo.trim());
//            }catch (NumberFormatException e) {
//                throw new IllegalArgumentException ("Error: No se pudo obtener la cantidad de dinero indicado de la carta.");
//            }
//        }
//        return 0;
//    }

    private int extraerSaldo(String description) {
        // Busca el primer número en la descripción (puede tener puntos como separador de miles)
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("(\\d{1,3}(?:\\.\\d{3})*)").matcher(description);
        if (matcher.find()) {
            String numero = matcher.group(1).replace(".", "");
            try {
                return Integer.parseInt(numero);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error: No se pudo obtener la cantidad de dinero indicado de la carta.");
            }
        }
        throw new IllegalArgumentException("Error: No se pudo obtener la cantidad de dinero indicado de la carta.");
    }

    public void recibirSaldo(int saldo, Jugador jugador) {
        try {
            if (saldo > 0) {
                jugador.saldoRecibido(saldo);

            } else {
                throw new IllegalArgumentException("El monto no puede ser negativo o la cantidad del dinero no es valida.");
            }
        }catch (Exception e) {
            System.err.println("Ocurrio un error la mover la carta: " + e.getMessage());
            e.printStackTrace();
        }
    }

}