package org.pokemonrun.service;

import org.pokemonrun.info.MomentInfo;

import java.util.List;

public interface GetMomentService {
    List<MomentInfo> getAll(long timestamp);//get ten moments before the timestamp order by time
    List<MomentInfo> refresh(long timestamp);//get ten moments after the timestamp order by time
}
