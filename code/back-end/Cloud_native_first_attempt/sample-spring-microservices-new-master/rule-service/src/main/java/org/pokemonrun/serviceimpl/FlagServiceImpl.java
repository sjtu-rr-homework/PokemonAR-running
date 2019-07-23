package org.pokemonrun.serviceimpl;

import org.pokemonrun.dao.FlagDao;
import org.pokemonrun.entity.Flag;
import org.pokemonrun.info.FlagInfo;
import org.pokemonrun.service.FlagService;
import org.pokemonrun.util.FlagConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class FlagServiceImpl implements FlagService {
    @Autowired
    private FlagDao flagDao;

    @Override
    public List<FlagInfo> getFlags() {
        List<Flag> flags = flagDao.getFlags();
        List<FlagInfo> list = new LinkedList<>();
        for (Flag flag : flags) {
            list.add(FlagConverter.toInfo(flag));
        }
        return list;
    }

    @Override
    public boolean setFlags(List<FlagInfo> flags){
        List<Flag> list = new LinkedList<>();
        for(FlagInfo info : flags){
            Flag flag = FlagConverter.toEntity(info);
            double lng = flag.getLongitude();
            double lat = flag.getLatitude();
            if(lng < -180 || lng > 180 || lat < -90 || lat > 90){
                // invalid position on map
                return false;
            }
            list.add(flag);
        }
        flagDao.replaceFlags(list);
        return true;
    }
}
