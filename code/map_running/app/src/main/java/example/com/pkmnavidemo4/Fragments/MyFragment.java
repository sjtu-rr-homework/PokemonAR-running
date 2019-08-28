package example.com.pkmnavidemo4.Fragments;


import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

import example.com.pkmnavidemo4.Bitmap.BitmapUtils;
import example.com.pkmnavidemo4.CheckNeighbour;
import example.com.pkmnavidemo4.FriendPageActivity;
import example.com.pkmnavidemo4.LoginActivity;
import example.com.pkmnavidemo4.R;
import example.com.pkmnavidemo4.RecordActivity;
import example.com.pkmnavidemo4.SquareActivity;
import example.com.pkmnavidemo4.classes.ElfSourceController;
import example.com.pkmnavidemo4.classes.HttpHandler;
import example.com.pkmnavidemo4.classes.UserData;
import example.com.pkmnavidemo4.classes.WeiboDialogUtils;

import static android.app.Activity.RESULT_OK;

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
    private ImageView myCover;
    private TextView username;
    private int elfId=-1;
    private TextView elfname;
    private TextView level;
    private TextView fightPoint;
    private TextView myExp;
    private Dialog mWeiboDialog;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    WeiboDialogUtils.closeDialog(mWeiboDialog);
                    break;
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content,container,false);
        checkfriend=(LinearLayout) view.findViewById(R.id.fg_layout_check_friend);
        checkneighbour=(LinearLayout) view.findViewById(R.id.fg_layout_check_neighbour);
        checkrecord=(LinearLayout) view.findViewById(R.id.fg_layout_check_record);
        checkBBS=(LinearLayout) view.findViewById(R.id.fg_layout_check_bbs);

        myCover=view.findViewById(R.id.fg_cover);
        distance=(TextView)view.findViewById(R.id.fg_achieve);
        mileage=(TextView)view.findViewById(R.id.fg_score);
        mileageGoal=(TextView)view.findViewById(R.id.fg_goal);
        myExp=view.findViewById(R.id.fg_layout_check_exp);
        DecimalFormat format=new DecimalFormat("#0.00");
        distance.setText(""+format.format(UserData.distance/1000)+"公里");
        mileage.setText(""+format.format(UserData.getMileage()/1000)+"公里");
        mileageGoal.setText(""+format.format(UserData.getMileageGoal()/1000)+"公里");
        int typeID = -1;
        int grade = -1;
        int exp = -1;
        if(!UserData.getUserInfo().isEmpty()&&!UserData.getElfWithId((int)UserData.getUserInfo().get("pet")).isEmpty()) {
            typeID = (int) (UserData.getElfWithId((int) UserData.getUserInfo().get("pet")).get("typeID"));
            grade = (int) UserData.getElfWithId(typeID).get("grade");
            exp = (int) UserData.getElfWithId(typeID).get("exp");
        }
        username=(TextView)view.findViewById(R.id.fg_username);
        username.setText(UserData.getUserName());
        elfname=(TextView)view.findViewById(R.id.fg_elfname);
        elfname.setText(ElfSourceController.getName(typeID,grade));
        level=(TextView)view.findViewById(R.id.fg_elflevel);
        level.setText("lv."+(exp/100+1));
        fightPoint=(TextView)view.findViewById(R.id.fg_fightpoint);
        fightPoint.setText(""+ElfSourceController.getPower(typeID,exp/100+1,grade));
        elfImage=view.findViewById(R.id.fg_elf);
        elfImage.setBackgroundResource(ElfSourceController.getBackgroundWithLevel(typeID,grade));
        myExp.setText(UserData.getExp()+"");
        if(UserData.getCover(UserData.getUserName())==null) {
            myCover.setBackgroundResource(R.drawable.pikachu);
        }
        else{
            myCover.setBackgroundResource(R.drawable.bg_blue);
            myCover.setImageBitmap(UserData.getCover(UserData.getUserName()));
        }
        //用户点击头像设置头像
        myCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        // 选择图片
                        Intent intent = new Intent(Intent.ACTION_PICK, null);
                        intent.setType("image/*");
                        startActivityForResult(intent, 0x1);
                }
        });
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
                mWeiboDialog = WeiboDialogUtils.createLoadingDialog(getActivity(), "加载动态中");
                mHandler.sendEmptyMessageDelayed(1, 1000);
                UserData.isNewTimeInit=false;
                UserData.isAllMomentsGet=false;
                UserData.setOldForumTime(-1);
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
            int typeID = -1;
            int grade = -1;
            int exp = -1;
            if(!UserData.getElfWithId((int)UserData.getUserInfo().get("pet")).isEmpty()) {
                typeID = (int) (UserData.getElfWithId((int) UserData.getUserInfo().get("pet")).get("typeID"));
                grade = (int) UserData.getElfWithId(typeID).get("grade");
                exp = (int) UserData.getElfWithId(typeID).get("exp");
            }
            username.setText(UserData.getUserName());
            elfname.setText(ElfSourceController.getName(typeID,grade));
            level.setText("lv."+(exp/100+1));
            fightPoint.setText(""+ElfSourceController.getPower(typeID,exp/100+1,grade));
            elfImage.setBackgroundResource(ElfSourceController.getBackgroundWithLevel(typeID,grade));
            myExp.setText(UserData.getExp()+"");
            if(UserData.getCover(UserData.getUserName())==null)
                myCover.setBackgroundResource(R.drawable.pikachu);
            else {
                myCover.setBackgroundResource(R.drawable.bg_blue);
                myCover.setImageBitmap(UserData.getCover(UserData.getUserName()));
            }
        }
    }

    // 响应startActivityForResult，获取图片路径
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 0x1 && resultCode == RESULT_OK) {
            if (data != null) {
                Bitmap bm;
                ContentResolver resolver = getActivity().getContentResolver();
                try {
                    Uri uri = data.getData();
                    // 这里开始的第二部分，获取图片的路径：
                    String[] proj = { MediaStore.Images.Media.DATA };
                    Cursor cursor = getActivity().managedQuery(uri, proj, null, null, null);
                    // 按我个人理解 这个是获得用户选择的图片的索引值
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    // 最后根据索引值获取图片路径
                    String photoPath = cursor.getString(column_index);
                    Log.d("pic1",photoPath);
                    Bitmap term=BitmapFactory.decodeFile(photoPath);
                    Bitmap bp=BitmapUtils.BitmapCompress(term);
                    Log.d("pic2",BitmapUtils.bitmapToBase64(bp));
                    HttpHandler.changeCover(UserData.getUserName(),BitmapUtils.bitmapToBase64(bp));
                    myCover.setBackgroundResource(R.drawable.bg_blue);
                    myCover.setImageBitmap(bp);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }


}
