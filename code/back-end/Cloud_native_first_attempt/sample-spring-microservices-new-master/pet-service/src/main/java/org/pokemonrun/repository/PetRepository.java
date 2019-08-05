package org.pokemonrun.repository;

import org.pokemonrun.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    List<Pet> findAllByUsername(String username);
    Pet findByUsernameAndTypeID(String username, int typeID);

}
