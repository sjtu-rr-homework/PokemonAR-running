package org.pokemonrun.daoimpl;

import org.pokemonrun.dao.UserDao;
import org.pokemonrun.entity.User;

import org.pokemonrun.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoimpl implements UserDao {

    @Autowired
    private UserInfoRepository UserRepository;

    @Override
    public User findOne(String username) {
        return UserRepository.findByUsername(username);
    }

    @Override
    public User findById(int id) {
        return UserRepository.findById(id).get();
    }

    @Override
    public void save(String username, String password, String email, int star) {
        User temp= new User(username,password,email,star,0,-1);
        UserRepository.save(temp);
    }

    @Override
    public void save(User user) {
        UserRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return UserRepository.findAll();
    }

    @Override
    public void remove(Integer id) {
        UserRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUser() {
        return UserRepository.findAll();
    }
}
