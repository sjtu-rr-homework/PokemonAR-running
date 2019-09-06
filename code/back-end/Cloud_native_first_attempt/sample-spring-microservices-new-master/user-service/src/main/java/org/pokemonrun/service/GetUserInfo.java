package org.pokemonrun.service;

import org.pokemonrun.info.Coverinfo;
import org.pokemonrun.info.UserInfoForAdmin;
import org.pokemonrun.info.Userinfo;

import java.util.List;

public interface GetUserInfo {
    public Userinfo getUserInfo(String username);
    public UserInfoForAdmin AdminGetUserInfo(String username);
    public int GetPet(String username);//get the pet which is going to fight
    public List<String> GetAllUser();//used for admin to get all username
    public Coverinfo getCover(String username);//get avatar
    public int getExp(String username);
}
