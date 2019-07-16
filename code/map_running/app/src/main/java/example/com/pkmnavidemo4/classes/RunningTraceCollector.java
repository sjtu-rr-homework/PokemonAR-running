package example.com.pkmnavidemo4.classes;

import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class RunningTraceCollector {
    private List<LatLng> allLatLng=new ArrayList<LatLng>();
    private List<LatLng> presentLatLng=new ArrayList<LatLng>();
    private double distance;//计算里程，单位米
    public RunningTraceCollector(){
        distance=0;
    }
    public void addLatLng(LatLng latLng){
        if(allLatLng.isEmpty()){
            allLatLng.add(latLng);
            return;
        }
        else{
            allLatLng.add(latLng);
            presentLatLng.clear();
            presentLatLng.add(allLatLng.get(allLatLng.size()-2));
            presentLatLng.add(allLatLng.get(allLatLng.size()-1));
            distance+= AMapUtils.calculateLineDistance(presentLatLng.get(0),presentLatLng.get(1));
        }
    }
    public List<LatLng> getAllLatLng(){
        return allLatLng;
    }
    public void setAllLatLng(List<LatLng> latLngList){
        this.allLatLng=latLngList;
    }
    public List<LatLng> getPresentLatLng(){
        return presentLatLng;
    }
    public double getDistance(){
        return distance;
    }
}
