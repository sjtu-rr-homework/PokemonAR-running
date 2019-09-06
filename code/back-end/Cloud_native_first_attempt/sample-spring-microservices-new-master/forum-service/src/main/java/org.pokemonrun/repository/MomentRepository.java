package org.pokemonrun.repository;

import org.pokemonrun.entity.Moment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MomentRepository extends MongoRepository<Moment,String> {
    List<Moment> findTop10ByTimestampLessThanOrderByTimestampDesc(long timestamp);//get ten moments which is uploaded before the timestamp and order by time
    List<Moment> findTop10ByTimestampGreaterThanOrderByTimestampAsc(long timestamp);//get ten moments which is uploaded after the timestamp and order by time
}
