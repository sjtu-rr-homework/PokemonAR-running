package org.pokemonrun.dao;

import org.pokemonrun.entity.Flag;

import javax.transaction.Transactional;
import java.util.List;

@Transactional(rollbackOn = Exception.class)
public interface FlagDao {
    List<Flag> getFlags();
    boolean replaceFlags(List<Flag> flags);
}
