package org.pokemonrun.serviceimpl;

import org.pokemonrun.dao.PetDao;
import org.pokemonrun.entity.Pet;
import org.pokemonrun.info.Petinfo;
import org.pokemonrun.service.PetInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetInfoServiceImpl implements PetInfoService {
    @Autowired
    private PetDao petDao;


    @Override
    public List<Petinfo> GetPets(String username) {
        List<Pet> templist= petDao.GetPets(username);
        List<Petinfo> tempinfolist=new ArrayList<>();
        for(Pet tempPet:templist)
        {
            Petinfo tempInfo=new Petinfo(tempPet.getPetID(),tempPet.getUsername(),tempPet.getTypeID(),tempPet.getExp(),tempPet.getNum(),tempPet.getGrade());
            tempinfolist.add(tempInfo);
        }
        return tempinfolist;
    }

    @Override
    public Petinfo GetOnePet(String username, int typeID) {
        Pet tempPet = petDao.GetOnePet(username, typeID);
        if(tempPet!=null)
        {
            Petinfo tempInfo=new Petinfo(tempPet.getPetID(),tempPet.getUsername(),tempPet.getTypeID(),tempPet.getExp(),tempPet.getNum(),tempPet.getGrade());
            return tempInfo;
        }
        else
        {
            return null;
        }
    }

    @Override
    public boolean OwnOrNot(String username, int typeID) {
        Pet tempPet = petDao.GetOnePet(username, typeID);
        if(tempPet==null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
