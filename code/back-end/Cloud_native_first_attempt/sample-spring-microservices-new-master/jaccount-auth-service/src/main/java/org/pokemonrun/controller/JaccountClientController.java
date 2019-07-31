package org.pokemonrun.controller;

import org.pokemonrun.info.JaccountTokenInfo;
import org.pokemonrun.info.UserInfo;
import org.pokemonrun.info.ValidationInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;

@RestController
public class JaccountClientController {
    @PostMapping("/token")
    public boolean postToken(@RequestBody JaccountTokenInfo resp){
        return true;
    }
    @GetMapping("/login")
    public UserInfo validate(@RequestBody ValidationInfo info){
        // if validation info is empty or invalid/expired, redirect to jAccount API
        RestTemplate restTemplate = new RestTemplate();
        return ;
    }
}
