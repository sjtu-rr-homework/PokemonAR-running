package pl.piomin.services.organization.dao;

import pl.piomin.services.organization.entity.User;

import java.util.List;


public interface UserDao {
    User findOne(String username);
    User findById(int id);
    void save(String username, String password, String email, int star);
    void save(User user);
    List<User> findAll();
    void remove(Integer id);
}
