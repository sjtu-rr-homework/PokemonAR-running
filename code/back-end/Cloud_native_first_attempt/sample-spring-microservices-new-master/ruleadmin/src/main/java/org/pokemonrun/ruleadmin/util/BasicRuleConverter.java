package org.pokemonrun.ruleadmin.util;


import org.pokemonrun.ruleadmin.entity.BasicRule;
import org.pokemonrun.ruleadmin.info.BasicRuleInfo;

public class BasicRuleConverter {
    public static BasicRuleInfo toInfo(BasicRule rule){
        Double mileageRequirement = rule.getMileageRequirement();
        String mile = mileageRequirement == null ? "" : String.valueOf(mileageRequirement);
        Double minSpeed = rule.getMinSpeed();
        String min = minSpeed == null ? "" : String.valueOf(minSpeed);
        Double maxSpeed = rule.getMaxSpeed();
        String max = maxSpeed == null ? "" : String.valueOf(maxSpeed);
        BasicRuleInfo info = new BasicRuleInfo(mile, min, max);
        return info;
    }
    public static BasicRule toEntity(BasicRuleInfo info){
        String mileageRequirement = info.getMileageRequirement();
        Double mile = mileageRequirement.isEmpty() ? null : Double.parseDouble(mileageRequirement);
        String minSpeed = info.getMinSpeed();
        Double min = minSpeed.isEmpty() ? null : Double.parseDouble(minSpeed);
        String maxSpeed = info.getMaxSpeed();
        Double max = maxSpeed.isEmpty() ? null : Double.parseDouble(maxSpeed);
        BasicRule rule = new BasicRule();
        rule.setMileageRequirement(mile);
        rule.setMinSpeed(min);
        rule.setMaxSpeed(max);
        return rule;
    }
}
