package org.pokemonrun.ruleadmin.dao;

import org.pokemonrun.ruleadmin.entity.Flag;

import javax.transaction.Transactional;
import java.util.List;

@Transactional(rollbackOn = Exception.class)
public interface FlagDao {
    List<Flag> getFlags();
    boolean replaceFlags(List<Flag> flags);
}
