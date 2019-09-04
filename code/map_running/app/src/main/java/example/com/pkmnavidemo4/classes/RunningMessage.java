package example.com.pkmnavidemo4.classes;

import com.amap.api.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RunningMessage {
    private RunningTraceCollector runningTraceCollector;
    private RunningTimeCollector runningTimeCollector;
    private double length;//总里程，单位m
    private int timePerKM;//每公里配速，单位s
    private int exp;//经验值，暂缺逻辑，等待实现
    private Date start;
    private long LastTime;
    public RunningMessage(Date start){
        this.start=start;
        runningTimeCollector=new RunningTimeCollector(start);
        runningTraceCollector=new RunningTraceCollector();
        length=0;
        LastTime=0;
        exp=0;
    }
    public void newPoing(LatLng newLatLng,Date present){
        runningTraceCollector.addLatLng(newLatLng);
        runningTimeCollector.setNewTime(present);
        length=runningTraceCollector.getDistance();
        LastTime=runningTimeCollector.getConstantTime();
        if(length!=0) {
            timePerKM = (int) (LastTime * 1000 / (int) length);
        }
        exp=(int)(length/20+LastTime/5);
    }
    public List<LatLng> getAllLatLng(){
        return runningTraceCollector.getAllLatLng();
    }
    public void setAllLatLng(List<LatLng> latLngList){
        runningTraceCollector.setAllLatLng(latLngList);
    }
    public List<LatLng> getPresentLatLng(){
        return runningTraceCollector.getAllLatLng();
    }
    public double getLength(){
        return length;
    }
    public long getLastTime(){
        return LastTime;
    }
    public int getTimePerKM(){
        return timePerKM;
    }
    public Date getStart(){
        return this.start;
    }
    public int getExp(){
        return exp;
    }
}
