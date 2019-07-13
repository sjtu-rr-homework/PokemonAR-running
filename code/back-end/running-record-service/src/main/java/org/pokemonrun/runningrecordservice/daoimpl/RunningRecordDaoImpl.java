package org.pokemonrun.runningrecordservice.daoimpl;

import org.pokemonrun.runningrecordservice.dao.RunningRecordDao;
import org.pokemonrun.runningrecordservice.entity.RunningRecord;
import org.pokemonrun.runningrecordservice.info.RunningRecordInfo;
import org.pokemonrun.runningrecordservice.repository.RunningRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
