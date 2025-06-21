package ar.edu.utn.frc.tup.lciii.repositories.Impl;

import ar.edu.utn.frc.tup.lciii.data.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.BancoEntity;
import ar.edu.utn.frc.tup.lciii.repositories.BancoRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BancoRepositoryImpl implements BancoRepository {
    public boolean save(BancoEntity banco){

        Transaction transaction = null;
        Session session = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.persist(banco);

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

    public BancoEntity getById (int id){

        BancoEntity banco = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            banco = session.get(BancoEntity.class, id);

        } catch (Exception e) {

            return null;
        }

        return banco;
    }
}
