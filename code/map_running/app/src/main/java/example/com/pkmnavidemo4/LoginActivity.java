package example.com.pkmnavidemo4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import example.com.pkmnavidemo4.classes.HttpHandler;
import example.com.pkmnavidemo4.classes.UserData;

public class LoginActivity  extends AppCompatActivity {
    private Button login;
    private TextView toRegister;
    EditText usernameText;
    EditText passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameText=(EditText)findViewById(R.id.name);
        passwordText=(EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.act_login_button_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=usernameText.getText().toString();
                String password=passwordText.getText().toString();
                HttpHandler.login(LoginActivity.this,username,password);
                HttpHandler.getElfs(username);
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
