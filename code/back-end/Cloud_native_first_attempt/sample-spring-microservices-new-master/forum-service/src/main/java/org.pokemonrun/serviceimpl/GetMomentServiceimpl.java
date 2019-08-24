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
        List<Moment> templist= MomentDao.getAll();
        List<Moment> beforelist=new ArrayList<>();
        for(Moment tempMoment:templist)
        {
            if (tempMoment.timestamp<timestamp)
            {
                beforelist.add(tempMoment);
            }
        }
        List<Moment> reslist=new ArrayList<>();
        while(reslist.size()<10&&beforelist.size()>0)
        {
            long newest=beforelist.get(0).timestamp;
            int index=0;
            for(int i=0;i<beforelist.size();i++)
            {
                if(beforelist.get(i).timestamp>newest)
                {
                    newest=beforelist.get(i).timestamp;
                    index=i;
                }
            }
            reslist.add(beforelist.get(index));
            beforelist.remove(index);
        }
        List<MomentInfo> tempInfoList=new ArrayList<>();
        for(Moment tempMoment:reslist)
        {
            MomentInfo tempInfo=new MomentInfo(tempMoment.text,tempMoment.timestamp,tempMoment.username,tempMoment.pic1,tempMoment.pic2,tempMoment.pic3,tempMoment.pic4,tempMoment.pic5,tempMoment.pic6,tempMoment.pic7,tempMoment.pic8,tempMoment.pic9, UserClient.getCover(tempMoment.username).pic);
            tempInfoList.add(tempInfo);
        }





        return tempInfoList;
    }

    @Override
    public List<MomentInfo> getOneUser(String username,long timestamp) {
        List<Moment> templist= MomentDao.getUserAll(username);
        List<Moment> beforelist=new ArrayList<>();
        for(Moment tempMoment:templist)
        {
            if (tempMoment.timestamp<timestamp)
            {
                beforelist.add(tempMoment);
            }
        }
        List<Moment> reslist=new ArrayList<>();
        while(reslist.size()<10&&beforelist.size()>0)
        {
            long newest=beforelist.get(0).timestamp;
            int index=0;
            for(int i=0;i<beforelist.size();i++)
            {
                if(beforelist.get(i).timestamp>newest)
                {
                    newest=beforelist.get(i).timestamp;
                    index=i;
                }
            }
            reslist.add(beforelist.get(index));
            beforelist.remove(index);
        }
        List<MomentInfo> tempInfoList=new ArrayList<>();
        for(Moment tempMoment:reslist)
        {
            MomentInfo tempInfo=new MomentInfo(tempMoment.text,tempMoment.timestamp,tempMoment.username,tempMoment.pic1,tempMoment.pic2,tempMoment.pic3,tempMoment.pic4,tempMoment.pic5,tempMoment.pic6,tempMoment.pic7,tempMoment.pic8,tempMoment.pic9, UserClient.getCover(tempMoment.username).pic);
            tempInfoList.add(tempInfo);
        }
        return tempInfoList;
    }

    @Override
    public List<MomentInfo> refresh(long timestamp) {
        List<Moment> templist= MomentDao.getAll();
        List<Moment> afterlist=new ArrayList<>();
        for(Moment tempMoment:templist)
        {
            if (tempMoment.timestamp>timestamp)
            {
                afterlist.add(tempMoment);
            }
        }
        List<MomentInfo> tempInfoList=new ArrayList<>();
        for(Moment tempMoment:afterlist)
        {
            MomentInfo tempInfo=new MomentInfo(tempMoment.text,tempMoment.timestamp,tempMoment.username,tempMoment.pic1,tempMoment.pic2,tempMoment.pic3,tempMoment.pic4,tempMoment.pic5,tempMoment.pic6,tempMoment.pic7,tempMoment.pic8,tempMoment.pic9, UserClient.getCover(tempMoment.username).pic);
            tempInfoList.add(tempInfo);
        }
        return tempInfoList;
    }
}
