package example.com.pkmnavidemo4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import example.com.pkmnavidemo4.classes.HttpHandler;
import example.com.pkmnavidemo4.classes.UserData;

public class FriendActivity extends AppCompatActivity {
    TextView username;
    int elfId=-1;
    TextView elfname;
    TextView level;
    TextView fightPoint;
    Button addfriend;
    Button fightfriend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        Intent intent=getIntent();
        int type=intent.getIntExtra("type",-1);//type为1则是添加好友，type为2则是好友对战
        String friendname=intent.getStringExtra("username");
        username=(TextView)findViewById(R.id.act_friend_username);
        elfname=(TextView)findViewById(R.id.act_friend_elfname);
        level=(TextView)findViewById(R.id.act_friend_elflevel);
        fightPoint=(TextView)findViewById(R.id.act_friend_fightpoint);
        addfriend=(Button)findViewById(R.id.act_friend_add);
        fightfriend=(Button)findViewById(R.id.act_friend_fight);
        if(type==1){
            addfriend.setVisibility(View.VISIBLE);
            fightfriend.setVisibility(View.INVISIBLE);
        }else if(type==2){
            addfriend.setVisibility(View.INVISIBLE);
            fightfriend.setVisibility(View.VISIBLE);
        }else{
            Log.d("error","type错误");
        }

        //http请求添加在这里

        username.setText(friendname);
        addfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpHandler.addFriend(FriendActivity.this,UserData.getUserName(),friendname);
            }
        });
    }
}
