package example.com.pkmmapdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        mapView=(MapView)findViewById(R.id.bmapview);
        baiduMap=mapView.getMap();
        registerSDKCheckReceiver();
        MapStatusUpdate mapStatusUpdate= MapStatusUpdateFactory.newLatLng(target);
        baiduMap.setMapStatus(mapStatusUpdate);
        mapStatusUpdate=MapStatusUpdateFactory.zoomTo(20);
        baiduMap.setMapStatus(mapStatusUpdate);
    }

    protected MapView mapView=null;
    public BaiduMap baiduMap;
    protected LatLng target=new LatLng(31.03201,121.443287);

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mapView.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onResume(){
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mapView.onPause();
    }

    private BroadcastReceiver receiver;

    private void registerSDKCheckReceiver(){
        receiver=new BroadcastReceiver(){
            @Override
            public void onReceive(Context context,Intent intent){
                String action=intent.getAction();
                if(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR.equals(action)){
                    Toast.makeText(getApplicationContext(),"网络错误",Toast.LENGTH_SHORT).show();
                }else if(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR.equals(action)){
                    Toast.makeText(getApplicationContext(),"KEY验证失败",Toast.LENGTH_SHORT).show();
                }
            }
        };
        IntentFilter filter=new IntentFilter();
        filter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        filter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        registerReceiver(receiver,filter);
    }
}
