package org.pokemonrun.info;

public class BasicRuleInfo {
    private String mileageRequirement;
    private String minSpeed;
    private String maxSpeed;
    public BasicRuleInfo(String mileageRequirement, String minSpeed, String maxSpeed){
        this.mileageRequirement = mileageRequirement;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
    }

    public String getMileageRequirement() {
        return mileageRequirement;
    }

    public String getMinSpeed() {
        return minSpeed;
    }

    public String getMaxSpeed() {
        return maxSpeed;
    }
}
