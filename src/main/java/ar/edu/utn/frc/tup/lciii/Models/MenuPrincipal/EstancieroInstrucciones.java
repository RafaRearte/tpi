package ar.edu.utn.frc.tup.lciii.Models.MenuPrincipal;

import ar.edu.utn.frc.tup.lciii.services.strategy.MenuPrincipalStrategy;

import java.util.Scanner;

public class EstancieroInstrucciones implements MenuPrincipalStrategy {
    @Override
    public void ajustarMenuPrincipal() {
        mostrarInstrucciones();
    }

    private void mostrarInstrucciones() {
        System.out.println("Instrucciones del juego de mesa El Estanciero.");
        System.out.println();
        System.out.println("Onjetivo del juego:");
        System.out.println("Sé el último jugador en pie después de que todos los demás hayan quedado en bancarrota..");
        System.out.println();
        System.out.println("Setup:");
        System.out.println("1. Cada jugador elige una ficha y la coloca en la casilla de salida.");
        System.out.println("2. Elijan a un banquero que administrará el dinero y las propiedades no vendidas.");
        System.out.println("3. Cada jugador recibe una cantidad inicial de dinero (según lo indiquen las reglas del juego).");
        System.out.println();
        System.out.println("Desarrollo del juego:");
        System.out.println("1. Los jugadores tiran los dados para determinar el orden de juego.");
        System.out.println("2. En cada turno, un jugador lanza los dos dados y mueve su ficha la cantidad de casillas igual a la suma de ambos.");
        System.out.println("3. Si un jugador saca dobles (el mismo número en ambos dados), obtiene un turno adicional. Sin embargo, si saca dobles tres veces seguidas, debe ir directamente a la cárcel.");
        System.out.println("4. Según la casilla en la que caiga el jugador, pueden suceder las siguientes acciones:");
        System.out.println("   - Compra de propiedades: Si la casilla no tiene dueño, el jugador puede comprarla al banco.");
        System.out.println("   - Pago de alquiler: Si la propiedad pertenece a otro jugador, se debe pagar el alquiler correspondiente.");
        System.out.println("   - Casillas especiales: El jugador puede caer en casillas como 'Ir a la cárcel', 'Suerte', 'Destino', etc.");
        System.out.println();
        System.out.println("Propiedades:");
        System.out.println("1. Los jugadores pueden comprar propiedades cuando caen en ellas y están disponibles.");
        System.out.println("2. Si un jugador compra una propiedad, recibe la tarjeta correspondiente.");
        System.out.println("3. Las propiedades pueden mejorarse comprando chacras y estancias (según las reglas del juego).");
        System.out.println();
        System.out.println("Alquiler:");
        System.out.println("1. Los jugadores deben pagar alquiler cuando caen en una propiedad que pertenece a otro jugador.");
        System.out.println("2. El monto del alquiler depende del tipo de propiedad y de si tiene chacras o estancias.");
        System.out.println();
        System.out.println("Bancarrota:");
        System.out.println("1. Un jugador entra en bancarrota cuando no puede pagar lo que debe.");
        System.out.println("2. Al quebrar, debe entregar todas sus propiedades y el dinero restante al acreedor (ya sea otro jugador o el banco).");
        System.out.println("3. El jugador en bancarrota queda fuera del juego.");
        System.out.println();
        System.out.println("Fin del juego:");
        System.out.println("El juego termina cuando solo queda un jugador que no ha quebrado.");
        System.out.println();
        System.out.println("Consejos:");
        System.out.println("1. Administrá bien tu dinero y comprá propiedades estratégicamente.");
        System.out.println("2. Construí chacras y estancias en propiedades de alto tránsito para aumentar tus ingresos.");
        System.out.println("3. Negociá con otros jugadores para intercambiar o vender propiedades.");
        System.out.println();
        System.out.println("¡Divertite jugando a El Estanciero!");

        System.out.println("-------------------------------------------------------------------------------");


        Scanner sc = new Scanner(System.in);
        System.out.println(" ");
        System.out.println("Presione enter para volver al menu principal");
        if (!sc.nextLine().isEmpty()){
            System.out.println(" press enter para volver al menu principal");
            mostrarInstrucciones();
        }
    }

}
