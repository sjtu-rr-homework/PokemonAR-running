package org.pokemonrun.serviceimpl;

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

    @Override
    public List<MomentInfo> getAll(String timestamp) {
        List<Moment> templist= MomentDao.getAll();
        Timestamp ts= Timestamp.valueOf(timestamp);
        List<Moment> reslist=new ArrayList<>();
        for(Moment tempMoment:templist)
        {
            if (Timestamp.valueOf(tempMoment.timestamp).after(ts) || Timestamp.valueOf(tempMoment.timestamp).equals(ts))
            {
                
            }

        }




        List<MomentInfo> tempInfoList=new ArrayList<>();
        for(Moment tempMoment:reslist)
        {
            MomentInfo tempInfo=new MomentInfo(tempMoment.text,tempMoment.timestamp,tempMoment.username,tempMoment.pic1,tempMoment.pic2,tempMoment.pic3,tempMoment.pic4,tempMoment.pic5,tempMoment.pic6,tempMoment.pic7,tempMoment.pic8,tempMoment.pic9);
            tempInfoList.add(tempInfo);
        }





        return tempInfoList;
    }

    @Override
    public List<MomentInfo> getOneUser(String username,String timestamp) {
        List<Moment> templist= MomentDao.getUserAll(username);
        List<MomentInfo> tempInfoList=new ArrayList<>();
        for(Moment tempMoment:templist)
        {
            MomentInfo tempInfo=new MomentInfo(tempMoment.text,tempMoment.timestamp,tempMoment.username,tempMoment.pic1,tempMoment.pic2,tempMoment.pic3,tempMoment.pic4,tempMoment.pic5,tempMoment.pic6,tempMoment.pic7,tempMoment.pic8,tempMoment.pic9);
            tempInfoList.add(tempInfo);
        }
        return tempInfoList;
    }
}
