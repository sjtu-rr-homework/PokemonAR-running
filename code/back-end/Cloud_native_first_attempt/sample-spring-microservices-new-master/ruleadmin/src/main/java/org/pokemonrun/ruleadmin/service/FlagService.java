package org.pokemonrun.ruleadmin.service;

import org.pokemonrun.ruleadmin.info.FlagInfo;

import java.util.List;

public interface FlagService {
    List<FlagInfo> getFlags();
    boolean setFlags(List<FlagInfo> flags);
}
