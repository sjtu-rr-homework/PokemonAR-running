package org.pokemonrun.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "petID")
public class Pet {
    private int petID;
    private String username;
    private int grade;
    private int typeID;
    private int exp;
    private int num;

    @Id
    @Column(name = "petID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getPetID() {
        return petID;
    }
    public void setPetID(int petID) {
        this.petID = petID;
    }

    @Basic
    @Column(name = "typeID")
    public int getTypeID() { return typeID; }
    public void setTypeID(int typeID) {this.typeID = typeID; }

    @Basic
    @Column(name = "username")
    public String getUsername() {return username; }
    public void setUsername(String username) {this.username=username; }

    @Basic
    @Column(name = "grade")
    public int getGrade() {return grade; }
    public void setGrade(int grade) {this.grade=grade; }

    @Basic
    @Column(name = "exp")
    public int getExp() {
        return exp;
    }
    public void setExp(int exp) {
        this.exp = exp;
    }


    @Basic
    @Column(name = "num")
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }


    public Pet(){
        // empty
    }

    public Pet(String username,int typeID,int exp,int num,int grade)
    {
        this.username=username;
        this.typeID=typeID;
        this.exp=exp;
        this.num=num;
    }



}
