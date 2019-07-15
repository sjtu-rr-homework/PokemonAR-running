package org.pokemonrun.serviceimpl;


import org.pokemonrun.entity.User;
import org.pokemonrun.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceimpl implements LoginService {
    @Autowired
    private org.pokemonrun.dao.UserDao UserDao;


    @Override
    public boolean Login(String username, String password) {

        User tempUser=UserDao.findOne(username);
        if(tempUser==null)
        {
           return false;
        }
        else
        {
            if(tempUser.getStar()==-1)
            {
                return false;
            }
            else
            {
                if (tempUser.getPassword().equals(password))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
    }


}
