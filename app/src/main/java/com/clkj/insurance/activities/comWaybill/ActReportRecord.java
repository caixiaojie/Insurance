package com.clkj.insurance.activities.comWaybill;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.clkj.insurance.R;
import com.clkj.insurance.activities.comComplementary.ActComplementary;
import com.clkj.insurance.api.TempAction;
import com.clkj.insurance.base.BaseActivity;
import com.clkj.insurance.config.BaseUriConfig;
import com.clkj.insurance.response.ResponseBillDetail;
import com.clkj.insurance.response.TempResponse;
import com.clkj.insurance.throwable.ExceptionEngine;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 报案记录
 */
public class ActReportRecord extends BaseActivity implements ViewActWaybillDetailI{
    @Bind(R.id.baoan_jilu_time)TextView baoan_jilu_time;//报案记录时间
    @Bind(R.id.baoan_address)TextView baoan_address;//报案地点
    @Bind(R.id.img1)ImageView img1;//第一张图片
    @Bind(R.id.img2)ImageView img2;//第二张图片
    @Bind(R.id.img3)ImageView img3;//第三张图片 暂时的 有可能是视频
    @Bind(R.id.renbao_chuli_yijian)TextView renbao_chuli_yijian;//人保处理意见
    @Bind(R.id.songda_time1)TextView songda_time1;//人保处理意见的送达时间
    @Bind(R.id.maoyishang_chuli_yijian)TextView maoyishang_chuli_yijian;//贸易商处理意见
    @Bind(R.id.songda_time2)TextView songda_time2;//贸易商处理意见的送达时间
    @Bind(R.id.me_to_renbao)TextView me_to_renbao;//我给人保处理意见
    @Bind(R.id.songda_time3)TextView songda_time3;//我给人保处理意见的送达时间
    @Bind(R.id.me_to_maoyishang)TextView me_to_maoyishang;//我给贸易商处理意见
    @Bind(R.id.songda_time4)TextView songda_time4;//我给贸易商处理意见的送达时间
    private AlertDialog mDailog;
    private String caseId;//案件编号
    private PreActWaybillDetailI waybillDetailI;
    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_report_record);
        caseId=getIntent().getStringExtra("caseId");
    }

    @Override
    protected void findViews() {
        waybillDetailI=new PreActWaybillDetailImpl(this);
        //访问接口
        waybillDetailI.getBillDetailSuccess(TempLoginConfig.sf_getSueid(),"1",TempLoginConfig.sf_getPassWord(),caseId);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {

    }

    @Override
    @OnClick({R.id.act_waybill_detail_complementary,
            R.id.act_waybill_detail_human_opinion,R.id.act_waybill_detail_traders_opinion})
    protected void OnViewClicked(View v) {
        switch (v.getId()){
            case R.id.act_waybill_detail_complementary://补传
                Intent intent=new Intent(this, ActComplementary.class);
                intent.putExtra("caseId",caseId);
                startActivity(intent);
                break;
            case R.id.act_waybill_detail_human_opinion://给人保处理意见
                showOpinionDialog(1);
                break;
            case R.id.act_waybill_detail_traders_opinion://给贸易商处理意见
                showOpinionDialog(2);
                break;
        }
    }
    /**
     * 意见的弹窗
     */
    private void showOpinionDialog(final int position) {
        View v = getLayoutInflater().inflate(R.layout.opinion_layout,null);
        mDailog = new AlertDialog.Builder(this).setView(v).create();
        mDailog.getWindow().setBackgroundDrawableResource(R.color.color_1);
        mDailog.show();
        final TextView opinion_title = (TextView) v.findViewById(R.id.opinion_title);
        switch (position){
            case 1://人保
                opinion_title.setText("给人保处理意见");
                break;
            case 2://贸易商
                opinion_title.setText("给贸易商处理意见");
                break;
        }
        final EditText opinions_content = (EditText) v.findViewById(R.id.opinions_content);//意见内容

        //发送的监听
        v.findViewById(R.id.opinion_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = opinions_content.getText().toString();
                switch (position){
                    case 1://人保
                        saveCaseFeedback("3",content);
                        break;
                    case 2://贸易商
                        saveCaseFeedback("2",content);
                        break;
                }
            }
        });

    }

    /**
     * 给出意见 mffoSuggestedType 接收意见方类型 1：驾驶员 2：贸易商 3：人保专员
     * mffoSuggestlType 谁给出的意见类型 1：驾驶员 2：贸易商 3：人保专员
     */
    private void saveCaseFeedback(String mffoSuggestedType,String content) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).saveCaseFeedback(TempLoginConfig.sf_getSueid(), "1", TempLoginConfig.sf_getPassWord(), content, caseId, mffoSuggestedType, "1"),
                new TempRemoteApiFactory.OnCallBack<TempResponse>() {
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
    public void setToolbar(Toolbar toolbarTop) {
        TextView toolbar_title = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        toolbar_title.setText("报案记录");
    }

    @Override
    public void getBillDetailSuccess(ResponseBillDetail data) {
        List<ResponseBillDetail.ResultBean.CaseBillRecordBean> caseBillRecord = data.getResult().getCaseBillRecord();
        for (int i = 0; i < caseBillRecord.size(); i++) {
            String mbimTime2 = data.getResult().getCaseBillRecord().get(i).getMbimTime();//报案记录时间
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            if (!TextUtils.isEmpty(mbimTime2)){
                long data4=Long.parseLong(mbimTime2);
                String format4 = dateFormat.format(data4);
                baoan_jilu_time.setText(format4);
            }
            baoan_address.setText(caseBillRecord.get(i).getMbimAddress());//报案地点

            String mbimImage = data.getResult().getWaterBillRecord().get(i).getMbimImage();//冲水记录图片
            if (!TextUtils.isEmpty(mbimImage)) {
                ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(mbimImage), img2);
            }
            String mbimImage1 = data.getResult().getStartBillRecord().getMbimImage();//起运记录图片
            if (!TextUtils.isEmpty(mbimImage1)){
                ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(mbimImage1), img1);
            }
            String mbimImage2 = caseBillRecord.get(i).getMbimImage();//报案记录图片
            if (!TextUtils.isEmpty(mbimImage2)){
                ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(mbimImage2), img3);
            }

        }


    }

    @Override
    public void toast(String message) {
        showToast(message);
    }

    @Override
    public void showConntectError(ExceptionEngine.ApiException e) {
        showToast(e.message);
    }
}
