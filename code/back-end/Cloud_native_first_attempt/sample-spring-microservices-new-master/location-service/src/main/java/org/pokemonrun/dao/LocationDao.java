package org.pokemonrun.dao;

import org.pokemonrun.entity.Location;

import java.util.List;

public interface LocationDao {
    List<Location> GetAll();
    Location GetOneLocation(String username);
    void save(Location location);
}
