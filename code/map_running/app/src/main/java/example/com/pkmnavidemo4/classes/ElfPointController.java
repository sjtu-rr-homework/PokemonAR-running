package example.com.pkmnavidemo4.classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import example.com.pkmnavidemo4.R;

public class ElfPointController {
    List<LatLng> allPoint;
    List<ElfPoint> presentElfPoint;
    int max_id;

    public ElfPointController(){
        max_id=0;
        allPoint=new ArrayList<LatLng>();
        presentElfPoint=new ArrayList<ElfPoint>();
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
        allPoint.add(new LatLng(31.026531,121.438647));//20个

        allPoint.add(new LatLng(31.021045,121.433814));
        allPoint.add(new LatLng(31.020071,121.434093));
        allPoint.add(new LatLng(31.020857,121.433004));
        allPoint.add(new LatLng(31.019928,121.433261));
        allPoint.add(new LatLng(31.021753,121.432022));
        allPoint.add(new LatLng(31.021661,121.43148));
        allPoint.add(new LatLng(31.021165,121.431603));
        allPoint.add(new LatLng(31.020425,121.43178));
        allPoint.add(new LatLng(31.019905,121.431872));
        allPoint.add(new LatLng(31.019248,121.43163));
        allPoint.add(new LatLng(31.021505,121.430418));
        allPoint.add(new LatLng(31.020719,121.430702));
        allPoint.add(new LatLng(31.022365,121.429576));
        allPoint.add(new LatLng(31.022857,121.431137));
        allPoint.add(new LatLng(31.023137,121.430981));
        allPoint.add(new LatLng(31.02424,121.430579));
        allPoint.add(new LatLng(31.02356,121.430847));
        allPoint.add(new LatLng(31.025045,121.431244));
        allPoint.add(new LatLng(31.023491,121.431968));
        allPoint.add(new LatLng(31.023459,121.429361));//40个

        allPoint.add(new LatLng(31.027863,121.445465));
        allPoint.add(new LatLng(31.028874,121.442783));
        allPoint.add(new LatLng(31.028506,121.43744));
        allPoint.add(new LatLng(31.028911,121.436088));
        allPoint.add(new LatLng(31.02516,121.442268));
        allPoint.add(new LatLng(31.025141,121.445615));
        allPoint.add(new LatLng(31.026355,121.446109));
        allPoint.add(new LatLng(31.02847,121.44701));
        allPoint.add(new LatLng(31.023413,121.438127));
        allPoint.add(new LatLng(31.024571,121.43759));
        allPoint.add(new LatLng(31.021041,121.439157));
        allPoint.add(new LatLng(31.02036,121.436903));
        allPoint.add(new LatLng(31.020066,121.436024));
        allPoint.add(new LatLng(31.019349,121.432612));
        allPoint.add(new LatLng(31.01934,121.430595));
        allPoint.add(new LatLng(31.018512,121.429876));
        allPoint.add(new LatLng(31.020434,121.428932));
        allPoint.add(new LatLng(31.021859,121.428213));
        allPoint.add(new LatLng(31.021841,121.432666));
        allPoint.add(new LatLng(31.022291,121.43406));//60个

        allPoint.add(new LatLng(31.028093,121.440712));
        allPoint.add(new LatLng(31.027762,121.439586));
        allPoint.add(new LatLng(31.027109,121.437386));
        allPoint.add(new LatLng(31.026686,121.436152));
        allPoint.add(new LatLng(31.02641,121.435552));
        allPoint.add(new LatLng(31.026879,121.435305));
        allPoint.add(new LatLng(31.027734,121.434962));
        allPoint.add(new LatLng(31.027164,121.435187));
        allPoint.add(new LatLng(31.026171,121.434554));
        allPoint.add(new LatLng(31.025859,121.433771));
        allPoint.add(new LatLng(31.02732,121.433288));
        allPoint.add(new LatLng(31.025684,121.433138));
        allPoint.add(new LatLng(31.025546,121.429565));
        allPoint.add(new LatLng(31.024967,121.427784));
        allPoint.add(new LatLng(31.026042,121.428406));
        allPoint.add(new LatLng(31.026493,121.430005));
        allPoint.add(new LatLng(31.02675,121.429104));
        allPoint.add(new LatLng(31.025905,121.426593));
        allPoint.add(new LatLng(31.027964,121.433063));
        allPoint.add(new LatLng(31.027302,121.425896));//80个
    }

    public void setMax_id(int max_id) {
        this.max_id = max_id;
    }

    public void showAllPoints(AMap aMap){
        for(int i=0;i<allPoint.size();++i){
            Marker marker=aMap.addMarker(new MarkerOptions().position(allPoint.get(i)).title("王凯源法学院").snippet("DefaultMarker"));
        }
    }

