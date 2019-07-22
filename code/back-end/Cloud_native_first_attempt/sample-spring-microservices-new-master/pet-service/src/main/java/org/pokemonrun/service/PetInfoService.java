package org.pokemonrun.service;

import org.pokemonrun.info.Petinfo;

import java.util.List;

public interface PetInfoService {
    List<Petinfo> GetPets(String username);
    Petinfo GetOnePet(String username,int typeID);
    boolean OwnOrNot(String username,int typeID);
    List<AdminGetPetInfo> AdminGetPetsInfo(String username);
}
