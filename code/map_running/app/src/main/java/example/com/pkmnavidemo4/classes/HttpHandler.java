package example.com.pkmnavidemo4.classes;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import example.com.pkmnavidemo4.LoginActivity;
import example.com.pkmnavidemo4.MainActivity;
import example.com.pkmnavidemo4.R;

public class HttpHandler {

    //private static String UrlHead="http://1c77d0af.ngrok.io";

    private static String UrlHead="http://202.120.40.8:30751";


    @Nullable
    public static Activity findActivity(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            ContextWrapper wrapper = (ContextWrapper) context;
            return findActivity(wrapper.getBaseContext());
        } else {
            return null;
        }
    }

    public static void getElfs(String username){

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Map> list = new ArrayList<Map>();
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String loginUrl=UrlHead+"/pet/user/"+username+"/getpets";
                try {
                    //URL url=new URL("https://5184c2d6.ngrok.io/user/login/username/macoredroid/password/c7o2r1e4");
                    URL url=new URL(loginUrl);
                    conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(888000);
                    conn.setReadTimeout(888000);
                   /* InputStream is = conn.getInputStream(); // 获取输入流
                    byte[] data = read(is);*/
                    InputStream in=conn.getInputStream();
                    br=new BufferedReader(new InputStreamReader(in));

                    StringBuilder sb=new StringBuilder();
                    String s;
                    while((s = br.readLine())!=null){
                        sb.append(s);
                    }
                    Log.d("123","---"+sb.toString());
                    JSONArray jsonArray = new JSONArray(sb.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i); // 得到每个对象
                        int id = item.getInt("typeID"); // 获取对象对应的值
                        int num = item.getInt("num");
                        int exp = item.getInt("exp");
                        int grade = item.getInt("grade");
                        Map map = null;
                        map = new HashMap(); // 存放到MAP里面
                        map.put("typeID", id );
                        map.put("num",num);
                        map.put("exp",exp);
                        map.put("grade",grade);
                        list.add(map);
                    }
                    UserData.setElfDetails(list);
                    List<String> elfList = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        elfList.add(""+ list.get(i).get("typeID"));
                    }
                    UserData.setElfList(elfList);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (conn!=null){
                        conn.disconnect();
                    }
                    if (br!=null){
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public static void getElfs(Context context,String username){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Map> list = new ArrayList<Map>();
                MainActivity mainActivity=(MainActivity)context;
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String loginUrl=UrlHead+"/pet/user/"+username+"/getpets";
                try {
                    //URL url=new URL("https://5184c2d6.ngrok.io/user/login/username/macoredroid/password/c7o2r1e4");
                    URL url=new URL(loginUrl);
                    conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(888000);
                    conn.setReadTimeout(888000);
                   /* InputStream is = conn.getInputStream(); // 获取输入流
                    byte[] data = read(is);*/

                    InputStream in=conn.getInputStream();
                    br=new BufferedReader(new InputStreamReader(in));

                    StringBuilder sb=new StringBuilder();
                    String s;
                    while((s = br.readLine())!=null){
                        sb.append(s);
                    }
                    Log.d("123","---"+sb.toString());
                    JSONArray jsonArray = new JSONArray(sb.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i); // 得到每个对象
                        int id = item.getInt("typeID"); // 获取对象对应的值
                        int num = item.getInt("num");
                        int exp = item.getInt("exp");
                        int grade = item.getInt("grade");
                        Map map = null;
                        map = new HashMap(); // 存放到MAP里面
                        map.put("typeID", id );
                        map.put("num",num);
                        map.put("exp",exp);
                        map.put("grade",grade);
                        list.add(map);
                    }
                    UserData.setElfDetails(list);
                    List<String> elfList = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        elfList.add(""+ list.get(i).get("typeID"));
                    }
                    UserData.setElfList(elfList);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (conn!=null){
                        conn.disconnect();
                    }
                    if (br!=null){
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
    public static void successCatch(String username,String typeid,String num){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String loginUrl=UrlHead+"/pet/user/"+username+"/addnum/"+typeid+"/num/"+num;
                try {
                    //URL url=new URL("https://5184c2d6.ngrok.io/user/login/username/macoredroid/password/c7o2r1e4");
                    URL url=new URL(loginUrl);
                    conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    InputStream in=conn.getInputStream();
                    br=new BufferedReader(new InputStreamReader(in));

                    StringBuilder sb=new StringBuilder();
                    String s;
                    while((s = br.readLine())!=null){
                        sb.append(s);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (conn!=null){
                        conn.disconnect();
                    }
                    if (br!=null){
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public static void setPet(String username,int typeid){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String loginUrl=UrlHead+"/user/setpet/username/"+username+"/setpet/"+typeid;
                try {
                    //URL url=new URL("https://5184c2d6.ngrok.io/user/login/username/macoredroid/password/c7o2r1e4");
                    URL url=new URL(loginUrl);
                    conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    InputStream in=conn.getInputStream();

                    br=new BufferedReader(new InputStreamReader(in));

                    StringBuilder sb=new StringBuilder();
                    String s;
                    while((s = br.readLine())!=null){
                        sb.append(s);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (conn!=null){
                        conn.disconnect();
                    }
                    if (br!=null){
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public static void addExp(String username,int variety,int num){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String loginUrl=UrlHead+"/pet/user/"+username+"/addexp/"+variety+"/exp/"+num;
                try {
                    //URL url=new URL("https://5184c2d6.ngrok.io/user/login/username/macoredroid/password/c7o2r1e4");
                    URL url=new URL(loginUrl);
                    conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    InputStream in=conn.getInputStream();
                    br=new BufferedReader(new InputStreamReader(in));

                    StringBuilder sb=new StringBuilder();
                    String s;
                    while((s = br.readLine())!=null){
                        sb.append(s);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (conn!=null){
                        conn.disconnect();
                    }
                    if (br!=null){
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public static void changeExp(String username,int num){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String loginUrl=UrlHead+"/user/addexp/username/"+username+"/exp/"+num;
                try {
                    //URL url=new URL("https://5184c2d6.ngrok.io/user/login/username/macoredroid/password/c7o2r1e4");
                    URL url=new URL(loginUrl);
                    conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    InputStream in=conn.getInputStream();
                    br=new BufferedReader(new InputStreamReader(in));

                    StringBuilder sb=new StringBuilder();
                    String s;
                    while((s = br.readLine())!=null){
                        sb.append(s);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (conn!=null){
                        conn.disconnect();
                    }
                    if (br!=null){
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public static void addGrade(String username,int variety,int num){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String loginUrl=UrlHead+"/pet/user/"+username+"/addgrade/"+variety+"/grade/"+num;
                try {
                    //URL url=new URL("https://5184c2d6.ngrok.io/user/login/username/macoredroid/password/c7o2r1e4");
                    URL url=new URL(loginUrl);
                    conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    InputStream in=conn.getInputStream();
                    br=new BufferedReader(new InputStreamReader(in));

                    StringBuilder sb=new StringBuilder();
                    String s;
                    while((s = br.readLine())!=null){
                        sb.append(s);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (conn!=null){
                        conn.disconnect();
                    }
                    if (br!=null){
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public static void login(Context context,String username, String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String loginUrl=UrlHead+"/user/login/username/"+username+"/password/"+password;
                try {
                    //URL url=new URL("https://5184c2d6.ngrok.io/user/login/username/macoredroid/password/c7o2r1e4");
                    URL url=new URL(loginUrl);
                    conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    InputStream in=conn.getInputStream();
                    br=new BufferedReader(new InputStreamReader(in));

                    StringBuilder sb=new StringBuilder();
                    String s;
                    while((s = br.readLine())!=null){
                        sb.append(s);
                    }
                    if(sb.toString().equals("true")){
                        if(SharedPreferencesUtil.getBoolean(context.getApplicationContext(),"isremember",false)) {
                            SharedPreferencesUtil.putBoolean(context.getApplicationContext(), "isauto", true);
                        }
                        Looper.prepare();
                        UserData.setUserName(username);
                        Toast.makeText(context,"登陆成功",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                        Looper.loop();

                    }
                    else{
                        Looper.prepare();
                        Toast.makeText(context,"登录失败",Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                    //setContent(sb.toString());
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                    if (conn!=null){
                        conn.disconnect();
                    }
                    if (br!=null){
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();

                        }
                    }
                }
            }
        }).start();
    }

    public static void getExp(String username) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String loginUrl=UrlHead+"/user/getexp/username/"+username;
                try {
                    //URL url=new URL("https://5184c2d6.ngrok.io/user/login/username/macoredroid/password/c7o2r1e4");
                    URL url=new URL(loginUrl);
                    conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    InputStream in=conn.getInputStream();
                    br=new BufferedReader(new InputStreamReader(in));

                    StringBuilder sb=new StringBuilder();
                    String s;
                    while((s = br.readLine())!=null){
                        sb.append(s);
                    }

                    UserData.setExp(Integer.valueOf(sb.toString()));
                    //setContent(sb.toString());
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                    if (conn!=null){
                        conn.disconnect();
                    }
                    if (br!=null){
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();

                        }
                    }
                }
            }
        }).start();
    }


    public static void addDistance(String username,double distance){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String loginUrl=UrlHead+"/user/addDistance/username/"+username+"/distance/"+distance;
                try {
                    //URL url=new URL("https://5184c2d6.ngrok.io/user/login/username/macoredroid/password/c7o2r1e4");
                    URL url=new URL(loginUrl);
                    conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    InputStream in=conn.getInputStream();
                    br=new BufferedReader(new InputStreamReader(in));

                    StringBuilder sb=new StringBuilder();
                    String s;
                    while((s = br.readLine())!=null){
                        sb.append(s);
                    }
                    Log.d("holytryer",sb.toString());
                    Log.d("holytryer",username);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (conn!=null){
                        conn.disconnect();
                    }
                    if (br!=null){
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public static void getPetInfo(String username,int variety) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String loginUrl=UrlHead+"/pet/user/"+username+"/getinfo/"+variety;
                try {
                    //URL url=new URL("https://5184c2d6.ngrok.io/user/login/username/macoredroid/password/c7o2r1e4");
                    URL url=new URL(loginUrl);
                    conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    InputStream in=conn.getInputStream();
                    br=new BufferedReader(new InputStreamReader(in));

                    StringBuilder sb=new StringBuilder();
                    String s;
                    while((s = br.readLine())!=null){
                        sb.append(s);
                    }
                    Log.d("123","---"+sb.toString());
                    JSONObject jsonobject = new JSONObject(sb.toString());
                    int exp = jsonobject.getInt("exp"); // 获取对象对应的值
                    int grade =jsonobject.getInt("grade");
                    Map map = null;
                    map = new HashMap(); // 存放到MAP里面
                    map.put("exp", exp);
                    map.put("grade",grade);
                    map.put("typeID",variety);
                    UserData.isFriendInfoGet=false;
                    UserData.setFriengPetInfoMap(map);
                    //setContent(sb.toString());
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                    if (conn!=null){
                        conn.disconnect();
                    }
                    if (br!=null){
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();

                        }
                    }
                }
            }
        }).start();
    }

    public static void getUserInfo(String username,int variety) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String loginUrl=UrlHead+"/user/getinfo/username/"+username;
                try {
                    //URL url=new URL("https://5184c2d6.ngrok.io/user/login/username/macoredroid/password/c7o2r1e4");
                    URL url=new URL(loginUrl);
                    conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    InputStream in=conn.getInputStream();
                    br=new BufferedReader(new InputStreamReader(in));

                    StringBuilder sb=new StringBuilder();
                    String s;
                    while((s = br.readLine())!=null){
                        sb.append(s);
                    }
                    Log.d("133","---"+sb.toString());
                    JSONObject jsonobject = new JSONObject(sb.toString());
                    double distance = jsonobject.getDouble("distance"); // 获取对象对应的值
                    int pet =jsonobject.getInt("pet");
                    String username=jsonobject.getString("username");
                    Map map = null;
                    map = new HashMap(); // 存放到MAP里面
                    map.put("distance", distance );
                    map.put("pet",pet);
                    map.put("username",username);
                    if(variety==2)
                    UserData.isFriendInfoGet=false;

                    UserData.setUserInfoMap(map,variety);
                    //setContent(sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                    if (conn!=null){
                        conn.disconnect();
                    }
                    if (br!=null){
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();

                        }
                    }
                }
            }
        }).start();
    }

    public static void register(Context context,String username,String password,String email) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String registerUrl=UrlHead+"/user/register/username/"+username+"/password/"+password+"/email/"+email;
                //https://6ed30734.ngrok.io/user/register/username/macoredroid/password/c7o2r1e4/email/coredroid0401@gmail.com
                try {
                    //URL url=new URL("https://5184c2d6.ngrok.io/user/login/username/macoredroid/password/c7o2r1e4");
                    URL url=new URL(registerUrl);
                    conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    InputStream in=conn.getInputStream();
                    br=new BufferedReader(new InputStreamReader(in));

                    StringBuilder sb=new StringBuilder();
                    String s;
                    while((s = br.readLine())!=null){
                        sb.append(s);
                    }
                    if(sb.toString().equals("true")){
                        Looper.prepare();
                        Toast.makeText(context,"注册成功",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(context,LoginActivity.class);
                        context.startActivity(intent);
                        Looper.loop();

                    }
                    else{
                        Looper.prepare();
                        Toast.makeText(context,"注册失败",Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                    //setContent(sb.toString());
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                    if (conn!=null){
                        conn.disconnect();
                    }
                    if (br!=null){
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

    }
    public static void postRunningRecord1(RunningMessage runningMessage){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = "";
                BufferedReader reader = null;
                long Lasttime=runningMessage.getLastTime();
                Date date=runningMessage.getStart();
                //SimpleDateFormat formater=new SimpleDateFormat("yyyy.MM.dd HH:mm");
                //String dateString=formater.format(date);
                double length=runningMessage.getLength();
                int exp=runningMessage.getExp();
                List<LatLng> course=runningMessage.getAllLatLng();
                JSONArray array=new JSONArray();
                for(int i=0;i<course.size();++i){
                    JSONObject jb=new JSONObject();
                    try{
                        jb.put("lat",course.get(i).latitude);
                        jb.put("lng",course.get(i).longitude);
                        array.put(jb);
                    }
                    catch (Exception e){

                    }
                }
                try {
                    JSONObject un=new JSONObject();
                    un.put("username",UserData.getUserName());
                    un.put("startTime",String.valueOf(date.getTime()));
                    un.put("course",array.toString());
                    un.put("duration",String.valueOf(Lasttime));
                    un.put("courseLength",String.valueOf(length));
                    un.put("exp",String.valueOf(exp));
                    String Json=un.toString();
                    String urlPath = UrlHead+"/record/running/record";
                    URL url = new URL(urlPath);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setUseCaches(false);
                    conn.setRequestProperty("Connection", "Keep-Alive");
                    conn.setRequestProperty("Charset", "UTF-8");
                    // 设置文件类型:
                    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    // 设置接收类型否则返回415错误
                    //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
                    conn.setRequestProperty("accept", "application/json");
                    // 往服务器里面发送数据
                    if (Json != null && !TextUtils.isEmpty(Json)) {
                        byte[] writebytes = Json.getBytes();
                        // 设置文件长度
                        conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                        OutputStream outwritestream = conn.getOutputStream();
                        outwritestream.write(Json.getBytes());
                        outwritestream.flush();
                        outwritestream.close();
                        Log.d("hlhupload", "doJsonPost: conn" + conn.getResponseCode());
                    }
                    if (conn.getResponseCode() == 200) {
                        Log.d("success","connected!!!!!");
                        reader = new BufferedReader(
                                new InputStreamReader(conn.getInputStream()));
                        result = reader.readLine();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
    public static void getNearBy(LatLng latLng, AMap aMap){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = "";
                BufferedReader reader = null;
                try {
                    JSONObject un=new JSONObject();
                    un.put("username",UserData.getUserName());
                    un.put("longitude",String.valueOf(latLng.longitude));
                    un.put("latitude",String.valueOf(latLng.latitude));
                    String Json=un.toString();
                    //String urlPath = UrlHead+"/record/refresh/location";
                    String urlPath = UrlHead+"/location/get/nearby";
                    URL url = new URL(urlPath);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setUseCaches(false);
                    conn.setRequestProperty("Connection", "Keep-Alive");
                    conn.setRequestProperty("Charset", "UTF-8");
                    // 设置文件类型:
                    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    // 设置接收类型否则返回415错误
                    //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
                    conn.setRequestProperty("accept", "application/json");
                    // 往服务器里面发送数据
                    if (Json != null && !TextUtils.isEmpty(Json)) {
                        byte[] writebytes = Json.getBytes();
                        // 设置文件长度
                        conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                        OutputStream outwritestream = conn.getOutputStream();
                        outwritestream.write(Json.getBytes());
                        outwritestream.flush();
                        outwritestream.close();
                        Log.d("hlhupload", "doJsonPost: conn" + conn.getResponseCode());
                    }
                    if (conn.getResponseCode() == 200) {
                        Log.d("success","connected!!!!!");
                        reader = new BufferedReader(
                                new InputStreamReader(conn.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String s;
                        while ((s = reader.readLine()) != null) {
                            sb.append(s);
                        }
                        Log.d("mess",sb.toString());
                        JSONArray json = new JSONArray(sb.toString());
                        for (int i = 0; i < json.length(); i++) {
                            JSONObject jb = json.getJSONObject(i);
                            //Log.d("AAA", jb.getString("username"));
                            String username = jb.getString("username");
                            String longitude = jb.getString("longitude");
                            String latitude = jb.getString("latitude");

                            double lng = Double.parseDouble(longitude);
                            double lat=Double.parseDouble(latitude);
                            LatLng latlng=new LatLng(lat,lng);
                            Marker marker=aMap.addMarker(new MarkerOptions().position(latlng).title(username).snippet("DefaultMarker"));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public static void getMileage(String username) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String Url=UrlHead+"/rule/rule/campus/user/"+username;
                try {
                    URL url=new URL(Url);
                    conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    InputStream in=conn.getInputStream();
                    br=new BufferedReader(new InputStreamReader(in));

                    StringBuilder sb=new StringBuilder();
                    String s;
                    while((s = br.readLine())!=null){
                        sb.append(s);
                    }
                    JSONObject jb = new JSONObject(sb.toString());
                    UserData.setMileage(Double.parseDouble(jb.getString("mileage")));
                    UserData.setMileageGoal(Double.parseDouble(jb.getString("mileageGoal")));
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                    if (conn!=null){
                        conn.disconnect();
                    }
                    if (br!=null){
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();

                        }
                    }
                }
            }
        }).start();
    }

    public static void getflag(AMap aMap,LatLng latLng,Context context) {
        UserData.flagNum=0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String Url=UrlHead+"/rule/rule/route/start_lng/"+latLng.longitude+"/start_lat/"+latLng.latitude;
                try {
                    URL url=new URL(Url);
                    conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    InputStream in=conn.getInputStream();
                    br=new BufferedReader(new InputStreamReader(in));

                    StringBuilder sb=new StringBuilder();
                    String s;
                    while((s = br.readLine())!=null){
                        sb.append(s);
                    }
                    JSONArray array = new JSONArray(sb.toString());
                    UserData.constraint.clear();
                    for (int j = 0; j < array.length(); ++j) {
                        JSONObject jjj = array.getJSONObject(j);
                        LatLng point = new LatLng(jjj.getDouble("lat"), jjj.getDouble("lng"));
                        UserData.constraint.add(point);
                        View view = View.inflate(context, R.layout.view_marker_constraint, null);
                        Bitmap bitmap = ElfPointController.convertViewToBitmap(view);
                        MarkerOptions markerOptions = new MarkerOptions()
                                .title("flag")
                                .snippet("必经点位")
                                .position(point)
                                .draggable(false)
                                .setFlat(true)
                                .icon(BitmapDescriptorFactory.fromBitmap(bitmap));
                        Marker marker = aMap.addMarker(markerOptions);
                        UserData.flagNum++;
                        Log.d("CCC", jjj.getString("lat") + "," + jjj.getString("lng"));
                    }
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                    if (conn!=null){
                        conn.disconnect();
                    }
                    if (br!=null){
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

    }

    public static void getMoments(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Map> list = new ArrayList<Map>();
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String loginUrl=UrlHead+"/forum/get/all/moment";
                try {
                    //URL url=new URL("https://5184c2d6.ngrok.io/user/login/username/macoredroid/password/c7o2r1e4");
                    URL url=new URL(loginUrl);
                    conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(888000);
                    conn.setReadTimeout(888000);
                   /* InputStream is = conn.getInputStream(); // 获取输入流
                    byte[] data = read(is);*/

                    InputStream in=conn.getInputStream();
                    br=new BufferedReader(new InputStreamReader(in));

                    StringBuilder sb=new StringBuilder();
                    String s;
                    while((s = br.readLine())!=null){
                        sb.append(s);
                    }
                    JSONArray jsonArray = new JSONArray(sb.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i); // 得到每个对象
                        String content = item.getString("text"); // 获取对象对应的值
                        String time = item.getString("timestamp");
                        String username = item.getString("username");
                        List<String> pics =new ArrayList<>();
                        for(int j=1;j<=9&&item.getString("pic"+j)!="null";j++)
                            pics.add(item.getString("pic"+j));
                        Log.d("44444444444fd",pics.toString());
                        Map map = null;
                        map = new HashMap(); // 存放到MAP里面
                        map.put("content", content );
                        map.put("time",time);
                        map.put("username",username);
                        map.put("pics", pics );
                        list.add(map);
                    }
                    UserData.setMoments(list);
                    UserData.isMomentsGet=false;
                } catch (Exception e) {
                    e.printStackTrace();
                    UserData.isMomentsGet=false;
                }finally {
                    UserData.isMomentsGet=false;
                    if (conn!=null){
                        conn.disconnect();
                    }
                    if (br!=null){
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public static void postPic(List<String> pic,String timestamp,String username,String text){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = "";
                BufferedReader reader = null;
                try {
                    JSONObject un=new JSONObject();

                    un.put("text",text);
                    un.put("timestamp", timestamp);
                    un.put("username",username);
                    for(int i=1;i<=pic.size();i++)
                    un.put("pic"+i,pic.get(i-1));
                    String Json=un.toString();
                    Log.d("5555",Json);
                    //String urlPath = UrlHead+"/record/refresh/location";
                    String urlPath = UrlHead+"/forum/add/moment";
                    URL url = new URL(urlPath);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setUseCaches(false);
                    conn.setRequestProperty("Connection", "Keep-Alive");
                    conn.setRequestProperty("Charset", "UTF-8");
                    // 设置文件类型:
                    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    // 设置接收类型否则返回415错误
                    //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
                    conn.setRequestProperty("accept", "application/json");
                    // 往服务器里面发送数据
                    if (Json != null && !TextUtils.isEmpty(Json)) {
                        byte[] writebytes = Json.getBytes();
                        // 设置文件长度
                        conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                        OutputStream outwritestream = conn.getOutputStream();
                        outwritestream.write(Json.getBytes());
                        outwritestream.flush();
                        outwritestream.close();
                        Log.d("hlhupload", "doJsonPost: conn" + conn.getResponseCode());
                    }
                    if (conn.getResponseCode() == 200) {
                        Log.d("success","connected!!!!!");
                        reader = new BufferedReader(
                                new InputStreamReader(conn.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String s;
                        while ((s = reader.readLine()) != null) {
                            sb.append(s);
                        }
                        Log.d("mess",sb.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public static void postPosition(LatLng latLng){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = "";
                BufferedReader reader = null;
                try {
                    JSONObject un=new JSONObject();
                    un.put("username",UserData.getUserName());
                    un.put("longitude",String.valueOf(latLng.longitude));
                    un.put("latitude",String.valueOf(latLng.latitude));
                    String Json=un.toString();
                    //String urlPath = UrlHead+"/record/refresh/location";
                    String urlPath = UrlHead+"/location/refresh/location";
                    URL url = new URL(urlPath);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setUseCaches(false);
                    conn.setRequestProperty("Connection", "Keep-Alive");
                    conn.setRequestProperty("Charset", "UTF-8");
                    // 设置文件类型:
                    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    // 设置接收类型否则返回415错误
                    //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
                    conn.setRequestProperty("accept", "application/json");
                    // 往服务器里面发送数据
                    if (Json != null && !TextUtils.isEmpty(Json)) {
                        byte[] writebytes = Json.getBytes();
                        // 设置文件长度
                        conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                        OutputStream outwritestream = conn.getOutputStream();
                        outwritestream.write(Json.getBytes());
                        outwritestream.flush();
                        outwritestream.close();
                        Log.d("hlhupload", "doJsonPost: conn" + conn.getResponseCode());
                    }
                    if (conn.getResponseCode() == 200) {
                        Log.d("success","connected!!!!!");
                        reader = new BufferedReader(
                                new InputStreamReader(conn.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String s;
                        while ((s = reader.readLine()) != null) {
                            sb.append(s);
                        }
                        Log.d("mess",sb.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
    public static void getRun() {
        UserData.isrecordGet=false;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String recordUrl=UrlHead+"/record/running/record/user/"+UserData.getUserName()+"/page/0";
                //https://6ed30734.ngrok.io/user/register/username/macoredroid/password/c7o2r1e4/email/coredroid0401@gmail.com
                try {
                    //URL url=new URL("https://5184c2d6.ngrok.io/user/login/username/macoredroid/password/c7o2r1e4");
                    URL url=new URL(recordUrl);
                    conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    InputStream in=conn.getInputStream();
                    br=new BufferedReader(new InputStreamReader(in));

                    if(conn.getResponseCode()==200) {
                        StringBuilder sb = new StringBuilder();
                        String s;
                        while ((s = br.readLine()) != null) {
                            sb.append(s);
                        }
                        Log.d("mse", sb.toString());
                        try {
                            if (UserData.rocordLength.size()>0) {
                                UserData.recordLastTime.clear();
                                for (int i = 0; i < UserData.recordLatLngList.size(); ++i) {
                                    UserData.recordLatLngList.get(i).clear();
                                }
                                UserData.recordLatLngList.clear();
                                UserData.startTime.clear();
                                UserData.rocordLength.clear();
                                UserData.isrecordGet = false;
                            }
                            JSONArray json = new JSONArray(sb.toString());
                            Log.d("length", "" + json.length());
                            for (int i = 0; i < json.length(); i++) {
                                JSONObject jb = json.getJSONObject(i);
                                //Log.d("AAA", jb.getString("username"));
                                String startTime = jb.getString("startTime");
                                String duration = jb.getString("duration");
                                String courseLength = jb.getString("courseLength");

                                DecimalFormat clformat = new DecimalFormat("#0.000");
                                double cLength = Double.parseDouble(courseLength) / 1000;
                                String cls = clformat.format(cLength) + "公里";
                                UserData.rocordLength.add(cls);
                                Log.d("length", cls);

                                long du = Long.parseLong(duration);
                                Date dudate = new Date(du * 1000 - 8 * 3600 * 1000);
                                SimpleDateFormat duformat = new SimpleDateFormat("HH:mm:ss");
                                String dus = duformat.format(dudate);
                                UserData.recordLastTime.add(dus);
                                Log.d("duration", dus);

                                long st = Long.parseLong(startTime);
                                Date stdate = new Date(st);
                                SimpleDateFormat stformater = new SimpleDateFormat("yyyy.MM.dd HH:mm");
                                String dateString = stformater.format(stdate);
                                UserData.startTime.add(dateString);
                                Log.d("startTime", dateString);

                                JSONArray array = new JSONArray(jb.getString("course"));
                                List<LatLng> runRecord = new ArrayList<LatLng>();
                                for (int j = 0; j < array.length(); ++j) {
                                    JSONObject jjj = array.getJSONObject(j);
                                    LatLng point = new LatLng(jjj.getDouble("lat"), jjj.getDouble("lng"));
                                    runRecord.add(point);
                                    Log.d("CCC", jjj.getString("lat") + "," + jjj.getString("lng"));
                                }
                                UserData.recordLatLngList.add(runRecord);
                                //UserData.rocordLength.add(Double.parseDouble(courseLength));
                                //Log.d("courseLength",""+Double.parseDouble(courseLength));
                                //UserData.recordLastTime.add(Long.parseLong(duration));
                                //UserData.startTime.add(startTime);

                                //Log.d("AAA",String.valueOf(json.length()));
                            }
                            UserData.isrecordGet = true;
                            //for(int i=0;i<UserData.startTime.size();++i){
                            //Log.d("m",UserData.startTime.get(i));
                            //}
                        } catch (Exception e) {
                            UserData.isrecordGet = true;
                        }
                    }
                    else{
                        UserData.isrecordGet = true;
                    }
                    //setContent(sb.toString());
                    //iLog.d("123","---"+sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    UserData.isrecordGet = true;
                    Log.d("haha",e.getMessage());
                }finally {
                    if (conn!=null){
                        conn.disconnect();
                    }
                    if (br!=null){
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();

                        }
                    }
                    if(!UserData.isrecordGet){
                        UserData.isrecordGet = true;
                    }
                }
            }
        }).start();

    }

    public static void addFriend(Context context,String username,String friendname) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String Url=UrlHead+"/user/addfriend/username/"+username+"/friendname/"+friendname;
                //https://6ed30734.ngrok.io/user/register/username/macoredroid/password/c7o2r1e4/email/coredroid0401@gmail.com
                try {
                    //URL url=new URL("https://5184c2d6.ngrok.io/user/login/username/macoredroid/password/c7o2r1e4");
                    URL url=new URL(Url);
                    conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    InputStream in=conn.getInputStream();
                    br=new BufferedReader(new InputStreamReader(in));

                    StringBuilder sb=new StringBuilder();
                    String s;
                    while((s = br.readLine())!=null){
                        sb.append(s);
                    }
                    Button addfriend=(Button)findActivity(context).findViewById(R.id.act_friend_add);
                    if(sb.toString().equals("true")){
                        Looper.prepare();
                        Toast.makeText(context,"添加好友成功",Toast.LENGTH_SHORT).show();
                        addfriend.setVisibility(View.INVISIBLE);
                        Looper.loop();

                    }
                    else{
                        Looper.prepare();
                        Toast.makeText(context,"添加失败，你们已经是好友",Toast.LENGTH_SHORT).show();
                        addfriend.setVisibility(View.INVISIBLE);
                        Looper.loop();
                    }
                    //setContent(sb.toString());
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                    if (conn!=null){
                        conn.disconnect();
                    }
                    if (br!=null){
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public static void getUserInfo() {
        UserData.isUserinfoGet=false;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String Url=UrlHead+"/user/getinfo/username/"+UserData.getUserName();
                //https://6ed30734.ngrok.io/user/register/username/macoredroid/password/c7o2r1e4/email/coredroid0401@gmail.com
                try {
                    //URL url=new URL("https://5184c2d6.ngrok.io/user/login/username/macoredroid/password/c7o2r1e4");
                    URL url=new URL(Url);
                    conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    InputStream in=conn.getInputStream();
                    br=new BufferedReader(new InputStreamReader(in));

                    if(conn.getResponseCode()==200) {
                        StringBuilder sb = new StringBuilder();
                        String s;
                        while ((s = br.readLine()) != null) {
                            sb.append(s);
                        }
                        Log.d("mse", sb.toString());
                        try {
                            if(UserData.friend.size()>0){
                                UserData.friend.clear();
                                UserData.isUserinfoGet=false;
                            }
                            JSONObject jb=new JSONObject(sb.toString());
                            UserData.distance=Double.parseDouble(jb.getString("distance"));
                            Log.d("distance:",""+UserData.distance);
                            JSONArray array = new JSONArray(jb.getString("friends"));
                            for (int j = 0; j < array.length(); ++j) {
                                JSONObject jjj = array.getJSONObject(j);
                                Log.d("check",jjj.getString("friendname"));
                                UserData.friend.add(jjj.getString("friendname"));
                                //LatLng point = new LatLng(jjj.getDouble("lat"), jjj.getDouble("lng"));
                                //runRecord.add(point);
                                //Log.d("CCC", jjj.getString("lat") + "," + jjj.getString("lng"));
                            }
                            UserData.isUserinfoGet=true;

                        } catch (Exception e) {
                            UserData.isUserinfoGet=true;
                        }
                    }
                    else{
                        UserData.isUserinfoGet = true;
                    }
                    //setContent(sb.toString());
                    //iLog.d("123","---"+sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    UserData.isUserinfoGet=true;
                    Log.d("haha",e.getMessage());
                }finally {
                    if (conn!=null){
                        conn.disconnect();
                    }
                    if (br!=null){
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();

                        }
                    }
                    if(!UserData.isUserinfoGet){
                        UserData.isUserinfoGet=true;
                    }
                }
            }
        }).start();

    }

    public static void finishRestrainRun(double length){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = "";
                BufferedReader reader = null;
                try {
                    JSONObject un=new JSONObject();
                    String urlPath = UrlHead+"/rule/rule/campus/user/"+"1"+"/length/"+length;
                    URL url = new URL(urlPath);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setUseCaches(false);
                    conn.setRequestProperty("Connection", "Keep-Alive");
                    conn.setRequestProperty("Charset", "UTF-8");
                    // 设置文件类型:
                    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    // 设置接收类型否则返回415错误
                    //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
                    conn.setRequestProperty("accept", "application/json");
                    if (conn.getResponseCode() == 200) {
                        Log.d("success","connected!!!!!");
                        reader = new BufferedReader(
                                new InputStreamReader(conn.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String s;
                        while ((s = reader.readLine()) != null) {
                            sb.append(s);
                        }
                        Log.d("hahahaha",sb.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
