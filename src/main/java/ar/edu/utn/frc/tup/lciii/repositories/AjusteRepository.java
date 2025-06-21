package ar.edu.utn.frc.tup.lciii.repositories;

import ar.edu.utn.frc.tup.lciii.entities.AjusteEntity;

public interface AjusteRepository {
    boolean save(AjusteEntity ajuste);

    AjusteEntity getById (int id);
}
