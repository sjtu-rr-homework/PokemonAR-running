package org.pokemonrun.service;

public interface CampusRecordService {
    String getCampusRunningLength(String username);
    boolean appendCampusRunningLength(String username, String length);
}
