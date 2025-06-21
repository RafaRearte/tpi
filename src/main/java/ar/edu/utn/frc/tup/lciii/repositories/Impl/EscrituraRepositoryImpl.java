package ar.edu.utn.frc.tup.lciii.repositories.Impl;

import ar.edu.utn.frc.tup.lciii.data.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.EscrituraEntity;
import ar.edu.utn.frc.tup.lciii.repositories.EscrituraRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EscrituraRepositoryImpl implements EscrituraRepository {
    public boolean save(EscrituraEntity escritura){

        Transaction transaction = null;
        Session session = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.persist(escritura);

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

    public EscrituraEntity getById (int id){

        EscrituraEntity escritura = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            escritura = session.get(EscrituraEntity.class, id);

        } catch (Exception e) {

            return null;
        }

        return escritura;
    }

    public EscrituraEntity getByIdCasilla(int id){
        EscrituraEntity escritura = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            escritura = session.createQuery(
                            "FROM EscrituraEntity e WHERE e.idCasilla.id = :idCasilla", EscrituraEntity.class)
                    .setParameter("idCasilla", id)
                    .uniqueResult();

        } catch (Exception e) {

            return null;
        }

        return escritura;
    }

    @Override
    public EscrituraEntity GetIdByNombre(String nombre) {
        EscrituraEntity escritura = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            escritura = session.createQuery(
                            "FROM EscrituraEntity e WHERE e.nombre = :nombre", EscrituraEntity.class)
                    .setParameter("nombre", nombre)
                    .uniqueResult();
        } catch (Exception e) {

            return null;
        }

        return escritura;
    }
}
