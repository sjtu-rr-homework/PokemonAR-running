package org.pokemonrun.util;

import org.pokemonrun.entity.BasicRule;
import org.pokemonrun.entity.Semester;
import org.pokemonrun.info.SemesterDetailedInfo;
import org.pokemonrun.info.SemesterInfo;

public class SemesterConverter {
    public static Semester toEntity(SemesterInfo info){
        String mile = info.getMileageGoal();
        double mileage = Double.parseDouble(mile);
        String end = info.getEndTime();
        long endTime = Long.parseLong(end);
        Semester semester = new Semester();
        semester.setMileageGoal(mileage);
        semester.setEndTime(endTime);
        return semester;
    }
    public static SemesterDetailedInfo toDetails(Semester semester, BasicRule rule){
        double mileage = semester.getMileageGoal();
        String mile = Double.toString(mileage);
        long endTime = semester.getEndTime();
        String end = String.valueOf(endTime);
        long startTime = semester.getStartTime();
        String start = String.valueOf(startTime);
        double minSpeed = rule.getMinSpeed();
        String minSpd = String.valueOf(minSpeed);
        double maxSpeed = rule.getMaxSpeed();
        String maxSpd = String.valueOf(maxSpeed);
        return new SemesterDetailedInfo(mile, end, start, minSpd, maxSpd);
    }
    public static SemesterInfo toInfo(Semester semester){
        double mileage = semester.getMileageGoal();
        String mile = Double.toString(mileage);
        long endTime = semester.getEndTime();
        String end = String.valueOf(endTime);
        return new SemesterInfo(mile, end);
    }
}
