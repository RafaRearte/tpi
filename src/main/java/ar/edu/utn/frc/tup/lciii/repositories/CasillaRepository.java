package ar.edu.utn.frc.tup.lciii.repositories;

import ar.edu.utn.frc.tup.lciii.entities.CasillaEntity;

import java.util.List;

public interface CasillaRepository {
    boolean save(CasillaEntity casilla);

    CasillaEntity getById (int id);
    List<CasillaEntity> getAll();
}
