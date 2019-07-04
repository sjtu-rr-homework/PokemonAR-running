package org.simulation.controller;

import org.simulation.client.StatsClient;
import org.simulation.service.Simulator;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class SimulationController {
    private StatsClient stats;
    private Simulator simulator = new Simulator();

    @PostMapping("simulate")
    public Map<String, Object> simulate(@RequestBody Map<String, Object> req){
        Map<String, Object> res = new HashMap<>();
        Object tmp = req.get("steps");
        int steps;
        if(tmp instanceof Integer){
            steps=(int)tmp;
        }
        else {
            steps = Integer.parseInt((String) req.get("steps"));
        }
        List<List<Integer>> initialBoard = (List<List<Integer>>) req.get("initialBoard");
        List<List<List<Integer>>> simulation = simulator.getSimulation(initialBoard, steps);
        res.put("simulation", simulation);
        System.out.println("Once");
        return res;
    }
}
