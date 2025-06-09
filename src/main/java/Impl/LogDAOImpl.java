package Impl;



import Controller.JpaUtils;
import Dao.LogDAO;
import Entity.Log;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class LogDAOImpl implements LogDAO {

    @Override
    public void create(Log log) {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(log);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}