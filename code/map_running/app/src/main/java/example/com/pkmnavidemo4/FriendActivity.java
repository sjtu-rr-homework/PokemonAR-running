package example.com.pkmnavidemo4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import example.com.pkmnavidemo4.classes.ElfSourceController;
import example.com.pkmnavidemo4.classes.HttpHandler;
import example.com.pkmnavidemo4.classes.UserData;

public class FriendActivity extends AppCompatActivity {
    ImageView elfImage;
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
        int typeID=intent.getIntExtra("typeID",-1);//出战精灵类型
        int grade=intent.getIntExtra("grade",-1);
        int exp=intent.getIntExtra("exp",-1);
        String friendname=intent.getStringExtra("username");
        username=(TextView)findViewById(R.id.act_friend_username);
        elfname=(TextView)findViewById(R.id.act_friend_elfname);
        elfname.setText(ElfSourceController.getName(typeID,grade));
        level=(TextView)findViewById(R.id.act_friend_elflevel);
        level.setText(""+exp/100+1);
        fightPoint=(TextView)findViewById(R.id.act_friend_fightpoint);
        fightPoint.setText(""+ElfSourceController.getPower(typeID,exp/100+1,grade));
        addfriend=(Button)findViewById(R.id.act_friend_add);
        fightfriend=(Button)findViewById(R.id.act_friend_fight);
        elfImage=findViewById(R.id.act_friend_elf);
        elfImage.setBackgroundResource(ElfSourceController.getBackgroundWithLevel(typeID,grade));
        if(type==1){
            addfriend.setVisibility(View.VISIBLE);
            fightfriend.setVisibility(View.INVISIBLE);
        }else if(type==2){
            addfriend.setVisibility(View.INVISIBLE);
            fightfriend.setVisibility(View.VISIBLE);
            fightfriend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int myPet=(int)(UserData.getElfWithId((int)UserData.getUserInfo().get("pet")).get("typeID"));
                    Intent intent=new Intent(FriendActivity.this,FightActivity.class);
                    intent.putExtra("leftElf",myPet);
                    intent.putExtra("rightElf",typeID);
                    intent.putExtra("leftPower",ElfSourceController.getPower(myPet,(int)UserData.getElfWithId(myPet).get("exp")/100+1,(int)UserData.getElfWithId(myPet).get("grade")));
                    intent.putExtra("rightPower",ElfSourceController.getPower(typeID,exp/100+1,grade));
                    intent.putExtra("leftGrade",(int)UserData.getElfWithId(myPet).get("grade"));
                    intent.putExtra("rightGrade",grade);
                    startActivity(intent);
                }
            });
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
