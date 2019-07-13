package org.pokemonrun.daoimpl;


import org.pokemonrun.dao.RunningRecordDao;
import org.pokemonrun.entity.RunningRecord;
import org.pokemonrun.repository.RunningRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RunningRecordDaoImpl implements RunningRecordDao {
    @Autowired
    private RunningRecordRepository runningRecordRepository;

    @Override
    public RunningRecord save(RunningRecord runningRecord) {
        RunningRecord record = runningRecordRepository.save(runningRecord);
        return record;
    }
    @Override
    public RunningRecord findOne(long recordID) {
        return runningRecordRepository.findById(recordID).get();
    }

    @Override
    public List<RunningRecord> findByUsername(String username) {
        return runningRecordRepository.findByUsername(username);
    }

}
