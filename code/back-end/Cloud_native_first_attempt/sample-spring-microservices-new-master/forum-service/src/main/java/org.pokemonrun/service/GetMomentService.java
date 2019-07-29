package org.pokemonrun.service;

import org.pokemonrun.info.MomentInfo;

import java.util.List;

public interface GetMomentService {
    List<MomentInfo> getAll();
    List<MomentInfo> getOneUser(String username);
}
