package org.pokemonrun.info;

import java.util.List;

public class Userinfo {
    public String username;
    public int star;
    public String email;
    public int exp;
    public List<String> friends;
    public Userinfo(String username,int star,String email,int exp,List<String> friends)
    {
        this.username=username;
        this.email=email;
        this.star=star;
        this.exp=exp;
        this.friends=friends;
    }
}
