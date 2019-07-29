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

    @GetMapping("/get/user/moment/{username}")
    public List<MomentInfo> getUserMoment(@PathVariable("username") String username) {
        return GetMomentService.getOneUser(username);
    }

    @GetMapping("/get/all/moment")
    public List<MomentInfo> getAllMoment() {
        return GetMomentService.getAll();
    }


}
