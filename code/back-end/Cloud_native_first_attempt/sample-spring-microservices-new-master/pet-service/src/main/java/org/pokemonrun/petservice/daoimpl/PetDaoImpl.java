package org.pokemonrun.petservice.daoimpl;

import org.pokemonrun.petservice.dao.PetDao;
import org.pokemonrun.petservice.entity.Pet;
import org.pokemonrun.petservice.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetDaoImpl implements PetDao {
    @Autowired
    PetRepository petRepository;


    @Override
    public List<Pet> GetPets(String username) {
        return petRepository.findAllByUsername(username);
    }

    @Override
    public Pet GetOnePet(String username, int typeID) {
        return petRepository.findByUsernameAndTypeID(username, typeID);
    }

    @Override
    public void save(Pet pet) {
        petRepository.save(pet);
    }


}
