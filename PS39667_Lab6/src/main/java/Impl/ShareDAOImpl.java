package Impl;

import java.util.List;

import Controller.JpaUtils;
import Dao.ShareDAO;
import Entity.Share;
import Entity.Video;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ShareDAOImpl implements ShareDAO {
    private EntityManager em;

    public ShareDAOImpl() {
        this.em = JpaUtils.getEntityManager();
    }

    @Override
    public List<Share> findAll() {
        try {
            String jpql = "SELECT s FROM Share s";
            TypedQuery<Share> query = em.createQuery(jpql, Share.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all shares", e);
        }
    }

    @Override
    public Share findById(String id) {
        try {
            return em.find(Share.class, Integer.parseInt(id));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid ID format", e);
        } catch (Exception e) {
            throw new RuntimeException("Error finding share with ID: " + id, e);
        }
    }

    @Override
    public void create(Share item) {
        try {
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error creating share", e);
        }
    }

    @Override
    public void update(Share item) {
        try {
            em.getTransaction().begin();
            em.merge(item);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error updating share", e);
        }
    }

    @Override
    public void deleteById(String id) {
        try {
            em.getTransaction().begin();
            Share entity = em.find(Share.class, Integer.parseInt(id));
            if (entity != null) {
                em.remove(entity);
            }
            em.getTransaction().commit();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid ID format", e);
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error deleting share with ID: " + id, e);
        }
    }

    @Override

	public List<Video> findVideosSharedIn2024() {
		String jpql = "SELECT s.video FROM Share s WHERE YEAR(s.shareDate) = 2024 ORDER BY s.shareDate";
		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
		return query.getResultList();
	}

	public List<Object[]> getShareForVideos() {
	    String jpql = "SELECT v.title, COUNT(s.id) AS share_count, MIN(s.shareDate) AS min_share_date, MAX(s.shareDate) AS max_share_date " +
	                  "FROM Share s JOIN s.video v " +
	                  "GROUP BY v.title ORDER BY v.title";
	    TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
	    return query.getResultList();
	}
    @Override
    public List<Share> getPage(int pageNo, int pageSize) {
        try {
            String jpql = "SELECT s FROM Share s ORDER BY s.sharedate DESC";
            TypedQuery<Share> query = em.createQuery(jpql, Share.class);
            query.setFirstResult(pageNo * pageSize);
            query.setMaxResults(pageSize);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving page " + pageNo, e);
        }
    }

    public void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

	@Override
	public List<Object[]> getShareVideoInfo() {
		// TODO Auto-generated method stub
		return null;
	}
}