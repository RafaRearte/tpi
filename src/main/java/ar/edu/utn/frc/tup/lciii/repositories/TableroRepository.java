package ar.edu.utn.frc.tup.lciii.repositories;

import ar.edu.utn.frc.tup.lciii.data.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.TableroEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

public interface TableroRepository {
    boolean save(TableroEntity tablero);

    TableroEntity getById (int id);
}
