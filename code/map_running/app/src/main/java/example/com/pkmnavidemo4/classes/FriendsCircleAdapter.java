package example.com.pkmnavidemo4.classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import example.com.pkmnavidemo4.Bitmap.BitmapUtils;
import example.com.pkmnavidemo4.R;

public class FriendsCircleAdapter extends RecyclerView.Adapter {

    private List<Map> moments;
    private Context mContext;

    // 普通布局
    private final int TYPE_ITEM = 1;
    // 脚布局
    private final int TYPE_FOOTER = 2;
    // 当前加载状态，默认为加载完成
    private static int loadState = 2;
    // 正在加载
    public static final int LOADING = 1;
    // 加载完成
    public static final int LOADING_COMPLETE = 2;
    // 加载到底
    public static final int LOADING_END = 3;


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    // 如果当前是footer的位置，那么该item占据2个单元格，正常情况下占据1个单元格
                    return getItemViewType(position) == TYPE_FOOTER ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }
    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为FooterView
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public FriendsCircleAdapter(Context context,List<Map> getmoments) {
        mContext = context;
        moments=getmoments;
    }

    public void addEnd( List<Map> getmoments) {
        for(int i=0;i<getmoments.size();i++)
            moments.add(getmoments.get(i));
        notifyDataSetChanged();
    }

    public void addStart( List<Map> getmoments) {
        for(int i=0;i<getmoments.size();i++) {
            moments.add(0, getmoments.get(i));
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            return new ContentViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_friends_circle, parent, false));

        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_refresh_footer, parent, false);
            return new FootViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return moments.size()+1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContentViewHolder) {
            ContentViewHolder viewHolder = (ContentViewHolder) holder;
            if(moments.get(position).get("cover")==null){
                viewHolder.mAvatarView.setBackgroundResource(R.drawable.bg_blue);
                viewHolder.mAvatarView.setBackgroundResource(R.drawable.pikachu);
            }
            else {
                Log.d("dsds",moments.get(position).get("cover").toString());
                viewHolder.mAvatarView.setBackgroundResource(R.drawable.bg_blue);
                viewHolder.mAvatarView.setImageBitmap(BitmapUtils.base64ToBitmap(moments.get(position).get("cover").toString()));
            }
            viewHolder.mNameTv.setText(moments.get(position).get("username")+"");
            viewHolder.mContentTv.setText(moments.get(position).get("content")+"");
            //int count = (int) (Math.random() * 9);
            viewHolder.mImageLayout.setImageUrls((List<String>)moments.get(position).get("pics"));
            viewHolder.mTimeTv.setText(moments.get(position).get("time").toString().substring(0,19));
        } else if (holder instanceof FootViewHolder) {
            FootViewHolder footViewHolder = (FootViewHolder) holder;
            switch (loadState) {
                case LOADING: // 正在加载
                    footViewHolder.pbLoading.setVisibility(View.VISIBLE);
                    footViewHolder.tvLoading.setVisibility(View.VISIBLE);
                    footViewHolder.tvLoading.setText("正在加载");
                    footViewHolder.llEnd.setVisibility(View.GONE);
                    break;

                case LOADING_COMPLETE: // 加载完成
                    footViewHolder.pbLoading.setVisibility(View.INVISIBLE);
                    footViewHolder.tvLoading.setVisibility(View.INVISIBLE);
                    footViewHolder.llEnd.setVisibility(View.GONE);
                    break;

                case LOADING_END: // 加载到底
                    footViewHolder.pbLoading.setVisibility(View.VISIBLE);
                    footViewHolder.tvLoading.setVisibility(View.VISIBLE);
                    footViewHolder.tvLoading.setText("无法加载更多");
                    footViewHolder.llEnd.setVisibility(View.GONE);
                    break;

                default:
                    break;
            }
        }
    }

    private class FootViewHolder extends RecyclerView.ViewHolder {

        ProgressBar pbLoading;
        TextView tvLoading;
        LinearLayout llEnd;

        FootViewHolder(View itemView) {
            super(itemView);
            pbLoading = (ProgressBar) itemView.findViewById(R.id.pb_loading);
            tvLoading = (TextView) itemView.findViewById(R.id.tv_loading);
            llEnd = (LinearLayout) itemView.findViewById(R.id.ll_end);
        }
    }

    /**
     * 设置上拉加载状态
     *
     * 0.正在加载 1.加载完成 2.加载到底
     */
    public void setLoadState(int getLoadState) {
        loadState = getLoadState;
        notifyDataSetChanged();
    }

    private static class ContentViewHolder extends RecyclerView.ViewHolder {

        private ImageView mAvatarView;
        private TextView mNameTv;
        private TextView mContentTv;
        private FriendsCircleImageLayout mImageLayout;
        private TextView mTimeTv;
        public ContentViewHolder(View itemView) {
            super(itemView);
            mAvatarView = itemView.findViewById(R.id.friends_circle_item_Image_view);
            mNameTv = itemView.findViewById(R.id.friends_circle_item_name_tv);
            mContentTv = itemView.findViewById(R.id.friends_circle_item_content_tv);
            mImageLayout = itemView.findViewById(R.id.friends_circle_item_image_layout);
            mTimeTv=itemView.findViewById(R.id.friends_circle_item_content_time);
        }
    }

}