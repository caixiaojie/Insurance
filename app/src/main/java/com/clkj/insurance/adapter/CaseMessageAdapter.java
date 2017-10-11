package com.clkj.insurance.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.clkj.insurance.R;
import com.clkj.insurance.response.ResponseCaseMessageList;
import com.lf.tempcore.tempBase.adapter.ListBaseAdapter;
import com.lf.tempcore.tempBase.adapter.SuperViewHolder;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/7/21.
 */

public class CaseMessageAdapter extends ListBaseAdapter<ResponseCaseMessageList.ResultBean.PageRecordBean> {
    private Context mContext;
    private ResponseCaseMessageList.ResultBean.PageRecordBean item;
    public CaseMessageAdapter(Context context) {
        super(context);
        this.mContext=context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.frag_system_message_recycler_layout;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        item=mDataList.get(position);
        holder.setText(R.id.message_title,item.getMffoContent());//消息内容
        ImageView isRead_img = holder.getView(R.id.isRead_img);
        String mmesCreateTime = item.getMfflReadTime();//读取时间   读取时间为空时，记为未读
        if (!TextUtils.isEmpty(mmesCreateTime)){
            long time=Long.parseLong(mmesCreateTime);
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String finalTime = dateFormat.format(time);
            holder.setText(R.id.message_time,finalTime);
            isRead_img.setVisibility(View.GONE);
        }else {
            holder.setText(R.id.message_time,"2017-07-28 09:30");
            isRead_img.setVisibility(View.VISIBLE);
        }
    }
}

