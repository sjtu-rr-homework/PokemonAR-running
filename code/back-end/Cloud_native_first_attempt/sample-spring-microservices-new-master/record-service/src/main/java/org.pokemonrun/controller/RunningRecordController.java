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

    @PostMapping("/running/record")
    public boolean saveRecord(@RequestBody RunningRecordInfo runningRecordInfo) {
        return recordService.saveRecord(runningRecordInfo);
    }

    @GetMapping("/running/record/user/{username}")
    public List<RunningRecordInfo> getUserRecordList(@PathVariable("username") String username) {
        return recordService.getUserRecordList(username);
    }

    @GetMapping("/running/record/user/{username}/page/{pageid}")
    public List<RunningRecordInfo> getUserRecordList(@PathVariable("username") String username,@PathVariable("pageid") String pid) {
        int pageID = Integer.parseInt(pid);
        return recordService.getUserLatestRecordListPage(username, pageID, RECORD_LIST_PAGE_SIZE);
    }
}
