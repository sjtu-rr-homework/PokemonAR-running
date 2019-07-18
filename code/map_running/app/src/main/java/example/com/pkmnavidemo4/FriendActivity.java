package example.com.pkmnavidemo4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class FriendActivity extends AppCompatActivity {
    TextView username;
    int elfId=-1;
    TextView elfname;
    TextView level;
    TextView fightPoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        username=(TextView)findViewById(R.id.act_friend_username);
        elfname=(TextView)findViewById(R.id.act_friend_elfname);
        level=(TextView)findViewById(R.id.act_friend_elflevel);
        fightPoint=(TextView)findViewById(R.id.act_friend_fightpoint);
    }
}
