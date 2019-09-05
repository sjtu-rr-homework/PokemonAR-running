package org.pokemonrun.dao;

import org.pokemonrun.entity.Location;

import java.util.List;

public interface LocationDao {

    List<Location> GetNearBy(double minlat,double maxlat,double minlong, double maxlong);//get nearby according to range
    Location GetOneLocation(String username);
    void save(Location location);
}
