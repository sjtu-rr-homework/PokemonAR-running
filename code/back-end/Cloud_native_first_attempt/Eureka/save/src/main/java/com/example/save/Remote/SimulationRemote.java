package com.example.save.Remote;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

@FeignClient(name= "SIMULATION")
public interface SimulationRemote {

      Map<String, Object> simulate(@RequestBody Map<String, Object> req);


}