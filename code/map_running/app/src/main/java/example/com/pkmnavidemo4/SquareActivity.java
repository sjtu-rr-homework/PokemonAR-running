package example.com.pkmnavidemo4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.Map;

import example.com.pkmnavidemo4.classes.FriendsCircleAdapter;
import example.com.pkmnavidemo4.classes.HttpHandler;
import example.com.pkmnavidemo4.classes.UserData;

public class SquareActivity extends AppCompatActivity {
    private static List<Map> moments;
    private Button share;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        moments= UserData.getMoments();
        super.onCreate(savedInstanceState);
        setTitle("仿微信朋友圈");
        setContentView(R.layout.activity_square);
        share=findViewById(R.id.act_square_button_share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SquareActivity.this, ShareActivity.class);
                startActivity(intent);
            }
        });
        if(moments!=null) {
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new FriendsCircleAdapter(this, moments));
        }
    }
}
