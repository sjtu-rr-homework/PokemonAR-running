package example.com.pkmnavidemo4.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import example.com.pkmnavidemo4.R;
import example.com.pkmnavidemo4.classes.TestRecycleViewAdapter;

public class ElfsFragment extends Fragment {
    private List<String> list;
    public ElfsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
       View view = inflater.inflate(R.layout.elfs_fg_content,container,false);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.elf_fg_recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        initData();
        //实例化并传输数据给adapter
        TestRecycleViewAdapter adapter = new TestRecycleViewAdapter(getActivity().getApplicationContext(), list);
        mRecyclerView.setAdapter(adapter);
        return view;
    }

    /**
     *  添加数据
     * */
    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("elf" + i);
        }
    }
    /*private Button arButton;
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
                Intent intent = new Intent(getActivity(), SceneformActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("variety", 1);
                intent.putExtras(bundle);
//这里一定要获取到所在Activity再startActivity()；
                getActivity().startActivity(intent);
            }
        });
        arButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SceneformActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("variety", 2);
                intent.putExtras(bundle);
//这里一定要获取到所在Activity再startActivity()；
                getActivity().startActivity(intent);
            }
        });
        return view;
    }*/
}
