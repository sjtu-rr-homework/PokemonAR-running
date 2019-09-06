package org.pokemonrun.serviceimpl;

import org.pokemonrun.dao.CoverDao;
import org.pokemonrun.dao.UserDao;
import org.pokemonrun.entity.Cover;
import org.pokemonrun.entity.User;
import org.pokemonrun.info.Coverinfo;
import org.pokemonrun.service.ModifyUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ModifyUserInfoimpl implements ModifyUserInfo {
    @Autowired
    UserDao UserDao;
    @Autowired
    CoverDao CoverDao;
    @Override
    public boolean ModifyExp(String username, int num) {
        User temp=UserDao.findOne(username);
        if(temp==null)//fault handling
        {
            return false;
        }
        else
        {
            int oldexp=temp.getExp();
            int newexp=oldexp+num;
            if(newexp<0)//fault handling
            {
                return false;
            }
            temp.setExp(newexp);
            UserDao.save(temp);//will automatically overwrite the old one, keep the same ID
        }
        return true;
    }

    @Override
    public boolean blockUser(String username) {//block and unblock , back and forth
        User temp=UserDao.findOne(username);
        if(temp==null)
        {
            return false;
        }
        else
        {
            int star=temp.getStar();
            if(star==-1)//unblock
            {
                temp.setStar(0);
            }
            else if(star==0)//block
            {
                temp.setStar(-1);
            }
            else//fault handling
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
        if(temp1==null||temp2==null)//fault handling
        {
            return false;
        }
        else
        {
            Set<User> follower1=temp1.getFollowers();
            if(follower1.contains(temp2))//already friend
            {
                return false;
            }
            follower1.add(temp2);
            temp1.setFollowers(follower1);
            Set<User> following1=temp1.getFollowing();
            following1.add(temp2);
            temp1.setFollowing(following1);

            UserDao.save(temp1);//and user2 as user1's friend

            Set<User> follower2=temp2.getFollowers();
            follower2.add(temp1);
            temp2.setFollowers(follower2);


            Set<User> following2=temp2.getFollowing();
            following2.add(temp1);
            temp2.setFollowing(following2);

            UserDao.save(temp2);//add user1 as user2's friend
            return true;
        }
    }

    @Override
    public boolean addCover(Coverinfo Coverinfo) {
        User tempUser=UserDao.findOne(Coverinfo.username);
        if(tempUser==null)
        {
            return false;
        }
        else
        {
            Cover tempCover= CoverDao.getOneCover(Coverinfo.username);
            if(tempCover!=null)
            {
                tempCover.pic=Coverinfo.pic;
                CoverDao.save(tempCover);
            }
            else
            {
                Cover newCover=new Cover(Coverinfo.username,Coverinfo.pic);
                CoverDao.save(newCover);
            }
            return true;
        }
    }


}
