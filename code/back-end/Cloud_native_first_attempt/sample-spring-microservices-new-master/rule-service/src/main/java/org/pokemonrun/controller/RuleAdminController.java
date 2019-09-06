package org.pokemonrun.controller;

import org.pokemonrun.info.*;
import org.pokemonrun.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
@RestController
public class RuleAdminController {
    @Autowired
    private FlagService flagService;
    @Autowired
    private BasicRuleService basicRuleService;
    @Autowired
    private BorderService borderService;
    @Autowired
    private CampusRecordService campusRecordService;
    @Autowired
    private SemesterService semesterService;

    @GetMapping("/admin/rule/basic")
    public BasicRuleInfo getBasicRule(){
        return basicRuleService.getBasicRule();
    }

    @PostMapping("/admin/post/rule/basic")
    public boolean setBasicRule(@RequestBody BasicRuleInfo info){
        return basicRuleService.setBasicRule(info);
    }

    @RequestMapping(value = "/admin/post/rule/basic", method = RequestMethod.OPTIONS)
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/admin/rule/flags")
    public List<FlagInfo> getFlags(){
        return flagService.getFlags();
    }

    @PostMapping("/admin/post/rule/flags")
    public boolean setFlags(@RequestBody List<FlagInfo> flags){
        return flagService.setFlags(flags);
    }

    @GetMapping("/admin/rule/border")
    public List<PathNodeInfo> getBorder(){
        return borderService.getBorder();
    }

    @PostMapping("/admin/post/rule/border")
    public boolean setBorder(@RequestBody List<PathNodeInfo> border){
        return borderService.setBorder(border);
    }

    @GetMapping("/rule/route/start_lng/{lng}/start_lat/{lat}")
    public List<FlagInfo> generateRoute(@PathVariable("lng") String lng, @PathVariable("lat") String lat){
        return flagService.getRandomRoute(Double.parseDouble(lng), Double.parseDouble(lat));
    }

    @GetMapping("/rule/campus/user/{username}")
    public CampusRunningInfo getCampusRunningInfo(@PathVariable("username") String username){
        String mileage = campusRecordService.getCampusRunningLength(username);
        String goal = semesterService.getMileageGoal();
        return new CampusRunningInfo(mileage, goal);
    }

    @PostMapping("/rule/campus/user/{username}/length/{length}")
    public boolean recordCampusRunning(@PathVariable("username") String username, @PathVariable("length") String length){
        return campusRecordService.appendCampusRunningLength(username, length);
    }

    @PostMapping("/rule/campus/semester")
    public boolean beginNewSemester(@RequestBody SemesterInfo info){
        return semesterService.beginNewSemester(info);
    }

    @PutMapping("/rule/campus/semester")
    public boolean modifySemesterInfo(@RequestBody SemesterInfo info){
        return semesterService.modifySemester(info);
    }

    @GetMapping("/rule/campus/semester")
    public SemesterDetailedInfo getSemesterDetails(){
        return semesterService.getSemesterDetails();
    }

}


