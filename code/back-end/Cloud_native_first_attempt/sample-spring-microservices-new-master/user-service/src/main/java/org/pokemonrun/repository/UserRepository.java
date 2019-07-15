package org.pokemonrun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.pokemonrun.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
