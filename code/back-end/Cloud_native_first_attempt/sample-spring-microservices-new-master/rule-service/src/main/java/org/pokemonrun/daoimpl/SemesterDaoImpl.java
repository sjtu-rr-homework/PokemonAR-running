package org.pokemonrun.daoimpl;

import org.pokemonrun.dao.SemesterDao;
import org.pokemonrun.entity.Semester;
import org.pokemonrun.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SemesterDaoImpl implements SemesterDao {
    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public Semester getSemester() {
        List<Semester> list = semesterRepository.findAll();
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public boolean setSemester(Semester semester) {
        List<Semester> list = semesterRepository.findAll();
        if(list.isEmpty()){
            semesterRepository.save(semester);
        }else{
            semesterRepository.deleteAll();
            semesterRepository.save(semester);
        }
        return true;
    }
}
