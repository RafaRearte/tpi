package ar.edu.utn.frc.tup.lciii.repositories;

import ar.edu.utn.frc.tup.lciii.data.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.MovimientoEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

public interface MovimientoRepository {
    boolean save(MovimientoEntity movimiento);

    MovimientoEntity getById (int id);
}
