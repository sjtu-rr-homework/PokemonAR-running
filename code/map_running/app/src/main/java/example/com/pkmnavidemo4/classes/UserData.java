package example.com.pkmnavidemo4.classes;

import com.amap.api.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    private static int exp;
    private static String userName;
    private static List<String> elfList;
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
    public static List<String> getElfList(){
        return elfList;
    }
    public static void setElfList( List<String> list){
        elfList=list;
    }
    public static List<String> friend=new ArrayList<String>();
    public static List <Map> getElfDetails(){
        return elfDetailsList;
    }
    public static void setElfDetails( List<Map> list){
        elfDetailsList=list;
    }
    public static void reverse(){
        onlyHave=!onlyHave;
    }
    public static void initonlyHave(){
        onlyHave=false;
    }
    public static boolean getOnlyHave(){
        return onlyHave;
    }
    public static boolean consumeExp(int num){
        if(exp>num){
            exp-=num;
            HttpHandler.changeExp(userName,0-num);
            return true;
        }
        else
            return false;
    }
    public static int getExp(){
       return exp;
    }
    public static void setExp(int num){
        exp=num; }
}
