package Dao;

import java.util.List;

import Entity.User;

public interface UserDAO {

    List<User> findAll();
    User findById(String id);
    void create(User item);
    void update(User item);
    void deleteById(String id);
    User findByIdOrEmail(String idoremail);
    List<User> getPage(int pageNo, int pageSize);
	void close();
	void changePassword(String userId, String currentPassword, String password);

}
