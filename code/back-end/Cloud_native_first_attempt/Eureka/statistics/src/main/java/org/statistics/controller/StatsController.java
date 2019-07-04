package org.statistics.controller;

import feign.Feign;
import feign.Retryer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.statistics.Remote.SimulationRemote;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
public class StatsController {
    static int boardAmount=0;
    static int maxStepAmount=0;
    static int boardSuccessAmount=0;
    @Autowired
    SimulationRemote simulationRemote;
    @PostMapping("/user")
    public Map<String, Object> getUserStats(){
        Map<String, Object> res = new HashMap<>();
        res.put("boardAmount",boardAmount);
        res.put("maxStepAmount",maxStepAmount);
        res.put("boardSuccessAmount",boardSuccessAmount);
        return res;
    }

    @PostMapping("/ChangeData")
    public boolean change(@RequestBody Map<String, Object> req){
        List<List<List<Integer>>> res = (List<List<List<Integer>>>) simulationRemote.simulate(req).get("simulation");
        boardAmount++;
        if(lastpage(res)>maxStepAmount)
            maxStepAmount=lastpage(res);
        if(lastpage(res)==res.size())
            boardSuccessAmount++;
        return true;
    }

    public boolean checklive (List<List<Integer>> page){
       for(int i=0;i<page.size();i++)
       {
           for(int j=0;j<page.size();j++)
           {
               if(page.get(i).get(j)==1)
                   return true;
           }
       }
       return false;
    }

    public int lastpage (List<List<List<Integer>>> pages)
    {
        for(int i=0;i<pages.size();i++) {
           if(!checklive(pages.get(i)))
               return i;
        }
        return pages.size();
    }
}
