package example.com.pkmnavidemo4;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import example.com.pkmnavidemo4.classes.ElfSourceController;


public class ElfDetailsActivity extends AppCompatActivity {
    private ImageView main;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elf_details);
        Intent intent=getIntent();
        int variety=intent.getIntExtra("variety", -1);
        back=findViewById(R.id.act_elf_details_elf_button_return);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        main=findViewById(R.id.act_elf_details_elf_nowimage);
        main.setBackgroundResource(ElfSourceController.getBackground(variety));
        image1=findViewById(R.id.act_elf_details_elf_image1);
        image1.setBackgroundResource(ElfSourceController.getBackgroundWithLevel(variety,1));
        image2=findViewById(R.id.act_elf_details_elf_image2);
        image2.setBackgroundResource(ElfSourceController.getBackgroundWithLevel(variety,2));
        if(ElfSourceController.getMaxLevel(variety)==3){
            image3=findViewById(R.id.act_elf_details_elf_image_3);
            image3.setBackgroundResource(ElfSourceController.getBackgroundWithLevel(variety,3));
        }
    }
}
