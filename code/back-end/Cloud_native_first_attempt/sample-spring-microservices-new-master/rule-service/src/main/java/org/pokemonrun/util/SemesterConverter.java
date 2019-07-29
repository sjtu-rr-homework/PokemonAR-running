package org.pokemonrun.util;

import org.pokemonrun.entity.Semester;
import org.pokemonrun.info.SemesterInfo;

import java.sql.Timestamp;

public class SemesterConverter {
    public static Semester toEntity(SemesterInfo info){
        String mile = info.getMileageGoal();
        double mileage = Double.parseDouble(mile);
        String end = info.getEndTime();
        end = end.replace('T', ' ');
        Timestamp endTime = DateUtils.parse(end, "yyyy-MM-dd HH:mm");
        Semester semester = new Semester();
        semester.setMileageGoal(mileage);
        semester.setEndTime(endTime);
        return semester;
    }
    public static SemesterInfo toInfo(Semester semester){
        double mileage = semester.getMileageGoal();
        String mile = Double.toString(mileage);
        Timestamp endTime = semester.getEndTime();
        String end = DateUtils.format(endTime);
        return new SemesterInfo(mile, end);
    }
}
