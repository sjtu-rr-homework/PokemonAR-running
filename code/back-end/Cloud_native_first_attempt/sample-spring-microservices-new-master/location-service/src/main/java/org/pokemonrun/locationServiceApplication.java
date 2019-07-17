package org.pokemonrun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class locationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(locationServiceApplication.class, args);
    }

}
