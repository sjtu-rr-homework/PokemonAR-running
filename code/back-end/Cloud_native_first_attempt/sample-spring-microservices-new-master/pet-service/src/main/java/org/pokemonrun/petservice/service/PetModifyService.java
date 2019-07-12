package org.pokemonrun.petservice.service;

public interface PetModifyService {
    boolean addPet(String username, int typeID);
    boolean addExp(String username, int typeID, int exp);
    boolean addNum(String username, int typeID,int num);
}
