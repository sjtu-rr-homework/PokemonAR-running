package org.pokemonrun.serviceimpl;

import org.pokemonrun.dao.UserDao;
import org.pokemonrun.entity.Cover;
import org.pokemonrun.entity.User;
import org.pokemonrun.info.Friendinfo;
import org.pokemonrun.info.UserInfoForAdmin;
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
                Cover tempcover=UserDao.getOneCover(tempUser.getUsername());
                Friendinfo tempInfo;
                if(tempcover!=null)
                {
                    tempInfo=new Friendinfo(tempUser.getUsername(),tempcover.pic);
                }
                else
                {
                    tempInfo = new Friendinfo(tempUser.getUsername(), null);
                }
                friends.add(tempInfo);
            }
            Cover mycover=UserDao.getOneCover(username);
            Userinfo tempinfo;
            if(mycover!=null)
            {
                tempinfo = new Userinfo(temp.getUsername(), temp.getStar(), temp.getEmail(), temp.getExp(), temp.getPet(), temp.getDistance(), friends, mycover.pic);
            }
            else
            {
                tempinfo = new Userinfo(temp.getUsername(), temp.getStar(), temp.getEmail(), temp.getExp(), temp.getPet(), temp.getDistance(), friends, null);
            }
            return tempinfo;
        }
    }

    @Override
    public UserInfoForAdmin AdminGetUserInfo(String username) {
        User temp=UserDao.findOne(username);
        if(temp==null)
        {
            return null;
        }
        else
        {
            UserInfoForAdmin tempinfo=new UserInfoForAdmin(temp.getUsername(),temp.getStar(),temp.getEmail(),temp.getExp(),temp.getUserID(),temp.getPet(),temp.getDistance());
            return tempinfo;
        }
    }

    @Override
    public int GetPet(String username) {
        User temp=UserDao.findOne(username);
        if(temp==null)
        {
            return -1;
        }
        else
        {
            int temppet=temp.getPet();
            return temppet;
        }
    }

    @Override
    public List<String> GetAllUser() {
        List<User> tempList=UserDao.findAll();
        List<String> usernamelist=new ArrayList<>();
        for(User tempUser:tempList)
        {
            usernamelist.add(tempUser.getUsername());
        }
        return usernamelist;
    }
}
