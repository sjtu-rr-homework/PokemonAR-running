package org.pokemonrun.daoimpl;

import org.pokemonrun.dao.FlagDao;
import org.pokemonrun.entity.Flag;
import org.pokemonrun.repository.FlagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FlagDaoImpl implements FlagDao {
    @Autowired
    private FlagRepository flagRepository;

    @Override
    public List<Flag> getFlags() {
        return flagRepository.findAll();
    }

    @Override
    public boolean replaceFlags(List<Flag> flags){
        flagRepository.deleteAll();
        flagRepository.saveAll(flags);
        return true;
    }
}
