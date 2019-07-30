package org.pokemonrun.serviceimpl;

import org.pokemonrun.dao.CampusRecordDao;
import org.pokemonrun.dao.SemesterDao;
import org.pokemonrun.entity.Semester;
import org.pokemonrun.info.SemesterInfo;
import org.pokemonrun.service.SemesterService;
import org.pokemonrun.util.SemesterConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class SemesterServiceImpl implements SemesterService {
    @Autowired
    private SemesterDao semesterDao;
    @Autowired
    private CampusRecordDao campusRecordDao;

    private Semester toSemester(SemesterInfo info){
        Semester semester = SemesterConverter.toEntity(info);
        // negative mileage goal
        if(semester.getMileageGoal() < 0){
            return null;
        }
        // past time as end time
        Timestamp end = semester.getEndTime();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if(!now.before(end)){
            return null;
        }
        return semester;
    }

    @Override
    public boolean beginNewSemester(SemesterInfo info) {
        Semester semester = toSemester(info);
        if(semester == null){
            return false;
        }
        // execute
        if(!campusRecordDao.reset()){
            return false;
        }
        return semesterDao.setSemester(semester);
    }

    @Override
    public boolean modifySemester(SemesterInfo info) {
        Semester semester = toSemester(info);
        if(semester == null){
            return false;
        }
        return semesterDao.setSemester(semester);
    }

    @Override
    public String getMileageGoal() {
        Semester semester = semesterDao.getSemester();
        if(semester == null){
            return "0";
        }
        return String.valueOf(semester.getMileageGoal());
    }
}
