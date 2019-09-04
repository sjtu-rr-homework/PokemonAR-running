package example.com.pkmnavidemo4.classes;

import java.util.Date;

public class RunningTimeCollector {
    private Date start;
    private Date present;
    private long constantTime;//持续时间，单位second
    public RunningTimeCollector(Date start){
        this.start=start;
        constantTime=0;
    }
    public void setNewTime(Date present){
        this.present=present;
        constantTime=this.present.getTime()-start.getTime();
        constantTime=constantTime/1000;
    }
    public long getConstantTime(){
        return constantTime;
    }
}
