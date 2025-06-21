package ar.edu.utn.frc.tup.lciii.repositories.Impl;

import ar.edu.utn.frc.tup.lciii.data.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.MovimientoEntity;
import ar.edu.utn.frc.tup.lciii.repositories.MovimientoRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MovimientoRepositoryImpl implements MovimientoRepository {
    public boolean save(MovimientoEntity movimiento){

        Transaction transaction = null;
        Session session = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.persist(movimiento);

            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            return false;
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public MovimientoEntity getById (int id){

        MovimientoEntity movimiento = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            movimiento = session.get(MovimientoEntity.class, id);

        } catch (Exception e) {

            return null;
        }

        return movimiento;
    }
}
