package org.pokemonrun.repository;

import org.pokemonrun.entity.PathNode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PathNodeRepository extends JpaRepository<PathNode, Integer> {
}
