package example.com.pkmnavidemo4;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import example.com.pkmnavidemo4.classes.ElfSourceController;
import example.com.pkmnavidemo4.classes.FightTextAdapter;
import example.com.pkmnavidemo4.classes.HttpHandler;
import example.com.pkmnavidemo4.classes.TestRecycleViewAdapter;
import example.com.pkmnavidemo4.classes.UserData;

public class FightActivity extends AppCompatActivity {
    FightTextAdapter adapter;
    private int leftHp=100;
    private int rightHp=100;
    private int leftElf;
    private int rightElf;
    private int leftPower;
    private int rightPower;
    private int leftGrade;
    private int rightGrade;
    private ProgressBar hp1;
    private ProgressBar hp2;
    private ImageView image1;
    private ImageView image2;
    private TextView hpt1;
    private TextView hpt2;
    private List<Spanned> list;
    private Button start;
    private Button finish;
    RecyclerView mRecyclerView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        leftElf = intent.getIntExtra("leftElf", 1);
        rightElf = intent.getIntExtra("rightElf", 2);
        leftPower = intent.getIntExtra("leftPower", 120);
        rightPower = intent.getIntExtra("rightPower", 100);
        leftGrade = intent.getIntExtra("leftGrade",2);
        rightGrade = intent.getIntExtra("rightGrade", 2);
        setContentView(R.layout.activity_fight);
        hp1=findViewById(R.id.act_fight_elf_hp1);
        hp2=findViewById(R.id.act_fight_elf_hp2);
        hpt1=findViewById(R.id.act_fight_elf_hpt1);
        hpt2=findViewById(R.id.act_fight_elf_hpt2);
        mRecyclerView = findViewById(R.id.elf_fight_recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(FightActivity.this));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        list = new ArrayList<>();
        SpannableStringBuilder ssb = new SpannableStringBuilder("点击对战");
            ssb.setSpan(new ForegroundColorSpan(Color.YELLOW), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        list.add(SpannableString.valueOf(ssb));
        adapter = new FightTextAdapter(FightActivity.this, list);
        mRecyclerView.setAdapter(adapter);
        start=findViewById(R.id.act_fight_button_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextFight();
            }
        });
        finish=findViewById(R.id.act_fight_button_finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        image1=findViewById(R.id.act_fight_elf_player1);
        image1.setBackgroundResource(ElfSourceController.getBackgroundWithLevel(leftElf,leftGrade));
        image2=findViewById(R.id.act_fight_elf_player2);
        image2.setBackgroundResource(ElfSourceController.getBackgroundWithLevel(rightElf,rightGrade));
    }

    private void startFight() {
        for(int i=0;leftHp > 0 && rightHp > 0;i++) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    nextFight();
                }
            }, 1000*i);
        }
    }
    /*private void startFight() {
        list = new ArrayList<>();
        int attack=0;
        //判断精灵攻击强度
        Random luck=new Random();
        for (i = 0; leftHp>0&&rightHp>0; i++) {
            if(i%2==0) {
                int randomAttack=luck.nextInt(10);
                if(ElfSourceController.getAttack(leftPower,rightPower)/2>=1)
                    attack=ElfSourceController.getAttack(leftPower,rightPower)/2+luck.nextInt(ElfSourceController.getAttack(leftPower,rightPower)/2);
                else
                    attack=1;
                switch (randomAttack){
                    case 1:
                        list.add(ElfSourceController.getName(leftElf, leftGrade)+"的攻击MISS了");
                        attack=0;
                        break;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        list.add(ElfSourceController.getName(leftElf, leftGrade)+"打出了普通一击");
                        break;
                    case 8:
                    case 9:
                        list.add(ElfSourceController.getName(leftElf, leftGrade)+"打出了会心一击");
                        attack*=2;
                        break;
                    case 0:
                        list.add(ElfSourceController.getName(leftElf, leftGrade)+"打出了致命一击");
                        attack*=5;
                        break;
                    default:
                }
                list.add("" + ElfSourceController.getName(leftElf, leftGrade) + "对" + ElfSourceController.getName(rightElf, rightGrade) + "造成了" +attack+ "%血量的伤害" );
                rightHp-=attack;
                if(rightHp<=0){
                    list.add(ElfSourceController.getName(leftElf, leftGrade)+"胜利!!!");
                    break;
                }
                list.add(ElfSourceController.getName(rightElf, rightGrade) +"还有" + rightHp+ "%血量");
            }
            else {
                if(ElfSourceController.getAttack(rightPower,leftPower)/2>=1)
                    attack=ElfSourceController.getAttack(rightPower,leftPower)/2+luck.nextInt(ElfSourceController.getAttack(rightPower,leftPower)/2);
                else
                    attack=1;
                int randomAttack=luck.nextInt(10);
                switch (randomAttack){
                    case 1:
                        list.add("" +ElfSourceController.getName(rightElf, rightGrade)+"的攻击MISS了");
                        attack=0;
                        break;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        list.add("" +ElfSourceController.getName(rightElf, rightGrade)+"打出了普通一击");
                        break;
                    case 8:
                    case 9:
                        list.add("" +ElfSourceController.getName(rightElf, rightGrade)+"打出了会心一击");
                        attack*=2;
                        break;
                    case 0:
                        list.add("" +ElfSourceController.getName(rightElf, rightGrade)+"打出了致命一击");
                        attack*=5;
                        break;
                    default:
                }
                list.add("     " + "" + ElfSourceController.getName(rightElf, rightGrade) + "对" + ElfSourceController.getName(leftElf, leftGrade) + "造成了" + attack + "%血量的伤害" );
                leftHp -=attack;
                if(leftHp<=0){
                    list.add(ElfSourceController.getName(rightElf, rightGrade)+"胜利!!!");
                    break;
                }
                list.add(ElfSourceController.getName(leftElf, leftGrade) +"还有" + leftHp+ "%血量");
            }
        }
        FightTextAdapter adapter = new FightTextAdapter(FightActivity.this,list);
        mRecyclerView.setAdapter(adapter);
    }*/
    private void nextFight() {
        adapter.myclear();
        list = new ArrayList<>();
        int attack = 0;
        //判断精灵攻击强度
        Random luck = new Random();
        if (leftHp > 0 && rightHp > 0) {
                int randomAttack = luck.nextInt(10);
                if (ElfSourceController.getAttack(leftPower, rightPower)>= 1)
                    attack = ElfSourceController.getAttack(leftPower, rightPower) / 2 + luck.nextInt(ElfSourceController.getAttack(leftPower, rightPower) / 2);
                else
                    attack = 1;
                switch (randomAttack) {
                    case 1:
                        adapter.addData( Html.fromHtml(getResources().getString(R.string.player1Fight2,ElfSourceController.getName(leftElf,leftGrade))));
                        //adapter.addData(SpannableString.valueOf(ElfSourceController.getColorfulElfName(leftElf, leftGrade,0) + "的攻击MISS了"));
                        attack = 0;
                        break;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        adapter.addData( Html.fromHtml(getResources().getString(R.string.player1Fight1,ElfSourceController.getName(leftElf,leftGrade))));
                       // adapter.addData(SpannableString.valueOf(ElfSourceController.getColorfulElfName(leftElf, leftGrade,0) + "打出了普通一击"));
                        break;
                    case 8:
                    case 9:
                        adapter.addData( Html.fromHtml(getResources().getString(R.string.player1Fight3,ElfSourceController.getName(leftElf,leftGrade))));
                       // adapter.addData(SpannableString.valueOf(ElfSourceController.getColorfulElfName(leftElf, leftGrade,0) + "打出了会心一击"));
                        attack *= 2;
                        break;
                    case 0:
                        adapter.addData( Html.fromHtml(getResources().getString(R.string.player1Fight4,ElfSourceController.getName(leftElf,leftGrade))));
                        //adapter.addData(SpannableString.valueOf(ElfSourceController.getColorfulElfName(leftElf, leftGrade,0) + "打出了致命一击"));
                        attack *= 5;
                        break;
                    default:
                }
                adapter.addData( Html.fromHtml(getResources().getString(R.string.player12,ElfSourceController.getName(leftElf,leftGrade),ElfSourceController.getName(rightElf,rightGrade),""+attack)));
               //adapter.addData(SpannableString.valueOf("" +ElfSourceController.getColorfulElfName(leftElf, leftGrade,0) + "对" +ElfSourceController.getColorfulElfName(rightElf, rightGrade,1) + "造成了" + attack + "%血量的伤害"));
                rightHp -= attack;
                if (rightHp <= 0) {
                    hp2.setProgress(0);
                    hpt1.setText("Win");
                    hpt2.setText("Lose");
                    adapter.addData(SpannableString.valueOf("胜利!!!"));
                    UserData.addExp(rightPower*50/leftPower);
                    adapter.addData(SpannableString.valueOf("你获得了"+rightPower*50/leftPower+"经验"));
                    finish();
                    return;
                }
                //adapter.addData(SpannableString.valueOf(ElfSourceController.getColorfulElfName(rightElf, rightGrade,1) + "还有" + rightHp + "%血量"));
                hpt2.setText(rightHp+"/100");
                hp2.setProgress(rightHp);

                if (ElfSourceController.getAttack(rightPower, leftPower)>= 1)
                    attack = ElfSourceController.getAttack(rightPower, leftPower) / 2 + luck.nextInt(ElfSourceController.getAttack(rightPower, leftPower) / 2);
                else
                    attack = 1;
                randomAttack = luck.nextInt(10);
                switch (randomAttack) {
                    case 1:
                        adapter.addData( Html.fromHtml(getResources().getString(R.string.player2Fight2,ElfSourceController.getName(rightElf,rightGrade))));
                        //adapter.addData(SpannableString.valueOf("" +ElfSourceController.getColorfulElfName(rightElf, rightGrade,1)+ "的攻击MISS了"));
                        attack = 0;
                        break;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        adapter.addData( Html.fromHtml(getResources().getString(R.string.player2Fight1,ElfSourceController.getName(rightElf,rightGrade))));
                        //adapter.addData(SpannableString.valueOf("" +ElfSourceController.getColorfulElfName(rightElf, rightGrade,1) + "打出了普通一击"));
                        break;
                    case 8:
                    case 9:
                        adapter.addData( Html.fromHtml(getResources().getString(R.string.player2Fight3,ElfSourceController.getName(rightElf,rightGrade))));
                        //adapter.addData(SpannableString.valueOf("" +ElfSourceController.getColorfulElfName(rightElf, rightGrade,1) + "打出了会心一击"));
                        attack *= 2.5;
                        break;
                    case 0:
                        adapter.addData( Html.fromHtml(getResources().getString(R.string.player2Fight4,ElfSourceController.getName(rightElf,rightGrade))));
                        //adapter.addData(SpannableString.valueOf("" +ElfSourceController.getColorfulElfName(rightElf, rightGrade,1) + "打出了致命一击"));
                        attack *= 6;
                        break;
                    default:
                }
                 adapter.addData( Html.fromHtml(getResources().getString(R.string.player21,ElfSourceController.getName(leftElf,leftGrade),ElfSourceController.getName(rightElf,rightGrade),""+attack)));
                 // adapter.addData(SpannableString.valueOf("     " + "" +ElfSourceController.getColorfulElfName(rightElf, rightGrade,1) + "对" + ElfSourceController.getColorfulElfName(leftElf, leftGrade,0) + "造成了" + attack + "%血量的伤害"));
                leftHp -= attack;
                if (leftHp <= 0) {
                    hp1.setProgress(0);
                    hpt1.setText("Lose");
                    hpt2.setText("Win");
                    adapter.addData(SpannableString.valueOf("失败"));
                    finish();
                    return;
                }
                //adapter.addData(SpannableString.valueOf(ElfSourceController.getColorfulElfName(leftElf, leftGrade,0) + "还有" + leftHp + "%血量"));
                hpt1.setText(leftHp+"/100");
                hp1.setProgress(leftHp);
        }
    }

    @Override
    public void finish() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(FightActivity.this);
        if(leftHp>0)
            dialog.setTitle("你胜利了!");
        else
            dialog.setTitle("你失败了!");
        dialog.setMessage("是否返回？");
        dialog.setCancelable(false);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FightActivity.super.finish();
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        dialog.show();
    }
}
