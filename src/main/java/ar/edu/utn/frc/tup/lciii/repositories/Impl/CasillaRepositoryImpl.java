package ar.edu.utn.frc.tup.lciii.repositories.Impl;

import ar.edu.utn.frc.tup.lciii.data.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.CasillaEntity;
import ar.edu.utn.frc.tup.lciii.repositories.CasillaRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CasillaRepositoryImpl implements CasillaRepository {
    public boolean save(CasillaEntity casilla){

        Transaction transaction = null;
        Session session = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.persist(casilla);

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

    public CasillaEntity getById (int id){

        CasillaEntity casilla = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            casilla = session.get(CasillaEntity.class, id);

        } catch (Exception e) {

            return null;
        }

        return casilla;
    }

    @Override
    public List<CasillaEntity> getAll() {
        List<CasillaEntity> casillas = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            casillas = session.createQuery("FROM CasillaEntity", CasillaEntity.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return casillas;
    }
}
