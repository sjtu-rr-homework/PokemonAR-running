package org.pokemonrun.repository;

import org.pokemonrun.entity.FastMoment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FastMomentRepository extends JpaRepository<FastMoment, String> {
    List<FastMoment> findTop10ByTimestampLessThanOrderBytOrderByTimestampAsc(long timestamp);
    List<FastMoment> findTop10ByTimestampGreaterThanOrderByTimestampDesc(long timestamp);
}
