package org.pokemonrun.dao;

import org.pokemonrun.entity.Cover;

public interface CoverDao {
    Cover getOneCover(String username);
    void save(Cover cover);
}
