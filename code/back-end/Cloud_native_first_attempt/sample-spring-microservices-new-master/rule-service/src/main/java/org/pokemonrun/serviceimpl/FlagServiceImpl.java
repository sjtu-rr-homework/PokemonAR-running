package org.pokemonrun.serviceimpl;

import org.pokemonrun.dao.FlagDao;
import org.pokemonrun.entity.Flag;
import org.pokemonrun.info.FlagInfo;
import org.pokemonrun.service.FlagService;
import org.pokemonrun.util.FlagConverter;
import org.pokemonrun.util.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FlagServiceImpl implements FlagService {
    @Autowired
    private FlagDao flagDao;

    @Override
    public List<FlagInfo> getFlags() {
        List<Flag> flags = flagDao.getFlags();
        List<FlagInfo> list = new LinkedList<>();
        for (Flag flag : flags) {
            list.add(FlagConverter.toInfo(flag));
        }
        return list;
    }

    @Override
    public boolean setFlags(List<FlagInfo> flags){
        List<Flag> list = new LinkedList<>();
        for(FlagInfo info : flags){
            Flag flag = FlagConverter.toEntity(info);
            double lng = flag.getLongitude();
            double lat = flag.getLatitude();
            if(lng < -180 || lng > 180 || lat < -90 || lat > 90){
                // invalid position on map
                return false;
            }
            list.add(flag);
        }
        flagDao.replaceFlags(list);
        return true;
    }

    @Override
    public List<FlagInfo> getRandomRoute(double lng, double lat) {
        List<Flag> flags = flagDao.getFlags();
        List<Point> points = new LinkedList<>();
        List<FlagInfo> route = new LinkedList<>();
        // generate route
        int num = 5
                ;
        double range = 0.00926275;
        //double range = 0.002;
        Point start = new Point(lng, lat * 2);
        // filter flags that are out of border or too far
        for(Flag flag : flags){
            Point p = new Point(flag.getLongitude(), flag.getLatitude() * 2);
            if(true && p.distanceFrom(start) <= range){ // TODO: inside border
                points.add(p);
            }
        }
        // insert num points into the route
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        for(int i = 0; i < num; i++){
            int j = Math.abs(rnd.nextInt());
            if(points.isEmpty()){
                break;
            }
            j = j % points.size();
            Point p = points.get(j);
            points.remove(j);
            route.add(new FlagInfo(String.valueOf(p.getX()), String.valueOf(p.getY() / 2)));
        }
        return route;
    }
}
