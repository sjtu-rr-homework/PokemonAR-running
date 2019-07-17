package org.pokemonrun.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "username")
public class location {
    private String username;
    private double longitude;
    private double latitude;


    @Id
    @Basic
    @Column(name = "username")
    public String getUsername() {return username; }
    public void setUsername(String username) {this.username=username; }


    @Basic
    @Column(name = "longitude")
    public double getLongitude() {return longitude; }
    public void setLongitude(double longitude) {this.longitude=longitude; }

    @Basic
    @Column(name = "latitude")
    public double getLatitude() {return latitude; }
    public void setLatitude(double latitude) {this.latitude=latitude; }


    public location(){
        // empty
    }

    public location(String username)
    {
        this.username=username;

    }



}
