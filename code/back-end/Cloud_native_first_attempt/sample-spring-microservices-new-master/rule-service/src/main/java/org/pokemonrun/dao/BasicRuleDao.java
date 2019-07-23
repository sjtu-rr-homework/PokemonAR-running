package org.pokemonrun.dao;

import org.pokemonrun.entity.BasicRule;

import javax.validation.constraints.NotNull;

public interface BasicRuleDao {
    boolean replaceBasicRule(BasicRule rule);
    @NotNull
    BasicRule getBasicRule();
}
