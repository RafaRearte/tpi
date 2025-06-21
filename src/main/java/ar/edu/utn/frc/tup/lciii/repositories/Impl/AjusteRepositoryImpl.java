package ar.edu.utn.frc.tup.lciii.repositories.Impl;

import ar.edu.utn.frc.tup.lciii.data.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.AjusteEntity;
import ar.edu.utn.frc.tup.lciii.repositories.AjusteRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AjusteRepositoryImpl implements AjusteRepository {
    public boolean save(AjusteEntity ajuste){

        Transaction transaction = null;
        Session session = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.persist(ajuste);

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

    public AjusteEntity getById (int id){

        AjusteEntity ajuste = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            ajuste = session.get(AjusteEntity.class, id);

        } catch (Exception e) {

            return null;
        }

        return ajuste;
    }
}
