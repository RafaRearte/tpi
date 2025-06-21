package ar.edu.utn.frc.tup.lciii.repositories.Impl;

import ar.edu.utn.frc.tup.lciii.data.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.JuegoEntity;
import ar.edu.utn.frc.tup.lciii.repositories.JuegoRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class JuegoRepositoryImpl implements JuegoRepository {
    public boolean save(JuegoEntity juego){

        Transaction transaction = null;
        Session session = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.persist(juego);

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

    public JuegoEntity getById (int id){

        JuegoEntity juego = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            juego = session.get(JuegoEntity.class, id);

        } catch (Exception e) {

            return null;
        }

        return juego;
    }
}
