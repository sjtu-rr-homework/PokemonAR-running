package org.pokemonrun.ruleadmin.info;

public class FlagInfo {
    private String lng;
    private String lat;
    public FlagInfo(String lng, String lat){
        this.lng = lng;
        this.lat = lat;
    }
    public String getLng(){
        return lng;
    }
    public String getLat(){
        return lat;
    }
}
