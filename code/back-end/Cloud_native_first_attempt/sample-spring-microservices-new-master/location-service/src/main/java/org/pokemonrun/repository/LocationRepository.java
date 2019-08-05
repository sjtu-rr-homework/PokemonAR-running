package org.pokemonrun.repository;

import org.pokemonrun.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    Location findByUsername(String username);


}
