package org.pokemonrun.repository;

import org.pokemonrun.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
