package org.pokemonrun.serviceimpl;

import org.pokemonrun.dao.UserDao;
import org.pokemonrun.entity.User;
import org.pokemonrun.info.Friendinfo;
import org.pokemonrun.info.Userinfo;
import org.pokemonrun.service.GetUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
            List<Friendinfo> friends = new ArrayList<>();
            Set<User> friendsset= temp.getFollowers();
            for(User tempUser:friendsset)
            {
                Friendinfo tempInfo=new Friendinfo(tempUser.getUsername());
                friends.add(tempInfo);
            }
            Userinfo tempinfo=new Userinfo(temp.getUsername(),temp.getStar(),temp.getEmail(),temp.getExp(),friends);
            return tempinfo;
        }
    }

    @Override
    public Integer getExp(String username) {
        User temp=UserDao.findOne(username);
        if(temp==null)
        {
            return null;
        }
        else
        {
            return temp.getExp();
        }

    }
}
