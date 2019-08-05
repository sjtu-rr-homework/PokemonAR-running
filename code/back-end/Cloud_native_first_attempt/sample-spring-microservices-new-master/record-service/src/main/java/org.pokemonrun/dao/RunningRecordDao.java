package org.pokemonrun.dao;


import org.pokemonrun.entity.RunningRecord;

import java.util.List;

public interface RunningRecordDao {
    RunningRecord save(RunningRecord runningRecord);
    RunningRecord findOne(long recordID);
    List<RunningRecord> findByUsername(String username);
}
