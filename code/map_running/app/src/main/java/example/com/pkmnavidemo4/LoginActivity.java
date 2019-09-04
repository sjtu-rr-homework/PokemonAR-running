package example.com.pkmnavidemo4;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

        List<String> permissionList = new ArrayList<>();
        //如果没有启动下面权限，就询问用户让用户打开
        if(ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(LoginActivity.this,Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED)
        {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(LoginActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(LoginActivity.this, permissions, 1);
        }

        HttpHandler.getlimit();
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
                HttpHandler.login(LoginActivity.this,username,password,auto_login.isChecked());
                /*
                HttpHandler.getMileage(username);
                HttpHandler.getElfs(username);
                HttpHandler.getExp(username);
                UserData.setUserInfo(username,1);
                UserData.initonlyHave();*/
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
                    if(auto_login.isChecked()){
                        SharedPreferencesUtil.putBoolean(getApplicationContext(),"isauto",true);
                    }
                    else{
                        SharedPreferencesUtil.putBoolean(getApplicationContext(),"isauto",false);
                    }
                }
                else{
                    SharedPreferencesUtil.putBoolean(getApplicationContext(),"isremember",false);
                }
                HttpHandler.login(LoginActivity.this,username,password,auto_login.isChecked());
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
