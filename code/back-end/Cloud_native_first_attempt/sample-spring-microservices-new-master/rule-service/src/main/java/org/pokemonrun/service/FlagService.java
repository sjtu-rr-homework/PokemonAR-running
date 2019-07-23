package org.pokemonrun.service;

import org.pokemonrun.info.FlagInfo;

import java.util.List;

public interface FlagService {
    List<FlagInfo> getFlags();
    boolean setFlags(List<FlagInfo> flags);
}
