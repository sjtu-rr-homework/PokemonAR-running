package org.pokemonrun.info;

public class UserInfoForAdmin {
    public int userID;
    public String username;
    public int star;
    public String email;
    public int exp;
    public int pet;
    public UserInfoForAdmin(String username,int star,String email,int exp, int userID,int pet)
    {
        this.username=username;
        this.email=email;
        this.star=star;
        this.exp=exp;
        this.userID=userID;
        this.pet=pet;
    }
}
