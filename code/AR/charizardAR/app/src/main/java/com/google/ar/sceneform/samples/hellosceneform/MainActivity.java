package com.google.ar.sceneform.samples.hellosceneform;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.TabActivity;
import android.view.LayoutInflater;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends TabActivity {
    private Button arButton;
    private Button arButton2;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main_tab);
        // 获取该Activity里面的TabHost组件
        TabHost tabHost = getTabHost();
        LayoutInflater.from(this).inflate(R.layout.main_tab,
                tabHost.getTabContentView(), true);
        arButton=findViewById(R.id.button1);
        arButton2=findViewById(R.id.button2);
        arButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("variety", 1);
                intent.setClass(MainActivity.this, SceneformActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);
            }
        });
        arButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("variety", 2);
                intent.setClass(MainActivity.this, SceneformActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);
            }
        });

        /* 以上创建和添加标签页用如下代码实现 */
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("精灵查看").setContent(R.id.tab01));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("标签页二").setContent(R.id.tab02));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("标签页三").setContent(R.id.tab03));

        //标签切换事件处理，setOnTabChangedListener
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener(){
            @Override
            // tabId是newTabSpec参数设置的tab页名，并不是layout里面的标识符id
            public void onTabChanged(String tabId) {
                if (tabId.equals("tab1")) {   //第一个标签
                }
                if (tabId.equals("tab2")) {   //第二个标签
                    Toast.makeText(MainActivity.this, "点击标签页二", Toast.LENGTH_SHORT).show();
                }
                if (tabId.equals("tab3")) {   //第三个标签
                    Toast.makeText(MainActivity.this, "点击标签页三", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
