package org.pokemonrun.ruleadmin.controller;

import org.pokemonrun.ruleadmin.info.FlagInfo;
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

    @GetMapping("/admin/rule/flags")
    public List<FlagInfo> getFlags(){
        return flagService.getFlags();
    }
    @PutMapping("/admin/rule/flags")
    public boolean setFlags(@RequestBody List<FlagInfo> flags){
        return flagService.setFlags(flags);
    }
}
