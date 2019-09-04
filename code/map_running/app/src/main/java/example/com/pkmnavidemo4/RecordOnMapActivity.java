package example.com.pkmnavidemo4;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;

import example.com.pkmnavidemo4.classes.UserData;

public class RecordOnMapActivity extends AppCompatActivity {

    MapView mapView=null;
    AMap aMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordonmap);
        mapView=(MapView)findViewById(R.id.act_recordonmap_map);
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        LatLng latLng = new LatLng(31.02228,121.442316);//构造一个位置
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));
        Intent intent=getIntent();
        int id=(int)Long.parseLong(intent.getStringExtra("id"));
        Log.d("id",""+id);
        Polyline polyline =aMap.addPolyline(new PolylineOptions().
                addAll(UserData.recordLatLngList.get(id)).width(10).color(Color.argb(255, 1, 1, 1)));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }
}
