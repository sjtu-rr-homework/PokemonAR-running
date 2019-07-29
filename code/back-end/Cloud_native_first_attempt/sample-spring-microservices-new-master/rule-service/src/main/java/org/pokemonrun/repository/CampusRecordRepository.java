package org.pokemonrun.repository;

import org.pokemonrun.entity.CampusRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampusRecordRepository extends JpaRepository<CampusRecord, Integer> {
    List<CampusRecord> findByUsername(String username);
    void deleteByUsername(String username);
}
