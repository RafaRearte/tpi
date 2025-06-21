package ar.edu.utn.frc.tup.lciii.repositories;

import ar.edu.utn.frc.tup.lciii.entities.BancoEntity;

public interface BancoRepository {
    boolean save(BancoEntity banco);

    BancoEntity getById (int id);
}
