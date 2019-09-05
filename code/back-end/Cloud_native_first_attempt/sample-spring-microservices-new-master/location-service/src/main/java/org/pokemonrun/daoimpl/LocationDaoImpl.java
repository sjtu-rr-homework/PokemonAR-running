package org.pokemonrun.daoimpl;

import org.pokemonrun.dao.LocationDao;
import org.pokemonrun.entity.Location;
import org.pokemonrun.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationDaoImpl implements LocationDao {
    @Autowired
    LocationRepository LocationRepository;

    @Override
    public List<Location> GetNearBy(double minlat, double maxlat, double minlong, double maxlong) { return LocationRepository.findByLatitudeBetweenaAndLongitudeBetween(minlat,maxlat,minlong,maxlong); }

    @Override
    public Location GetOneLocation(String username) {
        return LocationRepository.findByUsername(username);
    }//get someone's location according to username

    @Override
    public void save(Location location) {
        LocationRepository.save(location);
    }


}
