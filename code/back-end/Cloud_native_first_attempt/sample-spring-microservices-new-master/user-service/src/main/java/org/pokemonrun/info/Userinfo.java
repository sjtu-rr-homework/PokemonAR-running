package org.pokemonrun.info;

import java.util.List;

public class Userinfo {
    public String username;
    public int star;
    public String email;
    public int exp;
    public int pet;
    public double distance;
    public List<Friendinfo> friends;
    public Userinfo(String username,int star,String email,int exp,int pet, double distance,List<Friendinfo> friends)
    {
        this.username=username;
        this.email=email;
        this.star=star;
        this.exp=exp;
        this.pet=pet;
        this.distance=distance;
        this.friends=friends;

    }
}
