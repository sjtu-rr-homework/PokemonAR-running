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
        List<Moment> beforelist=new ArrayList<>();
        for(Moment tempMoment:templist)
        {
            if (Timestamp.valueOf(tempMoment.timestamp).before(ts))
            {
                beforelist.add(tempMoment);
            }
        }
        List<Moment> reslist=new ArrayList<>();
        while(reslist.size()<10&&beforelist.size()>0)
        {
            Timestamp newest=Timestamp.valueOf(beforelist.get(0).timestamp);
            int index=0;
            for(int i=0;i<beforelist.size();i++)
            {
                if(Timestamp.valueOf(beforelist.get(i).timestamp).after(newest))
                {
                    newest=Timestamp.valueOf(beforelist.get(i).timestamp);
                    index=i;
                }
            }
            reslist.add(beforelist.get(index));
            beforelist.remove(index);
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
        Timestamp ts= Timestamp.valueOf(timestamp);
        List<Moment> beforelist=new ArrayList<>();
        for(Moment tempMoment:templist)
        {
            if (Timestamp.valueOf(tempMoment.timestamp).before(ts))
            {
                beforelist.add(tempMoment);
            }
        }
        List<Moment> reslist=new ArrayList<>();
        while(reslist.size()<10&&beforelist.size()>0)
        {
            Timestamp newest=Timestamp.valueOf(beforelist.get(0).timestamp);
            int index=0;
            for(int i=0;i<beforelist.size();i++)
            {
                if(Timestamp.valueOf(beforelist.get(i).timestamp).after(newest))
                {
                    newest=Timestamp.valueOf(beforelist.get(i).timestamp);
                    index=i;
                }
            }
            reslist.add(beforelist.get(index));
            beforelist.remove(index);
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
    public List<MomentInfo> refresh(String timestamp) {
        List<Moment> templist= MomentDao.getAll();
        Timestamp ts= Timestamp.valueOf(timestamp);
        List<Moment> afterlist=new ArrayList<>();
        for(Moment tempMoment:templist)
        {
            if (Timestamp.valueOf(tempMoment.timestamp).after(ts))
            {
                afterlist.add(tempMoment);
            }
        }
        List<MomentInfo> tempInfoList=new ArrayList<>();
        for(Moment tempMoment:afterlist)
        {
            MomentInfo tempInfo=new MomentInfo(tempMoment.text,tempMoment.timestamp,tempMoment.username,tempMoment.pic1,tempMoment.pic2,tempMoment.pic3,tempMoment.pic4,tempMoment.pic5,tempMoment.pic6,tempMoment.pic7,tempMoment.pic8,tempMoment.pic9);
            tempInfoList.add(tempInfo);
        }
        return tempInfoList;
    }
}
