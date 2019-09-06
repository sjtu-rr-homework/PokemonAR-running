package org.pokemonrun.dao;

import org.pokemonrun.entity.Cover;

public interface CoverDao {
    Cover getOneCover(String username);//get avatar
    void save(Cover cover);//set avatar
}
