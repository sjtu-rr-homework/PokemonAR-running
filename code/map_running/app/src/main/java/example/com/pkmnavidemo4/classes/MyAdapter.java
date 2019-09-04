package example.com.pkmnavidemo4.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import example.com.pkmnavidemo4.R;

public class MyAdapter extends BaseAdapter {
    //数据集合list
    List<Map<String,Object>> list;
    //添加反射器
    LayoutInflater inflater;
    //构造器 上下文
    public MyAdapter(Context context){
        inflater=LayoutInflater.from(context);
    }
    //传入数据集合
    public void setList( List<Map<String,Object>> list){
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();//lisview的长度，如果是null啥也不显示
    }
    @Override
    public Object getItem(int position) {//没什么用
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {//没什么用
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //反射listview布局，后面null先默认如此，没啥用
        View view=inflater.inflate(R.layout.listview,null);
        //获取控件位置
        TextView time=(TextView)view.findViewById(R.id.listview_title);
        TextView length=(TextView)view.findViewById(R.id.listview_content1);
        TextView constant=(TextView)view.findViewById(R.id.listview_content2);
        //填充信息
        Map map=list.get(position);
        time.setText((String)map.get("time"));
        length.setText((String) map.get("length"));
        constant.setText((String) map.get("constant"));
        //将含有信息的view返回到ListView
        return view;
    }
}
