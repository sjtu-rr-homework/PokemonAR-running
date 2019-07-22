package org.pokemonrun;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {
    @RequestMapping("")
    public String fallback(){
        return "error";
    }
}
