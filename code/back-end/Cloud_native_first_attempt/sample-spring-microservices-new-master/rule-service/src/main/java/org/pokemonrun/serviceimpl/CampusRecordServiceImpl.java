package org.pokemonrun.serviceimpl;

import org.pokemonrun.dao.BasicRuleDao;
import org.pokemonrun.dao.CampusRecordDao;
import org.pokemonrun.dao.SemesterDao;
import org.pokemonrun.entity.BasicRule;
import org.pokemonrun.entity.Semester;
import org.pokemonrun.service.CampusRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class CampusRecordServiceImpl implements CampusRecordService {
    @Autowired
    private CampusRecordDao campusRecordDao;
    @Autowired
    private SemesterDao semesterDao;

    private boolean isInTimeWindow(){
        long now = System.currentTimeMillis();
        Semester semester = semesterDao.getSemester();
        if(semester == null){
            return false;
        }
        long end = semester.getEndTime();
        return now <= end;
    }

    @Override
    public String getCampusRunningLength(String username) {
        return String.valueOf(campusRecordDao.getMileage(username));
    }

    @Override
    public boolean appendCampusRunningLength(String username, String length) {
        // empty length
        if(length.isEmpty()){
            return false;
        }
        // negative length
        double len = Double.parseDouble(length);
        if(len < 0){
            return false;
        }
        // not in time window
        if(!isInTimeWindow()){
            return false;
        }
        // access Dao
        return campusRecordDao.addMileage(username, len);
    }
}
