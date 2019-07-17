package org.pokemonrun.daoimpl;

import org.pokemonrun.dao.PetDao;
import org.pokemonrun.entity.location;
import org.pokemonrun.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetDaoImpl implements PetDao {
    @Autowired
    PetRepository petRepository;


    @Override
    public List<location> GetPets(String username) {
        return petRepository.findAllByUsername(username);
    }

    @Override
    public location GetOnePet(String username, int typeID) {
        return petRepository.findByUsernameAndTypeID(username, typeID);
    }

    @Override
    public void save(location location) {
        petRepository.save(location);
    }


}
