package org.pokemonrun.util;

public class Edge {
    private double x1, x2;
    private double y1, y2;
    public Edge(double x1, double y1, double x2, double y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    private double cross(double x1, double y1, double x2, double y2){
        return x1 * y2 - x2 * y1;
    }
    // whether the 2 nodes are at both sides of edge e
    private boolean biside(Edge e){
        return cross(x1 - e.x1, y1 - e.y1, x1 - e.x2, y1 - e.y2)
                * cross(x2 - e.x1, y2 - e.y1, x2 - e.x2, y2 - e.y2) < 0;
    }
    public boolean intersects(Edge e){
        boolean ip1 = isPoint(), ip2 = e.isPoint();
        if(ip1 && ip2){
            return equals(e);
        }else if(ip1){
            return e.contains(x1, y1);
        }else if(ip2){
            return contains(e.x1, e.y1);
        }else{
            // 2 edges
            if(contains(e.x1, e.y1) || contains(e.x2, e.y2)
                    || e.contains(x1, y1) || e.contains(x2, y2)){
                return true;
            }
            return biside(e) && e.biside(this);
        }
    }
    public boolean contains(double x, double y){
        if((x1 == x && y1 == y) || (x2 == x && y2 == y)){
            return true;
        }
        return (x - x1) * (x - x2) + (y - y1) * (y - y2) < 0
                && cross(x - x1, y - y1, x - x2, y - y2) == 0;
    }
    public boolean isPoint(){
        return x1 == x2 && y1 == y2;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Edge){
            Edge e = (Edge)o;
            return (x1 == e.x1 && y1 == e.y1 && x2 == e.x2 && y2 == e.y2)
                    || (x1 == e.x2 && y1 == e.y2 && x2 == e.x1 && y2 == e.y1);
        }
        return false;
    }
}
