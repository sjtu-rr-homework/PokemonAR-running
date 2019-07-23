package org.pokemonrun.ruleadmin.service;

import org.pokemonrun.ruleadmin.info.PathNodeInfo;

import java.util.List;

public interface BorderService {
    List<PathNodeInfo> getBorder();
    boolean setBorder(List<PathNodeInfo> border);
}
