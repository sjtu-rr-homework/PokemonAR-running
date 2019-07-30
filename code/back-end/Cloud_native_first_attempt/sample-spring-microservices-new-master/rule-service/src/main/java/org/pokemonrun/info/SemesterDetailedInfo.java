package org.pokemonrun.info;

// for front-end reader
public class SemesterDetailedInfo {
    private String mileageGoal;
    private String endTime;
    private String startTime;
    public SemesterDetailedInfo(String mileageGoal, String endTime, String startTime){
        this.mileageGoal = mileageGoal;
        this.endTime = endTime;
        this.startTime = startTime;
    }

    public String getMileageGoal() {
        return mileageGoal;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getStartTime() {
        return startTime;
    }
}
