package ar.edu.utn.frc.tup.lciii.repositories;

import ar.edu.utn.frc.tup.lciii.data.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.JugadorEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

public interface JugadorRepository {
    boolean save(JugadorEntity jugador);

    JugadorEntity getById (int id);
}
