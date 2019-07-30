package org.pokemonrun.info;

public class CampusRunningInfo {
    private String mileage;
    private String mileageGoal;
    public CampusRunningInfo(String mileage, String mileageGoal){
        this.mileage = mileage;
        this.mileageGoal = mileageGoal;
    }
    public String getMileage(){
        return mileage;
    }
    public String getMileageGoal(){
        return mileageGoal;
    }
}
