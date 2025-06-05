package ar.edu.utn.frc.tup.lciii.Services;

import ar.edu.utn.frc.tup.lciii.Dtos.EscrituraDto;

public interface CasillaService {
    int obtenerPrecio();
    EscrituraDto obtenerEscritura(int id);
}
