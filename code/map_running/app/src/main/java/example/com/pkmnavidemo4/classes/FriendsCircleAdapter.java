package example.com.pkmnavidemo4.classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import example.com.pkmnavidemo4.R;

public class FriendsCircleAdapter extends RecyclerView.Adapter {

    private List<Map> moments;
    private Context mContext;

    public FriendsCircleAdapter(Context context,List<Map> getmoments) {
        mContext = context;
        moments=getmoments;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContentViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_friends_circle, parent, false));
    }

    @Override
    public int getItemCount() {
        return moments.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ContentViewHolder viewHolder = (ContentViewHolder) holder;
        viewHolder.mAvatarView.setBackgroundResource(R.drawable.pikachu);
        viewHolder.mNameTv.setText(moments.get(position).get("username")+"");
        viewHolder.mContentTv.setText(moments.get(position).get("content")+"");
        //int count = (int) (Math.random() * 9);

        viewHolder.mImageLayout.setImageUrls((List<String>)moments.get(position).get("pics"));
    }


    private static class ContentViewHolder extends RecyclerView.ViewHolder {

        private ImageView mAvatarView;
        private TextView mNameTv;
        private TextView mContentTv;
        private FriendsCircleImageLayout mImageLayout;

        public ContentViewHolder(View itemView) {
            super(itemView);
            mAvatarView = itemView.findViewById(R.id.friends_circle_item_Image_view);
            mNameTv = itemView.findViewById(R.id.friends_circle_item_name_tv);
            mContentTv = itemView.findViewById(R.id.friends_circle_item_content_tv);
            mImageLayout = itemView.findViewById(R.id.friends_circle_item_image_layout);
        }
    }
}