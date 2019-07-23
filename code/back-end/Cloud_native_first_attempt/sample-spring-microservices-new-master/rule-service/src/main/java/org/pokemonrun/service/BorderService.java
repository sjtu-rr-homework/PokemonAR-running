package org.pokemonrun.service;

import org.pokemonrun.info.PathNodeInfo;

import java.util.List;

public interface BorderService {
    List<PathNodeInfo> getBorder();
    boolean setBorder(List<PathNodeInfo> border);
}
