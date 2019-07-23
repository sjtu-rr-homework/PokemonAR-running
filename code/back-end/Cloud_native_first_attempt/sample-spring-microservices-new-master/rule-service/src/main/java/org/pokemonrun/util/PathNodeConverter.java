package org.pokemonrun.util;

import org.pokemonrun.info.PathNodeInfo;
import org.pokemonrun.entity.PathNode;

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
