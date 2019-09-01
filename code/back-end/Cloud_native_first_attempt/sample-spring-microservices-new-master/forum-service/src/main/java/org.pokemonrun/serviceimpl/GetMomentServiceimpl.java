package org.pokemonrun.serviceimpl;

import org.pokemonrun.client.UserClient;
import org.pokemonrun.dao.MomentDao;
import org.pokemonrun.entity.Moment;
import org.pokemonrun.info.MomentInfo;
import org.pokemonrun.service.GetMomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Service
public class GetMomentServiceimpl implements GetMomentService {
    @Autowired
    private MomentDao MomentDao;

    @Autowired
    private UserClient UserClient;

    @Override
    public List<MomentInfo> getAll(long timestamp) {
        List<Moment> templist= MomentDao.gettenhistorymoment(timestamp);
        List<MomentInfo> tempInfoList=new ArrayList<>();
        for(Moment tempMoment:templist)
        {
            MomentInfo tempInfo=new MomentInfo(tempMoment.text,tempMoment.timestamp,tempMoment.username,tempMoment.pic1,tempMoment.pic2,tempMoment.pic3,tempMoment.pic4,tempMoment.pic5,tempMoment.pic6,tempMoment.pic7,tempMoment.pic8,tempMoment.pic9, UserClient.getCover(tempMoment.username).pic);
            tempInfoList.add(tempInfo);
        }
        return tempInfoList;
    }

    @Override
    public List<MomentInfo> refresh(long timestamp) {
        List<Moment> templist= MomentDao.gettennewmoment(timestamp);
        List<MomentInfo> tempInfoList=new ArrayList<>();
        for(int i=templist.size()-1;i>=0;i--)
        {
            Moment tempMoment=templist.get(i);
            MomentInfo tempInfo=new MomentInfo(tempMoment.text,tempMoment.timestamp,tempMoment.username,tempMoment.pic1,tempMoment.pic2,tempMoment.pic3,tempMoment.pic4,tempMoment.pic5,tempMoment.pic6,tempMoment.pic7,tempMoment.pic8,tempMoment.pic9, UserClient.getCover(tempMoment.username).pic);
            tempInfoList.add(tempInfo);
        }
        return tempInfoList;

    }
}
