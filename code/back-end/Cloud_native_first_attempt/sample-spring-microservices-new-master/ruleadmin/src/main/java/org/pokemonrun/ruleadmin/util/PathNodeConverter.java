package org.pokemonrun.ruleadmin.util;

import org.pokemonrun.ruleadmin.entity.PathNode;
import org.pokemonrun.ruleadmin.info.PathNodeInfo;

public class PathNodeConverter {
    public static PathNodeInfo toInfo(PathNode flag){
        String lng = String.valueOf(flag.getLongitude());
        String lat = String.valueOf(flag.getLatitude());
        PathNodeInfo info = new PathNodeInfo(lng, lat);
        return info;
    }
    public static PathNode toEntity(PathNodeInfo info){
        double lng = Double.parseDouble(info.getLng());
        double lat = Double.parseDouble(info.getLat());
        PathNode node = new PathNode();
        node.setLongitude(lng);
        node.setLatitude(lat);
        return node;
    }
}