    public int[] getPointLogic(int number,LatLng latLng,int pu100,int pu500,int pu1000,int po1000){
        if(number!=(pu100+pu500+pu1000+po1000)||number>allPoint.size()){
            return null;
        }
        int[] pidList=new int[number];
        List pointUnder100=new ArrayList();
        List pointUnder500=new ArrayList();
        List pointUnder1000=new ArrayList();
        List pointOver1000=new ArrayList();
        Set<Integer> pSetU100=new TreeSet<>();
        Set<Integer> pSetU500=new TreeSet<>();
        Set<Integer> pSetU1000=new TreeSet<>();
        Set<Integer> pSetO1000=new TreeSet<>();
        int pointRan=0;
        for(int i=0;i<allPoint.size();++i){
            float distance= AMapUtils.calculateLineDistance(latLng,allPoint.get(i));
            if(distance<100){
                pointUnder100.add(i);
            }
            else if(distance<500){
                pointUnder500.add(i);
            }
            else if(distance<1000){
                pointUnder1000.add(i);
            }
            else{
                pointOver1000.add(i);
            }
        }

        if(pu100>pointUnder100.size()){
            pu500+=(pu100-pointUnder100.size());
            pu100=pointUnder100.size();
        }
        if(pu500>pointUnder500.size()){
            pu1000+=(pu500-pointUnder500.size());
            pu500=pointUnder500.size();
        }
        if(pu1000>pointUnder1000.size()){
            po1000+=(pu1000-pointUnder1000.size());
            pu1000=pointUnder1000.size();
        }

        if(po1000>pointOver1000.size()){
            pointRan+=(po1000-pointOver1000.size());
            po1000=pointOver1000.size();
        }
        /*
        for(int i=0;i<pointUnder500.size();++i){
            Marker marker=aMap.addMarker(new MarkerOptions().position(allPoint.get((int)pointUnder500.get(i))).title("王凯源法学院").snippet("DefaultMarker"));
        }
        if(true) return null;*/

        for(int i=0;i<pu100;++i){
            int id=(int)(Math.random()*pointUnder100.size());
            if(pSetU100.contains(id)){
                --i;
                continue;
            }
            else{
                pSetU100.add(id);
            }
        }
        {int i=0;
        for(Integer value:pSetU100){
            pidList[i]=(int)pointUnder100.get(value);
            ++i;
        }}

        for(int i=0;i<pu500;++i){
            int id=(int)(Math.random()*pointUnder500.size());
            if(pSetU500.contains(id)){
                --i;
                continue;
            }
            else{
                pSetU500.add(id);
            }
        }
        {int i=pu100;
            for(Integer value:pSetU500){
                pidList[i]=(int)pointUnder500.get(value);
                ++i;
            }}

        for(int i=0;i<pu1000;++i){
            int id=(int)(Math.random()*pointUnder1000.size());
            if(pSetU1000.contains(id)){
                --i;
                continue;
            }
            else{
                pSetU1000.add(id);
            }
        }
        {int i=pu100+pu500;
            for(Integer value:pSetU1000){
                pidList[i]=(int)pointUnder1000.get(value);
                ++i;
            }}

        for(int i=0;i<po1000;++i){
            int id=(int)(Math.random()*pointOver1000.size());
            if(pSetO1000.contains(id)){
                --i;
                continue;
            }
            else{
                pSetO1000.add(id);
            }
        }
        {int i=pu100+pu500+pu1000;
            for(Integer value:pSetO1000){
                pidList[i]=(int)pointOver1000.get(value);
                ++i;
            }}
        /*for(int i=0;i<pu100+pu500+pu1000+po1000;++i){
            Marker marker=aMap.addMarker(new MarkerOptions().position(allPoint.get(pidList[i])).title("王凯源法学院").snippet("DefaultMarker"));
        }
        if(true) return null;*/
        if(pointRan!=0){
            for(int i=pu100+pu500+pu1000+po1000;i<pu100+pu500+pu1000+po1000+pointRan;++i){
                int pid=(int)(Math.random()*allPoint.size());
                boolean isExist=false;
                for(int j=0;j<i;++j){
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
        }
        return pidList;
    }

    public void generateElfPoing(Context context,AMap amap,LatLng latLng){
        int[] pidList;
        int number=10;
        int pu100=1;
        int pu500=5;
        int pu1000=3;
        int po1000=1;
        pidList=getPointLogic(number,latLng,pu100,pu500,pu1000,po1000);
        if(pidList==null) return;

        for(int i=0;i<number;++i){
            int id=(int)(Math.random()*max_id+1);
            LatLng point=allPoint.get(pidList[i]);
            ElfPoint elfPoint=new ElfPoint(point,id);
            presentElfPoint.add(elfPoint);
            addMarkersToMap(context,amap,point,id);
        }
    }

    public static void addMarkersToMap(Context context, AMap aMap, LatLng latlng,int id) {
        if (aMap != null) {
            View view = View.inflate(context, R.layout.view_marker, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.ivQuality);
            imageView.setImageResource(ElfSourceController.getMapPic(id));
            Bitmap bitmap = convertViewToBitmap(view);
            MarkerOptions markerOptions = new MarkerOptions()
                    .snippet(""+id)
                    .position(latlng)
                    .draggable(false)
                    .setFlat(true)
                    .anchor(0.5F,0.5F)
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

    public List<ElfPoint> getPresentElfPoint() {
        return presentElfPoint;
    }
}
