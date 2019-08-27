package org.pokemonrun.dao;

import org.pokemonrun.entity.Moment;

import java.util.List;

public interface MomentDao {
    List<Moment> getAll();
    List<Moment> getUserAll(String username);
    void save(Moment moment);
    List<Moment> gettenhistorymoment(long timestamp);
    List<Moment> gettennewmoment(long timestamp);
}
