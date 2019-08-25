package org.pokemonrun.service;

import org.pokemonrun.info.MomentInfo;

import java.util.List;

public interface GetMomentService {
    List<MomentInfo> getAll(long timestamp);
    List<MomentInfo> getOneUser(String username, long timestamp);
    List<MomentInfo> refresh(long timestamp);
}
