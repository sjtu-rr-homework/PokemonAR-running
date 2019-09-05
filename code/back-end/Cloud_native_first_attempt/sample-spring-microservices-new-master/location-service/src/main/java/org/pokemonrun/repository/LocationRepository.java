package org.pokemonrun.repository;

import org.pokemonrun.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    Location findByUsername(String username);

    @Query(value="select locationID,username,longitude,latitude from Location l where l.latitude>?1 and l.latitude<?2 and l.longitude>?3 and l.longitude<?4", nativeQuery = true)
    List<Location> findByLatitudeBetweenaAndLongitudeBetween(double minlat,double maxlat,double minlong, double maxlong);
}
