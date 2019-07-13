package org.pokemonrun.info;

public class Petinfo {
    public int petID;
    public String username;
    public int typeID;
    public int exp;
    public int num;
    public Petinfo(int petID,String username,int typeID,int exp,int num)
    {
        this.petID=petID;
        this.username=username;
        this.typeID=typeID;
        this.exp=exp;
        this.num=num;
    }
}
