package com.clkj.insurance.activities.comSearch;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.clkj.insurance.R;
import com.clkj.insurance.activities.comWaybill.ActWaybillDetail;
import com.clkj.insurance.base.BaseActivity;
import com.lf.tempcore.tempBase.adapter.ListBaseAdapter;
import com.lf.tempcore.tempBase.adapter.SuperViewHolder;
import com.lf.tempcore.tempRecyclerView.TempRVDividerDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 搜索
 */
public class ActSearch extends BaseActivity {

    @Bind(R.id.act_search_recycler)RecyclerView act_search_recycler;
    private ListBaseAdapter<String> baseAdapter;
    //临时数据
    private String[] orders={"YCEG204454231456755421","YCEG204454231456755422","YCEG204454231456755423","YCEG204454231456755424"};
    private List<String> data=new ArrayList<>();
    private AlertDialog mDailog;
    private TextView frag_order_call_phone;
    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_search);
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {

    }
    /**
     * 加载数据
     */
    private void initRv() {
        for (int i = 0; i < orders.length; i++) {
            data.add(orders[i]);
        }
        act_search_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        act_search_recycler.addItemDecoration(new TempRVDividerDecoration(Color.parseColor("#f3f3f3"),20));
        baseAdapter=new ListBaseAdapter<String>(this) {
            @Override
            public int getLayoutId() {
                return R.layout.frag_home_recycler_layout;
            }

            @Override
            public void onBindItemHolder(SuperViewHolder holder, int position) {
                com.rey.material.widget.TextView frag_home_recycler_start = holder.getView(R.id.frag_home_recycler_start);//起运
                com.rey.material.widget.TextView frag_home_recycler_detail = holder.getView(R.id.frag_home_recycler_detail);//详情
                frag_home_recycler_start.setVisibility(View.GONE);
                frag_home_recycler_detail.setVisibility(View.VISIBLE);
                holder.setText(R.id.frag_home_recycler_waybill_number,data.get(position));//运单号
                ImageView frag_home_recycler_traders_phone = holder.getView(R.id.frag_home_recycler_traders_phone);//贸易商电话
                //贸易商电话点击
                frag_home_recycler_traders_phone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog();
                    }
                });
                //详情点击
                frag_home_recycler_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(ActSearch.this, ActWaybillDetail.class));
                    }
                });

            }
        };
        baseAdapter.setDataList(data);
        act_search_recycler.setAdapter(baseAdapter);
    }
    /**
     * 拨打电话的弹窗
     */
    private void showDialog() {
        View v =getLayoutInflater().inflate(R.layout.making_a_call_dailog_layout,null);
        frag_order_call_phone = (TextView) v.findViewById(R.id.frag_order_call_phone);
        mDailog = new AlertDialog.Builder(this).setView(v).create();
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
                    if (ActivityCompat.checkSelfPermission(ActSearch.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((ActSearch.this), new String[]{Manifest.permission.CALL_PHONE}, 100);
                    }
                    startActivity(i);
                    break;
            }
        }
    }

    @Override
    @OnClick({R.id.toolbar_back,R.id.toolbar_menu_tv})
    protected void OnViewClicked(View v) {
        switch (v.getId()){
            case R.id.toolbar_back://返回键
                finish();
                break;
            case R.id.toolbar_menu_tv://搜索
                    act_search_recycler.setVisibility(View.VISIBLE);
                    initRv();
                break;
        }
    }

    @Override
    public void setToolbar(Toolbar toolbarTop) {
        toolbarTop.findViewById(R.id.toolbar_title).setVisibility(View.GONE);
        toolbarTop.findViewById(R.id.toolbar_search_layout).setVisibility(View.VISIBLE);
        TextView toolbar_menu_tv = (TextView) toolbarTop.findViewById(R.id.toolbar_menu_tv);
        toolbar_menu_tv.setVisibility(View.VISIBLE);
        toolbar_menu_tv.setText("搜索");
    }
}
