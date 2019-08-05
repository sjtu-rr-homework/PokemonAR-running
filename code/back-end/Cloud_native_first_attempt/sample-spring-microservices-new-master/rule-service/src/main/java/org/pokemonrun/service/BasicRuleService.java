package org.pokemonrun.service;

import org.pokemonrun.info.BasicRuleInfo;

public interface BasicRuleService {
    BasicRuleInfo getBasicRule();
    boolean setBasicRule(BasicRuleInfo info);
}
