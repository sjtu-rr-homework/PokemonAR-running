package org.pokemonrun.controller;

import org.pokemonrun.service.AddMomentService;
import org.pokemonrun.service.GetMomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MomentController {
    @Autowired
    private AddMomentService AddMomentService;

    @Autowired
    private GetMomentService GetMomentService;
}
