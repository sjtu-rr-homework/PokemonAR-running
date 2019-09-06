package org.pokemonrun.info;

public class UserInfoForAdmin {//user info which is visible to admin
    public int userID;
    public String username;
    public int star;
    public String email;
    public int exp;
    public int pet;
    public double distance;
    public UserInfoForAdmin(String username,int star,String email,int exp, int userID,int pet, double distance)
    {
        this.username=username;
        this.email=email;
        this.star=star;
        this.exp=exp;
        this.userID=userID;
        this.pet=pet;
        this.distance=distance;
    }
}
