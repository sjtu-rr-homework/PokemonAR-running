package org.pokemonrun.service;

import org.pokemonrun.info.SemesterInfo;

public interface SemesterService {
    boolean beginNewSemester(SemesterInfo info);
    boolean modifySemester(SemesterInfo info);
    String getMileageGoal();
}
