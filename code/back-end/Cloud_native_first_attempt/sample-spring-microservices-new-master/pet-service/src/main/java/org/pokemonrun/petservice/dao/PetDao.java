package org.pokemonrun.petservice.dao;

import org.pokemonrun.petservice.entity.Pet;

import java.util.List;

public interface PetDao {
    List<Pet> GetPets(String username);
    Pet GetOnePet(String username, int typeID);
    void save(Pet pet);
}
