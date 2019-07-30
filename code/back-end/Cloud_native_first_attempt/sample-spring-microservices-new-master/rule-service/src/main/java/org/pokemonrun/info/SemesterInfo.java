package org.pokemonrun.info;

public class SemesterInfo {
    private String mileageGoal;
    private String endTime;
    public SemesterInfo(String mileageGoal, String endTime){
        this.mileageGoal = mileageGoal;
        this.endTime = endTime;
    }

    public String getMileageGoal() {
        return mileageGoal;
    }

    public String getEndTime() {
        return endTime;
    }
}
