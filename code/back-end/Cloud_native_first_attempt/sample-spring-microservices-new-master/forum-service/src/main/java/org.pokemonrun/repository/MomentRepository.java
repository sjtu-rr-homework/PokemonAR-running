package org.pokemonrun.repository;

import org.pokemonrun.entity.Moment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MomentRepository extends MongoRepository<Moment,String> {
}
