package org.pokemonrun.ruleadmin.service;

import org.pokemonrun.ruleadmin.info.BasicRuleInfo;

public interface BasicRuleService {
    BasicRuleInfo getBasicRule();
    boolean setBasicRule(BasicRuleInfo info);
}
