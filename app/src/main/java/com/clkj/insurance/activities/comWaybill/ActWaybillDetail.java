package com.clkj.insurance.activities.comWaybill;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clkj.insurance.R;
import com.clkj.insurance.base.BaseActivity;
import com.clkj.insurance.response.ResponseBillDetail;
import com.clkj.insurance.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;

import java.text.SimpleDateFormat;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 运单详情
 */
public class ActWaybillDetail extends BaseActivity implements ViewActWaybillDetailI{
    @Bind(R.id.report_record_layout)LinearLayout report_record_layout;//下面那个报案记录
    @Bind(R.id.report_record_img)ImageView report_record_img;
    @Bind(R.id.frag_home_recycler_waybill_number)TextView frag_home_recycler_waybill_number;//运单号
    @Bind(R.id.frag_home_recycler_departure)TextView frag_home_recycler_departure;//起运地
    @Bind(R.id.frag_home_recycler_destination)TextView frag_home_recycler_destination;//目的地
    @Bind(R.id.frag_home_recycler_start_time)TextView frag_home_recycler_start_time;//起运时间
    @Bind(R.id.frag_home_recycler_arrive_time)TextView frag_home_recycler_arrive_time;//到达时间
    @Bind(R.id.frag_home_recycler_traders)TextView frag_home_recycler_traders;//贸易商
    @Bind(R.id.qiyun_jilu_time)TextView qiyun_jilu_time;//起运记录时间
    @Bind(R.id.chongshui_jilu_time)TextView chongshui_jilu_time;//冲水记录时间
    @Bind(R.id.baoan_jilu_time)TextView baoan_jilu_time;//报案记录时间
    /*@Bind(R.id.baoan_address)TextView baoan_address;//报案地点
    @Bind(R.id.img1)ImageView img1;//第一张图片
    @Bind(R.id.img2)ImageView img2;//第二张图片
    @Bind(R.id.renbao_chuli_yijian)TextView renbao_chuli_yijian;//人保处理意见
    @Bind(R.id.songda_time1)TextView songda_time1;//人保处理意见的送达时间
    @Bind(R.id.maoyishang_chuli_yijian)TextView maoyishang_chuli_yijian;//贸易商处理意见
    @Bind(R.id.songda_time2)TextView songda_time2;//贸易商处理意见的送达时间
    @Bind(R.id.me_to_renbao)TextView me_to_renbao;//我给人保处理意见
    @Bind(R.id.songda_time3)TextView songda_time3;//我给人保处理意见的送达时间
    @Bind(R.id.me_to_maoyishang)TextView me_to_maoyishang;//我给贸易商处理意见
    @Bind(R.id.songda_time4)TextView songda_time4;//我给贸易商处理意见的送达时间*/
    private boolean isChecked=false;
    private AlertDialog mDailog1;
    private TextView frag_order_call_phone;
    private String swayId;//运单编号
    private PreActWaybillDetailI waybillDetailI;

