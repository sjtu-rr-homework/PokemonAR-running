package org.pokemonrun.serviceimpl;

import org.pokemonrun.dao.CoverDao;
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
    @Autowired
    CoverDao CoverDao;
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
            Userinfo tempinfo=new Userinfo(temp.getUsername(),temp.getStar(),temp.getEmail(),temp.getExp(),temp.getPet(),temp.getDistance(),friends);
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

    @Override
    public byte[] getCover(String username) {
        User tempUser=UserDao.findOne(username);
        if(tempUser==null)
        {
            return null;
        }
        Cover tempCover=CoverDao.getOneCover(username);
        if(tempCover==null)
        {
            return null;
        }
        else
        {
            return tempCover.pic;
        }
    }

    @Override
    public int getExp(String username) {
        User tempUser=UserDao.findOne(username);
        if(tempUser==null)
        {
            return 0;
        }
        else
        {
            return tempUser.getExp();
        }
    }
}
