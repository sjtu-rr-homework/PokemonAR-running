package org.pokemonrun.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "userID")
public class User implements Serializable {
    private int userID;
    private String username;
    private String password;
    private String email;
    private int star;
    private int exp;

    @Id
    @Column(name = "userID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getUserID() { return userID; }

    public void setUserID(int userID) { this.userID=userID; }

    @Basic
    @Column(name = "username" ,unique=true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "star")
    public int getStar() { return star; }

    public void setStar(int star) { this.star =star; }

    @Basic
    @Column(name = "exp")
    public int getExp() {return exp;}
    public void setExp(int exp) { this.exp=exp; }



    private User()
    {

    }

    public User(String username,String password,String email,int star,int exp)
    {
        this.username=username;
        this.password=password;
        this.email=email;
        this.star=star;
        this.exp=exp;


    }
}
