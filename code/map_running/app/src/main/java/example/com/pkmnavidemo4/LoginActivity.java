package example.com.pkmnavidemo4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import example.com.pkmnavidemo4.classes.HttpHandler;

public class LoginActivity  extends AppCompatActivity {
    private Button login;
    private TextView toRegister;
    EditText usernameText;
    EditText passwordText;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameText=(EditText)findViewById(R.id.name);
        passwordText=(EditText)findViewById(R.id.password);
        text=(TextView)findViewById(R.id.act_login_text_jaccount);
        login = (Button)findViewById(R.id.act_login_button_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //HttpHandler.postRunningRecord1(LoginActivity.this);
                HttpHandler.getRun();
                /*String username=usernameText.getText().toString();
                String password=passwordText.getText().toString();
                List<LatLng> course=new ArrayList<LatLng>();
                course.add(new LatLng(31.024944,121.43619));
                course.add(new LatLng(31.025279,121.437376));
                course.add(new LatLng(31.025831,121.438996));
                String arr=String.valueOf(course);*/

                //String json = JSONArray.
                //List<LatLng> tmp= Arrays.asList(course.toString());
                //text.setText(course.toString());
                //HttpHandler.postRunningRecord();
                //HttpHandler.login(LoginActivity.this,username,password);
                try {
                    //JSONObject un = new JSONObject();
                    //un.put("username", "test1");
                    //String Json = un.toString();

                }
                catch (Exception e){

                }
            }
        });
        toRegister=findViewById(R.id.act_login_text_new);
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
