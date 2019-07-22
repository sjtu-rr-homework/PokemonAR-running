package example.com.pkmnavidemo4.classes;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
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
    public static void successCatch(Context context,String username,String typeid,String num){
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
                            if (UserData.isrecordGet) {
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
                }
            }
        }).start();

    }
    public static void test() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String recordUrl=UrlHead+"/running/record/user/wzr/page/0";
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

                    StringBuilder sb=new StringBuilder();
                    String s;
                    while((s = br.readLine())!=null){
                        sb.append(s);
                    }
                    //Log.d("sb",sb.toString());
                    try{
                        JSONArray json=new JSONArray(sb.toString());
                        for(int i=0;i<json.length();i++)
                        {
                            JSONObject jb=json.getJSONObject(i);
                            //Log.d("AAA", jb.getString("username"));
                            String startTime=jb.getString("startTime");
                            String duration=jb.getString("duration");
                            String courseLength=jb.getString("courseLength");
                            JSONArray array=new JSONArray(jb.getString("course"));
                            //UserData.rocordLength.add(Double.valueOf(courseLength));
                            //UserData.recordLastTime.add(Long.valueOf(duration));
                            UserData.startTime.add(startTime);
                            for(int j=0;j<array.length();++j){
                                JSONObject jjj=array.getJSONObject(i);

                                Log.d("CCC",jjj.getString("lat")+","+jjj.getString("lng"));
                            }
                            //Log.d("AAA",String.valueOf(json.length()));
                        }
                    }
                    catch (Exception e){

                    }
                    //setContent(sb.toString());
                    //iLog.d("123","---"+sb.toString());
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

    public static void getFriend() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String Url=UrlHead+"/user/getinfo/username/"+UserData.getUserName();
                UserData.isFriendGet=false;
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
                            if(UserData.isFriendGet||UserData.friend.size()>0){
                                UserData.friend.clear();
                                UserData.isFriendGet=false;
                            }
                            JSONObject jb=new JSONObject(sb.toString());
                            JSONArray array = new JSONArray(jb.getString("friends"));
                            for (int j = 0; j < array.length(); ++j) {
                                JSONObject jjj = array.getJSONObject(j);
                                Log.d("check",jjj.getString("friendname"));
                                UserData.friend.add(jjj.getString("friendname"));
                                //LatLng point = new LatLng(jjj.getDouble("lat"), jjj.getDouble("lng"));
                                //runRecord.add(point);
                                //Log.d("CCC", jjj.getString("lat") + "," + jjj.getString("lng"));
                            }
                            UserData.isFriendGet=true;

                        } catch (Exception e) {
                        }
                    }
                    else{
                        UserData.isFriendGet = true;
                    }
                    //setContent(sb.toString());
                    //iLog.d("123","---"+sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    UserData.isFriendGet=true;
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
}
