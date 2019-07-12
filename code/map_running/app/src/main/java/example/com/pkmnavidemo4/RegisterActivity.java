package example.com.pkmnavidemo4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import example.com.pkmnavidemo4.classes.HttpHandler;


public class RegisterActivity extends AppCompatActivity {
    private Button register;
    private EditText usernameText;
    private EditText passwordText;
    private EditText rpasswordText;
    private EditText emailText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameText=findViewById(R.id.act_register_username);
        passwordText=findViewById(R.id.act_register_password);
        rpasswordText=findViewById(R.id.act_register_password2);
        emailText=findViewById(R.id.act_register_emailedit);
        register = findViewById(R.id.act_register_button_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!passwordText.getText().toString().equals(rpasswordText.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"两次输入的密码必须相同",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    String username=usernameText.getText().toString();
                    String password=passwordText.getText().toString();
                    String email=emailText.getText().toString();
                    //register(username,password,email);
                    HttpHandler.register(RegisterActivity.this,username,password,email);
                }
                //Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                //startActivity(intent);
            }
        });
    }

    private void register(String username,String password,String email) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String loginUrl="https://6ed30734.ngrok.io/user/register/username/"+username+"/password/"+password+"/email/"+email;
                //https://6ed30734.ngrok.io/user/register/username/macoredroid/password/c7o2r1e4/email/coredroid0401@gmail.com
                try {
                    //URL url=new URL("https://5184c2d6.ngrok.io/user/login/username/macoredroid/password/c7o2r1e4");
                    URL url=new URL(loginUrl);
                    conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    InputStream in=conn.getInputStream();
                    br=new BufferedReader(new InputStreamReader(in));

                    StringBuilder sb=new StringBuilder();
                    String s;
                    while((s = br.readLine())!=null){
                        sb.append(s);
                    }
                    if(sb.toString().equals("true")){
                        Looper.prepare();
                        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                        Looper.loop();

                    }
                    else{
                        Looper.prepare();
                        Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                    //setContent(sb.toString());
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                    if (conn!=null){
                        conn.disconnect();
                    }
                    if (br!=null){
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();

                        }
                    }
                }
            }
        }).start();

    }
}
