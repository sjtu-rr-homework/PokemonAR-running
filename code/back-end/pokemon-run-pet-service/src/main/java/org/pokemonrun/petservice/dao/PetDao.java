package org.pokemonrun.petservice.dao;

import org.pokemonrun.petservice.entity.Pet;

public interface PetDao {
    Pet findById(long id);
    Pet findByOwnerAndTypeId(long ownerUid, long typeId);
    void save(Pet pet);
}
