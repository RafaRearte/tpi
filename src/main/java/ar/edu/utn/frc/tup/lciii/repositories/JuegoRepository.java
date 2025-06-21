package ar.edu.utn.frc.tup.lciii.repositories;

import ar.edu.utn.frc.tup.lciii.data.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.JuegoEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

public interface JuegoRepository {
    boolean save(JuegoEntity juego);

    JuegoEntity getById (int id);
}
