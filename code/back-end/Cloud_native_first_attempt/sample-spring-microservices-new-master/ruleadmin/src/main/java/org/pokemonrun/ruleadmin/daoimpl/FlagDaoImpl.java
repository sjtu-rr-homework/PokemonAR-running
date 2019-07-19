package org.pokemonrun.ruleadmin.daoimpl;

import org.pokemonrun.ruleadmin.dao.FlagDao;
import org.pokemonrun.ruleadmin.entity.Flag;
import org.pokemonrun.ruleadmin.repository.FlagRepository;
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
