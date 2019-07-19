package org.pokemonrun.ruleadmin.repository;

import org.pokemonrun.ruleadmin.entity.BasicRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicRuleRepository extends JpaRepository<BasicRule, Integer> {
}
