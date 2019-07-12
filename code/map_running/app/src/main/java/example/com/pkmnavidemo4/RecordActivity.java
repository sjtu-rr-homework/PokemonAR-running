package example.com.pkmnavidemo4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class RecordActivity extends AppCompatActivity {
    private Button showrecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        showrecord = findViewById(R.id.act_record_showRecord);
        showrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RecordActivity.this,RecordOnMapActivity.class);
                startActivity(intent);
            }
        });
    }
}
