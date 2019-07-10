package example.com.pkmnavidemo4;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.location.Location;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import example.com.pkmnavidemo4.classes.ElfPoint;
import example.com.pkmnavidemo4.classes.RunningMessage;

public class MapActivity extends AppCompatActivity implements LocationSource, AMapLocationListener {

    MapView mMapView = null;
    private TextView positionText;
    private TextView distText;
    private TextView timePerKM;
    private TextView timeText;
    private StringBuilder currentPosition;
    private RunningMessage runningMessage;
    private ElfPoint elfPoint;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //绑定文本控件
        positionText=(TextView)findViewById(R.id.act_map_textView);
        distText=(TextView)findViewById(R.id.act_map_textView_dist);
        timePerKM=(TextView)findViewById(R.id.act_map_textView_speed);
        timeText=(TextView)findViewById(R.id.act_map_textView_time);

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
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。


        myLocationStyle.showMyLocation(true);

        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                //从location对象中获取经纬度信息，地址描述信息，建议拿到位置之后调用逆地理编码接口获取
            }
        });
        /*
        AMap.OnMarkerClickListener markerClickListener=new AMap.OnMarkerClickListener(){
            @Override
            public boolean onMarkerClick(Marker marker){
                return false;
            }
        };*/
        //aMap.setOnMapClickListener((AMap.OnMapClickListener) markerClickListener);
        //初始化跑步信息管理器
        Date date=new Date(System.currentTimeMillis());
        //System.out.println(date);
        runningMessage=new RunningMessage(date);
        /*elfPoint=new ElfPoint();
        elfPoint.setMax_id(5);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                elfPoint.generateElfPoing(2,getApplicationContext(),aMap);
            }
        });*/

        drawPoint();
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
    }

    private void drawPoint(){
        LatLng latlng =new LatLng(31.0239310214,121.4350658655);
        List<LatLng> allPoint;
        allPoint=new ArrayList<LatLng>();
        allPoint.add(new LatLng(31.022264,121.442783));
        allPoint.add(new LatLng(31.02276,121.444548));
        allPoint.add(new LatLng(31.024365,121.444634));
        allPoint.add(new LatLng(31.026056,121.439752));
        allPoint.add(new LatLng(31.025275,121.437338));
        allPoint.add(new LatLng(31.023882,121.435122));
        allPoint.add(new LatLng(31.024585,121.43634));
        allPoint.add(new LatLng(31.022638,121.435305));
        allPoint.add(new LatLng(31.024136,121.436539));
        allPoint.add(new LatLng(31.023401,121.436834));
        allPoint.add(new LatLng(31.022224,121.437209));
        allPoint.add(new LatLng(31.022932,121.440229));
        allPoint.add(new LatLng(31.021622,121.440809));
        allPoint.add(new LatLng(31.023419,121.446629));
        allPoint.add(new LatLng(31.022431,121.434393));
        allPoint.add(new LatLng(31.024996,121.440245));
        allPoint.add(new LatLng(31.02427,121.4412));
        allPoint.add(new LatLng(31.026724,121.443689));
        allPoint.add(new LatLng(31.023828,121.439881));
        allPoint.add(new LatLng(31.026531,121.438647));

        for(int i=0;i<20;++i){
            addMarkersToMap(getApplicationContext(),aMap,allPoint.get(i),i%5+1);
        }
        //final Marker marker=aMap.addMarker(new MarkerOptions().position(latlng).title("王凯源法学院").snippet("DefaultMarker"));
        /*MarkerOptions markerOptions=new MarkerOptions();
        markerOptions.position(latlng);
        markerOptions.title("精灵").snippet("id");
        markerOptions.anchor(0.5F,0.5F);
        markerOptions.draggable(false);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.npc_86)));
        markerOptions.setFlat(true);
        final Marker marker=aMap.addMarker(markerOptions);*/
        //elfPoint.addMarkersToMap(getApplicationContext(),aMap,latlng);
    }
    public static void addMarkersToMap(Context context, AMap aMap, LatLng latlng,int id) {
        if (aMap != null) {
            View view = View.inflate(context, R.layout.view_marker, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.ivQuality);
            imageView.setImageResource(convertID(id));
            //int aqi=Integer.parseInt(model.getAqi());
            //String s= Environment.getExternalStorageDirectory().getAbsolutePath()
            Bitmap bitmap = convertViewToBitmap(view);
            MarkerOptions markerOptions = new MarkerOptions()
                    .snippet(""+id)
                    .position(latlng)
                    .draggable(true)
                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap));
            Marker marker = aMap.addMarker(markerOptions);
        }
    }
    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }
    public static int convertID(int id){
        switch (id){
            case 1:
                return R.drawable.elf_1;
            case 2:
                return R.drawable.elf_2;
            case 3:
                return R.drawable.elf_3;
            case 4:
                return R.drawable.elf_4;
            case 5:
                return R.drawable.elf_5;
            default:
                return 0;
        }
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
            //显示跑步总时长，格式XX:XX:XX
            /*int hou=0;
            sec=(int)runningMessage.getLastTime()%60;
            min=(int)runningMessage.getLastTime()/60%60;
            hou=(int)runningMessage.getLastTime()/60/60;
            String timeString;
            if(hou==0){
                timeString="00";
            }
            else if(hou>0&&hou<10){
                timeString="0"+hou;
            }
            else {
                timeString=""+hou;
            }
            if(min==0){
                timeString+=":00";
            }
            else if(min>0&&min<10){
                timeString+=(":0"+min);
            }
            else{
                timeString+=(":"+min);
            }
            if(sec==0){
                timeString+=":00";
            }
            else if(sec>0&&sec<10){
                timeString+=(":0"+sec);
            }
            else{
                timeString+=(":"+sec);
            }
            timeText.setText(timeString);*/
        }
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
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
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
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                //Date date=new Date(System.currentTimeMillis());
                //SimpleDateFormat formatter=new SimpleDateFormat("HH:mm:ss");
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
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }
}