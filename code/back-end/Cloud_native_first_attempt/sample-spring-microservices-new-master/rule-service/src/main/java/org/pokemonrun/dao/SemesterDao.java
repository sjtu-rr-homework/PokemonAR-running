package org.pokemonrun.dao;

import org.pokemonrun.entity.Semester;

public interface SemesterDao {
    Semester getSemester();
    boolean setSemester(Semester semester);
}
