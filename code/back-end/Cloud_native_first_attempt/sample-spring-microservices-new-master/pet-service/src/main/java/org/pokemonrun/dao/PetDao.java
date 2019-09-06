package org.pokemonrun.dao;

import org.pokemonrun.entity.Pet;

import java.util.List;

public interface PetDao {
    List<Pet> GetPets(String username);//get all pets which belong to one user
    Pet GetOnePet(String username, int typeID);//get one pet belong to one user
    void save(Pet pet);
}
