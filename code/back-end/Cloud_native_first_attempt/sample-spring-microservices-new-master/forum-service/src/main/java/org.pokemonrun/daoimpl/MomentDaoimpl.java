package org.pokemonrun.daoimpl;

import org.pokemonrun.dao.MomentDao;
import org.pokemonrun.entity.Moment;
import org.pokemonrun.repository.MomentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MomentDaoimpl implements MomentDao {
    @Autowired
    private MomentRepository MomentRepository;

    @Override
    public List<Moment> getAll() {
        return MomentRepository.findAll();
    }

    @Override
    public List<Moment> getUserAll(String username) {
        return MomentRepository.findAllByUsername(username);
    }

    @Override
    public void save(Moment moment) {
        MomentRepository.save(moment);
    }
}
