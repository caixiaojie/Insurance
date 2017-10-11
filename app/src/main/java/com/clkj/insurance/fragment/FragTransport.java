package com.clkj.insurance.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.clkj.insurance.R;
import com.clkj.insurance.activities.comRecording.ActRecording;
import com.clkj.insurance.activities.comReport.ActReport;
import com.clkj.insurance.api.TempAction;
import com.clkj.insurance.base.BaseFragment;
import com.clkj.insurance.config.BaseUriConfig;
import com.clkj.insurance.listener.AppBarStateChangeListener;
import com.clkj.insurance.response.ResponseWaterRemind;
import com.clkj.insurance.response.ResponseWaybillRecordList;
import com.clkj.insurance.response.TempResponse;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * 运输
 */
public class FragTransport extends BaseFragment {

    @Bind(R.id.frag_transport_scrollview)NestedScrollView frag_transport_scrollview;
    @Bind(R.id.frag_transport_appBarLayout)AppBarLayout frag_transport_appBarLayout;
    @Bind(R.id.toolbar_layout)Toolbar toolbar_layout;
    @Bind(R.id.water_remind_content)TextView water_remind_content;//冲水提醒
    //堵车记录
    @Bind(R.id.duche_img)ImageView duche_img;
    @Bind(R.id.duche_time)TextView duche_time;
    @Bind(R.id.duche_address)TextView duche_address;
    @Bind(R.id.duche_content)TextView duche_content;
    @Bind(R.id.duche_img_down)ImageView duche_img_down;//下面那张图片
    //冲水记录
    @Bind(R.id.chongshui_img)ImageView chongshui_img;
    @Bind(R.id.chongshui_time)TextView chongshui_time;
    @Bind(R.id.chongshui_address)TextView chongshui_address;
    @Bind(R.id.chongshui_content)TextView chongshui_content;
    @Bind(R.id.chongshui_img_down)ImageView chongshui_img_down;
    //起运记录
    @Bind(R.id.qiyun_img)ImageView qiyun_img;
    @Bind(R.id.qiyun_time)TextView qiyun_time;
    @Bind(R.id.qiyun_address)TextView qiyun_address;
    @Bind(R.id.qiyun_content)TextView qiyun_content;
    @Bind(R.id.qiyun_img_down)ImageView qiyun_img_down;

    private AlertDialog mDailog;

