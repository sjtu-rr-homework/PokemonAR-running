package example.com.pkmnavidemo4;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import example.com.pkmnavidemo4.classes.HttpHandler;
import example.com.pkmnavidemo4.classes.SharedPreferencesUtil;
import example.com.pkmnavidemo4.classes.UserData;
import example.com.pkmnavidemo4.classes.WeiboDialogUtils;

public class LoginActivity  extends AppCompatActivity {
    private Button login;
    private TextView toRegister;
    private Dialog mWeiboDialog;
    EditText usernameText;
    EditText passwordText;
    TextView text;
    CheckBox remember_password;
    CheckBox auto_login;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    WeiboDialogUtils.closeDialog(mWeiboDialog);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameText=(EditText)findViewById(R.id.name);
        passwordText=(EditText)findViewById(R.id.password);
        remember_password=(CheckBox)findViewById(R.id.act_login_remember);
        auto_login=(CheckBox)findViewById(R.id.act_login_auto);
        text=(TextView)findViewById(R.id.act_login_text_jaccount);
        login = (Button)findViewById(R.id.act_login_button_login);
        if(SharedPreferencesUtil.getBoolean(getApplicationContext(),"isremember",false)){
            usernameText.setText(SharedPreferencesUtil.getString(getApplicationContext(),"username",""));
            passwordText.setText(SharedPreferencesUtil.getString(getApplicationContext(),"password",""));
            remember_password.setChecked(true);
            if(SharedPreferencesUtil.getBoolean(getApplicationContext(),"isauto",false)){
                auto_login.setChecked(true);
                mWeiboDialog = WeiboDialogUtils.createLoadingDialog(LoginActivity.this, "自动登录中...");
                mHandler.sendEmptyMessageDelayed(1, 1000);
                String username=usernameText.getText().toString();
                String password=passwordText.getText().toString();
                HttpHandler.login(LoginActivity.this,username,password);
                HttpHandler.getMileage(username);
                HttpHandler.getElfs(username);
                HttpHandler.getExp(username);
                UserData.setUserInfo(username,1);
                UserData.initonlyHave();
            }
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=usernameText.getText().toString();
                String password=passwordText.getText().toString();
                if(remember_password.isChecked()){
                    SharedPreferencesUtil.putString(getApplicationContext(),"username",username);
                    SharedPreferencesUtil.putString(getApplicationContext(),"password",password);
                    SharedPreferencesUtil.putBoolean(getApplicationContext(),"isremember",true);
                }
                HttpHandler.login(LoginActivity.this,username,password);
                HttpHandler.getMileage(username);
                HttpHandler.getElfs(username);
                HttpHandler.getExp(username);
                UserData.setUserInfo(username,1);
                UserData.initonlyHave();

            }
        });
        auto_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!remember_password.isChecked()&&b){
                    auto_login.setChecked(false);
                }
            }
        });
        remember_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!b&&auto_login.isChecked()){
                    auto_login.setChecked(false);
                }
            }
        });
        toRegister=findViewById(R.id.act_login_text_new);
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);;
            }
        });
    }
}
