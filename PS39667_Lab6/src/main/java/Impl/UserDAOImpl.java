package Impl;

import java.util.List;

import Controller.JpaUtils;
import Dao.UserDAO;
import Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class UserDAOImpl implements UserDAO {
    private EntityManager em = JpaUtils.getEntityManager();

    @Override
	public List<User> findAll() {
        String jpql = "SELECT u FROM User u";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        return query.getResultList();
    }

    @Override
	public User findById(String id) {
        return em.find(User.class, id);
    }

    @Override
	public void update(User item) {
        try {
            em.getTransaction().begin();
            em.merge(item);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
	public void deleteById(String id) {
        User entity = em.find(User.class, id);
        try {
            if (entity != null) {
                em.getTransaction().begin();
                em.remove(entity);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void create(User user) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
	public List<User> getPage(int pageNo, int pageSize) {
        String jpql = "SELECT u FROM User u";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setFirstResult(pageNo * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }
    @Override
	public User findByIdOrEmail(String idOrEmail) {
		String jpql = "SELECT u FROM User u WHERE u.id = :id OR u.email = :email";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setParameter("id", idOrEmail);
		query.setParameter("email", idOrEmail);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
    @Override
	public void changePassword(String userId, String oldPass, String newPass) {

		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		String jpql = "select u from User u where u.userId= :userId and u.password= :password";

		try {
			trans.begin();
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			query.setParameter("userId", userId);
			query.setParameter("password", oldPass);

			User user = query.getSingleResult();
			if (user == null) {
				throw new Exception("Current password or username are incorrect!");
			}
			user.setPassword(newPass);
			em.merge(user);

			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
	}
	@Override
	public void close() {
		// TODO Auto-generated method stub

	}
	public static void main(String[] args) {
		 UserDAOImpl usDAO = new UserDAOImpl();

	        usDAO.findAll();

	}

	public List<User> findByIdOrEmail(User userid) {
		// TODO Auto-generated method stub
		return null;
	}
}
