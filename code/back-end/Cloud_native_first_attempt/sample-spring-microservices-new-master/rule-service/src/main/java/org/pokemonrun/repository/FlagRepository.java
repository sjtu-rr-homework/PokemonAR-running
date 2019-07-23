package org.pokemonrun.repository;

import org.pokemonrun.entity.Flag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlagRepository extends JpaRepository<Flag, Integer> {
}
