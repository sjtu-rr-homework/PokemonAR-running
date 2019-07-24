package org.pokemonrun.dao;

import org.pokemonrun.entity.PathNode;

import javax.transaction.Transactional;
import java.util.List;

@Transactional(rollbackOn = Exception.class)
public interface PathNodeDao {
    List<PathNode> getNodes();
    boolean replaceNodes(List<PathNode> flags);
}
