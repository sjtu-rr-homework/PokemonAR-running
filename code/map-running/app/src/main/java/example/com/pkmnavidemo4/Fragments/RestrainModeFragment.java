package example.com.pkmnavidemo4.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import example.com.pkmnavidemo4.MapActivity;
import example.com.pkmnavidemo4.R;
import example.com.pkmnavidemo4.classes.UserData;

public class RestrainModeFragment extends Fragment {
    private Button run;
    public RestrainModeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment_fouth
        return inflater.inflate(R.layout.fg_running_restrain_mode, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        run = (Button) getActivity().findViewById(R.id.fg_running_restrainrun);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(),"开始定点跑",Toast.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(), ""+UserData.place_choice,Toast.LENGTH_SHORT).show();
                if(UserData.place_choice==0){
                Intent intent=new Intent(getActivity(), MapActivity.class);
                intent.putExtra("type",0);
                startActivity(intent);
                }
                else {
                    Toast.makeText(getActivity(),"选择“自由地点”时，无法进行约束跑",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
