package org.pokemonrun.controller;

import org.pokemonrun.info.MomentInfo;
import org.pokemonrun.service.AddMomentService;
import org.pokemonrun.service.GetMomentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MomentController {
    @Autowired
    private AddMomentService AddMomentService;

    @Autowired
    private GetMomentService GetMomentService;

    @PostMapping("/add/moment")
    public boolean AddMoment(@RequestBody MomentInfo MomentInfo) {
        return AddMomentService.addMoment(MomentInfo);
    }

    @GetMapping("/get/all/moment/timestamp/{timestamp}")//get ten moments which is uploaded before the timestamp
    public List<MomentInfo> getAllMoment(@PathVariable("timestamp") String timestamp) {
        return GetMomentService.getAll(Long.parseLong(timestamp));
    }

    @GetMapping("/get/refresh/moment/timestamp/{timestamp}")//get ten moments which is posted after the timestamp
    public List<MomentInfo> refreshMoment(@PathVariable("timestamp") String timestamp) {
        return GetMomentService.refresh(Long.parseLong(timestamp));
    }

}
