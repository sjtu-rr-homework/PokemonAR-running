package org.simulation.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient("statistics")
public interface StatsClient {
    @PostMapping("/user")
    Map<String, Object> getUserStats();
}
