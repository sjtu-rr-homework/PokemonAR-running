package org.pokemonrun.serviceimpl;

import org.pokemonrun.dao.MomentDao;
import org.pokemonrun.info.MomentInfo;
import org.pokemonrun.service.GetMomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GetMomentServiceimpl implements GetMomentService {
    @Autowired
    private org.pokemonrun.dao.MomentDao MomentDao;
    @Override
    public List<MomentInfo> getAll() {
        return null;
    }

    @Override
    public List<MomentInfo> getOneUser() {
        return null;
    }
}
