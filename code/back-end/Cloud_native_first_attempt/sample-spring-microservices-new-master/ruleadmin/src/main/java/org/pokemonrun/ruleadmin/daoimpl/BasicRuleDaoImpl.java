package org.pokemonrun.ruleadmin.daoimpl;

import org.pokemonrun.ruleadmin.dao.BasicRuleDao;
import org.pokemonrun.ruleadmin.entity.BasicRule;
import org.pokemonrun.ruleadmin.repository.BasicRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public class BasicRuleDaoImpl implements BasicRuleDao {
    @Autowired
    private BasicRuleRepository ruleRepository;

    @Override
    public boolean replaceBasicRule(BasicRule rule) {
        ruleRepository.deleteAll();
        ruleRepository.save(rule);
        return true;
    }

    @Override
    @NotNull
    public BasicRule getBasicRule() {
        List<BasicRule> list = ruleRepository.findAll();
        if(list.isEmpty()){
            return new BasicRule();
        }
        return list.get(0);
    }
}
