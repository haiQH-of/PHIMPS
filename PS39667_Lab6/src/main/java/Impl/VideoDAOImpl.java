package Impl;

import java.util.List;

import Controller.JpaUtils;
import Dao.VideoDAO;
import Entity.Video;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class VideoDAOImpl implements VideoDAO {
    private EntityManager em;

    public VideoDAOImpl() {
        this.em = JpaUtils.getEntityManager();
    }

    @Override
    public List<Video> findAll() {
 	   String jpql = "SELECT o FROM Video o";
 	   TypedQuery<Video> query = em.createQuery(jpql, Video.class);
 	   List<Video> videos = query.getResultList();

 	   System.out.println("Danh sách người dùng:");
 	   for (Video vd : videos) {
 	       System.out.println("-------------------");
 	       System.out.println("ID: " + vd.getId());
 	       System.out.println("Title: " + vd.getTitle());
 	       System.out.println("Poster: " + vd.getPoster());
 	       System.out.println("Description: " + vd.getDescription());
 	       System.out.println("Active: " + vd.getActive());
 	   }

 	   return query.getResultList();
 	}
    @Override
//    public Video findById(String id) {
//        try {
//            return em.find(Video.class, id);
//        } catch (Exception e) {
//            throw new RuntimeException("Error finding video with ID: " + id, e);
//        }
//    }
    public Video findById(String id) {
        EntityManager em = JpaUtils.getEntityManager();

        // Fetch the specific video by ID
        Video video = em.find(Video.class, id);

        if (video != null) {
            // Print the details of the found video
            System.out.println("-------------------");
            System.out.println("ID: " + video.getId());
            System.out.println("Title: " + video.getTitle());
            System.out.println("Poster: " + video.getPoster());
            System.out.println("Description: " + video.getDescription());
            System.out.println("Active: " + video.getActive());
        } else {
            System.out.println("Video with ID " + id + " not found.");
        }

        return video;
    }
    @Override
    public void create(Video item) {
        try {
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error creating video", e);
        }
    }

    @Override
    public void update(Video item) {
        try {
            em.getTransaction().begin();
            em.merge(item);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error updating video", e);
        }
    }

    @Override
    public void deleteById(String id) {
        try {
            em.getTransaction().begin();
            Video entity = em.find(Video.class, id);
            if (entity != null) {
                em.remove(entity);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error deleting video with ID: " + id, e);
        }
    }

    @Override
    public List<Video> findByKeyword(String keyword) {
        try {
            String jpql = "SELECT v FROM Video v WHERE LOWER(v.title) LIKE LOWER(:keyword)";
            TypedQuery<Video> query = em.createQuery(jpql, Video.class);
            query.setParameter("keyword", "%" + keyword + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error searching videos with keyword: " + keyword, e);
        }
    }

    @Override
    public List<Video> getPage(int pageNo, int pageSize) {
        try {
            String jpql = "SELECT o FROM Video o";
            TypedQuery<Video> query = em.createQuery(jpql, Video.class);
            query.setFirstResult(pageNo * pageSize);
            query.setMaxResults(pageSize);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving page " + pageNo, e);
        }
    }

    // Proper resource cleanup
    public void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
	public List<Video> findByTitleKeyword(String keyword) {
		String jpql = "SELECT v FROM Video v WHERE v.title LIKE :keyword";
		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
		query.setParameter("keyword", "%" + keyword + "%");
		return query.getResultList();
	}
	public List<Video> findFavoriteVideosByUserId(String userId) {
	    EntityManager em = null;
	    List<Video> list = null;
	    try {
	        em = JpaUtils.getEntityManager();
	        String jpql = "SELECT v FROM Video v JOIN Favorite f ON v.id = f.video.id WHERE f.user.id = :userId";
	        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
	        query.setParameter("userId", userId);
	        list = query.getResultList();
	    } catch (Exception e) {
	        System.err.println("Error finding favorite videos for user ID: " + userId + ", error: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }
	    return list;
	}


    public static void main(String[] args) {
    	try{
        VideoDAO vd = new VideoDAOImpl();
//
//        // Tạo một người dùng mới
//        User newUser = new User();
//        newUser.setId("user001");
//        newUser.setEmail("john.doe@example.com");
//        newUser.setPassword("password123");
//        udao.create(newUser);
//

         vd.findById("video01");
//
//        // Cập nhật người dùng
//        foundUser.setEmail("updated@example.com");
//        udao.update(foundUser);
//        System.out.println("Đã cập nhật email người dùng: " + foundUser.getEmail());
//
//        // Tìm người dùng bằng ID hoặc email
//        User userByInfo = udao.findByIdOrEmail("user001");
//
//        userByInfo = udao.findByIdOrEmail("updated@example.com");
//
//        // Lấy một trang người dùng
//        List<User> page1 = udao.getPage(0, 2);
//        System.out.println("Trang 1 người dùng:");
//        for (User user : page1) {
//        }
//
//        List<User> page2 = udao.getPage(1, 2);
//        System.out.println("Trang 2 người dùng:");
//        for (User user : page2) {
//        }
//          vd.findAll();

//        // Xóa người dùng
//        udao.deleteById("user001");
//        System.out.println("Đã xóa người dùng.");
        //
        EntityManager em = JpaUtils.getEntityManager();
        System.out.println("Kết nối thành công: " + em.isOpen());
        em.close();
    } catch (Exception e) {
        System.out.println("Lỗi kết nối: " + e.getMessage());
        e.printStackTrace();
    }
    }

	public List<Video> findByKeyword() {
		// TODO Auto-generated method stub
		return null;
	}
}