package example.com.pkmnavidemo4.classes;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import example.com.pkmnavidemo4.ElfDetailsActivity;;
import example.com.pkmnavidemo4.R;

public class FightTextAdapter extends RecyclerView.Adapter<FightTextAdapter.ViewHolderA> {
    private Context mContext;
    private List<Spanned> mList;
    public FightTextAdapter(Context context, List<Spanned> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public ViewHolderA onCreateViewHolder(ViewGroup parent, int viewType) {
        //此处动态加载ViewHolder的布局文件并返回holder
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycleview_item_fight, parent, false);
        ViewHolderA holderA = new ViewHolderA(view);
        return holderA;
    }

    @Override
    public void onBindViewHolder(ViewHolderA holder, final int position) {
        //此处设置Item中view的数据
        holder.mTextView.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        //生成的item的数量
        return mList.size();
    }
    public void myclear(){

    }
    public void addData(Spanned content){
        mList.add(1, content);
        //通知适配器item内容插入
        notifyItemInserted(1);
    }

    //Item的ViewHolder以及item内部布局控件的id绑定
    class ViewHolderA extends RecyclerView.ViewHolder{
        TextView mTextView;
        public ViewHolderA(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.recycle_textview);
        }
    }
}