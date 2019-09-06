package org.pokemonrun.service;

import org.pokemonrun.info.Coverinfo;

public interface ModifyUserInfo {
    public boolean ModifyExp(String username,int num);
    public boolean blockUser(String username);//block user from login (set star)
    public boolean SetPet(String username, int pet);//user set the pet to fight
    public boolean AddDistance(String username, double distance);//add to the whole distance a user has run
    public boolean addFriend(String username,String friendname);//add a user as another user's friend, automatically back and forth
    public boolean addCover(Coverinfo Coverinfo);
}
