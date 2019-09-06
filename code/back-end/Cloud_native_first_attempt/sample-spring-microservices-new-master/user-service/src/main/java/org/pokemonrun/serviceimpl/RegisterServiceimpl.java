package org.pokemonrun.serviceimpl;

import org.pokemonrun.entity.User;
import org.pokemonrun.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceimpl implements RegisterService {
    @Autowired
    private org.pokemonrun.dao.UserDao UserDao;

    @Override
    public boolean Register(String username, String password, String email, int star) {

        User tempUser=UserDao.findOne(username);
        if(tempUser==null) {
            UserDao.save(username, password, email, star);
            return true;
        }
        else//already registered
        {
            return false;
        }

    }
}
