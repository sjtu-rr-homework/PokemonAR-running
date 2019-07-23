package example.com.pkmnavidemo4.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.com.pkmnavidemo4.CheckNeighbour;
import example.com.pkmnavidemo4.R;
import example.com.pkmnavidemo4.RecordActivity;

public class RunningFragment extends Fragment implements View.OnClickListener{
    private TextView toRecord;
    //private Button button;
    private ImageView checkNeighbour;
    //private String content;

    //public RunningFragment(String content) {
        //this.content = content;
    //}
    private TextView title, item_weixin, item_tongxunlu;
    private ViewPager vp;
    private FreeModeFragment freeModeFragment;
    private RestrainModeFragment restrainModeFragment;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.running_fg_content,container,false);

        //TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        //txt_content.setText(content);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //button = (Button) getActivity().findViewById(R.id.button3);
        //toRecord=(TextView)getActivity().findViewById(R.id.fg_running_textView);
        //checkNeighbour=(ImageView)getActivity().findViewById(R.id.fg_running_checkneighbour);
        initViews();

        mFragmentAdapter = new FragmentAdapter((FragmentManager) getChildFragmentManager(), mFragmentList);
        vp.setOffscreenPageLimit(4);//ViewPager的缓存为4帧
        vp.setAdapter(mFragmentAdapter);
        vp.setCurrentItem(0);//初始设置ViewPager选中第一帧
        item_weixin.setTextColor(Color.parseColor("#66CDAA"));

        //ViewPager的监听事件
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                /*此方法在页面被选中时调用*/
                changeTextColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                /*此方法是在状态改变的时候调用，其中arg0这个参数有三种状态（0，1，2）。
                arg0 ==1的时辰默示正在滑动，
                arg0==2的时辰默示滑动完毕了，
                arg0==0的时辰默示什么都没做。*/
            }
        });
        /*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"开始跑步",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), MapActivity.class);
                startActivity(intent);
            }
        });*/
        toRecord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getActivity(), RecordActivity.class);
                startActivity(intent);
            }
        });
        checkNeighbour.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent =new Intent(getActivity(), CheckNeighbour.class);
                startActivity(intent);
            }
        });
    }
    private void initViews() {
        toRecord=(TextView)getActivity().findViewById(R.id.fg_running_textView);
        checkNeighbour=(ImageView)getActivity().findViewById(R.id.fg_running_checkneighbour);
        item_weixin = (TextView) getActivity().findViewById(R.id.item_weixin);
        item_tongxunlu = (TextView) getActivity().findViewById(R.id.item_tongxunlu);

        item_weixin.setOnClickListener(this);
        item_tongxunlu.setOnClickListener(this);

        vp = (ViewPager) getActivity().findViewById(R.id.fg_running_mainViewPager);
        freeModeFragment = new FreeModeFragment();
        restrainModeFragment = new RestrainModeFragment();
        //给FragmentList添加数据
        mFragmentList.add(freeModeFragment);
        mFragmentList.add(restrainModeFragment);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_weixin:
                vp.setCurrentItem(0, true);
                break;
            case R.id.item_tongxunlu:
                vp.setCurrentItem(1, true);
                break;
        }
    }
    public class FragmentAdapter extends FragmentPagerAdapter {

        List<Fragment> fragmentList = new ArrayList<Fragment>();

        public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

    }
    private void changeTextColor(int position) {
        if (position == 0) {
            item_weixin.setTextColor(Color.parseColor("#66CDAA"));
            item_tongxunlu.setTextColor(Color.parseColor("#000000"));
        } else if (position == 1) {
            item_tongxunlu.setTextColor(Color.parseColor("#66CDAA"));
            item_weixin.setTextColor(Color.parseColor("#000000"));
        }
    }
}
