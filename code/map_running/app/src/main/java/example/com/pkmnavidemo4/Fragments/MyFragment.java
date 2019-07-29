package example.com.pkmnavidemo4.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import example.com.pkmnavidemo4.FriendPageActivity;
import example.com.pkmnavidemo4.R;
import example.com.pkmnavidemo4.classes.ElfSourceController;
import example.com.pkmnavidemo4.classes.HttpHandler;
import example.com.pkmnavidemo4.classes.UserData;

/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment extends Fragment {

    private Button checkfriend;
    ImageView elfImage;
    TextView username;
    int elfId=-1;
    TextView elfname;
    TextView level;
    TextView fightPoint;
    Button addfriend;
    Button fightfriend;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fg_content,container,false);
        checkfriend=(Button)view.findViewById(R.id.fg_button);
        checkfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), FriendPageActivity.class);
                startActivity(intent);
            }
        });
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
        return view;
    }
    @Override
    public void onHiddenChanged(boolean hidden){
        super.onHiddenChanged(hidden);
        int typeID=(int)(UserData.getElfWithId((int)UserData.getUserInfo().get("pet")).get("typeID"));
        int grade=(int)UserData.getElfWithId(typeID).get("grade");
        int exp=(int)UserData.getElfWithId(typeID).get("exp");
        username.setText(UserData.getUserName());
        elfname.setText(ElfSourceController.getName(typeID,grade));
        level.setText(""+(exp/100+1));
        fightPoint.setText(""+ElfSourceController.getPower(typeID,exp/100+1,grade));
        elfImage.setBackgroundResource(ElfSourceController.getBackgroundWithLevel(typeID,grade));
    }
}
