package org.pokemonrun.runningrecordservice.repository;

import org.pokemonrun.runningrecordservice.entity.RunningRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RunningRecordRepository extends JpaRepository<RunningRecord, Long> {
    List<RunningRecord> findByUsername(String username);
}
