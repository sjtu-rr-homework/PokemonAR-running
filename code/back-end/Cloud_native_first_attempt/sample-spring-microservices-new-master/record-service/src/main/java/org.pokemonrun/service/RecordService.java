package org.pokemonrun.service;


import org.pokemonrun.info.RunningRecordInfo;

import java.util.List;

public interface RecordService {
    boolean saveRecord(RunningRecordInfo info);
    List<RunningRecordInfo> getUserRecordList(String username);
    List<RunningRecordInfo> getUserRecordListPage(String username, int pageID, int pageSize);
    List<RunningRecordInfo> getUserLatestRecordListPage(String username, int pageID, int pageSize);
}
