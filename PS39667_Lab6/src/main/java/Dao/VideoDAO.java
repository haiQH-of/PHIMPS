package Dao;

import java.util.List;

import Entity.Video;

public interface VideoDAO {


    /** Truy vấn tất cả */
    List<Video> findAll();

    /** Truy vấn theo mã */
    Video findById(String id);

    /** Thêm mới */
    void create(Video item);

    /** Cập nhật */
    void update(Video item);

    /** Xóa theo mã */
    void deleteById(String id);

    /** Tìm kiếm video theo từ khóa */
    List<Video> findByKeyword(String keyword);

    /** Phân trang */
    List<Video> getPage(int pageNo, int pageSize);

}
