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

public class FreeModeFragment extends Fragment {
    private Button run;
    public FreeModeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment_fouth
        return inflater.inflate(R.layout.fg_running_free_mode, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        run = (Button) getActivity().findViewById(R.id.fg_running_freerun);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(),"开始自由跑",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), MapActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);
            }
        });
    }
}