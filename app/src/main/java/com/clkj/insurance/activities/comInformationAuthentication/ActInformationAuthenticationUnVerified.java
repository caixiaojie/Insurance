package com.clkj.insurance.activities.comInformationAuthentication;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.clkj.insurance.R;
import com.clkj.insurance.api.TempAction;
import com.clkj.insurance.base.BaseActivity;
import com.clkj.insurance.config.BaseUriConfig;
import com.clkj.insurance.listener.TempImgBottomDialogListener;
import com.clkj.insurance.response.ResponseUploadPic;
import com.clkj.insurance.response.TempResponseOther;
import com.clkj.insurance.utils.TempBottomDialogUtil;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempPhotoPick.TempPKHelper;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 信息认证 未认证
 */
public class ActInformationAuthenticationUnVerified extends BaseActivity implements EasyPermissions.PermissionCallbacks ,TempImgBottomDialogListener,ViewActInformationAuthenticationUnVerifiedI {

    @Bind(R.id.ID_card_1_img)ImageView ID_card_1_img;
    @Bind(R.id.ID_card_1_text)TextView ID_card_1_text;

    //身份证正面
    @Bind(R.id.ID_card_1_layout1)FrameLayout ID_card_1_layout1;
    @Bind(R.id.ID_card_1_img1)ImageView ID_card_1_img1;
    @Bind(R.id.ID_card_1_layout)FrameLayout ID_card_1_layout;
    //身份证反面
    @Bind(R.id.ID_card_2_layout1)FrameLayout ID_card_2_layout1;
    @Bind(R.id.ID_card_2_img1)ImageView ID_card_2_img1;
    @Bind(R.id.ID_card_2_layout)FrameLayout ID_card_2_layout;
    //驾照正本
    @Bind(R.id.jiazhao_1_layout1)FrameLayout jiazhao_1_layout1;
    @Bind(R.id.jiazhao_1_img1)ImageView jiazhao_1_img1;
    @Bind(R.id.jiazhao_1_layout)FrameLayout jiazhao_1_layout;
    //驾照副本
    @Bind(R.id.jiazhao_2_layout1)FrameLayout jiazhao_2_layout1;
    @Bind(R.id.jiazhao_2_img1)ImageView jiazhao_2_img1;
    @Bind(R.id.jiazhao_2_layout)FrameLayout jiazhao_2_layout;
    //行驶证正本
    @Bind(R.id.xs_1_layout1)FrameLayout xs_1_layout1;
    @Bind(R.id.xs_1_img1)ImageView xs_1_img1;
    @Bind(R.id.xs_1_layout)FrameLayout xs_1_layout;
    //行驶证副本
    @Bind(R.id.xs_2_layout1)FrameLayout xs_2_layout1;
    @Bind(R.id.xs_2_img1)ImageView xs_2_img1;
    @Bind(R.id.xs_2_layout)FrameLayout xs_2_layout;
    //司机自拍照
    @Bind(R.id.driver_layout1)FrameLayout driver_layout1;
    @Bind(R.id.driver_img1)ImageView driver_img1;
    @Bind(R.id.driver_layout)FrameLayout driver_layout;
    //司机与车合照
    @Bind(R.id.driver_and_car_layout1)FrameLayout driver_and_car_layout1;
    @Bind(R.id.driver_and_car_img1)ImageView driver_and_car_img1;
    @Bind(R.id.driver_and_car_layout)FrameLayout driver_and_car_layout;

    @Bind(R.id.ID_number_text)EditText ID_number_text;//身份证号
    private TempBottomDialogUtil dialogUtil;
    //检查权限
    String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int PERSONINFO_CODE = 100;

    private int position=0;//用于区分
    private PreActInformationAuthenticationUnVerifiedI mPreI;

    int isSuccess=0;

