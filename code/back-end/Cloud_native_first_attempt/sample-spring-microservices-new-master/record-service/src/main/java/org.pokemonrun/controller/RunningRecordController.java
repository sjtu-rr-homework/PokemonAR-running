package org.pokemonrun.controller;

import org.pokemonrun.info.RunningRecordInfo;
import org.pokemonrun.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class RunningRecordController {
    private static final int RECORD_LIST_PAGE_SIZE = 5;

    @Autowired
    private RecordService recordService;

    /**
     * Upload a running record.
     * @param runningRecordInfo a RunningRecordInfo object describing the running record
     * @return whether the running record is saved successfully
     */
    @PostMapping("/running/record")
    public boolean saveRecord(@RequestBody RunningRecordInfo runningRecordInfo) {
        return recordService.saveRecord(runningRecordInfo);
    }

    /**
     * Get a user's running records.
     * @param username the username of user
     * @return a list containing all the running records of the user
     */
    @GetMapping("/running/record/user/{username}")
    public List<RunningRecordInfo> getUserRecordList(@PathVariable("username") String username) {
        return recordService.getUserRecordList(username);
    }

    /**
     * Get a user's running records by page.
     * At most 10 records per page.
     * @param username the username of user
     * @param pid page index
     * @return a page of user's running records
     */
    @GetMapping("/running/record/user/{username}/page/{pageid}")
    public List<RunningRecordInfo> getUserRecordList(@PathVariable("username") String username,@PathVariable("pageid") String pid) {
        int pageID = Integer.parseInt(pid);
        return recordService.getUserLatestRecordListPage(username, pageID, RECORD_LIST_PAGE_SIZE);
    }
}
