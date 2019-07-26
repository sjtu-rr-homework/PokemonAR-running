package org.pokemonrun.controller;

import org.pokemonrun.info.BasicRuleInfo;
import org.pokemonrun.info.PathNodeInfo;
import org.pokemonrun.info.FlagInfo;
import org.pokemonrun.service.BasicRuleService;
import org.pokemonrun.service.BorderService;
import org.pokemonrun.service.FlagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class RuleAdminController {
    @Autowired
    private FlagService flagService;
    @Autowired
    private BasicRuleService basicRuleService;
    @Autowired
    private BorderService borderService;



    @GetMapping("/admin/rule/basic")
    public BasicRuleInfo getBasicRule(){
        return basicRuleService.getBasicRule();
    }

    @PostMapping("/admin/post/rule/basic")

    public boolean setBasicRule(@RequestBody BasicRuleInfo info){
        return basicRuleService.setBasicRule(info);
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
        // TODO: temp
        return flagService.getFlags();
    }
}


