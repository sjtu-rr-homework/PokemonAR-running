package org.pokemonrun.info;

public class BasicRuleInfo {
    private String minSpeed;
    private String maxSpeed;
    public BasicRuleInfo(String minSpeed, String maxSpeed){
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
    }

    public String getMinSpeed() {
        return minSpeed;
    }

    public String getMaxSpeed() {
        return maxSpeed;
    }
}
