package example.com.pkmnavidemo4.classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import example.com.pkmnavidemo4.R;

public class ElfPoint {
    List<LatLng> allPoint;
    int max_id;

    public ElfPoint(){
        max_id=0;
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
    }

    public void setMax_id(int max_id) {
        this.max_id = max_id;
    }

    public void generateElfPoing(int number,Context context,AMap amap){
        int[] pidList=new int[number];
        boolean isExist=false;
        for(int i=0;i<number;++i){
            int pid=(int)(Math.random()*allPoint.size());
            for(int j=0;j<pidList.length;++j){
                if(pid==pidList[j]){
                    isExist=true;
                    break;
                }
            }
            if(isExist){
                --i;
                continue;
            }
            else{
                pidList[i]=pid;
            }
        }
        for(int i=0;i<number;++i){
            int id=(int)(Math.random()*max_id);
            LatLng point=allPoint.get(pidList[i]);
            addMarkersToMap(context,amap,point,id);
        }
    }

    public static void addMarkersToMap(Context context, AMap aMap, LatLng latlng,int id) {
        if (aMap != null) {
            View view = View.inflate(context, R.layout.view_marker, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.ivQuality);
            //int aqi=Integer.parseInt(model.getAqi());
            //String s= Environment.getExternalStorageDirectory().getAbsolutePath()
            imageView.setImageResource(convertID(id));
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
}
