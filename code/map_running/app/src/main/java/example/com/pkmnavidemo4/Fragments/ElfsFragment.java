package example.com.pkmnavidemo4.Fragments;


import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import example.com.pkmnavidemo4.LoginActivity;
import example.com.pkmnavidemo4.R;
import example.com.pkmnavidemo4.SceneformActivity;
import example.com.pkmnavidemo4.classes.HttpHandler;
import example.com.pkmnavidemo4.classes.TestRecycleViewAdapter;
import example.com.pkmnavidemo4.classes.UserData;

public class ElfsFragment extends Fragment {
    private Switch switchModel;
    private Button refresh;
    private List<String> list;
    RecyclerView mRecyclerView;
    public ElfsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.elfs_fg_content,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.elf_fg_recycle_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        initData();
        //实例化并传输数据给adapter
        TestRecycleViewAdapter adapter = new TestRecycleViewAdapter(getActivity(),list);
        mRecyclerView.setAdapter(adapter);
        refresh( mRecyclerView);
        refresh=view.findViewById(R.id.elf_fg_button_refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh( mRecyclerView);
            }
        });
        switchModel =view.findViewById(R.id.elf_fg_button_switch);
        switchModel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    //选择只查看已有精灵
                    UserData.reverse();
                    TestRecycleViewAdapter adapter = new TestRecycleViewAdapter(getActivity(),UserData.getElfList());
                    mRecyclerView.setAdapter(adapter);
                }else {
                    UserData.reverse();
                    //查看所有精灵
                    initData();
                    //实例化并传输数据给adapter
                    TestRecycleViewAdapter adapter = new TestRecycleViewAdapter(getActivity(),list);
                    mRecyclerView.setAdapter(adapter);
                }
            }
        });

        LinearLayout linearLayout=view.findViewById(R.id.fg_elf_topbar);
        linearLayout.setPadding(0,getStatusBarHeight(),0,0);
        return view;
    }

    private void refresh( RecyclerView mRecyclerView){
        HttpHandler.getElfs(getActivity(),UserData.getUserName());
        if(!UserData.getOnlyHave())
            return;
        TestRecycleViewAdapter adapter = new TestRecycleViewAdapter(getActivity(),UserData.getElfList());
        mRecyclerView.setAdapter(adapter);
    }

    private void refresh(String userName){
        HttpHandler.getElfs(UserData.getUserName());
        TestRecycleViewAdapter adapter = new TestRecycleViewAdapter(getActivity(),UserData.getElfList());
        mRecyclerView.setAdapter(adapter);
    }

    //得到所有精灵
    private void initData() {
        list = new ArrayList<>();
        for (int i = 1; i<= 5; i++) {
            list.add(""+i);
        }
    }
    //得到用户已经有的精灵
    public void getElfsOfUser(List<Map> elfs) {
        list = new ArrayList<>();
        for (int i = 0; i < elfs.size(); i++) {
            list.add("" + elfs.get(i).get("typeID"));
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden){
        super.onHiddenChanged(hidden);
        if (!hidden) {
            refresh( mRecyclerView);
        }
    }

    private int getStatusBarHeight() {
        Resources resources = getActivity().getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen","android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v("dbw", "Status height:" + height);
        return height;
    }
}
