package example.com.pkmnavidemo4.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.com.pkmnavidemo4.R;

public class RestrainModeFragment extends Fragment {
    public RestrainModeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment_fouth
        return inflater.inflate(R.layout.fg_running_restrain_mode, container, false);
    }
}
