package example.com.pkmnavidemo4.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import example.com.pkmnavidemo4.FriendPageActivity;
import example.com.pkmnavidemo4.R;

/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment extends Fragment {

    private Button checkfriend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content,container,false);
        checkfriend=(Button)view.findViewById(R.id.fg_button);
        checkfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //HttpHandler.getFriend();
                Intent intent=new Intent(getActivity(), FriendPageActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
