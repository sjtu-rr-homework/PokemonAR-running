package org.pokemonrun.petservice.serviceimpl;

import org.pokemonrun.petservice.dao.PetDao;
import org.pokemonrun.petservice.entity.Pet;
import org.pokemonrun.petservice.service.PetRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetRegisterServiceImpl implements PetRegisterService {
    @Autowired
    private PetDao petDao;

    public boolean gainPet(long uid, long typeId) {
        Pet pet = petDao.findByOwnerAndTypeId(uid, typeId);
        if(pet == null){
            // the user does not have this type of pet
            pet = new Pet();
            pet.setOwnerUid(uid);
            pet.setTypeId(typeId);
            petDao.save(pet);
            return true;
        }else{
            // the user has owned this type of pet
            // TODO: bonus logic
            return false;
        }
    }
}
