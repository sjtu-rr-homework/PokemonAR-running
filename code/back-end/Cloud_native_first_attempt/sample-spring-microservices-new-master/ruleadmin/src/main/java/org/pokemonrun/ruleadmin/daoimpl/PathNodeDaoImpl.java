package org.pokemonrun.ruleadmin.daoimpl;

import org.pokemonrun.ruleadmin.dao.PathNodeDao;
import org.pokemonrun.ruleadmin.entity.PathNode;
import org.pokemonrun.ruleadmin.repository.PathNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PathNodeDaoImpl implements PathNodeDao {
    @Autowired
    private PathNodeRepository pathNodeRepository;

    @Override
    public List<PathNode> getNodes() {
        return pathNodeRepository.findAll();
    }

    @Override
    public boolean replaceNodes(List<PathNode> nodes){
        pathNodeRepository.deleteAll();
        pathNodeRepository.saveAll(nodes);
        return true;
    }
}
