package ar.edu.utn.frc.tup.lciii.repositories;

import ar.edu.utn.frc.tup.lciii.entities.EscrituraEntity;

public interface EscrituraRepository {
    boolean save(EscrituraEntity escritura);

    EscrituraEntity getById (int id);
    EscrituraEntity getByIdCasilla(int id);
    EscrituraEntity GetIdByNombre(String nombre);
}