    private String mbimBillId;//运单编号
    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frag_transport, container, false);
    }

    @Override
    protected void setListeners() {
        frag_transport_appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if( state == State.EXPANDED ) {

                    //展开状态

                }else if(state == State.COLLAPSED){

                    //折叠状态
                    toolbar_layout.setVisibility(View.VISIBLE);

                }else {

                    //中间状态
                    toolbar_layout.setVisibility(View.GONE);

                }

            }
        });


    }

    @Override
    protected void bundleValues() {
        saveWaterRemind();
        getWaybillRecordList();
    }

    /**
     * 运单过程记录
     */
    private void getWaybillRecordList() {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).getWaybillRecordList(TempLoginConfig.sf_getSueid(), "1", TempLoginConfig.sf_getPassWord()),
                new TempRemoteApiFactory.OnCallBack<ResponseWaybillRecordList>() {
                    @Override
                    public void onSucceed(ResponseWaybillRecordList data) {
                        if (data.getType().equals("1")){
                            List<ResponseWaybillRecordList.ResultBean> resultBeen1 = data.getResult();
                            for (int i = 0; i < resultBeen1.size(); i++) {
                                ResponseWaybillRecordList.ResultBean resultBean = resultBeen1.get(i);
                                if (!TextUtils.isEmpty(resultBean.getMbimBillId())){
                                    mbimBillId=resultBean.getMbimBillId();//运单编号
                                }
                                String mbimAddress = resultBean.getMbimAddress();//地址
                                String mbimContent = resultBean.getMbimContent();//内容
                                String mbimTime = resultBean.getMbimTime();//时间
                                String time = "";//转换后的时间
                                if (!TextUtils.isEmpty(mbimTime)){
                                    long date=Long.parseLong(mbimTime);
                                    SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
                                    time= dateFormat.format(date);
                                }
                                String mbimImage = resultBean.getMbimImage();//图片
                                String mbimType = resultBean.getMbimType();//类型 1：起运 2：冲水 3：堵车
                                if (!TextUtils.isEmpty(mbimType)){
                                    switch (mbimType){
                                        case "1"://起运
                                            qiyun_img.setImageResource(R.mipmap.ys_5);
                                            qiyun_time.setText(time);
                                            qiyun_address.setText(mbimAddress);
                                            qiyun_content.setText(mbimContent);
                                            if (!TextUtils.isEmpty(mbimImage)){
                                                ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(mbimImage), qiyun_img_down);
                                            }

                                            break;
                                        case "2"://冲水
                                            chongshui_img.setImageResource(R.mipmap.ys_5);
                                            chongshui_time.setText(time);
                                            chongshui_address.setText(mbimAddress);
                                            chongshui_content.setText(mbimContent);
                                            if (!TextUtils.isEmpty(mbimImage)){
                                                ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(mbimImage), chongshui_img_down);
                                            }
                                            break;
                                        case "3"://堵车
                                            duche_img.setImageResource(R.mipmap.ys_5);
                                            duche_time.setText(time);
                                            duche_address.setText(mbimAddress);
                                            duche_content.setText(mbimContent);
                                            if (!TextUtils.isEmpty(mbimImage)){
                                                ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(mbimImage), duche_img_down);
                                            }
                                            break;
                                    }
                                }

                            }
                        }else {
                            showToast(data.getMsg());
                        }
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }
                });
    }

    /**
     * 冲水提醒
     */
    private void saveWaterRemind() {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).saveWaterRemind(TempLoginConfig.sf_getSueid(), "1", TempLoginConfig.sf_getPassWord()),
                new TempRemoteApiFactory.OnCallBack<ResponseWaterRemind>() {
                    @Override
                    public void onSucceed(ResponseWaterRemind data) {
                        if (data.getType().equals("1")){
                            if (!TextUtils.isEmpty(data.getResult().getMmmeContent())){
                                water_remind_content.setText(data.getResult().getMmmeContent());
                            }else {
                                water_remind_content.setText("");
                            }

                        }else {
                            showToast(data.getMsg());
                        }
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }
                });
    }

    @Override
    @OnClick({R.id.frag_transport_report,R.id.frag_transport_recording,R.id.frag_transport_arrive,
              R.id.report_img,R.id.recoding_img,R.id.arrive_img})
    protected void OnViewClicked(View v) {
        switch (v.getId()){
            case R.id.frag_transport_report://报案
                startActivity(new Intent(getActivity(), ActReport.class));
                SharedPreferences sp=getContext().getSharedPreferences("report", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("mbimBillId",mbimBillId);
                editor.commit();
                break;
            case R.id.frag_transport_recording://记录
                startActivity(new Intent(getActivity(), ActRecording.class));
                break;
            case R.id.frag_transport_arrive://到达
                showDialog();
                break;
            case R.id.report_img://滑上去之后的报案
                startActivity(new Intent(getActivity(), ActReport.class));
                break;
            case R.id.recoding_img://滑上去之后的记录
                startActivity(new Intent(getActivity(), ActRecording.class));
                break;
            case R.id.arrive_img://滑上去之后的到达
                showDialog();
                break;
        }
    }

    /**
     * 到达的弹窗
     */
    private void showDialog() {
        View v = getActivity().getLayoutInflater().inflate(R.layout.making_a_call_dailog_layout,null);
        mDailog = new AlertDialog.Builder(getContext()).setView(v).create();
        mDailog.getWindow().setBackgroundDrawableResource(R.color.color_1);
        mDailog.show();
        v.findViewById(R.id.phone_layout).setVisibility(View.GONE);
        v.findViewById(R.id.report_layout).setVisibility(View.VISIBLE);
        TextView phone_title = (TextView) v.findViewById(R.id.phone_title);
        v.findViewById(R.id.phone_text).setVisibility(View.GONE);
        v.findViewById(R.id.arrive_text).setVisibility(View.VISIBLE);
        phone_title.setText("提示");
        android.widget.TextView cancel = (android.widget.TextView) v.findViewById(R.id.arrive_cancel);
        android.widget.TextView sure = (android.widget.TextView) v.findViewById(R.id.arrive_sure);
        MyListener listener=new MyListener();
        cancel.setOnClickListener(listener);
        sure.setOnClickListener(listener);

    }
    /**
     * 到达弹窗监听事件
     */
    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.arrive_cancel://否 去报案
                    startActivity(new Intent(getActivity(),ActReport.class));
                    break;
                case R.id.arrive_sure://是
                    saveMemberBillArrive();
                    break;
            }
        }
    }

    /**
     * 到达
     */
    private void saveMemberBillArrive() {
        Log.e("==mbimBillId==",mbimBillId);
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).saveMemberBillArrive(TempLoginConfig.sf_getSueid(), "1", TempLoginConfig.sf_getPassWord(),
                mbimBillId, TempLoginConfig.sf_getLat(), TempLoginConfig.sf_getLng()), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {
                if (data.getType()==1){
                    showToast(data.getMsg());
                    if (mDailog!=null&&mDailog.isShowing()){
                        mDailog.dismiss();
                    }
                }else {
                    showToast(data.getMsg());
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                showToast(e.getMessage());
            }
        });
    }

    @Override
    public void setToolbar(Toolbar toolbar) {

    }
}
