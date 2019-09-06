package org.pokemonrun.daoimpl;

import org.pokemonrun.dao.PetDao;
import org.pokemonrun.entity.Pet;
import org.pokemonrun.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetDaoImpl implements PetDao {
    @Autowired
    PetRepository petRepository;


    @Override//get all pets belong to one user
    public List<Pet> GetPets(String username) {
        return petRepository.findAllByUsername(username);
    }

    @Override//get one pet belong to one user
    public Pet GetOnePet(String username, int typeID) {
        return petRepository.findByUsernameAndTypeID(username, typeID);
    }

    @Override
    public void save(Pet pet) {
        petRepository.save(pet);
    }


}
