package ar.edu.utn.frc.tup.lciii.repositories.Impl;

import ar.edu.utn.frc.tup.lciii.data.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.TableroEntity;
import ar.edu.utn.frc.tup.lciii.repositories.TableroRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TableroRepositoryImpl implements TableroRepository {
    public boolean save(TableroEntity tablero){

        Transaction transaction = null;
        Session session = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.persist(tablero);

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

    public TableroEntity getById (int id){

        TableroEntity tablero = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tablero = session.get(TableroEntity.class, id);

        } catch (Exception e) {

            return null;
        }

        return tablero;
    }
}
