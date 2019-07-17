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
    public List<Location> GetAll() {
        return LocationRepository.findAll();
    }

    @Override
    public Location GetOneLocation(String username) {
        return LocationRepository.findByUsername(username);
    }

    @Override
    public void save(Location location) {
        LocationRepository.save(location);
    }


}
