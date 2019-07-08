package example.com.pkmnavidemo4.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import example.com.pkmnavidemo4.MainActivity;
import example.com.pkmnavidemo4.R;
import example.com.pkmnavidemo4.SceneformActivity;

public class ElfsFragment extends Fragment {

    private Button arButton;
    private Button arButton2;
    public ElfsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.elfs_fg_content,container,false);
        arButton=view.findViewById(R.id.button1);
        arButton2=view.findViewById(R.id.button2);
        arButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("variety", 1);
                intent=new Intent(getActivity(), SceneformActivity.class);
                startActivity(intent);
            }
        });
        arButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("variety", 2);
                intent=new Intent(getActivity(), SceneformActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
