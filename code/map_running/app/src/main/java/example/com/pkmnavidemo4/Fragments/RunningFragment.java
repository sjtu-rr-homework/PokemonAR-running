package example.com.pkmnavidemo4.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import example.com.pkmnavidemo4.MapActivity;
import example.com.pkmnavidemo4.R;

public class RunningFragment extends Fragment {
    //private String content;

    //public RunningFragment(String content) {
        //this.content = content;
    //}

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
        Button button = (Button) getActivity().findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"我被点击了",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), MapActivity.class);
                startActivity(intent);
            }
        });
    }
}
