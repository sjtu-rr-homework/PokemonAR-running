package org.pokemonrun.daoimpl;

import org.pokemonrun.dao.MomentDao;
import org.pokemonrun.repository.MomentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MomentDaoimpl implements MomentDao {
    @Autowired
    private MomentRepository MomentRepository;
}
