package org.pokemonrun.controller;

import org.pokemonrun.info.*;
import org.pokemonrun.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller of rule application.
 * Receive and reply to requests from the gateway.
 */
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

    /**
     * Get basic rule info, including valid speed range.
     * @return an BasicRuleInfo object
     */
    @GetMapping("/admin/rule/basic")
    public BasicRuleInfo getBasicRule(){
        return basicRuleService.getBasicRule();
    }

    /**
     * Modify the basic rule.
     * @param info an BasicRuleInfo object, according to which the basic rule is modified
     * @return whether the basic rule is modified successfully
     */
    @PostMapping("/admin/post/rule/basic")
    public boolean setBasicRule(@RequestBody BasicRuleInfo info){
        return basicRuleService.setBasicRule(info);
    }

    /**
     * OPTIONS method handler. Helpful to handle cross-origin requests.
     * @return an ResponseEntity object
     */
    @RequestMapping(value = "/admin/post/rule/basic", method = RequestMethod.OPTIONS)
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Get preset flags in the map.
     * Flags are used as checkpoints in campus running.
     * @return all the flags in the map
     */
    @GetMapping("/admin/rule/flags")
    public List<FlagInfo> getFlags(){
        return flagService.getFlags();
    }

    /**
     * Set flags in the map.
     * @param flags the flags which will replace the old ones
     * @return whether the flags are modified successfully
     */
    @PostMapping("/admin/post/rule/flags")
    public boolean setFlags(@RequestBody List<FlagInfo> flags){
        return flagService.setFlags(flags);
    }

    /**
     * Get the running range (border) of campus running.
     * @return a list of vertices which indicate a polygon (the border)
     */
    @GetMapping("/admin/rule/border")
    public List<PathNodeInfo> getBorder(){
        return borderService.getBorder();
    }

    /**
     * Set the running range (border) of campus running.
     * @param border a list of vertices which indicate a polygon (the border)
     * @return whether the border is modified successfully
     */
    @PostMapping("/admin/post/rule/border")
    public boolean setBorder(@RequestBody List<PathNodeInfo> border){
        return borderService.setBorder(border);
    }

    /**
     * Generate a route containing at most 5 points.
     * All the points are selected from flags
     * and should be near enough to the reference point (lng, lat)
     * @param lng longitude of the reference point
     * @param lat latitude of the reference point
     * @return a list of points indicating the route
     */
    @GetMapping("/rule/route/start_lng/{lng}/start_lat/{lat}")
    public List<FlagInfo> generateRoute(@PathVariable("lng") String lng, @PathVariable("lat") String lat){
        return flagService.getRandomRoute(Double.parseDouble(lng), Double.parseDouble(lat));
    }

    /**
     * Get a student's campus running information, including student's mileage
     * and goal of the current semester.
     * @param username the username of student
     * @return student's campus running progress
     */
    @GetMapping("/rule/campus/user/{username}")
    public CampusRunningInfo getCampusRunningInfo(@PathVariable("username") String username){
        String mileage = campusRecordService.getCampusRunningLength(username);
        String goal = semesterService.getMileageGoal();
        return new CampusRunningInfo(mileage, goal);
    }

    /**
     * Add to a student's campus running mileage.
     * @param username the username of student
     * @param length course length to be added
     * @return whether mileage is added successfully
     */
    @PostMapping("/rule/campus/user/{username}/length/{length}")
    public boolean recordCampusRunning(@PathVariable("username") String username, @PathVariable("length") String length){
        return campusRecordService.appendCampusRunningLength(username, length);
    }

    /**
     * Begin a new semester with new set of running rules.
     * @param info a SemesterInfo object describing running rules of the new semester
     * @return whether a new semester is created successfully
     */
    @PostMapping("/rule/campus/semester")
    public boolean beginNewSemester(@RequestBody SemesterInfo info){
        return semesterService.beginNewSemester(info);
    }

    /**
     * Modify running rules of the current semester.
     * @param info a SemesterInfo object, according to which running rules are modified
     * @return whether running rules are modified successfully
     */
    @PutMapping("/rule/campus/semester")
    public boolean modifySemesterInfo(@RequestBody SemesterInfo info){
        return semesterService.modifySemester(info);
    }

    /**
     * Get details of the current semester, including start & end time,
     * valid speed range and mileage goal.
     * @return details of the current semester
     */
    @GetMapping("/rule/campus/semester")
    public SemesterDetailedInfo getSemesterDetails(){
        return semesterService.getSemesterDetails();
    }

}


