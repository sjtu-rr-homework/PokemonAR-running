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
    public void save(Moment moment) {
        MomentRepository.save(moment);
    }

    @Override
    public List<Moment> gettenhistorymoment(long timestamp) {
        return MomentRepository.findTop10ByTimestampLessThanOrderByTimestampDesc(timestamp);//get ten moments which is uploaded before the timestamp and order by time
    }

    @Override
    public List<Moment> gettennewmoment(long timestamp) {
        return MomentRepository.findTop10ByTimestampGreaterThanOrderByTimestampAsc(timestamp);//get ten moments which is uploaded after the timestamp and order by time
    }
}
