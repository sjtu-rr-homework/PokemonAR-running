package org.pokemonrun.service;

import org.pokemonrun.info.MomentInfo;

import java.util.List;

public interface GetMomentService {
    List<MomentInfo> getAll(String timestamp);
    List<MomentInfo> getOneUser(String username, String timestamp);
}
