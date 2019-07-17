package org.pokemonrun.repository;

import org.pokemonrun.entity.location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<location, Integer> {
    List<location> findAllByUsername(String username);
    location findByUsernameAndTypeID(String username, int typeID);

}
