package org.pokemonrun.info;

// for front-end reader
public class SemesterDetailedInfo {
    private String mileageGoal;
    private String endTime;
    private String startTime;
    private String minSpeed;
    private String maxSpeed;
    public SemesterDetailedInfo(String mileageGoal, String endTime, String startTime,
                                String minSpeed, String maxSpeed){
        this.mileageGoal = mileageGoal;
        this.endTime = endTime;
        this.startTime = startTime;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
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

    public String getMinSpeed() {
        return minSpeed;
    }

    public String getMaxSpeed() {
        return maxSpeed;
    }
}
