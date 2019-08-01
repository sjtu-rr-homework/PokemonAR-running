package example.com.pkmnavidemo4.Fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;

import java.text.DecimalFormat;

import example.com.pkmnavidemo4.CheckNeighbour;
import example.com.pkmnavidemo4.FriendPageActivity;
import example.com.pkmnavidemo4.R;
import example.com.pkmnavidemo4.RecordActivity;
import example.com.pkmnavidemo4.SquareActivity;
import example.com.pkmnavidemo4.classes.ElfSourceController;
import example.com.pkmnavidemo4.classes.HttpHandler;
import example.com.pkmnavidemo4.classes.UserData;

/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment extends Fragment {

    private LinearLayout checkfriend;
    private LinearLayout checkneighbour;
    private LinearLayout checkrecord;
    private LinearLayout checkBBS;
    private TextView distance;
    private TextView mileage;
    private TextView mileageGoal;
    private ImageView elfImage;
    private TextView username;
    private int elfId=-1;
    private TextView elfname;
    private TextView level;
    private TextView fightPoint;
    private TextView myExp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content,container,false);
        checkfriend=(LinearLayout) view.findViewById(R.id.fg_layout_check_friend);
        checkneighbour=(LinearLayout) view.findViewById(R.id.fg_layout_check_neighbour);
        checkrecord=(LinearLayout) view.findViewById(R.id.fg_layout_check_record);
        checkBBS=(LinearLayout) view.findViewById(R.id.fg_layout_check_bbs);

        distance=(TextView)view.findViewById(R.id.fg_achieve);
        mileage=(TextView)view.findViewById(R.id.fg_score);
        mileageGoal=(TextView)view.findViewById(R.id.fg_goal);
        myExp=view.findViewById(R.id.fg_layout_check_exp);
        DecimalFormat format=new DecimalFormat("#0.00");
        distance.setText(""+format.format(UserData.distance/1000)+"公里");
        mileage.setText(""+format.format(UserData.getMileage()/1000)+"公里");
        mileageGoal.setText(""+format.format(UserData.getMileageGoal()/1000)+"公里");

        int typeID=(int)(UserData.getElfWithId((int)UserData.getUserInfo().get("pet")).get("typeID"));
        int grade=(int)UserData.getElfWithId(typeID).get("grade");
        int exp=(int)UserData.getElfWithId(typeID).get("exp");
        username=(TextView)view.findViewById(R.id.fg_username);
        username.setText(UserData.getUserName());
        elfname=(TextView)view.findViewById(R.id.fg_elfname);
        elfname.setText(ElfSourceController.getName(typeID,grade));
        level=(TextView)view.findViewById(R.id.fg_elflevel);
        level.setText(""+(exp/100+1));
        fightPoint=(TextView)view.findViewById(R.id.fg_fightpoint);
        fightPoint.setText(""+ElfSourceController.getPower(typeID,exp/100+1,grade));
        elfImage=view.findViewById(R.id.fg_elf);
        elfImage.setBackgroundResource(ElfSourceController.getBackgroundWithLevel(typeID,grade));
        myExp.setText(UserData.getExp()+"");
        checkfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), FriendPageActivity.class);
                startActivity(intent);
            }
        });

        checkneighbour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(), CheckNeighbour.class);
                startActivity(intent);
            }
        });

        checkrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), RecordActivity.class);
                startActivity(intent);
            }
        });

        checkBBS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserData.isNewTimeInit=false;
                UserData.isAllMomentsGet=false;
                UserData.setOldForumTime(null);
                Intent intent=new Intent(getActivity(), SquareActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout linearLayout=view.findViewById(R.id.fg_title);
        linearLayout.setPadding(0,getStatusBarHeight(),0,0);
        return view;
    }

    private int getStatusBarHeight() {
        Resources resources = getActivity().getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen","android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v("dbw", "Status height:" + height);
        return height;
    }

    @Override
    public void onHiddenChanged(boolean hidden){
        super.onHiddenChanged(hidden);
        if (!hidden) {
            DecimalFormat format=new DecimalFormat("#0.00");
            distance.setText(""+format.format(UserData.distance/1000)+"公里");
            mileage.setText(""+format.format(UserData.getMileage()/1000)+"公里");
            mileageGoal.setText(""+format.format(UserData.getMileageGoal()/1000)+"公里");
            int typeID=(int)(UserData.getElfWithId((int)UserData.getUserInfo().get("pet")).get("typeID"));
            int grade=(int)UserData.getElfWithId(typeID).get("grade");
            int exp=(int)UserData.getElfWithId(typeID).get("exp");
            username.setText(UserData.getUserName());
            elfname.setText(ElfSourceController.getName(typeID,grade));
            level.setText("lv."+(exp/100+1));
            fightPoint.setText(""+ElfSourceController.getPower(typeID,exp/100+1,grade));
            elfImage.setBackgroundResource(ElfSourceController.getBackgroundWithLevel(typeID,grade));
            myExp.setText(UserData.getExp()+"");
        }
    }

}
