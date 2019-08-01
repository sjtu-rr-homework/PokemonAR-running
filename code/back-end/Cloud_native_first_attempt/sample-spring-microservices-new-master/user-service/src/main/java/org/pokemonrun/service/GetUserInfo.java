package org.pokemonrun.service;

import org.pokemonrun.info.Coverinfo;
import org.pokemonrun.info.UserInfoForAdmin;
import org.pokemonrun.info.Userinfo;

import java.util.List;

public interface GetUserInfo {
    public Userinfo getUserInfo(String username);
    public UserInfoForAdmin AdminGetUserInfo(String username);
    public int GetPet(String username);
    public List<String> GetAllUser();
    public Coverinfo GetCover(String username);
}
