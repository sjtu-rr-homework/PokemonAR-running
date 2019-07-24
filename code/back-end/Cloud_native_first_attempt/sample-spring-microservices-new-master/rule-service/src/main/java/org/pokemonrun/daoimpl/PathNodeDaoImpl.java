package org.pokemonrun.daoimpl;

import org.pokemonrun.repository.PathNodeRepository;
import org.pokemonrun.dao.PathNodeDao;
import org.pokemonrun.entity.PathNode;
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
