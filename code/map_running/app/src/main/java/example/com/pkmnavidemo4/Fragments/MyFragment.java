package example.com.pkmnavidemo4.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import example.com.pkmnavidemo4.R;

/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment extends Fragment {

    private String content;
    public MyFragment() {
        this.content = "nothing here";
    }
    public MyFragment(String content) {
        this.content = content;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content,container,false);
        TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        txt_content.setText(content);
        return view;
    }
}
