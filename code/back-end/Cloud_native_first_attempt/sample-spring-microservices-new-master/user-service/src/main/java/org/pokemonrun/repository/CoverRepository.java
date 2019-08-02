package org.pokemonrun.repository;

import org.pokemonrun.entity.Cover;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CoverRepository extends MongoRepository<Cover, String> {
    Cover findByUsername(String username);
}
