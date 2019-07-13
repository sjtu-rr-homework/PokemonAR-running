package org.pokemonrun.runningrecordservice.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.pokemonrun.runningrecordservice.info.RunningRecordInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "recordID")
public class RunningRecord implements Serializable {
    private long recordID;
    private String username;
    private Long startTime;
    private String course;
    private Long duration;
    private Double courseLength;

    public RunningRecord() {
        // empty
    }

    public RunningRecord(RunningRecordInfo info) {
        username = info.getUsername();
        startTime = Long.parseLong(info.getStartTime());
        course = info.getCourse();
        duration = Long.parseLong(info.getDuration());
        courseLength = Double.parseDouble(info.getCourseLength());
    }

    @Id
    @Column(name = "recordID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getRecordID() {
        return recordID;
    }

    public void setRecordID(long recordID) {
        this.recordID = recordID;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "startTime")
    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    @Lob
    @Column(name = "course")
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Column(name = "duration")
    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    @Column(name = "courseLength")
    public Double getCourseLength() {
        return courseLength;
    }

    public void setCourseLength(Double courseLength) {
        this.courseLength = courseLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RunningRecord that = (RunningRecord) o;

        if (!Objects.equals(username, that.username)) return false;
        if (!Objects.equals(startTime, that.startTime)) return false;
        if (!Objects.equals(course, that.course)) return false;
        if (!Objects.equals(duration, that.duration)) return false;
        if (!Objects.equals(courseLength, that.courseLength)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (username != null ? username.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (course != null ? course.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (courseLength != null ? courseLength.hashCode() : 0);
        return result;
    }

}
