package org.pokemonrun.service;

import org.pokemonrun.info.UserInfoForAdmin;
import org.pokemonrun.info.Userinfo;

public interface GetUserInfo {
    public Userinfo getUserInfo(String username);
    public UserInfoForAdmin AdminGetUserInfo(String username);
    public int GetPet(String username);
}
