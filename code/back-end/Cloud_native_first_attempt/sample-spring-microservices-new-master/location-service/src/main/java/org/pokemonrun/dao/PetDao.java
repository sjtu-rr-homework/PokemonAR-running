package org.pokemonrun.dao;

import org.pokemonrun.entity.location;

import java.util.List;

public interface PetDao {
    List<location> GetPets(String username);
    location GetOnePet(String username, int typeID);
    void save(location location);
}
