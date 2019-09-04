package org.pokemonrun.client;

import org.pokemonrun.info.Coverinfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/get/cover/username/{username}")
    public Coverinfo getCover(@PathVariable("username") String username);//get 
}