    private ResponseUploadPic mdata;
    private String img1,img2,img3,img4,img5,img6,img7,img8;
    private String ID_number;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_information_authentication_un_verified);
    }

    @Override
    protected void findViews() {
        dialogUtil=new TempBottomDialogUtil(this);
        dialogUtil.setBaseListener(this);
    }

    @Override
    protected void setListeners() {
        mPreI = new PreActInformationAuthenticationUnVerifiedImpl(this);

    }

    @Override
    protected void bindValues() {

    }

    @Override
    @OnClick({R.id.toolbar_back,R.id.act_information_commit,R.id.ID_card_1_layout,R.id.ID_card_2_layout,R.id.jiazhao_1_layout,R.id.jiazhao_2_layout,
              R.id.xs_1_layout,R.id.xs_2_layout,R.id.driver_layout,R.id.driver_and_car_layout,
              R.id.ID_card_1_reduce_img1,R.id.ID_card_2_reduce_img1,R.id.jiazhao_1_reduce_img1,
              R.id.jiazhao_2_reduce_img1,R.id.xs_1_reduce_img1,R.id.xs_2_reduce_img1,
              R.id.driver_reduce_img1,R.id.driver_and_car_reduce_img1})
    protected void OnViewClicked(View v) {
        switch (v.getId()){
            case R.id.toolbar_back://返回键
                finish();
                break;
            case R.id.act_information_commit://审核
                ID_number = ID_number_text.getText().toString();
                if (TextUtils.isEmpty(ID_number)){
                    showToast("请输入身份证号");
                    return;
                }
                saveMemberUserCertification();
                /*isSuccess++;
                if (isSuccess%3==0){
                    startActivity(new Intent(getBaseContext(), ActReviewSuccess.class));
                }else if (isSuccess%3==1){
                    startActivity(new Intent(getBaseContext(), ActReviewFail.class));
                }else {
                    startActivity(new Intent(getBaseContext(), ActUnderReview.class));
                }*/

                break;
            case R.id.ID_card_1_layout://身份证正面
                position=1;
                dialogUtil.showIconDialog("ActComplementary");
                break;
            case R.id.ID_card_2_layout://身份证反面
                position=2;
                dialogUtil.showIconDialog("ActComplementary");
                break;
            case R.id.jiazhao_1_layout://驾照正本
                position=3;
                dialogUtil.showIconDialog("ActComplementary");
                break;
            case R.id.jiazhao_2_layout://驾照副本
                position=4;
                dialogUtil.showIconDialog("ActComplementary");
                break;
            case R.id.xs_1_layout://行驶证正本
                position=5;
                dialogUtil.showIconDialog("ActComplementary");
                break;
            case R.id.xs_2_layout://行驶证副本
                position=6;
                dialogUtil.showIconDialog("ActComplementary");
                break;
            case R.id.driver_layout://司机自拍照
                position=7;
                dialogUtil.showIconDialog("ActComplementary");
                break;
            case R.id.driver_and_car_layout://司机与车合照
                position=8;
                dialogUtil.showIconDialog("ActComplementary");
                break;
            case R.id.ID_card_1_reduce_img1://身份证正面减号
                ID_card_1_img1.setImageURI(null);
                ID_card_1_layout.setVisibility(View.VISIBLE);
                ID_card_1_layout1.setVisibility(View.GONE);
                break;
            case R.id.ID_card_2_reduce_img1://身份证反面减号
                ID_card_2_layout.setVisibility(View.VISIBLE);
                ID_card_2_layout1.setVisibility(View.GONE);
                ID_card_2_img1.setImageURI(null);
                break;
            case R.id.jiazhao_1_reduce_img1://驾照正本减号
                jiazhao_1_layout.setVisibility(View.VISIBLE);
                jiazhao_1_layout1.setVisibility(View.GONE);
                jiazhao_1_img1.setImageURI(null);
                break;
            case R.id.jiazhao_2_reduce_img1://驾照副本减号
                jiazhao_2_layout.setVisibility(View.VISIBLE);
                jiazhao_2_layout1.setVisibility(View.GONE);
                jiazhao_2_img1.setImageURI(null);
                break;
            case R.id.xs_1_reduce_img1://行驶证正本减号
                xs_1_layout.setVisibility(View.VISIBLE);
                xs_1_layout1.setVisibility(View.GONE);
                xs_1_img1.setImageURI(null);
                break;
            case R.id.xs_2_reduce_img1://行驶证副本减号
                xs_2_layout.setVisibility(View.VISIBLE);
                xs_2_layout1.setVisibility(View.GONE);
                xs_2_img1.setImageURI(null);
                break;
            case R.id.driver_reduce_img1://司机自拍照减号
                driver_layout.setVisibility(View.VISIBLE);
                driver_layout1.setVisibility(View.GONE);
                driver_img1.setImageURI(null);
                break;
            case R.id.driver_and_car_reduce_img1://司机与车合照减号
                driver_and_car_layout.setVisibility(View.VISIBLE);
                driver_and_car_layout1.setVisibility(View.GONE);
                driver_and_car_img1.setImageURI(null);
                break;

        }
    }

    @Override
    public void photoSuccess(Uri uri) {
        mPreI.uploadImage(new String[]{uri.getPath()});
    }

    /**
     * 实名认证  mcinId认证编号暂无
     */
    private void saveMemberUserCertification() {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).saveMemberUserCertification(img2,img4,img3,img8,ID_number,img1,img6,img5,img7,TempLoginConfig.sf_getSueid(),"1" /*TempLoginConfig.sf_getOnLineKey()*/,TempLoginConfig.sf_getPassWord(),""), new TempRemoteApiFactory.OnCallBack<TempResponseOther>() {
            @Override
            public void onSucceed(TempResponseOther data) {
                if (data.getResult()==1){
                    showToast(data.getMsg());
                    ActInformationAuthenticationUnVerified.this.finish();
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
    public void setToolbar(Toolbar toolbarTop) {
        TextView toolbar_title = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        toolbar_title.setText("认证信息");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== TempPKHelper.TEMP_REQUEST_CODE_CAMERA){
            dialogUtil.onResult(requestCode,resultCode,data);
        }else if (requestCode==TempPKHelper.TEMP_REQUEST_CODE_GALLERY){
            dialogUtil.onResult(requestCode,resultCode,data);
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        requestCodeQRCodePermissions();
    }

    private void requestCodeQRCodePermissions() {
        if (!EasyPermissions.hasPermissions(this, permissions)) {
            EasyPermissions.requestPermissions(this, "拍照需要开启相机权限", PERSONINFO_CODE, permissions);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
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
    }

    @Override
    public void upLoadImageSuccess(ResponseUploadPic data) {
        mdata=data;
        imagenum(mdata);
    }

    @Override
    public void upLoadVideoSuccess(ResponseUploadPic data) {

    }

    private void imagenum(ResponseUploadPic mdata) {
        switch (position){
            case 1://身份证正面
                img1 = mdata.getTitle();
                ID_card_1_layout.setVisibility(View.GONE);
                ID_card_1_layout1.setVisibility(View.VISIBLE);
                ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(mdata.getTitle()), ID_card_1_img1);
                break;
            case 2://身份证反面
                img2 = mdata.getTitle();
                ID_card_2_layout.setVisibility(View.GONE);
                ID_card_2_layout1.setVisibility(View.VISIBLE);
                ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(mdata.getTitle()), ID_card_2_img1);
                break;
            case 3://驾照正本
                img3 = mdata.getTitle();
                jiazhao_1_layout.setVisibility(View.GONE);
                jiazhao_1_layout1.setVisibility(View.VISIBLE);
                ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(mdata.getTitle()), jiazhao_1_img1);
                break;
            case 4://驾照副本
                img4 = mdata.getTitle();
                jiazhao_2_layout.setVisibility(View.GONE);
                jiazhao_2_layout1.setVisibility(View.VISIBLE);
                ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(mdata.getTitle()), jiazhao_2_img1);
                break;
            case 5://行驶证正本
                img5 = mdata.getTitle();
                xs_1_layout.setVisibility(View.GONE);
                xs_1_layout1.setVisibility(View.VISIBLE);
                ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(mdata.getTitle()), xs_1_img1);
                break;
            case 6://行驶证副本
                img6 = mdata.getTitle();
                xs_2_layout.setVisibility(View.GONE);
                xs_2_layout1.setVisibility(View.VISIBLE);
                ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(mdata.getTitle()), xs_2_img1);
                break;
            case 7://司机自拍照
                img7 = mdata.getTitle();
                driver_layout.setVisibility(View.GONE);
                driver_layout1.setVisibility(View.VISIBLE);
                ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(mdata.getTitle()), driver_img1);
                break;
            case 8://司机与车合照
                img8 = mdata.getTitle();
                driver_and_car_layout.setVisibility(View.GONE);
                driver_and_car_layout1.setVisibility(View.VISIBLE);
                ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(mdata.getTitle()), driver_and_car_img1);
                break;
        }


    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public void showPro() {

    }

    @Override
    public void dismissPro() {

    }

    @Override
    public void toast(String message) {

    }

    @Override
    public void showConntectError() {

    }
}
