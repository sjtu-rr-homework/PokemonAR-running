package org.pokemonrun.petservice.serviceimpl;

import org.pokemonrun.petservice.dao.PetDao;
import org.pokemonrun.petservice.entity.Pet;
import org.pokemonrun.petservice.service.PetInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetInfoServiceImpl implements PetInfoService {
    @Autowired
    PetDao petDao;

    public Pet getPetInfoByPetId(long petId) {
        Pet pet = petDao.findById(petId);
        return pet;
    }
}
