package org.pokemonrun.serviceimpl;

import org.pokemonrun.dao.LocationDao;
import org.pokemonrun.entity.Location;
import org.pokemonrun.info.Locationinfo;
import org.pokemonrun.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class LocationServiceimpl implements LocationService {
    @Autowired
    LocationDao LocationDao;

    @Override
    public List<Locationinfo> getNearBy(Locationinfo Locationinfo) {
        List<Location> templist = LocationDao.GetAll();
        double longitude= Double.parseDouble(Locationinfo.longitude);
        double latitude = Double.parseDouble(Locationinfo.latitude);
        double r = 6371;//地球半径千米
        double dis = 0.5;//0.5千米距离
        double dlng =  2*Math.asin(Math.sin(dis/(2*r))/Math.cos(latitude*Math.PI/180));
        dlng = dlng*180/Math.PI;//角度转为弧度
        double dlat = dis/r;
        dlat = dlat*180/Math.PI;
        double minlat =latitude-dlat;
        double maxlat = latitude+dlat;
        double minlng = longitude -dlng;
        double maxlng = longitude + dlng;
        List<Location> res = new ArrayList<>();
        for(int i=0;i<templist.size();i++)
        {
            double tempLong=templist.get(i).getLongitude();
            double tempLati=templist.get(i).getLatitude();
            if(tempLong>minlng&&tempLong<maxlng&&tempLati>minlat&&tempLati<maxlat)
            {
                res.add(templist.get(i));
            }
        }
        List<Locationinfo> resinfo = new ArrayList<>();
        for(int j=0;j<res.size();j++)
        {
            Locationinfo tempinfo= new Locationinfo(res.get(j).getUsername(),Double.toString(res.get(j).getLongitude()),Double.toString(res.get(j).getLatitude()));
            resinfo.add(tempinfo);
        }
        Location temp= new Location(Locationinfo.username,Double.parseDouble(Locationinfo.longitude),Double.parseDouble(Locationinfo.latitude));
        LocationDao.save(temp);
        return resinfo;
    }

    @Override
    public boolean refreshLocation(Locationinfo Locationinfo) {
        Location temp= LocationDao.GetOneLocation(Locationinfo.username);
        if(temp==null)
        {
            Location temp1 = new Location(Locationinfo.username, Double.parseDouble(Locationinfo.longitude), Double.parseDouble(Locationinfo.latitude));
            LocationDao.save(temp1);
        }
        else
        {
            temp.setLatitude(Double.parseDouble(Locationinfo.latitude));
            temp.setLongitude(Double.parseDouble(Locationinfo.longitude));
            LocationDao.save(temp);
        }
        return true;
    }
}
