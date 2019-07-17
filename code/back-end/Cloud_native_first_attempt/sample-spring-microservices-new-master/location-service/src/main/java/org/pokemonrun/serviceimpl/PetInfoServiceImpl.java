package org.pokemonrun.serviceimpl;

import org.pokemonrun.dao.PetDao;
import org.pokemonrun.entity.location;
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
        List<location> templist= petDao.GetPets(username);
        List<Petinfo> tempinfolist=new ArrayList<>();
        for(location tempLocation :templist)
        {
            Petinfo tempInfo=new Petinfo(tempLocation.getPetID(), tempLocation.getUsername(), tempLocation.getTypeID(), tempLocation.getExp(), tempLocation.getNum());
            tempinfolist.add(tempInfo);
        }
        return tempinfolist;
    }

    @Override
    public Petinfo GetOnePet(String username, int typeID) {
        location tempLocation = petDao.GetOnePet(username, typeID);
        if(tempLocation !=null)
        {
            Petinfo tempInfo=new Petinfo(tempLocation.getPetID(), tempLocation.getUsername(), tempLocation.getTypeID(), tempLocation.getExp(), tempLocation.getNum());
            return tempInfo;
        }
        else
        {
            return null;
        }
    }

    @Override
    public boolean OwnOrNot(String username, int typeID) {
        location tempLocation = petDao.GetOnePet(username, typeID);
        if(tempLocation ==null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
