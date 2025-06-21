package ar.edu.utn.frc.tup.lciii.repositories.Impl;

import ar.edu.utn.frc.tup.lciii.data.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.CartaEntity;
import ar.edu.utn.frc.tup.lciii.repositories.CartaRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CartaRepositoryImpl implements CartaRepository {
    public boolean save(CartaEntity carta){

        Transaction transaction = null;
        Session session = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.persist(carta);

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

    public CartaEntity getById (int id){

        CartaEntity carta = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            carta = session.get(CartaEntity.class, id);

        } catch (Exception e) {

            return null;
        }

        return carta;
    }
}
