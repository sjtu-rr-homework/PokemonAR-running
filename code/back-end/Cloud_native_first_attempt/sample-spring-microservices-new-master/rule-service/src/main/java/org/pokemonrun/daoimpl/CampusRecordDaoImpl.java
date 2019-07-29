package org.pokemonrun.daoimpl;

import org.pokemonrun.dao.CampusRecordDao;
import org.pokemonrun.entity.CampusRecord;
import org.pokemonrun.repository.CampusRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CampusRecordDaoImpl implements CampusRecordDao {
    @Autowired
    private CampusRecordRepository campusRecordRepository;

    @Override
    public boolean addMileage(String username, double length) {
        List<CampusRecord> list = campusRecordRepository.findByUsername(username);
        CampusRecord record = null;
        if(!list.isEmpty()){
            record = list.get(0);
        }
        if(record == null){
            record = new CampusRecord();
            record.setUsername(username);
            record.setMileage(length);
            campusRecordRepository.save(record);
        }else{
            campusRecordRepository.deleteByUsername(username);
            CampusRecord rec = new CampusRecord();
            rec.setMileage(record.getMileage() + length);
            rec.setUsername(username);
            campusRecordRepository.save(rec);
        }
        return true;
    }

    @Override
    public double getMileage(String username) {
        List<CampusRecord> list = campusRecordRepository.findByUsername(username);
        CampusRecord record = null;
        if(!list.isEmpty()){
            record = list.get(0);
        }
        if(record == null){
            return 0;
        }
        return record.getMileage();
    }

    @Override
    public boolean reset() {
        campusRecordRepository.deleteAll();
        return true;
    }
}
