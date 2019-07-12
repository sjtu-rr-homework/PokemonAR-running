package org.pokemonrun.petservice.repository;

import org.pokemonrun.petservice.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByOwnerUid(Long ownerUid);
    List<Pet> findByOwnerUidAndTypeId(Long ownerUid, Long typeId);
}
