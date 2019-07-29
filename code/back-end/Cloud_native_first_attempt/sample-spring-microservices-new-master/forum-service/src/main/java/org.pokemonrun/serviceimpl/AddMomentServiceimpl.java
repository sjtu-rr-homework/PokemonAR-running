package org.pokemonrun.serviceimpl;

import org.pokemonrun.dao.MomentDao;
import org.pokemonrun.info.MomentInfo;
import org.pokemonrun.service.AddMomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddMomentServiceimpl implements AddMomentService {
    @Autowired
    private MomentDao MomentDao;
    @Override
    public boolean addMoment(MomentInfo momentInfo) {
        return false;
    }
}
