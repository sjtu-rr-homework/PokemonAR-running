package example.com.pkmnavidemo4;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

import example.com.pkmnavidemo4.classes.ElfSourceController;
import example.com.pkmnavidemo4.classes.HttpHandler;
import example.com.pkmnavidemo4.classes.UserData;


public class ElfDetailsActivity extends AppCompatActivity {
    private ImageView main;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private TextView  nowNumText;
    private TextView  nowExpText;
    private TextView  name;
    private TextView  needNumText;
    private TextView  needExpText;
    private TextView  numOfExp;
    private TextView  nowLevel;
    private Button back;
    private Button AR;
    private Button addExp;
    private Button addGrade;
    private int nowExp=0;
    private int level=1;
    private int nowNum=0;
    private int grade=1;
    private int nowGrade=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elf_details);
        Intent intent=getIntent();
        int variety=intent.getIntExtra("variety", -1);

        //找到当前精灵的详细信息
        for (int i = 0; i < UserData.getElfDetails().size(); i++) {
            if(UserData.getElfDetails().get(i).get("typeID").toString().equals(variety+"")){
                nowNum=Integer.valueOf((UserData.getElfDetails().get(i).get("num").toString()));
                nowExp=Integer.valueOf((UserData.getElfDetails().get(i).get("exp").toString()));
                grade=Integer.valueOf((UserData.getElfDetails().get(i).get("grade").toString()));
                nowGrade=grade;
                break;
            }
        }

        //查看AR
        AR=findViewById(R.id.act_elf_details_elf_button_ar);
        AR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ElfDetailsActivity.this, SceneformActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("variety", variety);
                bundle.putInt("grade",nowGrade);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //返回
        back=findViewById(R.id.act_elf_details_elf_button_return);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //增加经验
        numOfExp=findViewById(R.id.act_elf_details_elf_add);
        nowLevel=findViewById(R.id.act_elf_details_elf_level);
        addExp=findViewById(R.id.act_elf_details_elf_button_add);
        addExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int exp=Integer.valueOf(numOfExp.getText().toString());
                nowExp+=exp;
                HttpHandler.addExp(UserData.getUserName(),variety,exp);
                numOfExp.clearComposingText();
                nowLevel.setText("等级 "+(nowExp/100+1));
                nowExpText.setText("当前经验 "+ nowExp);
                for (int i = 0; i < UserData.getElfDetails().size(); i++) {
                    if(UserData.getElfDetails().get(i).get("typeID").toString().equals(variety+"")){
                        UserData.getElfDetails().get(i).replace("exp",(Integer.valueOf(UserData.getElfDetails().get(i).get("exp").toString())+exp));
                        break;
                    }
                }
            }
        });


        //控制用户该精灵的各种信息
        needNumText=findViewById(R.id.act_elf_details_elf_need_num);
        nowNumText=findViewById(R.id.act_elf_details_elf_num);
        nowNumText.setText("已有数量 "+ nowNum);
        nowExpText=findViewById(R.id.act_elf_details_elf_exp);
        nowExpText.setText("当前经验 "+ nowExp);
        nowLevel.setText("等级 "+(nowExp/100+1));
        needExpText=findViewById(R.id.act_elf_details_elf_need_exp);
        name=findViewById(R.id.act_elf_details_elf_name);
        name.setText("精灵名 "+ElfSourceController.getName(variety,nowGrade));
        main=findViewById(R.id.act_elf_details_elf_nowimage);
        main.setBackgroundResource(ElfSourceController.getBackgroundWithLevel(variety,nowGrade));

        //进化
        addGrade=findViewById(R.id.act_elf_details_elf_button_grow);
        addGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(nowGrade){
                    case 1:
                        nowGrade+=1;
                        HttpHandler.addGrade(UserData.getUserName(),variety,1);
                        name.setText("精灵名 "+ElfSourceController.getName(variety,nowGrade));
                        main.setBackgroundResource(ElfSourceController.getBackgroundWithLevel(variety,nowGrade));
                        for (int i = 0; i < UserData.getElfDetails().size(); i++) {
                            if(UserData.getElfDetails().get(i).get("typeID").toString().equals(variety+"")){
                                UserData.getElfDetails().get(i).replace("grade",(Integer.valueOf(UserData.getElfDetails().get(i).get("grade").toString())+1));
                                break;
                            }
                        }
                    case 2:
                        if(ElfSourceController.getMaxLevel(variety)==3) {
                            nowGrade += 1;
                            HttpHandler.addGrade(UserData.getUserName(),variety,1);
                            name.setText("精灵名 " + ElfSourceController.getName(variety, nowGrade));
                            main.setBackgroundResource(ElfSourceController.getBackgroundWithLevel(variety, nowGrade));
                            for (int i = 0; i < UserData.getElfDetails().size(); i++) {
                                if(UserData.getElfDetails().get(i).get("typeID").toString().equals(variety+"")){
                                    UserData.getElfDetails().get(i).replace("grade",(Integer.valueOf(UserData.getElfDetails().get(i).get("grade").toString())+1));
                                    break;
                                }
                            }
                        }
                     default:

                }
            }
        });

        //控制第一阶段精灵的图像以及数据
        image1=findViewById(R.id.act_elf_details_elf_image1);
        image1.setBackgroundResource(ElfSourceController.getBackgroundWithLevel(variety,1));
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                needNumText.setText("需要数量 "+1);
                needExpText.setText("需要经验 "+0);
                grade=1;
            }
        });

        //控制第二阶段精灵的图像以及数据
        image2=findViewById(R.id.act_elf_details_elf_image2);
        image2.setBackgroundResource(ElfSourceController.getBackgroundWithLevel(variety,2));
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                needNumText.setText("需要数量 "+4);
                needExpText.setText("需要等级 "+20);
                grade=2;
            }
        });

        //检验精灵是否有第三阶段并控制第三阶段精灵的图像以及数据
        if(ElfSourceController.getMaxLevel(variety)==3){
            image3=findViewById(R.id.act_elf_details_elf_image_3);
            image3.setBackgroundResource(ElfSourceController.getBackgroundWithLevel(variety,3));
            image3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    needNumText.setText("需要数量 "+9);
                    needExpText.setText("需要等级 "+60);
                    grade=3;
                }
            });
        }
    }
}
