package org.pokemonrun.controller;

import org.pokemonrun.info.Locationinfo;
import org.pokemonrun.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class LocationController {

    @Autowired
    LocationService LocationService;

    @PostMapping("/refresh/location")
    public boolean saveRecord(@RequestBody Locationinfo Locationinfo) {
        return LocationService.refreshLocation(Locationinfo);
    }

    @PostMapping("/get/nearby")
    public List<Locationinfo> getNearBy(@RequestBody Locationinfo Locationinfo) {
        return LocationService.getNearBy(Locationinfo);
    }

}
