package com.clkj.insurance.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clkj.insurance.R;
import com.clkj.insurance.fragment.FragEmergency;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者：ikkong （ikkong@163.com），修改 jeasonlzy（廖子尧）
 * 版    本：1.0
 * 创建日期：2016/5/19
 * 描    述：
 * 修订历史：微信图片选择的Adapter, 感谢 ikkong 的提交
 * ================================================
 */
public class ImagePickerAdapter extends RecyclerView.Adapter<ImagePickerAdapter.SelectedPicViewHolder> {
    private int maxImgCount;
    private Context mContext;
    private ArrayList<ImageItem> mData;
    private LayoutInflater mInflater;
    private OnRecyclerViewItemClickListener listener;
    private boolean isAdded;   //是否额外添加了最后一个图片

    private String tag;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
        void ondeteleChange(ArrayList<ImageItem> images);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;

    }

    public void setImages(List<ImageItem> data) {
        mData = new ArrayList<>(data);
        if (getItemCount() < maxImgCount) {
            mData.add(new ImageItem());
            isAdded = true;
        } else {
            isAdded = false;
        }
        notifyDataSetChanged();
    }

    public List<ImageItem> getImages() {
        //由于图片未选满时，最后一张显示添加图片，因此这个方法返回真正的已选图片
        if (isAdded) return new ArrayList<>(mData.subList(0, mData.size() - 1));
        else return mData;
    }

    public ImagePickerAdapter(Context mContext, List<ImageItem> data, int maxImgCount,String tag) {
        this.mContext = mContext;
        this.maxImgCount = maxImgCount;
        this.mInflater = LayoutInflater.from(mContext);
        this.tag=tag;
        setImages(data);
    }

    @Override
    public SelectedPicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SelectedPicViewHolder(mInflater.inflate(R.layout.report_recycler_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(SelectedPicViewHolder holder, final int position) {
        holder.bind(position);
        holder.imageView_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.remove(position);
                mNewData= mData;
                if (listener != null) listener.ondeteleChange(mNewData);
            }
        });
        switch (tag){
            case "report"://报案
                holder.act_name_check_id_mentou_license_layout.setLayoutParams(new LinearLayout.LayoutParams(130,130));
                holder.photo_layout.setBackgroundResource(R.mipmap.qy_2);
                break;
            case "information"://认证信息
                holder.act_name_check_id_mentou_license_layout.setLayoutParams(new LinearLayout.LayoutParams(300,173));
                holder.photo_layout.setBackgroundResource(R.mipmap.rz);
                break;
        }
       // LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
      //  int margin_horizontal = mContext.getResources().getDimensionPixelSize(R.dimen.padding_size_default);
     /*   if(position % 1 == 0) {
            lp.setMargins(margin_horizontal/3,margin_horizontal/3, margin_horizontal/3, margin_horizontal/3);
        } else {
            lp.setMargins(margin_horizontal/3, margin_horizontal/3, margin_horizontal/3, margin_horizontal/3);
        }*/
      //  holder.itemView.setLayoutParams(lp);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public class SelectedPicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView_delete;
        private SimpleDraweeView iv_img;
        private int clickPosition;
        private TextView frag_up_video_mine_add_video_text;
        private RelativeLayout act_name_check_id_mentou_license_layout;
        private LinearLayout photo_layout;
        public SelectedPicViewHolder(View itemView) {
            super(itemView);
            iv_img = (SimpleDraweeView) itemView.findViewById(R.id.frag_up_video_mine_add_video_iv);
            act_name_check_id_mentou_license_layout= (RelativeLayout) itemView.findViewById(R.id.act_name_check_id_mentou_license_layout);
            photo_layout= (LinearLayout) itemView.findViewById(R.id.photo_layout);
            frag_up_video_mine_add_video_text = (TextView) itemView.findViewById(R.id.frag_up_video_mine_add_video_text);
            imageView_delete = (ImageView) itemView.findViewById(R.id.imageView_delete);
        }

        public void bind( int position) {
            //设置条目的点击事件
            itemView.setOnClickListener(this);
            //根据条目位置设置图片
            ImageItem item = mData.get(position);
            if (isAdded && position == getItemCount() - 1) {
              //  iv_img.setImageResource(R.drawable.selector_image_add);
                clickPosition = FragEmergency.IMAGE_ITEM_ADD;
                frag_up_video_mine_add_video_text.setVisibility(View.VISIBLE);
                ImagePicker.getInstance().getImageLoader().fresscoImage((Activity) mContext,"", iv_img, 400, 400);
                imageView_delete.setVisibility(View.GONE);
            } else {
                frag_up_video_mine_add_video_text.setVisibility(View.GONE);
                ImagePicker.getInstance().getImageLoader().fresscoImage((Activity) mContext, item.path, iv_img, 400, 400);
                clickPosition = position;
                imageView_delete.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onClick(View v) {
            if (listener != null) listener.onItemClick(v, clickPosition);
        }
    }
    private ArrayList<ImageItem> mNewData=new ArrayList<>();
}