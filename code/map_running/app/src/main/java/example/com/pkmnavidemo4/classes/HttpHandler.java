package example.com.pkmnavidemo4.classes;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.model.LatLng;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import example.com.pkmnavidemo4.LoginActivity;
import example.com.pkmnavidemo4.MainActivity;
import example.com.pkmnavidemo4.RegisterActivity;

public class HttpHandler {
    private static String UrlHead="https://6ed30734.ngrok.io";
    public static void successCatch(Context context,String username,String typeid){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String loginUrl=UrlHead+"/user/"+username+"/addnum/"+typeid;
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
                double length=runningMessage.getLength();
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
                    String Json=un.toString();
                    String urlPath = "https://6d0c9e3c.ngrok.io/running/record";
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
                        Log.d("success","shit!shit!shit!");
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
    public static void getRun() {
        RunningMessage record;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String registerUrl=UrlHead+"/running/record/user/test1";
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
                    try{
                        JSONArray json=new JSONArray(sb.toString());
                        for(int i=0;i<json.length();i++)
                        {
                            JSONObject jb=json.getJSONObject(i);
                            //Log.d("AAA", jb.getString("username"));
                            JSONArray array=new JSONArray(jb.getString("course"));
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
}
