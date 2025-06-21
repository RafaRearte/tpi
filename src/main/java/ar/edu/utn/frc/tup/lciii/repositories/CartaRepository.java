package ar.edu.utn.frc.tup.lciii.repositories;

import ar.edu.utn.frc.tup.lciii.entities.CartaEntity;

public interface CartaRepository {
    boolean save(CartaEntity carta);

    CartaEntity getById (int id);
}
