package com.example.save;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("com.example.save.Remote")
@RestController
public class SaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaveApplication.class, args);
    }
    @GetMapping("/SaveResult")
    public String test(){
        return "this is save!";
    }
}
