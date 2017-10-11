package com.clkj.insurance.activities.comInformationAuthentication;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.clkj.insurance.R;
import com.clkj.insurance.api.TempAction;
import com.clkj.insurance.base.BaseActivity;
import com.clkj.insurance.config.BaseUriConfig;
import com.clkj.insurance.response.ResponeMemberUserCertification;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 消息认证 已认证
 */
public class ActInformationAuthenticationVerified extends BaseActivity {
    @Bind(R.id.ID_number_text)TextView ID_number_text;//身份证号
    @Bind(R.id.ID_card_front)ImageView ID_card_front;//身份证正面
    @Bind(R.id.ID_card_back)ImageView ID_card_back;//身份证反面
    @Bind(R.id.jiazhao_front)ImageView jiazhao_front;//驾照正本
    @Bind(R.id.jiazhao_back)ImageView jiazhao_back;//驾照副本
    @Bind(R.id.xs_front)ImageView xs_front;//行驶证正本
    @Bind(R.id.xs_back)ImageView xs_back;//行驶证副本
    @Bind(R.id.own_phone)ImageView own_phone;//自拍照
    @Bind(R.id.driver_and_car_phone)ImageView driver_and_car_phone;//合照
    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_information_authentication_verified);
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {
        queryMemberUserCertification();
    }

    /**
     * 查询认证信息
     */
    private void queryMemberUserCertification() {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).queryMemberUserCertification(TempLoginConfig.sf_getSueid(),"1" /*TempLoginConfig.sf_getOnLineKey()*/,TempLoginConfig.sf_getPassWord()),
                new TempRemoteApiFactory.OnCallBack<ResponeMemberUserCertification>() {
            @Override
            public void onSucceed(ResponeMemberUserCertification data) {
                if (data.getType().equals("1")){
                    ID_number_text.setText(data.getResult().getMcinIdcardNumber());
                    if (!TextUtils.isEmpty(data.getResult().getMcinIfrontIdcard())){
                        ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(data.getResult().getMcinIfrontIdcard()),ID_card_front);
                    }else {
                        ID_card_front.setImageResource(R.mipmap.zj_1);
                    }
                    if (!TextUtils.isEmpty(data.getResult().getMcinBackIdcard())){
                        ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(data.getResult().getMcinBackIdcard()),ID_card_back);
                    }else {
                        ID_card_back.setImageResource(R.mipmap.zj_2);
                    }
                    if (!TextUtils.isEmpty(data.getResult().getMcinDriveLicenseIfront())){
                        ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(data.getResult().getMcinDriveLicenseIfront()),jiazhao_front);
                    }else {
                        jiazhao_front.setImageResource(R.mipmap.zj_3);
                    }
                    if (!TextUtils.isEmpty(data.getResult().getMcinDriveLicenseCopy())){
                        ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(data.getResult().getMcinDriveLicenseCopy()),jiazhao_back);
                    }else {
                        jiazhao_back.setImageResource(R.mipmap.zj_4);
                    }
                    if (!TextUtils.isEmpty(data.getResult().getMcinMoveLicenseInfront())){
                        ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(data.getResult().getMcinMoveLicenseInfront()),xs_front);
                    }else {
                        xs_front.setImageResource(R.mipmap.zj_5);
                    }
                    if (!TextUtils.isEmpty(data.getResult().getMcinMoveLicenseCopy())){
                        ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(data.getResult().getMcinMoveLicenseCopy()),xs_back);
                    }else {
                        xs_back.setImageResource(R.mipmap.zj_6);
                    }
                    if (!TextUtils.isEmpty(data.getResult().getMcinSelfImage())){
                        ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(data.getResult().getMcinSelfImage()),own_phone);
                    }else {
                        own_phone.setImageResource(R.mipmap.zj_7);
                    }
                    if (!TextUtils.isEmpty(data.getResult().getMcinDriverPhotograph())){
                        ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(data.getResult().getMcinDriverPhotograph()),driver_and_car_phone);
                    }else {
                        driver_and_car_phone.setImageResource(R.mipmap.zj_8);
                    }

                    showToast(data.getMsg());
                }else {
                    showToast(data.getMsg());
                }
            }

            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
            }
        });
    }

    @Override
    @OnClick({R.id.toolbar_back})
    protected void OnViewClicked(View v) {
            switch (v.getId()){
                case R.id.toolbar_back://返回键
                    finish();
                    break;
            }
    }

    @Override
    public void setToolbar(Toolbar toolbarTop) {
        TextView toolbar_title = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        toolbar_title.setText("认证信息");
    }
}
