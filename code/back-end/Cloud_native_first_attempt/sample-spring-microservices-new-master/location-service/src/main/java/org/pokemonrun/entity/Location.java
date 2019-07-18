package org.pokemonrun.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "locationID")
public class Location {
    public int locationID;
    private String username;
    private double longitude;
    private double latitude;



    @Id
    @Column(name = "locationID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getLocationID() {return locationID; }
    public void setLocationID(int locationID){this.locationID=locationID; }


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


    public Location(){
        // empty
    }

    public Location(String username, double longitude, double latitude)
    {
        this.username=username;
        this.longitude=longitude;
        this.latitude=latitude;
    }



}
