package org.pokemonrun.petservice.service;

import org.pokemonrun.petservice.entity.Pet;

public interface PetInfoService {
    Pet getPetInfoByPetId(long petId);
}
