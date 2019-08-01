package org.pokemonrun.serviceimpl;

import org.pokemonrun.dao.UserDao;
import org.pokemonrun.entity.Cover;
import org.pokemonrun.entity.User;
import org.pokemonrun.info.Coverinfo;
import org.pokemonrun.repository.CoverRepository;
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
    public boolean blockUser(String username) {
        User temp=UserDao.findOne(username);
        if(temp==null)
        {
            return false;
        }
        else
        {
            int star=temp.getStar();
            if(star==-1)
            {
                temp.setStar(0);
            }
            else if(star==-2)
            {
                temp.setStar(1);
            }
            else if(star==1)
            {
                temp.setStar(-2);
            }
            else if(star==0)
            {
                temp.setStar(-1);
            }
            else
            {
                return false;
            }
            UserDao.save(temp);
            return true;
        }
    }

    @Override
    public boolean SetPet(String username, int pet) {
        User temp=UserDao.findOne(username);
        if(temp==null)
        {
            return false;
        }
        else
        {
            temp.setPet(pet);
            UserDao.save(temp);
            return true;
        }
    }

    @Override
    public boolean AddDistance(String username, double distance) {
        User temp=UserDao.findOne(username);
        if(temp==null)
        {
            return false;
        }
        else
        {
            if(distance<=0)
            {
                return false;
            }
            else
            {
                double tempDistance=temp.getDistance();
                tempDistance+=distance;
                temp.setDistance(tempDistance);
                UserDao.save(temp);
                return true;
            }
        }
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
            if(follower1.contains(temp2))
            {
                return false;
            }
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
            return true;
        }
    }

    @Override
    public boolean addCover(Coverinfo Coverinfo) {
        User tempuser=UserDao.findOne(Coverinfo.username);
        if(tempuser==null)
        {
            return false;
        }
        Cover temp=new Cover(Coverinfo.username,Coverinfo.cover);
        UserDao.saveCover(temp);
        return true;

    }


}
