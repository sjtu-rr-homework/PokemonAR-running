package org.pokemonrun.petservice.service;

import org.pokemonrun.petservice.entity.Pet;
import org.pokemonrun.petservice.info.Petinfo;

import java.util.List;

public interface PetInfoService {
    List<Petinfo> GetPets(String username);
    Petinfo GetOnePet(String username,int typeID);
    boolean OwnOrNot(String username,int typeID);
}
