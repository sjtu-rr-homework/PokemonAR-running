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
            }
        });

        //准备适配器
        MyAdapter adapter=new MyAdapter(this);
        adapter.setList(list);
        //将适配器与ListView关联
        listview.setAdapter(adapter);
    }
}
