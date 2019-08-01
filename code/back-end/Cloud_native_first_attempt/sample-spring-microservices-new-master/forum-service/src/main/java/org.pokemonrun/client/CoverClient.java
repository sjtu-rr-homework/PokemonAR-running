package org.pokemonrun.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface CoverClient {
    @GetMapping("/get/cover/username/{username}")
    public byte[] getCover(@PathVariable("username") String username);
}
