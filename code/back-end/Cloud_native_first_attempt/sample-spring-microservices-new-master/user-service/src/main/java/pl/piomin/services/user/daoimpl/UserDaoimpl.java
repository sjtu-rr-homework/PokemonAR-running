package pl.piomin.services.user.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.piomin.services.user.dao.UserDao;
import pl.piomin.services.user.entity.User;
import pl.piomin.services.user.repository.UserRepository;

import java.util.List;

@Repository
public class UserDaoimpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findOne(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void save(String username, String password, String email, int star) {
        User temp= new User(username,password,email,star);
        userRepository.save(temp);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void remove(Integer id) {
        userRepository.deleteById(id);
    }
}