package org.pokemonrun.ruleadmin.dao;

import org.pokemonrun.ruleadmin.entity.PathNode;

import javax.transaction.Transactional;
import java.util.List;

@Transactional(rollbackOn = Exception.class)
public interface PathNodeDao {
    List<PathNode> getNodes();
    boolean replaceNodes(List<PathNode> flags);
}
