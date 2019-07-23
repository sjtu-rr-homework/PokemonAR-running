package org.pokemonrun.ruleadmin.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "nodeID")
public class PathNode {
    private int nodeID;
    private double longitude;
    private double latitude;

    public PathNode(){
        // empty
    }

    @Id
    @Column(name = "nodeID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getNodeID() {
        return nodeID;
    }

    public void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }

    @Basic
    @Column(name = "longitude")
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "latitude")
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
