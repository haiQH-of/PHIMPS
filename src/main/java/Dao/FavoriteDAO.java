package Dao;


import java.util.List;

import Entity.Favorite;
import Entity.FavoriteReport;
import Entity.FavoriteUserReport;
import Entity.ShareReport;

public interface FavoriteDAO {
    /** Truy vấn tất cả */
    List<Favorite> findAll();

    /** Truy vấn theo mã */
    Favorite findById(String id);

    /** Thêm mới */
    void create(Favorite item);

    /** Cập nhật */
    void update(Favorite item);

    /** Xóa theo mã */
    void deleteById(String id);

    /** Phân trang */
    List<Favorite> getPage(int pageNo, int pageSize);

	List<Favorite> findByUserId(String userId);

	List<Object[]> findFavoriteVideos();

		
	

	List<ShareReport> reportShare(String videoShareId);

	List<FavoriteUserReport> reportFavoriteUsersByVideos(String videoUserId);

	List<FavoriteReport> reportFavoritesByVideos();
}
