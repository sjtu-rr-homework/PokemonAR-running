package org.pokemonrun.repository;


import org.pokemonrun.entity.RunningRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RunningRecordRepository extends JpaRepository<RunningRecord, Long> {
    List<RunningRecord> findByUsername(String username);
}
