package example.com.pkmnavidemo4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import example.com.pkmnavidemo4.FriendPage.SideBar;
import example.com.pkmnavidemo4.FriendPage.SortAdapter;
import example.com.pkmnavidemo4.FriendPage.User;
import example.com.pkmnavidemo4.classes.HttpHandler;
import example.com.pkmnavidemo4.classes.UserData;

public class FriendPageActivity extends AppCompatActivity {

    private ListView listView;
    private SideBar sideBar;
    private ArrayList<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendpage);
        HttpHandler.getFriend();
        while(!UserData.isFriendGet){

        };
        initView();
        initData();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.act_friendpage_listView);
        sideBar = (SideBar) findViewById(R.id.act_friendpage_side_bar);
        sideBar.setOnStrSelectCallBack(new SideBar.ISideBarSelectCallBack() {
            @Override
            public void onSelectStr(int index, String selectStr) {
                for (int i = 0; i < list.size(); i++) {
                    if (selectStr.equalsIgnoreCase(list.get(i).getFirstLetter())) {
                        listView.setSelection(i); // 选择到首字母出现的位置
                        return;
                    }
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>parent,View view,int position,long id) {
                Toast.makeText(FriendPageActivity.this,list.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        list = new ArrayList<>();
        if(list.size()>0){
            list.clear();
        }
        for(int i=0;i< UserData.friend.size();++i){
            list.add(new User(UserData.friend.get(i)));
        }
        /*list.add(new User("王凯源")); // 亳[bó]属于不常见的二级汉字
        list.add(new User("魏卓然"));
        list.add(new User("马致远"));
        list.add(new User("浙江温州皮革厂"));
        list.add(new User("黄鹤"));
        list.add(new User("黄鹤的小姨子"));
        list.add(new User("易拉罐"));
        list.add(new User("百事可乐"));
        list.add(new User("交大男子派出所"));
        list.add(new User("纯品纸巾"));
        list.add(new User("夏令营"));
        list.add(new User("软件学院"));
        list.add(new User("沈老师"));
        list.add(new User("JJBoss"));
        list.add(new User("神船"));
        list.add(new User("406扫黑除恶小分队"));
        list.add(new User("东湖面馆"));
        list.add(new User("吃面吃到吐"));
        list.add(new User("我曾今跨过山河大海"));
        list.add(new User("戴安东之九点上班"));
        list.add(new User("徐礼杰之孤独的组长"));
        list.add(new User("Vr真吉尔好玩"));
        list.add(new User("wky是sb"));*/
        Collections.sort(list); // 对list进行排序，需要让User实现Comparable接口重写compareTo方法
        SortAdapter adapter = new SortAdapter(this, list);
        listView.setAdapter(adapter);
    }
}
