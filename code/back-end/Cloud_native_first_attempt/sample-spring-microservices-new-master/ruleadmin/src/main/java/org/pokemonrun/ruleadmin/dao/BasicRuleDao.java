package org.pokemonrun.ruleadmin.dao;

import org.pokemonrun.ruleadmin.entity.BasicRule;

import javax.validation.constraints.NotNull;

public interface BasicRuleDao {
    boolean replaceBasicRule(BasicRule rule);
    @NotNull
    BasicRule getBasicRule();
}
