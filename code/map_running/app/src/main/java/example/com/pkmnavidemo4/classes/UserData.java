package example.com.pkmnavidemo4.classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.Pair;

import com.amap.api.maps.model.LatLng;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import example.com.pkmnavidemo4.Bitmap.BitmapUtils;
import example.com.pkmnavidemo4.R;
import example.com.pkmnavidemo4.SceneformActivity;

public class UserData {
    //用户头像
    private static Bitmap cover;
    //头像锁
    public static boolean isCoverGet;
    //判断所有动态是否已经获取
    public static boolean isAllMomentsGet;
    //锁.用来判断得到的新的最新动态时间戳是否更更新
    public static boolean isNewTimeInit;
    //新旧动态时间戳
    private static long oldForumTime=-1;
    private static long newForumTime;
    private static double mileage;
    private static double mileageGoal;
    public static double distance;
    //锁,朋友信息获取的锁
    public static boolean isFriendInfoGet=false;
    private static Map userInfo;
    private static Map friendUserInfo;
    private static Map friendPetInfo;
    private static int exp;
    private static String userName;
    private static List<String> elfList;
    private static  List<Map> elfDetailsList;
    private static  List<Map> moments;
    //动态获取锁
    public static double upper_border=-1;
    public static double lower_border=-1;
    public static  boolean isMomentsGet=false;
    public static  boolean isMomentsRefresh=false;
    //用来判断是否只看已拥有精灵
    private static  boolean onlyHave=false;
    //锁,自己信息获取的锁
    public static boolean isUserinfoGet=false;
    public static int flagNum=0;
    public static int place_choice=0;
    public static Map<Integer,Integer> catchElfList=new HashMap();
    public static List<LatLng> constraint=new ArrayList<LatLng>();
    public static String accessToken;
	//设置头像
    public static void setCover(String getCover){
        if(getCover.length()==0) {
            cover = null;
        }
        else {
            cover = BitmapUtils.base64ToBitmap(getCover);
        }
    }
	//得到用户头像
    public static Bitmap getCover(String name){
        isCoverGet=true;
        HttpHandler.getCover(name);
        while(isCoverGet){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return cover;
    }
    //直接得到用户头像
    public static Bitmap getCoverImmediately(String name){
        return cover;
    }
	//设置最新动态的时间标签
    public static void setNewForumTime(long t){
        //第一次进入时刷新最新动态时间或新得到的时间更新时才能更新
        if(!isNewTimeInit||t>newForumTime) {
            newForumTime = t;
            isNewTimeInit=true;
        }
    }
	//得到最新动态的时间标签
    public static long getNewForumTime(){
        return newForumTime;
    }
	//设置最老动态的时间标签
    public static void setOldForumTime(long t){
        oldForumTime=t;
    }
    //得到最老动态的时间标签
    public static long getOldForumTime(){
        if(oldForumTime==-1)
            return System.currentTimeMillis();
        return oldForumTime;
    }
    public static List<Map> getMoments() {
		moments=null;
        isMomentsGet=true;
        HttpHandler.getMoments(UserData.getOldForumTime());
        while(isMomentsGet){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return moments;
    }
	public static List<Map> refreshMoments(Context context) {
        moments=null;
        isMomentsRefresh=true;
        HttpHandler.refreshMoments(UserData.getNewForumTime(),context);
        while(isMomentsRefresh){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return moments;
    }
    public static void setMoments( List<Map> getMoments) {
        moments = getMoments;
    }
    public static double getMileage(){
        return mileage;
    }
    public static double getMileageGoal(){
        return mileageGoal;
    }
    public static void setMileage(double mileage){
        UserData.mileage=mileage;
    }
    public static void setMileageGoal(double mileageGoal){
        UserData.mileageGoal=mileageGoal;
    }
    public static void catchOne(int variety){
         if(catchElfList.get(variety)==null)
             catchElfList.put(variety,1);
         else{
             int termVariety=variety;
             int num=catchElfList.get(variety)+1;
                 catchElfList.put(termVariety,num);
         }
    }
    public static void successRun(){
        for (Integer key : catchElfList.keySet()) {
            HttpHandler.successCatch(userName,key+"",""+catchElfList.get(key));
        }
        catchElfList.clear();
    }
    public static void  failRun(){
        catchElfList.clear();
    }
    public static Map getUserInfo(){return userInfo;}
    public static Map getElfWithId(int id){
        for (int i = 0; i < elfDetailsList.size(); i++) {
            if(elfDetailsList.get(i).get("typeID").toString().equals(id+"")){
               return elfDetailsList.get(i);
            }
        }
        return new HashMap();
    }
    public static Map getFriendInfo(String username){
        isFriendInfoGet=true;
        HttpHandler.getUserInfo(username,2);
        while(isFriendInfoGet){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //用户未设置出战精灵
        if((int)friendUserInfo.get("pet")==-1) {
            return null;
        }
        isFriendInfoGet=true;
        HttpHandler.getPetInfo(username,(int)friendUserInfo.get("pet"));
        while(isFriendInfoGet){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return friendPetInfo;

    }
    public static void setPet(int variety){
        HttpHandler.setPet(userName,variety);
        userInfo.replace("pet",variety);
    }
    public static void setFriengPetInfoMap(Map map){
        friendPetInfo=map;
    }
    public static void setUserInfoMap(Map map,int variety){
        if(variety==1)
            userInfo=map;
        if(variety==2)
            friendUserInfo=map;
    }
    public static void setUserInfo(String userName,int variety){
        HttpHandler.getUserInfo(userName,variety);
    }
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
        if(exp>=num){
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
        exp=num;
    }
    //增加经验
	public static void addExp(int num){
        HttpHandler.changeExp(userName,num);
        exp+=num;
    }
}
