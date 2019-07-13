package org.pokemonrun.info;


import org.pokemonrun.entity.RunningRecord;

public class RunningRecordInfo {
    private String username;
    private String startTime;
    private String course;
    private String duration;
    private String courseLength;
    public RunningRecordInfo() {
        // empty
    }
    public RunningRecordInfo(RunningRecord runningRecord) {
        this.username = runningRecord.getUsername();
        this.startTime = String.valueOf(runningRecord.getStartTime());
        this.course = runningRecord.getCourse();
        this.duration = String.valueOf(runningRecord.getDuration());
        this.courseLength = String.valueOf(runningRecord.getCourseLength());
    }
    public RunningRecordInfo(String username, String startTime, String course,
                             String duration, String courseLength) {
        this.username = username;
        this.startTime = startTime;
        this.course = course;
        this.duration = duration;
        this.courseLength = courseLength;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCourseLength() {
        return courseLength;
    }

    public void setCourseLength(String courseLength) {
        this.courseLength = courseLength;
    }
}

