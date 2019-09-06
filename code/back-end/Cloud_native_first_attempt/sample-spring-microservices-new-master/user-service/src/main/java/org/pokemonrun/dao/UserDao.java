package org.pokemonrun.dao;

import org.pokemonrun.entity.User;

import java.util.List;


public interface UserDao {
    User findOne(String username);//find one user by username (no duplicate)
    User findById(int id);//find one user by ID, no duplicate
    void save(String username, String password, String email, int star);//save one user , no duplicate username
    void save(User user);//save one user , no duplicate username
    List<User> findAll();//find all user

}
