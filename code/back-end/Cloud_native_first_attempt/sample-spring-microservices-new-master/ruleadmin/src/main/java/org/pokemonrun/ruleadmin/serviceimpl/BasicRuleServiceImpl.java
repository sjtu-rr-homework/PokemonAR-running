package org.pokemonrun.ruleadmin.serviceimpl;

import org.pokemonrun.ruleadmin.dao.BasicRuleDao;
import org.pokemonrun.ruleadmin.entity.BasicRule;
import org.pokemonrun.ruleadmin.info.BasicRuleInfo;
import org.pokemonrun.ruleadmin.service.BasicRuleService;
import org.pokemonrun.ruleadmin.util.BasicRuleConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicRuleServiceImpl implements BasicRuleService {
    @Autowired
    private BasicRuleDao basicRuleDao;

    @Override
    public BasicRuleInfo getBasicRule() {
        BasicRule rule = basicRuleDao.getBasicRule();
        return BasicRuleConverter.toInfo(rule);
    }

    @Override
    public boolean setBasicRule(BasicRuleInfo info) {
        BasicRule rule = BasicRuleConverter.toEntity(info);
        Double mile = rule.getMileageRequirement();
        Double min = rule.getMinSpeed();
        Double max = rule.getMaxSpeed();
        if(mile != null && mile < 0){
            return false;
        }
        if(min != null && max != null && (min >= max || min < 0)){
            return false;
        }
        return basicRuleDao.replaceBasicRule(rule);
    }
}
