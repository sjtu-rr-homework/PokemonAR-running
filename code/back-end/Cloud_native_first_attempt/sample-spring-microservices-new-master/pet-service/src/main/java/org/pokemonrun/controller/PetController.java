package org.pokemonrun.controller;

import org.pokemonrun.info.Petinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetController {
    @Autowired
    private org.pokemonrun.service.PetInfoService PetInfoService;
    @Autowired
    private org.pokemonrun.service.PetModifyService PetModifyService;

    @GetMapping("/user/{username}/getinfo/{typeID}")//get a pet's info
    public Petinfo getinfo(@PathVariable("username") String username, @PathVariable("typeID") String typeID) {
        return PetInfoService.GetOnePet(username, Integer.parseInt(typeID));
    }

    @GetMapping("/user/{username}/getpets")//get all pets belong to one username
    public List<Petinfo> getpets(@PathVariable("username") String username){
        return PetInfoService.GetPets(username);
    }

    @GetMapping("/user/{username}/own/{typeID}")//findout whether the user has this kind of pet
    public boolean query(@PathVariable("username") String username, @PathVariable("typeID") String typeID)
    {
        return PetInfoService.OwnOrNot(username, Integer.parseInt(typeID));
    }

    @GetMapping("/user/{username}/addexp/{typeID}/exp/{exp}")//add exp to this pet
    public boolean addExp(@PathVariable("username") String username, @PathVariable("typeID") String typeID,@PathVariable("exp") String exp)
    {
        return PetModifyService.addExp(username, Integer.parseInt(typeID), Integer.parseInt(exp));
    }


    @GetMapping("/user/{username}/addnum/{typeID}/num/{num}")//add num to a pet
    public boolean addNum(@PathVariable("username") String username, @PathVariable("typeID") String typeID,@PathVariable("num") String num)
    {
        int id = Integer.parseInt(typeID), nm = Integer.parseInt(num);
        if(nm <= 0){
            return nm == 0;
        }
        if(!PetInfoService.OwnOrNot(username, id))
        {
            if(!PetModifyService.addPet(username, id)){
                return false;
            }
            return PetModifyService.addNum(username, id, nm - 1);
        }
        else
        {
            return PetModifyService.addNum(username, id, nm);
        }
    }



}
