package com.example.save.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SaveController {
    static   List<List<List<List<Integer>>>> record=new ArrayList<>();
    @PostMapping("/SaveResult")
    public String save(@RequestBody Map<String, Object> req){
        List<List<List<Integer>>> res =  (List<List<List<Integer>>>) req.get("result");
        record.add(res);
        System.out.print(record.size());
        return "this is save!";
    }
    @PostMapping("/get")
    public Map<String, Object> get(@RequestBody Map<String,String> req){
        Map<String, Object> res=new HashMap<>();
        List<List<List<Integer>>> get =  record.get(Integer.valueOf(req.get("id")));
        res.put("simulation",get);
        return res;
    }
}
