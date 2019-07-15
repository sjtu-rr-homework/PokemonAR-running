package example.com.pkmnavidemo4.classes;

import com.amap.api.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    private static String userName="test1";
    public static String getUserName(){
        return userName;
    }
    public static void setUserName(String name){
        userName=name;
    }
    public static List<List<LatLng>> recordLatLngList=new ArrayList<List<LatLng>>();
    public static List<String> startTime=new ArrayList<String>();
    public static List<String> rocordLength=new ArrayList<String>();
    public static List<String> recordLastTime=new ArrayList<String>();
    public static boolean isrecordGet=false;
}
