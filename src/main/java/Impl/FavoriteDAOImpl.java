package Impl;

import java.util.List;
import Controller.JpaUtils;
import Dao.FavoriteDAO;
import Entity.Favorite;
import Entity.FavoriteReport;
import Entity.FavoriteUserReport;
import Entity.ShareReport;
import Entity.Video;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class FavoriteDAOImpl implements FavoriteDAO {
    private EntityManager em;

    public FavoriteDAOImpl() {
        this.em = JpaUtils.getEntityManager();
    }

    @Override
    public List<Favorite> findAll() {
        String jpql = "SELECT f FROM Favorite f";
        TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
        List<Favorite> favorites = query.getResultList();

        System.out.println("Danh sách video yêu thích:");
        for (Favorite favorite : favorites) {
            System.out.println("-------------------");
            System.out.println("ID Người dùng: " + favorite.getId());

            if (favorite.getVideo() != null) {
                System.out.println("Video yêu thích: " + favorite.getVideo());
            } else {
                System.out.println("Video yêu thích: N/A");
            }

            System.out.println("Ngày thích: " + favorite.getLikedate());
        }
        return favorites;
    }

    @Override
    public Favorite findById(String id) {
        try {
            return em.find(Favorite.class, Long.parseLong(id));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid ID format", e);
        } catch (Exception e) {
            throw new RuntimeException("Error finding favorite with ID: " + id, e);
        }
    }

    @Override
    public void create(Favorite fa) {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(fa);
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

    @Override
    public void update(Favorite item) {
        try {
            em.getTransaction().begin();
            em.merge(item);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error updating favorite", e);
        }
    }

    @Override
    public void deleteById(String id) {
        try {
            em.getTransaction().begin();
            Favorite entity = em.find(Favorite.class, Long.parseLong(id));
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
            throw new RuntimeException("Error deleting favorite with ID: " + id, e);
        }
    }

    @Override
    public List<Favorite> findByUserId(String userId) {
        try {
            String jpql = "SELECT f FROM Favorite f WHERE f.user.id = :userId";
            TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
            query.setParameter("userId", userId);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding favorites for user: " + userId, e);
        }
    }

    @Override
    public List<Object[]> findFavoriteVideos() {
        try {
            String jpql = "SELECT v.title, u.fullname, f.likedate " +
                          "FROM Favorite f " +
                          "JOIN f.video v " +
                          "JOIN f.user u";
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving favorite videos", e);
        }
    }

    public void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

    @Override
    public List<Favorite> getPage(int pageNo, int pageSize) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Video> findTop10MostFavoritedVideos() {
        String jpql = "SELECT f.video FROM Favorite f GROUP BY f.video ORDER BY COUNT(f) DESC";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        query.setMaxResults(10);
        return query.getResultList();
    }

    public List<Video> findVideosWithNoFavorites() {
        String jpql = "SELECT v FROM Video v LEFT JOIN Favorite f ON v.id = f.video.id WHERE f.id IS NULL";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        return query.getResultList();
    }

    public List<Favorite> timTheoNguoiDungVaVideo(String userId, String videoId) {
        try {
            String jpql = "SELECT f FROM Favorite f WHERE f.user.id = :userId AND f.video.id = :videoId";
            TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
            query.setParameter("userId", userId);
            query.setParameter("videoId", videoId);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi tìm kiếm like", e);
        }
    }

    public boolean daDuocLike(String userId, String videoId) {
        List<Favorite> danhSachLike = timTheoNguoiDungVaVideo(userId, videoId);
        return !danhSachLike.isEmpty();
    }

    @Override
    public List<ShareReport> reportShare(String videoShareId) {
        String jpql = "SELECT new Entity.ShareReport(s.user.username, s.recipientEmail, s.sharedAt) " +
                      "FROM Share s WHERE s.video.id = :videoShareId";
        TypedQuery<ShareReport> query = em.createQuery(jpql, ShareReport.class);
        query.setParameter("videoShareId", videoShareId);
        return query.getResultList();
    }

    @Override
    public List<FavoriteUserReport> reportFavoriteUsersByVideos(String videoUserId) {
        String jpql = "SELECT new Entity.FavoriteUserReport(f.user.id, f.user.fullname, f.user.email, f.likeDate) " +
                      "FROM Favorite f WHERE f.video.id = :videoUserId";
        TypedQuery<FavoriteUserReport> query = em.createQuery(jpql, FavoriteUserReport.class);
        query.setParameter("videoUserId", videoUserId);
        return query.getResultList();
    }

    @Override
    public List<FavoriteReport> reportFavoritesByVideos() {
        String jpql = "SELECT new Entity.FavoriteReport(f.video.id, f.video.title, COUNT(f)) " +
                      "FROM Favorite f GROUP BY f.video.id, f.video.title";
        TypedQuery<FavoriteReport> query = em.createQuery(jpql, FavoriteReport.class);
        List<FavoriteReport> result = query.getResultList();
        
        // Kiểm tra xem kết quả có được trả về hay không
        System.out.println("Kết quả truy vấn reportFavoritesByVideos: " + result);

        return result;
    }

    // Phương thức main để hiển thị danh sách video yêu thích
    public static void main(String[] args) {
        FavoriteDAOImpl favoriteDAO = new FavoriteDAOImpl();

        favoriteDAO.daDuocLike("user03@example.com", "video02");
        favoriteDAO.timTheoNguoiDungVaVideo("user03@example.com", "video02");

        favoriteDAO.findByUserId("user03");

        favoriteDAO.close();
    }

    public Favorite findByUserAndVideo(String userId, String videoId) {
        // TODO Auto-generated method stub
        return null;
    }
}
