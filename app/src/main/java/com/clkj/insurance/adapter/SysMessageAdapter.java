package com.clkj.insurance.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.clkj.insurance.R;
import com.clkj.insurance.response.ResponseSysMessageList;
import com.lf.tempcore.tempBase.adapter.ListBaseAdapter;
import com.lf.tempcore.tempBase.adapter.SuperViewHolder;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/7/21.
 */

public class SysMessageAdapter extends ListBaseAdapter<ResponseSysMessageList.ResultBean.PageRecordBean> {
    private Context mContext;
    public SysMessageAdapter(Context context) {
        super(context);
        this.mContext=context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.frag_system_message_recycler_layout;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        ResponseSysMessageList.ResultBean.PageRecordBean   item=mDataList.get(position);
        holder.setText(R.id.message_title,item.getMmesContent());//消息内容
        String isReaded = item.getMmIsReaded();//是否已读 1：未读 2：已读
        ImageView isRead_img = holder.getView(R.id.isRead_img);
        if (isReaded.equals("1")){
            isRead_img.setVisibility(View.VISIBLE);
        }else {
            isRead_img.setVisibility(View.GONE);
        }
        String mmesCreateTime = item.getMmmeReadTime();
        if (!TextUtils.isEmpty(mmesCreateTime)){
            long time=Long.parseLong(mmesCreateTime);
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String finalTime = dateFormat.format(time);
            holder.setText(R.id.message_time,finalTime);
        }else {
            holder.setText(R.id.message_time,"2017-07-28 09:30");
        }


    }
}
