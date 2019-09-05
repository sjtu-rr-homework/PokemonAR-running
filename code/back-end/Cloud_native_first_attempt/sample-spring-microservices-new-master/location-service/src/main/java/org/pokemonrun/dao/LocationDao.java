package org.pokemonrun.dao;

import org.pokemonrun.entity.Location;

import java.util.List;

public interface LocationDao {
    List<Location> GetAll();
    List<Location> GetNearBy(double minlat,double maxlat,double minlong, double maxlong);
    Location GetOneLocation(String username);
    void save(Location location);
}
