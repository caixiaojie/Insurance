package com.clkj.insurance.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.clkj.insurance.R;
import com.clkj.insurance.activities.ActStart;
import com.clkj.insurance.response.ResponseUserSwayBillList;
import com.lf.tempcore.tempBase.adapter.ListBaseAdapter;
import com.lf.tempcore.tempBase.adapter.SuperViewHolder;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/7/21.
 */

public class UserSwayBillAdapter extends ListBaseAdapter<ResponseUserSwayBillList.ResultBean.PageRecordBean> {
    private AlertDialog mDailog;
    private TextView frag_order_call_phone;
    private Activity activity;
    private LayoutInflater inflater;
    private Context mContext;
    private ResponseUserSwayBillList.ResultBean.PageRecordBean item;
    private String phone;//贸易商电话
    public UserSwayBillAdapter(Context context,Activity activity) {
        super(context);
        this.mContext=context;
        inflater=LayoutInflater.from(mContext);
        this.activity=activity;
    }

    @Override
    public int getLayoutId() {
        return R.layout.frag_home_recycler_layout;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        item=mDataList.get(position);
        com.rey.material.widget.TextView frag_home_recycler_start = holder.getView(R.id.frag_home_recycler_start);//起运
        holder.setText(R.id.frag_home_recycler_waybill_number,item.getSwayBillCode());//运单号
        holder.setText(R.id.frag_home_recycler_departure,item.getSwayActualFromAddress());//起运地
        holder.setText(R.id.frag_home_recycler_destination,item.getSwayActualEndAdress());//目的地
        String swayFromTime = item.getSwayFromTime();
        if (!TextUtils.isEmpty(swayFromTime)){
            long time=Long.parseLong(swayFromTime);
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
            String formatTime = dateFormat.format(time);
            holder.setText(R.id.frag_home_recycler_start_time,formatTime);//起运时间
        }

        holder.setText(R.id.frag_home_recycler_traders,item.getSysDeclarationForm().getSdfoUserName());//贸易商
        phone=item.getSysDeclarationForm().getSdfoUserPhone();//贸易商电话
        ImageView frag_home_recycler_traders_phone = holder.getView(R.id.frag_home_recycler_traders_phone);//贸易商电话
        //贸易商电话点击
        frag_home_recycler_traders_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        //起运点击
        frag_home_recycler_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, ActStart.class);
                intent.putExtra("swayId",item.getSwayId());
                mContext.startActivity(intent);
            }
        });

    }

    /**
     * 拨打电话的弹窗
     */
    private void showDialog() {
        View v =inflater.inflate(R.layout.making_a_call_dailog_layout,null);
        frag_order_call_phone = (TextView) v.findViewById(R.id.frag_order_call_phone);
        frag_order_call_phone.setText(phone);
        mDailog = new AlertDialog.Builder(mContext).setView(v).create();
        mDailog.getWindow().setBackgroundDrawableResource(R.color.color_1);
        mDailog.show();
        android.widget.TextView cancel = (android.widget.TextView) v.findViewById(R.id.making_a_call_cancel);
        android.widget.TextView sure = (android.widget.TextView) v.findViewById(R.id.making_a_call_sure);
        MyListener listener=new MyListener();
        cancel.setOnClickListener(listener);
        sure.setOnClickListener(listener);

    }
    /**
     * 拨打电话监听事件
     */
    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.making_a_call_cancel://取消
                    if (mDailog!=null && mDailog.isShowing()){
                        mDailog.dismiss();
                    }
                    break;
                case R.id.making_a_call_sure://确定
                    //调用系统的拨号服务实现电话拨打功能
                    //封装一个拨打电话的intent，并且将电话号码包装成一个Uri对象传入
                    Uri uri = Uri.parse("tel:" + frag_order_call_phone.getText().toString().trim());
                    Intent i = new Intent(Intent.ACTION_CALL, uri);
                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, 100);
                    }
                    mContext.startActivity(i);
                    break;
            }
        }
    }



}

