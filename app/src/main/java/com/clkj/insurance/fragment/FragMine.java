package com.clkj.insurance.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clkj.insurance.R;
import com.clkj.insurance.activities.comChangePassword.ActChangePassword;
import com.clkj.insurance.activities.comInformationAuthentication.ActInformationAuthenticationUnVerified;
import com.clkj.insurance.activities.comInformationAuthentication.ActInformationAuthenticationVerified;
import com.clkj.insurance.api.TempAction;
import com.clkj.insurance.base.BaseFragment;
import com.clkj.insurance.response.ReponseUserInfo;
import com.clkj.insurance.response.TempResponse;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempViews.TempRoundImage;

import java.text.SimpleDateFormat;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * 我的
 */
public class FragMine extends BaseFragment /*implements EasyPermissions.PermissionCallbacks ,TempImgBottomDialogListener */{
    @Bind(R.id.frag_mine_no_verified)TextView frag_mine_no_verified;//信息认证 未认证
    @Bind(R.id.frag_mine_verified)TextView frag_mine_verified;//信息认证 已认证
    @Bind(R.id.user_head_img)TempRoundImage user_head_img;//头像
    @Bind(R.id.user_name)TextView user_name;//用户名字
    @Bind(R.id.frag_mine_district_belong_to)TextView frag_mine_district_belong_to;//所属地区
    @Bind(R.id.frag_mine_tel)TextView frag_mine_tel;//电话号码
    @Bind(R.id.frag_mine_register_time)TextView frag_mine_register_time;//注册时间
    @Bind(R.id.frag_mine_number_plate)TextView frag_mine_number_plate;//车牌号

    //检查权限
    /*String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int PERSONINFO_CODE = 100;
    private TempBottomDialogUtil dialogUtil;*/

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frag_mine, container, false);
    }

    @Override
    protected void setListeners() {
        /*dialogUtil=new TempBottomDialogUtil((TempActivity) getActivity());
        dialogUtil.setBaseListener(this);*/
    }

    @Override
    protected void bundleValues() {
        queryMemberUserInfo();
    }
    /**
     * 查询用户信息
     */
    private void queryMemberUserInfo() {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).queryMemberUserInfo(TempLoginConfig.sf_getSueid(),"1" /*TempLoginConfig.sf_getOnLineKey()*/, TempLoginConfig.sf_getPassWord()),
                new TempRemoteApiFactory.OnCallBack<ReponseUserInfo>() {
                    @Override
                    public void onSucceed(ReponseUserInfo data) {
                        if (data.getType().equals("1")){
                            user_name.setText(data.getResult().getMuseName());
                            frag_mine_district_belong_to.setText(data.getResult().getMuseAreaName());
                            frag_mine_tel.setText(data.getResult().getMusePhone());
                            //注册时间
                            String registerTime = data.getResult().getMuseRegisterTime();
                            if (!TextUtils.isEmpty(registerTime)){
                                long time1=Long.parseLong(registerTime);
                                SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");//24小时制
                                String time = sdformat.format(time1);
                                frag_mine_register_time.setText(time);
                            }

                            frag_mine_number_plate.setText(data.getResult().getMuseLicensePlates());

                            //认证状态 0：没有 1：已认证
                            String statusFlag = data.getResult().getMuseConfirmFlag();
                            if (!TextUtils.isEmpty(statusFlag)){
                                if (statusFlag.equals("0")){//未认证
                                    frag_mine_no_verified.setVisibility(View.VISIBLE);//未认证
                                    frag_mine_verified.setVisibility(View.GONE);//已认证

                                }else {
                                    frag_mine_no_verified.setVisibility(View.GONE);//未认证
                                    frag_mine_verified.setVisibility(View.VISIBLE);//已认证
                                }
                            }else {
                                frag_mine_no_verified.setVisibility(View.VISIBLE);//未认证
                                frag_mine_verified.setVisibility(View.GONE);//已认证
                            }



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
    @OnClick({R.id.exit_login,R.id.frag_mine_no_verified,R.id.frag_mine_verified,R.id.frag_mine_change_password,R.id.user_head_img})
    protected void OnViewClicked(View v) {
        switch (v.getId()){
            case R.id.exit_login://退出登录
                exitLogin();
                break;
            case R.id.frag_mine_no_verified://信息认证 未认证
                startActivity(new Intent(getActivity(), ActInformationAuthenticationUnVerified.class));
                break;
            case R.id.frag_mine_verified://信息认证 已认证
                startActivity(new Intent(getActivity(), ActInformationAuthenticationVerified.class));
                break;
            case R.id.frag_mine_change_password://修改密码
                startActivity(new Intent(getActivity(), ActChangePassword.class));
                break;
            case R.id.user_head_img://头像 点击更换头像
//                dialogUtil.showIconDialog("ActComplementary");
                break;
        }
    }


    /**
     * 退出登录
     */
    private void exitLogin() {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).exitLogin(TempLoginConfig.sf_getSueid()), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {

                if (data.getType()==1){
                    showToast(data.getMsg());
                    getActivity().finish();
                }else {
                    showToast(data.getMsg());
                }
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }

    @Override
    public void setToolbar(Toolbar toolbar) {

    }

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode== TempPKHelper.TEMP_REQUEST_CODE_CAMERA){
                dialogUtil.onResult(requestCode,resultCode,data);
            }else if (requestCode==TempPKHelper.TEMP_REQUEST_CODE_GALLERY){
                dialogUtil.onResult(requestCode,resultCode,data);
            }
        }

    }
*/
   /* @Override
    public void onStart() {
        super.onStart();
        requestCodeQRCodePermissions();
    }

    private void requestCodeQRCodePermissions() {
        if (!EasyPermissions.hasPermissions(getContext(), permissions)) {
            EasyPermissions.requestPermissions(this, "拍照需要开启相机权限", PERSONINFO_CODE, permissions);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }

    @Override
    public void photoSuccess(Uri uri) {
        user_head_img.setImageURI(uri);
    }

    @Override
    public void photoFail(int flag) {
        showToast("已取消当前操作");
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        switch (requestCode){
            case PERSONINFO_CODE:

                break;
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        switch (requestCode) {
            case PERSONINFO_CODE:
                //引导用户跳转到设置界面
                new AppSettingsDialog.Builder(this, "希望您通过权限")
                        .setTitle("权限设置")
                        .setPositiveButton("设置")
                        .setNegativeButton("取消", null)
                        .setRequestCode(PERSONINFO_CODE)
                        .build()
                        .show();
                break;
        }
    }*/
}
