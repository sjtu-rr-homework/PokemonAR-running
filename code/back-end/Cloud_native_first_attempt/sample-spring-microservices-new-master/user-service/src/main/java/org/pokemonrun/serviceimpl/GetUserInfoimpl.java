package org.pokemonrun.serviceimpl;

import org.pokemonrun.dao.UserDao;
import org.pokemonrun.entity.User;
import org.pokemonrun.info.Userinfo;
import org.pokemonrun.service.GetUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetUserInfoimpl implements GetUserInfo {
    @Autowired
    UserDao UserDao;
    @Override
    public Userinfo getUserInfo(String username) {
        User temp=UserDao.findOne(username);
        if(temp==null)
        {
            return null;
        }
        else
        {
            Userinfo tempinfo=new Userinfo(temp.getUsername(),temp.getStar(),temp.getEmail(),temp.getExp());
            return tempinfo;
        }
    }
}