    private String phone;//贸易商电话
    private String caseId;//案件编号

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_waybill_detail);
        swayId=getIntent().getStringExtra("swayId");

    }

    @Override
    protected void findViews() {
        waybillDetailI=new PreActWaybillDetailImpl(this);
        //访问接口
        waybillDetailI.getBillDetailSuccess(TempLoginConfig.sf_getSueid(),"1",TempLoginConfig.sf_getPassWord(),swayId);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {

    }

    @Override
    @OnClick({R.id.toolbar_back, R.id.frag_home_recycler_traders_phone,R.id.baoan_jilu_layout})
    protected void OnViewClicked(View v) {
        switch (v.getId()){
            case R.id.toolbar_back://返回键
                finish();
                break;
            case R.id.frag_home_recycler_traders_phone://拨打电话
                showDialog();
                break;
            case R.id.baoan_jilu_layout://报案记录
                Intent intent=new Intent(this,ActReportRecord.class);
                intent.putExtra("caseId",caseId);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void setToolbar(Toolbar toolbarTop) {
        TextView toolbar_title = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        toolbar_title.setText("运单详情");
    }
    /**
     * 拨打电话的弹窗
     */
    private void showDialog() {
        View v =getLayoutInflater().inflate(R.layout.making_a_call_dailog_layout,null);
        frag_order_call_phone = (TextView) v.findViewById(R.id.frag_order_call_phone);
        frag_order_call_phone.setText(phone);
        mDailog1 = new AlertDialog.Builder(this).setView(v).create();
        mDailog1.getWindow().setBackgroundDrawableResource(R.color.color_1);
        mDailog1.show();
        android.widget.TextView cancel = (android.widget.TextView) v.findViewById(R.id.making_a_call_cancel);
        android.widget.TextView sure = (android.widget.TextView) v.findViewById(R.id.making_a_call_sure);
        MyListener listener=new MyListener();
        cancel.setOnClickListener(listener);
        sure.setOnClickListener(listener);

    }

    @Override
    public void getBillDetailSuccess(ResponseBillDetail data) {
        frag_home_recycler_waybill_number.setText(data.getResult().getSwayBillCode());//运单号
        frag_home_recycler_departure.setText(data.getResult().getSwayActualFromAddress());//起运地
        frag_home_recycler_destination.setText(data.getResult().getSwayActualEndAdress());//目的地
        String swayFromTime = data.getResult().getSwayFromTime();//起运时间
        String swayEndTime = data.getResult().getSwayEndTime();//到达时间
        long date = 0;
        long date1=0;
        if (!TextUtils.isEmpty(swayFromTime)){
            date=Long.parseLong(swayFromTime);
        }
        if (!TextUtils.isEmpty(swayEndTime)){
            date1=Long.parseLong(swayEndTime);
        }
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format = dateFormat.format(date);
        String format1 = dateFormat.format(date1);
        frag_home_recycler_start_time.setText(format);//起运时间
        frag_home_recycler_arrive_time.setText(format1);//到达时间
        frag_home_recycler_traders.setText(data.getResult().getSdfoUserName());//贸易商
        phone=data.getResult().getSdfoUserPhone();//贸易商电话
        String mbimTime = data.getResult().getStartBillRecord().getMbimTime();//起运记录时间
        if (!TextUtils.isEmpty(mbimTime)){
            long date2=Long.parseLong(mbimTime);
            String format2 = dateFormat.format(date2);
            qiyun_jilu_time.setText(format2);
        }
        String mbimTime1 = data.getResult().getWaterBillRecord().get(0).getMbimTime();//冲水记录时间
        String mbimTime2 = data.getResult().getCaseBillRecord().get(0).getMbimTime();//报案记录时间
        if (!TextUtils.isEmpty(mbimTime1)){
            long date3=Long.parseLong(mbimTime1);
            String format3 = dateFormat.format(date3);
            chongshui_jilu_time.setText(format3);
        }
        if (!TextUtils.isEmpty(mbimTime2)){
            long data4=Long.parseLong(mbimTime2);
            String format4 = dateFormat.format(data4);
            baoan_jilu_time.setText(format4);
        }
//        caseId=data.getResult().getStartBillRecord().getMbimCaseId();//案件编号
        caseId=data.getResult().getCaseBillRecord().get(0).getMbimCaseId();



    }

    @Override
    public void toast(String message) {
        showToast(message);
    }

    @Override
    public void showConntectError(ExceptionEngine.ApiException e) {
        showToast(e.message);
    }

    /**
     * 拨打电话监听事件
     */
    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.making_a_call_cancel://取消
                    if (mDailog1!=null && mDailog1.isShowing()){
                        mDailog1.dismiss();
                    }
                    break;
                case R.id.making_a_call_sure://确定
                    //调用系统的拨号服务实现电话拨打功能
                    //封装一个拨打电话的intent，并且将电话号码包装成一个Uri对象传入
                    Uri uri = Uri.parse("tel:" + frag_order_call_phone.getText().toString().trim());
                    Intent i = new Intent(Intent.ACTION_CALL, uri);
                    if (ActivityCompat.checkSelfPermission(ActWaybillDetail.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((ActWaybillDetail.this), new String[]{Manifest.permission.CALL_PHONE}, 100);
                    }
                    startActivity(i);
                    break;
            }
        }
    }
}
