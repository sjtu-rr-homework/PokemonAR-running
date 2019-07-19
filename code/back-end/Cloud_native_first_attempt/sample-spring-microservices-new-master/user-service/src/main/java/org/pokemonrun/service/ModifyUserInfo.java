package org.pokemonrun.service;

public interface ModifyUserInfo {
    public boolean ModifyExp(String username,int num);
    public boolean addFriend(String username,String friendname);
}
