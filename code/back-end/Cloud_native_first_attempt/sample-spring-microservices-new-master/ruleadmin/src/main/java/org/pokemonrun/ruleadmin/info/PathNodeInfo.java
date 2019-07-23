package org.pokemonrun.ruleadmin.info;

public class PathNodeInfo {
    private String lng;
    private String lat;
    public PathNodeInfo(String lng, String lat){
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
