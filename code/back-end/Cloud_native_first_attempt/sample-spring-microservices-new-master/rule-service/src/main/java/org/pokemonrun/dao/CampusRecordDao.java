package org.pokemonrun.dao;

public interface CampusRecordDao {
    boolean addMileage(String username, double length);
    double getMileage(String username);
    boolean reset();
}
