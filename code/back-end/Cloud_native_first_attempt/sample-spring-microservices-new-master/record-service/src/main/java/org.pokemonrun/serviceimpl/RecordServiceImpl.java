package org.pokemonrun.serviceimpl;


import org.pokemonrun.dao.RunningRecordDao;
import org.pokemonrun.entity.RunningRecord;
import org.pokemonrun.info.RunningRecordInfo;
import org.pokemonrun.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.LinkedList;
import java.util.List;


@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RunningRecordDao runningRecordDao;

    @Override
    public boolean saveRecord(RunningRecordInfo info) {
        RunningRecord record = new RunningRecord(info);
        runningRecordDao.save(record);
        return true;
    }
    @Override
    public List<RunningRecordInfo> getUserRecordList(String username) {
        List<RunningRecord> list = runningRecordDao.findByUsername(username);
        List<RunningRecordInfo> result = new LinkedList<>();
        for(RunningRecord record : list){
            result.add(new RunningRecordInfo(record));
        }
        return result;
    }

    @Override
    public List<RunningRecordInfo> getUserRecordListPage(String username, int pageID, int pageSize) {
        List<RunningRecord> list = runningRecordDao.findByUsername(username);
        List<RunningRecordInfo> result = new LinkedList<>();
        int index = 0, size = 0, indexStart = pageID * pageSize;
        for(RunningRecord record : list){
            if(index++ >= indexStart){
                result.add(new RunningRecordInfo(record));
                if(++size >= pageSize){
                    break;
                }
            }
        }
        return result;
    }

    @Override
    // latest at first
    public List<RunningRecordInfo> getUserLatestRecordListPage(String username, int pageID, int pageSize) {
        List<RunningRecord> list = runningRecordDao.findByUsername(username);
        list.sort((RunningRecord o1, RunningRecord o2) -> {
            // descending
            if(o1.getStartTime() < o2.getStartTime()){
                return 1;
            }else if(o1.getStartTime() > o2.getStartTime()){
                return -1;
            }
            return 0;
        });
        List<RunningRecordInfo> result = new LinkedList<>();
        int index = 0, size = 0, indexStart = pageID * pageSize;
        for(RunningRecord record : list){
            if(index++ >= indexStart){
                result.add(new RunningRecordInfo(record));
                if(++size >= pageSize){
                    break;
                }
            }
        }
        return result;
    }
}
