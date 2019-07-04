package example.com.pkmnavidemo4;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import example.com.pkmnavidemo4.Fragments.ElfsFragment;
import example.com.pkmnavidemo4.Fragments.MyFragment;

/**
 * Created by Coder-pig on 2015/8/28 0028.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //UI Object
    private TextView txt_topbar;
    private TextView txt_channel;
    private TextView txt_message;
    private TextView txt_better;
    private FrameLayout ly_content;

    //Fragment Object
    private MyFragment fg2,fg3;
    private ElfsFragment elfsFragment;
    private FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        fManager = getFragmentManager();
        bindViews();
        txt_channel.performClick();   //模拟一次点击，既进去后选择第一项



    }

    //UI组件初始化与事件绑定
    private void bindViews() {
        txt_topbar = (TextView) findViewById(R.id.txt_topbar);
        txt_channel = (TextView) findViewById(R.id.txt_channel);
        txt_message = (TextView) findViewById(R.id.txt_message);
        txt_better = (TextView) findViewById(R.id.txt_better);
        ly_content = (FrameLayout) findViewById(R.id.ly_content);

        txt_channel.setOnClickListener(this);
        txt_message.setOnClickListener(this);
        txt_better.setOnClickListener(this);
    }

    //重置所有文本的选中状态
    private void setSelected(){
        txt_channel.setSelected(false);
        txt_message.setSelected(false);
        txt_better.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(elfsFragment != null)fragmentTransaction.hide(elfsFragment);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
    }


    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (v.getId()){
            case R.id.txt_channel:
                setSelected();
                txt_channel.setSelected(true);
                if(elfsFragment == null){
                    elfsFragment = new ElfsFragment();
                    fTransaction.add(R.id.ly_content,elfsFragment);
                }else{
                    fTransaction.show(elfsFragment);
                }
                break;
            case R.id.txt_message:
                setSelected();
                txt_message.setSelected(true);
                if(fg2 == null){
                    fg2 = new MyFragment("第二个Fragment");
                    fTransaction.add(R.id.ly_content,fg2);
                }else{
                    fTransaction.show(fg2);
                }
                break;
            case R.id.txt_better:
                setSelected();
                txt_better.setSelected(true);
                if(fg3 == null){
                    fg3 = new MyFragment("第三个Fragment");
                    fTransaction.add(R.id.ly_content,fg3);
                }else{
                    fTransaction.show(fg3);
                }
                break;
        }
        fTransaction.commit();
    }
}
