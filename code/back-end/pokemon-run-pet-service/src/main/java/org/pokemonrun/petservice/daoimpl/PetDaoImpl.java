package org.pokemonrun.petservice.daoimpl;

import org.pokemonrun.petservice.dao.PetDao;
import org.pokemonrun.petservice.entity.Pet;
import org.pokemonrun.petservice.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PetDaoImpl implements PetDao {
    @Autowired
    PetRepository petRepository;

    public Pet findById(long id) {
        return petRepository.findById(id).get();
    }
    public Pet findByOwnerAndTypeId(long ownerUid, long typeId) {
        return petRepository.findByOwnerUidAndTypeId(ownerUid, typeId).get(0);
    }
    public void save(Pet pet) {
        petRepository.save(pet);
    }
}
