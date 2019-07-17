package org.pokemonrun.serviceimpl;

import org.pokemonrun.dao.PetDao;
import org.pokemonrun.entity.location;
import org.pokemonrun.service.PetModifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetModifyServiceimpl implements PetModifyService {
    @Autowired
    private PetDao petDao;

    @Override
    public boolean addPet(String username, int typeID) {
        location tempLocation = petDao.GetOnePet(username, typeID);
        if(tempLocation !=null)
        {
            return false;
        }
        else
        {
            location tempLocation1 =new location(username,typeID,0,1);
            petDao.save(tempLocation1);
            return true;
        }
    }

    @Override
    public boolean addExp(String username, int typeID, int exp) {
        location tempLocation = petDao.GetOnePet(username, typeID);
        if(tempLocation ==null)
        {
            return false;
        }
        else
        {
            int oldexp= tempLocation.getExp();
            int newexp=oldexp+exp;
            tempLocation.setExp(newexp);
            petDao.save(tempLocation);
            return true;
        }
    }

    @Override
    public boolean addNum(String username, int typeID, int num) {
        location tempLocation = petDao.GetOnePet(username, typeID);
        if(tempLocation ==null)
        {
            return false;
        }
        else
        {
            int oldnum= tempLocation.getNum();
            int newnum=oldnum+num;
            tempLocation.setNum(newnum);
            petDao.save(tempLocation);
            return true;
        }

    }
}
