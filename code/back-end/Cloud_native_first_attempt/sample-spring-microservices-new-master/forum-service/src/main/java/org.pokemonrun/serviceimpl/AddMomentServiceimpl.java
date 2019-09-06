package org.pokemonrun.serviceimpl;

import org.pokemonrun.dao.MomentDao;
import org.pokemonrun.entity.Moment;
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
        System.out.println(momentInfo.timestamp);
        Moment temp=new Moment(momentInfo.text,momentInfo.timestamp,momentInfo.username,momentInfo.pic1,momentInfo.pic2,momentInfo.pic3,momentInfo.pic4,momentInfo.pic5,momentInfo.pic6,momentInfo.pic7,momentInfo.pic8,momentInfo.pic9);
        MomentDao.save(temp);//generate ID automatically
        return true;
    }
}
