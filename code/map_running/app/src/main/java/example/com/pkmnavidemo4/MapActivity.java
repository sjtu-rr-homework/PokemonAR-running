package example.com.pkmnavidemo4;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.location.Location;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import example.com.pkmnavidemo4.classes.ElfPoint;
import example.com.pkmnavidemo4.classes.ElfPointController;
import example.com.pkmnavidemo4.classes.HttpHandler;
import example.com.pkmnavidemo4.classes.RunningMessage;
import example.com.pkmnavidemo4.classes.UserData;

public class MapActivity extends AppCompatActivity implements LocationSource, AMapLocationListener {

    MapView mMapView = null;
    private int countDown=0;
    private TextView positionText;
    private TextView distText;
    private TextView timePerKM;
    private TextView timeText;
    private TextView exp;
    private Button endButton;
    private StringBuilder currentPosition;
    private RunningMessage runningMessage;
    private ElfPointController elfPointController;
    private List<ElfPoint> presentElfPoint=new ArrayList<ElfPoint>();
    //private List<LatLng> latLngs=new ArrayList<LatLng>();
    //private double run_dist=0;

    //初始化地图控制器对象
    AMap aMap;


    //定位需要的数据
    LocationSource.OnLocationChangedListener mListener;
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;

    //定位蓝点
    MyLocationStyle myLocationStyle;

    //是否第一次定位
    boolean isFirstLocate=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Log.d("sha1",sHA1(MapActivity.this));
        //绑定文本控件
        positionText=(TextView)findViewById(R.id.act_map_textView);
        distText=(TextView)findViewById(R.id.act_map_textView_dist);
        timePerKM=(TextView)findViewById(R.id.act_map_textView_speed);
        timeText=(TextView)findViewById(R.id.act_map_textView_time);
        exp=(TextView)findViewById(R.id.act_map_textView_exp);

        endButton=(Button)findViewById(R.id.act_map_endButton);

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        //设置初始点位
        LatLng latLng = new LatLng(31.02228,121.442316);//构造一个位置
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));

        //设置地图的放缩级别
        aMap.moveCamera(CameraUpdateFactory.zoomTo(19));
        // 设置定位监听
        aMap.setLocationSource(this);
        // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationEnabled(true);
        // 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);


        //蓝点初始化
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。


        myLocationStyle.showMyLocation(true);

        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                //从location对象中获取经纬度信息，地址描述信息，建议拿到位置之后调用逆地理编码接口获取
            }
        });

        Date date=new Date(System.currentTimeMillis());
        //System.out.println(date);
        runningMessage=new RunningMessage(date);
        elfPointController=new ElfPointController();
        elfPointController.setMax_id(5);

        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent=new Intent(MapActivity.this,SceneformActivity.class);
                int id=Integer.parseInt(marker.getSnippet());
                intent.putExtra("variety",id);
                MapActivity.this.startActivity(intent);
                return true;
            }
        });

        endButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MapActivity.this.finish();
            }
        });
    }

    private void drawLine(LatLng latLng){
        if(runningMessage.getLength()==0){
            Date date=new Date(System.currentTimeMillis());
            //System.out.println(date);
            runningMessage.newPoing(latLng,date);
            return;
        }
        else{
            Date date=new Date(System.currentTimeMillis());
            System.out.println(date);
            runningMessage.newPoing(latLng,date);
            //显示跑步里程，单位km
            double showDist;
            showDist=((double)((int)(runningMessage.getLength())/10))/100;
            distText.setText(""+showDist);
            Polyline polyline=aMap.addPolyline(new PolylineOptions().addAll(runningMessage.getPresentLatLng()).width(10).color(Color.argb(255, 1, 1, 1)));//连线
            //显示跑步配速，格式XX分XX秒
            int min=0;
            int sec=0;
            min=(int)runningMessage.getTimePerKM()/60;
            sec=(int)runningMessage.getTimePerKM()%60;
            timePerKM.setText(""+min+"分"+sec+"秒");
            //显示跑步总时长
            Date tmpdate=new Date(runningMessage.getLastTime()*1000-8*3600*1000);
            SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss");
            timeText.setText(formater.format(tmpdate));
            exp.setText(String.valueOf(runningMessage.getExp()));
        }
    }

    public void showCatchMessage(int id,Marker marker){
        AlertDialog alertDialog = new AlertDialog.Builder(MapActivity.this)
                .setTitle("你已到达一个精灵点位")
                .setMessage("是否开始捕捉")
                .setPositiveButton("确定，开始捕捉", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MapActivity.this, "开始捕捉", Toast.LENGTH_SHORT).show();
                        marker.remove();
                        Intent intent=new Intent(MapActivity.this,SceneformActivity.class);
                        intent.putExtra("variety",id);
                        MapActivity.this.startActivity(intent);
                    }
                })
                .setNegativeButton("放弃，继续跑步", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MapActivity.this, "继续跑步", Toast.LENGTH_SHORT).show();
                        countDown=3;
                        return;
                    }
                }).create();
        alertDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }


    //--定位监听---------

    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(this);
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mlocationClient.setLocationListener(this);

            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();//启动定位
        }

    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }


    //定位回调  在回调方法中调用“mListener.onLocationChanged(amapLocation);”可以在地图上显示系统小蓝点。
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                if(isFirstLocate){

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LatLng start=new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());
                            elfPointController.generateElfPoing(getApplicationContext(),aMap,start);
                            presentElfPoint=elfPointController.getPresentElfPoint();
                            //elfPoint.showAllPoints(aMap);
                        }
                    });
                    isFirstLocate=false;
                }
                else{
                    LatLng present=new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());
                    List<Marker> mapScreenMarkers=aMap.getMapScreenMarkers();
                    if(countDown==0) {
                        for (int i = 0; i < mapScreenMarkers.size(); ++i) {
                            Marker marker = mapScreenMarkers.get(i);
                            LatLng point = marker.getPosition();
                            float distance = AMapUtils.calculateLineDistance(point, present);
                            if (distance < 10) {
                                showCatchMessage(Integer.parseInt(marker.getSnippet()), marker);
                                break;
                            }
                        }
                    }
                    else if(countDown>=1){
                        --countDown;
                    }
                    /*
                    for(int i=0;i<presentElfPoint.size();++i){
                        float distance=AMapUtils.calculateLineDistance(present,presentElfPoint.get(i).getLatLng());
                        if(distance<10){
                            showCatchMessage(presentElfPoint.get(i).getElfId());
                            break;
                        }
                    }*/
                }
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                currentPosition=new StringBuilder();
                currentPosition.append("经度：").append(aMapLocation.getLongitude()).append("\n");
                currentPosition.append("纬度：").append(aMapLocation.getLatitude()).append("\n");
                //currentPosition.append("时间：").append(date.getTime()).append("\n");
                positionText.setText(currentPosition);
                LatLng latLng=new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());
                drawLine(latLng);

            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("定位AmapErr", errText);
            }
        }
    }


    @Override
    public void finish() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MapActivity.this);
        dialog.setTitle("温馨提示");
        dialog.setMessage("是否结束跑步？");
        dialog.setCancelable(false);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HttpHandler.postRunningRecord1(runningMessage);
                HttpHandler.changeExp(UserData.getUserName(),runningMessage.getExp());
                MapActivity.super.finish();
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        dialog.show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }

    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length()-1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}