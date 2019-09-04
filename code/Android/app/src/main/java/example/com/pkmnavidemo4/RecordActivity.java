package example.com.pkmnavidemo4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import example.com.pkmnavidemo4.classes.HttpHandler;
import example.com.pkmnavidemo4.classes.MyAdapter;
import example.com.pkmnavidemo4.classes.UserData;

public class RecordActivity extends AppCompatActivity {
    //private String[] time={"2019.04.29 23:11","2019.05.04 20.52","2019.05.05 22.10","2019.05.06 20:35","2019.05.07 19:32","2019.05.08 13:02","2019.05.09 15:31","2019.05.10 15:46","2019.05.12 08:58","2019.05.13 22:43"};
    //private String[] length={"1.64公里","2.15公里","3.26公里","7.78公里","1.05公里","2.97公里","5.00公里","4.32公里","2.36公里","4.33公里"};
    //private String[] constant={"00:13:59","00:16:45","00:34:10","00:55:27","00:06:54","00:20:43","00:31:28","00:29:46","00:20:34","00:33:24"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        HttpHandler.getRun();
        ListView listview=(ListView)findViewById(R.id.ListView1);
        //创建一个List集合，List集合的元素是map
        while(!UserData.isrecordGet){
            try{
                Thread.sleep(10);
            }catch (Exception e){

            }
        }
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        {
            //利用循环将键和值一一对应放入map集合内。
            for (int i = 0; i < UserData.recordLastTime.size(); i++) {
                //下面一句放在循环外和里面不一样
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("time", UserData.startTime.get(i));
                map.put("length", ""+UserData.rocordLength.get(i));
                map.put("constant", ""+UserData.recordLastTime.get(i));
                //将map值存进list
                list.add(map);
            }
        }
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(RecordActivity.this,""+position,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(RecordActivity.this,RecordOnMapActivity.class);
                intent.putExtra("id",String.valueOf(id));
                startActivity(intent);
                //Toast.makeText(ShareActivity.this, "选择了"+textID[position],Toast.LENGTH_SHORT).show();
            }
        });

//        //数据源
//        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
//        Map<String, Object> map = new HashMap<String, Object>();
//        map=new HashMap<String,Object>();
//        map.put("image",R.drawable.apple);
//        map.put("text","pingg ");
//        map.put("context","苹果");
//        list.add(map);

        //准备适配器
        MyAdapter adapter=new MyAdapter(this);
        adapter.setList(list);
        //将适配器与ListView关联
        listview.setAdapter(adapter);
    }
}
