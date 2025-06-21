package ar.edu.utn.frc.tup.lciii.repositories.Impl;

import ar.edu.utn.frc.tup.lciii.data.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.JugadorEntity;
import ar.edu.utn.frc.tup.lciii.repositories.JugadorRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class JugadorRepositoryImpl implements JugadorRepository {
    public boolean save(JugadorEntity jugador){

        Transaction transaction = null;
        Session session = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.persist(jugador);

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

    public JugadorEntity getById (int id){

        JugadorEntity jugador = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            jugador = session.get(JugadorEntity.class, id);

        } catch (Exception e) {

            return null;
        }

        return jugador;
    }
}
