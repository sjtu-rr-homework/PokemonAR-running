package org.pokemonrun.service;

public interface ModifyUserInfo {
    public boolean ModifyExp(String username,int num);
    public boolean blockUser(String username);
    public boolean SetPet(String username, int pet);
    public boolean AddDistance(String username, double distance);
}
