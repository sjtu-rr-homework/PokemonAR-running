package example.com.pkmnavidemo4.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import example.com.pkmnavidemo4.MapActivity;
import example.com.pkmnavidemo4.R;
import example.com.pkmnavidemo4.RecordActivity;
import example.com.pkmnavidemo4.RecordOnMapActivity;
import example.com.pkmnavidemo4.RegisterActivity;

public class RunningFragment extends Fragment {
    private TextView toRecord;
    private Button button;
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
        button = (Button) getActivity().findViewById(R.id.button3);
        toRecord=(TextView)getActivity().findViewById(R.id.fg_running_textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"开始跑步",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), MapActivity.class);
                startActivity(intent);
            }
        });
        toRecord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getActivity(), RecordActivity.class);
                startActivity(intent);
            }
        });
    }
}
