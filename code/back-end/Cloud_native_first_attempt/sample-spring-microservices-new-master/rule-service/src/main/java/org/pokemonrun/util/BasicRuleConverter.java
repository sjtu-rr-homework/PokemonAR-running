package org.pokemonrun.util;


import org.pokemonrun.entity.BasicRule;
import org.pokemonrun.info.BasicRuleInfo;

public class BasicRuleConverter {
    public static BasicRuleInfo toInfo(BasicRule rule){
        Double minSpeed = rule.getMinSpeed();
        String min = minSpeed == null ? "" : String.valueOf(minSpeed);
        Double maxSpeed = rule.getMaxSpeed();
        String max = maxSpeed == null ? "" : String.valueOf(maxSpeed);
        BasicRuleInfo info = new BasicRuleInfo(min, max);
        return info;
    }
    public static BasicRule toEntity(BasicRuleInfo info){
        String minSpeed = info.getMinSpeed();
        Double min = minSpeed.isEmpty() ? null : Double.parseDouble(minSpeed);
        String maxSpeed = info.getMaxSpeed();
        Double max = maxSpeed.isEmpty() ? null : Double.parseDouble(maxSpeed);
        BasicRule rule = new BasicRule();
        rule.setMinSpeed(min);
        rule.setMaxSpeed(max);
        return rule;
    }
}
