package example.com.pkmnavidemo4.classes;

import com.amap.api.maps.model.LatLng;

public class ElfPoint {
    private LatLng latLng;
    private int elfId;
    public ElfPoint(LatLng latLng,int elfId){
        this.latLng=latLng;
        this.elfId=elfId;
    }
    public ElfPoint(){}
    public LatLng getLatLng(){
        return this.latLng;
    }
    public int getElfId(){
        return this.elfId;
    }
    public void setLatLng(LatLng latLng){
        this.latLng=latLng;
    }
    public void setElfId(int elfId){
        this.elfId=elfId;
    }
}
