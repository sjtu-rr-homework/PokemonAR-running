package org.pokemonrun.daoimpl;

import org.pokemonrun.dao.CoverDao;
import org.pokemonrun.entity.Cover;
import org.pokemonrun.repository.CoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CoverDaoimpl implements CoverDao {

    @Autowired
    private CoverRepository CoverRepository;

    @Override
    public Cover getOneCover(String username) {
        return CoverRepository.findByUsername(username);
    }

    @Override
    public void save(Cover cover) {
        CoverRepository.save(cover);
    }
}
