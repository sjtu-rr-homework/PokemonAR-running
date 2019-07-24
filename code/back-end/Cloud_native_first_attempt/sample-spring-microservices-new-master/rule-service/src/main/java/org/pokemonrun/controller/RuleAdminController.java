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

    @PutMapping("/admin/rule/basic")
    public boolean setBasicRule(@RequestBody BasicRuleInfo info){
        return basicRuleService.setBasicRule(info);
    }

    @GetMapping("/admin/rule/flags")
    public List<FlagInfo> getFlags(){
        return flagService.getFlags();
    }
    @PutMapping("/admin/rule/flags")
    public boolean setFlags(@RequestBody List<FlagInfo> flags){
        return flagService.setFlags(flags);
    }

    @GetMapping("/admin/rule/border")
    public List<PathNodeInfo> getBorder(){
        return borderService.getBorder();
    }
    @PutMapping("/admin/rule/border")
    public boolean setBorder(@RequestBody List<PathNodeInfo> border){
        return borderService.setBorder(border);
    }
}
