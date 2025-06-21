package ar.edu.utn.frc.tup.lciii.repositories;

import ar.edu.utn.frc.tup.lciii.entities.MejoraEntity;

public interface MejoraRepository {
    MejoraEntity getByTipoAndValor(String tipo, int valor);
}
