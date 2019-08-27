package org.pokemonrun.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "momentid")
public class FastMoment {
    private String momentid;
    private long timestamp;
    @Id
    @Column(name = "momentid")
    public String getMomentid()
    {
        return momentid;
    }
    public void setMomentid()
    {
        this.momentid=momentid;
    }

    @Column(name = "timestamp")
    public long getTimestamp()
    {
        return timestamp;
    }
    public void setTimestamp()
    {
        this.timestamp=timestamp;
    }

    public FastMoment()
    {

    }

    public FastMoment(String momentid, long timestamp)
    {
        this.momentid=momentid;
        this.timestamp=timestamp;
    }

}
