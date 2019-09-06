package org.pokemonrun.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "semesterID")
public class Semester {
    private int semesterID;
    private double mileageGoal;
    private long endTime;
    private long startTime;

    @Id
    @Column(name = "semesterID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getSemesterID() {
        return semesterID;
    }

    public void setSemesterID(int semesterID) {
        this.semesterID = semesterID;
    }

    @Basic
    @Column(name = "mileageGoal")
    public double getMileageGoal() {
        return mileageGoal;
    }

    public void setMileageGoal(double mileageGoal) {
        this.mileageGoal = mileageGoal;
    }

    @Basic
    @Column(name = "endTime")
    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "startTime")
    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
