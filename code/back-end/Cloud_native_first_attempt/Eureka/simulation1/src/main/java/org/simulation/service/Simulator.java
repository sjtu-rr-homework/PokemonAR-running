package org.simulation.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Simulator {
    private boolean inBorder(List<List<Integer>> board, int row, int col){
        return row >= 0 && row < board.size() && col >= 0 && col < board.get(0).size();
    }
    private int liveAround(List<List<Integer>> board, int row, int col){
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1}, dc = {-1, 0, 1, -1, 1, -1, 0, 1};
        int res = 0;
        for(int i = 0; i < 8; i++){
            int r = row + dr[i], c = col + dc[i];
            if(inBorder(board, r, c) && board.get(r).get(c) == 1){
                res++;
            }
        }
        return res;
    }
    public List<List<List<Integer>>> getSimulation(List<List<Integer>> board, int steps){
        List<List<List<Integer>>> simulation = new LinkedList<>();
        simulation.add(board);
        int height = board.size(), width = board.get(0).size();
        for(int s = 0; s < steps; s++){
            List<List<Integer>> gen = new ArrayList<>();
            for(int i = 0; i < height; i++){
                List<Integer> row = new ArrayList<>();
                for(int j = 0; j < width; j++){
                    int live = liveAround(board, i, j);
                    if(live == 3){
                        // alive
                        row.add(1);
                    }else if(live == 2){
                        // remain
                        row.add(board.get(i).get(j));
                    }else{
                        // dead
                        row.add(0);
                    }
                }
                gen.add(row);
            }
            simulation.add(gen);
            board = gen;
        }
        return simulation;
    }
}
