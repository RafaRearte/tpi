package ar.edu.utn.frc.tup.lciii.services.strategy;

import ar.edu.utn.frc.tup.lciii.Models.Propiedad;

public interface CompraStrategy {
    boolean deberiaComprar(Propiedad propiedad);
}
