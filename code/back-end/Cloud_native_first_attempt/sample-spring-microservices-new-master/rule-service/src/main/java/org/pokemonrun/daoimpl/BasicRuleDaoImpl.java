package org.pokemonrun.daoimpl;

import org.pokemonrun.dao.BasicRuleDao;
import org.pokemonrun.entity.BasicRule;
import org.pokemonrun.repository.BasicRuleRepository;
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
