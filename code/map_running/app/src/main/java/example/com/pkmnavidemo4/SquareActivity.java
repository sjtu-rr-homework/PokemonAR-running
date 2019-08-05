package example.com.pkmnavidemo4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import example.com.pkmnavidemo4.classes.EndlessRecyclerOnScrollListener;
import example.com.pkmnavidemo4.classes.FriendsCircleAdapter;
import example.com.pkmnavidemo4.classes.HttpHandler;
import example.com.pkmnavidemo4.classes.UserData;

public class SquareActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    boolean isAdd=false;
    boolean isFresh=false;
    private TextView back_arrow;
    private ImageView to_share;
    private static List<Map> moments;
    private SwipeRefreshLayout refreshLayout;
    private FriendsCircleAdapter adapter;
    private RecyclerView recyclerView;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private int lastVisibleItem = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        moments = UserData.getMoments();
        super.onCreate(savedInstanceState);
        setTitle("仿微信朋友圈");
        setContentView(R.layout.activity_square);
        initRefreshLayout();
        back_arrow=findViewById(R.id.act_square_left_arrow);
        to_share=findViewById(R.id.act_squre_to_share);
        back_arrow.setText("<");
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        to_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SquareActivity.this, ShareActivity.class);
                startActivity(intent);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_forum);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (moments != null) {
            adapter=new FriendsCircleAdapter(this, moments);
            recyclerView.setAdapter(adapter);
        }
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                if (!isAdd) {
                    isAdd=true;
                    adapter.setLoadState(adapter.LOADING);
                    if(!UserData.isAllMomentsGet) {
                        moments = UserData.getMoments();
                    }
                    else
                        moments=null;
                    if (moments!=null&&moments.size()!= 0) {
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        adapter.setLoadState(adapter.LOADING_COMPLETE);
                                    }
                                });
                            }
                        }, 1000);
                        adapter.addEnd(moments);
                    } else {
                        // 显示加载到底的提示
                        UserData.isAllMomentsGet=true;
                        adapter.setLoadState(adapter.LOADING_END);
                    }
                }
                isAdd=false;
            }
        });
    }

    private void initRefreshLayout() {
        refreshLayout=findViewById(R.id.refreshLayout);
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        if (!isFresh) {
            isFresh=true;
            // 设置可见
            refreshLayout.setRefreshing(true);
            adapter.setLoadState(adapter.LOADING);
            moments = UserData.refreshMoments();
            if (moments != null) {
                adapter.addStart(moments);
            }
            refreshLayout.setRefreshing(false);
        }
        isFresh=false;
    }

    @Override
    public void onRestart(){
        try{
            Thread.sleep(200);
        }catch (Exception e){
        }
        if (!isFresh) {
            isFresh=true;
            // 设置可见
            refreshLayout.setRefreshing(true);
            adapter.setLoadState(adapter.LOADING);
            moments = UserData.refreshMoments();
            if (moments != null) {
                adapter.addStart(moments);
            }
            refreshLayout.setRefreshing(false);
        }
        isFresh=false;
        super.onRestart();
    }


}
