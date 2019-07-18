package org.pokemonrun.serviceimpl;

import org.pokemonrun.dao.UserDao;
import org.pokemonrun.entity.User;
import org.pokemonrun.service.ModifyUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ModifyUserInfoimpl implements ModifyUserInfo {
    @Autowired
    UserDao UserDao;
    @Override
    public boolean ModifyExp(String username, int num) {
        User temp=UserDao.findOne(username);
        if(temp==null)
        {
            return false;
        }
        else
        {
            int oldexp=temp.getExp();
            int newexp=oldexp+num;
            if(newexp<0)
            {
                return false;
            }
            temp.setExp(newexp);
            UserDao.save(temp);
        }
        return true;
    }

    @Override
    public boolean addFriend(String username, String friendname) {
        User temp1=UserDao.findOne(username);
        User temp2=UserDao.findOne(friendname);
        if(temp1==null||temp2==null)
        {
            return false;
        }
        else
        {
            Set<User> follower1=temp1.getFollowers();
            follower1.add(temp2);
            temp1.setFollowers(follower1);

            Set<User> following1=temp1.getFollowing();
            following1.add(temp2);
            temp1.setFollowing(following1);

            UserDao.save(temp1);

            Set<User> follower2=temp2.getFollowers();
            follower2.add(temp1);
            temp2.setFollowers(follower2);


            Set<User> following2=temp2.getFollowing();
            following2.add(temp1);
            temp2.setFollowing(following2);

            UserDao.save(temp2);
        }
        return false;
    }
}
