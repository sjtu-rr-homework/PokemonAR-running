package org.pokemonrun.service;

import org.pokemonrun.info.Locationinfo;

import java.util.List;

public interface LocationService {
    List<Locationinfo> getNearBy(Locationinfo Locationinfo);
    boolean refreshLocation(Locationinfo Locationinfo);
}
