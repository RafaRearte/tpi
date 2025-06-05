package ar.edu.utn.frc.tup.lciii.Services.Impl;

import ar.edu.utn.frc.tup.lciii.Dtos.EscrituraDto;
import ar.edu.utn.frc.tup.lciii.Models.Casilla;
import ar.edu.utn.frc.tup.lciii.Models.TipoDeCasilla;
import ar.edu.utn.frc.tup.lciii.Services.CasillaService;

public class CasillaServiceImpl implements CasillaService {
    Casilla casilla;
    @Override
    public int obtenerPrecio() {
        return casilla.getEscritura().getPrecio();
    }

    //FIXME
    @Override
    public EscrituraDto obtenerEscritura(int id) {
        if(casilla.getId() == id && !casilla.getTipoDeCasilla().equals(TipoDeCasilla.ESPECIAL)){
            EscrituraDto respuesta = new EscrituraDto();

            respuesta.nombre = casilla.getEscritura().getNombre();
            respuesta.precio = casilla.getEscritura().getPrecio();
            respuesta.propietario = casilla.getEscritura().getJugador().getNombre();
            respuesta.disponibilidad = casilla.getEscritura().isDisponibilidad();
            respuesta.tieneMejoras = !casilla.getEscritura().getMejoras().isEmpty();
            return respuesta;
        }
        else return null;
    }
}
