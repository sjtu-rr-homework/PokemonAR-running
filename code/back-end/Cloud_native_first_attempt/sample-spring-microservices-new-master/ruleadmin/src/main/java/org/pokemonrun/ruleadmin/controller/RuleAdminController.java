package org.pokemonrun.ruleadmin.controller;

import org.pokemonrun.ruleadmin.info.BasicRuleInfo;
import org.pokemonrun.ruleadmin.info.FlagInfo;
import org.pokemonrun.ruleadmin.service.BasicRuleService;
import org.pokemonrun.ruleadmin.service.FlagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RuleAdminController {
    @Autowired
    private FlagService flagService;
    @Autowired
    private BasicRuleService basicRuleService;

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
}
