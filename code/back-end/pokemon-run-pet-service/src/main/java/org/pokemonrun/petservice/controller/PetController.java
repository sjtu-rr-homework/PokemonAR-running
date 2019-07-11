package org.pokemonrun.petservice.controller;

import org.pokemonrun.petservice.entity.Pet;
import org.pokemonrun.petservice.service.PetInfoService;
import org.pokemonrun.petservice.service.PetRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PetController {
    @Autowired
    private PetInfoService petInfoService;
    @Autowired
    private PetRegisterService petRegisterService;

    @PostMapping("/user/{uid}/pet/{typeId}")
    public boolean gainPet(@PathVariable long uid, @PathVariable long typeId){
        // false if duplicate, true if first
        return petRegisterService.gainPet(uid, typeId);
    }
    @GetMapping("/pet/{id}")
    public Pet getPetInfo(@PathVariable long id){
        return petInfoService.getPetInfoByPetId(id);
    }
}
