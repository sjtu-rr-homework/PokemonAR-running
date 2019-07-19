package example.com.pkmnavidemo4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import example.com.pkmnavidemo4.classes.ElfSourceController;
import example.com.pkmnavidemo4.classes.FightTextAdapter;
import example.com.pkmnavidemo4.classes.TestRecycleViewAdapter;
import example.com.pkmnavidemo4.classes.UserData;

public class FightActivity extends AppCompatActivity {
    private int i;
    private int leftHp=100;
    private int rightHp=100;
    private int leftElf;
    private int rightElf;
    private int leftPower;
    private int rightPower;
    private int leftGrade;
    private int rightGrade;
    private ImageView image1;
    private ImageView image2;
    private List<String> list;
    private Queue index;
    private List<List<String>> showList;
    private Button start;
    RecyclerView mRecyclerView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        mRecyclerView = findViewById(R.id.elf_fight_recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(FightActivity.this));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        Intent intent = getIntent();
        leftElf = intent.getIntExtra("leftElf", 1);
        rightElf = intent.getIntExtra("rightElf", 2);
        leftPower = intent.getIntExtra("leftPower", 100);
        rightPower = intent.getIntExtra("rightPower", 100);
        leftGrade = intent.getIntExtra("leftGrade",2);
        rightGrade = intent.getIntExtra("rightGrade", 2);
        start=findViewById(R.id.act_fight_button_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
        image1=findViewById(R.id.act_fight_elf_player1);
        image1.setBackgroundResource(ElfSourceController.getBackgroundWithLevel(leftElf,leftGrade));
        image2=findViewById(R.id.act_fight_elf_player2);
        image2.setBackgroundResource(ElfSourceController.getBackgroundWithLevel(rightElf,rightGrade));
    }
    private void initData() {
        list = new ArrayList<>();
        showList=new ArrayList<>();
        for (i = 1; i<= 20; i++) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //do something
                    list.add(""+i);
                    index.add(i);
                    showList.add(list);
                    FightTextAdapter adapter = new FightTextAdapter(FightActivity.this,showList.get((int)index.poll()));
                    mRecyclerView.setAdapter(adapter);
                              }
                  }, 500*i);
            //实例化并传输数据给adapter
        }
    }
}
