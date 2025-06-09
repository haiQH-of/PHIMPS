package Dao;

import java.util.List;

import Entity.Share;
import Entity.Video;

public interface ShareDAO {
    /** Truy vấn tất cả */
    List<Share> findAll();

    /** Truy vấn theo mã */
    Share findById(String id);

    /** Thêm mới */
    void create(Share item);

    /** Cập nhật */
    void update(Share item);

    /** Xóa theo mã */
    void deleteById(String id);

    /** Lấy thông tin chia sẻ video */
    List<Object[]> getShareVideoInfo();

    /** Phân trang */
    List<Share> getPage(int pageNo, int pageSize);

	List<Video> findVideosSharedIn2024();
}
