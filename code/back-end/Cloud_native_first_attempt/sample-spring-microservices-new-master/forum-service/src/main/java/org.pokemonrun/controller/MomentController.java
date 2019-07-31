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

    @GetMapping("/get/user/moment/username/{username}/timestamp/{timestamp}")
    public List<MomentInfo> getUserMoment(@PathVariable("username") String username, @PathVariable("timestamp") String timestamp) {
        return GetMomentService.getOneUser(username,timestamp);
    }

    @GetMapping("/get/all/moment/timestamp/{timestamp}")
    public List<MomentInfo> getAllMoment(@PathVariable("timestamp") String timestamp) {
        return GetMomentService.getAll(timestamp);
    }


}
