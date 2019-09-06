package org.pokemonrun.client;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/login/username/{username}/password/{password}")
    public boolean login(@PathVariable("username") String username, @PathVariable("password") String password);


}
