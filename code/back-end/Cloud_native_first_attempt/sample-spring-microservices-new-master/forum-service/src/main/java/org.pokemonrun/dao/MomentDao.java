package org.pokemonrun.dao;

import org.pokemonrun.entity.Moment;

import java.util.List;

public interface MomentDao {
    void save(Moment moment);//save new moment
    List<Moment> gettenhistorymoment(long timestamp);//get ten moments which is uploaded before the timestamp
    List<Moment> gettennewmoment(long timestamp);//get ten moments which is uploaded after the timestamp
}
