package org.pokemonrun.serviceimpl;

import org.pokemonrun.dao.BasicRuleDao;
import org.pokemonrun.info.BasicRuleInfo;
import org.pokemonrun.entity.BasicRule;
import org.pokemonrun.service.BasicRuleService;
import org.pokemonrun.util.BasicRuleConverter;
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
