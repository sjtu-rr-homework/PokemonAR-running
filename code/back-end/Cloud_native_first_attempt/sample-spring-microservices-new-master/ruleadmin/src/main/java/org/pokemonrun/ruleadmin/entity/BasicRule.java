package org.pokemonrun.ruleadmin.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "ruleID")
public class BasicRule {
    private int ruleID;
    private Double mileageRequirement = null;
    private Double minSpeed = null;
    private Double maxSpeed = null;

    public BasicRule(){
        // empty
    }

    @Id
    @Column(name = "ruleID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getRuleID() {
        return ruleID;
    }

    public void setRuleID(int ruleID) {
        this.ruleID = ruleID;
    }

    @Column(name = "mileageRequirement")
    public Double getMileageRequirement() {
        return mileageRequirement;
    }

    public void setMileageRequirement(Double mileageRequirement) {
        this.mileageRequirement = mileageRequirement;
    }

    @Column(name = "minSpeed")
    public Double getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(Double minSpeed) {
        this.minSpeed = minSpeed;
    }

    @Column(name = "maxSpeed")
    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
