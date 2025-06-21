package ar.edu.utn.frc.tup.lciii.Services.Impl;

import ar.edu.utn.frc.tup.lciii.Models.Escritura;
import ar.edu.utn.frc.tup.lciii.Models.Jugador;
import ar.edu.utn.frc.tup.lciii.Services.EscrituraService;

import static ar.edu.utn.frc.tup.lciii.Models.TipoDeMejora.CHACRA;
import static ar.edu.utn.frc.tup.lciii.Models.TipoDeMejora.ESTANCIA;
//TODO: Revisar calcularAlquilerConMejoras
public class EscrituraServiceImpl implements EscrituraService {

    private int calcularAlquilerConMejoras(Escritura escritura) {
        int chacras = 0, estancias = 0;
        int valorChacras = 0, valorEstancias = 0;
        String nombre = escritura.getNombre();

        for (var mejora : escritura.getMejoras()) {
            if (mejora.getTipoDeMejora().equals(CHACRA)) {
                valorChacras = getValorChacra(nombre);
                chacras++;
            } else if (mejora.getTipoDeMejora().equals(ESTANCIA)) {
                valorEstancias = getValorEstancia(nombre);
                estancias++;
            }
        }
        return (valorEstancias * estancias) + (valorChacras * chacras);
    }

    private int getValorChacra(String nombre) {
        if (nombre.contains("Formosa")) return 10;
        if (nombre.contains("Rio Negro")) return 20;
        if (nombre.contains("Salta")) return 30;
        if (nombre.contains("Mendoza")) return 40;
        if (nombre.contains("Santa Fe")) return 50;
        if (nombre.contains("Tucuman")) return 60;
        if (nombre.contains("Cordoba")) return 70;
        if (nombre.contains("Buenos Aires")) return 80;
        return 0;
    }

    private int getValorEstancia(String nombre) {
        if (nombre.contains("Formosa")) return 250;
        if (nombre.contains("Rio Negro")) return 450;
        if (nombre.contains("Salta")) return 550;
        if (nombre.contains("Mendoza")) return 600;
        if (nombre.contains("Santa Fe")) return 750;
        if (nombre.contains("Tucuman")) return 900;
        if (nombre.contains("Cordoba")) return 950;
        if (nombre.contains("Buenos Aires")) return 1000;
        return 0;
    }

    @Override
    public void cobrarAlquiler(Escritura escritura, Jugador jugador) {
        int ferrocarriles = 0;
        int servicios = 0;


        for(Escritura e: jugador.getPropiedades()){
            if(e.getNombre().contains("Ferrocarril")){
                ferrocarriles++;
            } else if (e.getNombre().contains("Luz") || e.getNombre().contains("Agua")) {
                servicios++;
            }
        }

        if(!jugador.getPropiedades().contains(escritura)){
            if(escritura.getMejoras().isEmpty()){
                jugador.setSaldo(jugador.getSaldo() - escritura.getValorAlquiler());
            } else if (escritura.getNombre().contains("Ferrocarril")) {
                if(ferrocarriles == 1){
                    jugador.setSaldo(jugador.getSaldo() - 25);
                } else if (ferrocarriles == 2) {
                    jugador.setSaldo(jugador.getSaldo() - 50);
                } else if (ferrocarriles == 3) {
                    jugador.setSaldo(jugador.getSaldo() - 100);
                } else if (ferrocarriles == 4) {
                    jugador.setSaldo(jugador.getSaldo() - 200);
                }
            } else if (servicios == 1) {
                jugador.setSaldo(jugador.getSaldo() - (jugador.lanzarDado() *4));
            } else if (servicios == 2) {
                jugador.setSaldo(jugador.getSaldo() - (jugador.lanzarDado() *7));
            } else{
                jugador.setSaldo(jugador.getSaldo() - calcularAlquilerConMejoras(escritura));
            }
        }
    }
}
