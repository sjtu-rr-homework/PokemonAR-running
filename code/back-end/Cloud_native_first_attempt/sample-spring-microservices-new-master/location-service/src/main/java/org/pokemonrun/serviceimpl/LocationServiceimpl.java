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
        double longitude= Double.parseDouble(Locationinfo.longitude);
        double latitude = Double.parseDouble(Locationinfo.latitude);
        double r = 6371;//the redius of the earth
        double dis = 2.5;//the distance is no more than 2.5 km
        double dlng =  2*Math.asin(Math.sin(dis/(2*r))/Math.cos(latitude*Math.PI/180));
        dlng = dlng*180/Math.PI;//角度转为弧度
        double dlat = dis/r;
        dlat = dlat*180/Math.PI;
        double minlat =latitude-dlat;// the latitude range
        double maxlat = latitude+dlat;
        double minlng = longitude -dlng;//the longitude range
        double maxlng = longitude + dlng;
        String tempName=Locationinfo.username;
        List<Location> res=LocationDao.GetNearBy(minlat,maxlat,minlng,maxlng);
        List<Locationinfo> resinfo = new ArrayList<>();
        for(int j=0;j<res.size();j++)
        {
            if(!res.get(j).getUsername().equals(Locationinfo.username))
            {
                Locationinfo tempinfo = new Locationinfo(res.get(j).getUsername(), Double.toString(res.get(j).getLongitude()), Double.toString(res.get(j).getLatitude()));
                resinfo.add(tempinfo);
            }
        }
        Location temp= LocationDao.GetOneLocation(Locationinfo.username);//get old location for this user
        if(temp==null)//refresh location
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
        if(resinfo.size()==0)//just for fault handling
        {
            return null;
        }
        return resinfo;
    }

    @Override
    public boolean refreshLocation(Locationinfo Locationinfo) {
        Location temp= LocationDao.GetOneLocation(Locationinfo.username);
        if(temp==null)// have no location for this user
        {
            Location temp1 = new Location(Locationinfo.username, Double.parseDouble(Locationinfo.longitude), Double.parseDouble(Locationinfo.latitude));
            LocationDao.save(temp1);
        }
        else//replace the old location , no duplicate
        {
            temp.setLatitude(Double.parseDouble(Locationinfo.latitude));
            temp.setLongitude(Double.parseDouble(Locationinfo.longitude));
            LocationDao.save(temp);
        }
        return true;
    }
}
