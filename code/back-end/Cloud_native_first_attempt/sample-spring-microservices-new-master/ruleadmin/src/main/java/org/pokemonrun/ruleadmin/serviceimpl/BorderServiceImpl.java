package org.pokemonrun.ruleadmin.serviceimpl;

import org.pokemonrun.ruleadmin.dao.PathNodeDao;
import org.pokemonrun.ruleadmin.entity.PathNode;
import org.pokemonrun.ruleadmin.info.PathNodeInfo;
import org.pokemonrun.ruleadmin.service.BorderService;
import org.pokemonrun.ruleadmin.util.Edge;
import org.pokemonrun.ruleadmin.util.PathNodeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class BorderServiceImpl implements BorderService {
    @Autowired
    private PathNodeDao pathNodeDao;

    @Override
    public List<PathNodeInfo> getBorder() {
        List<PathNode> nodes = pathNodeDao.getNodes();
        List<PathNodeInfo> list = new LinkedList<>();
        for (PathNode node : nodes) {
            list.add(PathNodeConverter.toInfo(node));
        }
        return list;
    }

    @Override
    public boolean setBorder(List<PathNodeInfo> border){
        int size = border.size();
        if(size >= 1 && size <= 2){
            // incomplete border
            return false;
        }
        // fetch nodes on the border path
        List<PathNode> list = new ArrayList<>();
        for(PathNodeInfo info : border){
            PathNode node = PathNodeConverter.toEntity(info);
            double lng = node.getLongitude();
            double lat = node.getLatitude();
            if(lng < -180 || lng > 180 || lat < -90 || lat > 90){
                // invalid position on map
                return false;
            }
            list.add(node);
        }
        // construct edge list
        List<Edge> edges = new ArrayList<>();
        for(int i = 0; i < size; i++){
            PathNode n1 = list.get(i), n2 = list.get((i + 1) % size);
            edges.add(new Edge(n1.getLongitude(), n1.getLatitude(),
                    n2.getLongitude(), n2.getLatitude()));
        }
        // check if adjacent edges duplicate
        for(int i = 0; i < size; i++){
            Edge e1 = edges.get(i), e2 = edges.get((i + 1) % size);
            if(e1.equals(e2)){
                return false;
            }
        }
        // check if non-adjacent edges intersect
        for(int i = 0; i < size - 2; i++){
            for(int j = i + 2; j < size; j++){
                if(i == 0 && j == size - 1){
                    continue;
                }
                Edge e1 = edges.get(i), e2 = edges.get(j);
                if(e1.intersects(e2)){
                    return false;
                }
            }
        }
        // call DAO
        pathNodeDao.replaceNodes(list);
        return true;
    }
}
