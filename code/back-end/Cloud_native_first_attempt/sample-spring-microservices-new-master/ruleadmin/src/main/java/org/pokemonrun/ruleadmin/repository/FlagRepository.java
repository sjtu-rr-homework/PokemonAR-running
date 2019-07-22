package org.pokemonrun.ruleadmin.repository;

import org.pokemonrun.ruleadmin.entity.Flag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlagRepository extends JpaRepository<Flag, Integer> {
}
