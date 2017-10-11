package com.clkj.insurance.activities;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.clkj.insurance.R;
import com.clkj.insurance.activities.comInformationAuthentication.PreActInformationAuthenticationUnVerifiedI;
import com.clkj.insurance.activities.comInformationAuthentication.PreActInformationAuthenticationUnVerifiedImpl;
import com.clkj.insurance.activities.comInformationAuthentication.ViewActInformationAuthenticationUnVerifiedI;
import com.clkj.insurance.api.TempAction;
import com.clkj.insurance.base.BaseActivity;
import com.clkj.insurance.config.BaseUriConfig;
import com.clkj.insurance.listener.TempImgBottomDialogListener;
import com.clkj.insurance.response.ResponseUploadPic;
import com.clkj.insurance.response.TempResponse;
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
 * 起运
 */
public class ActStart extends BaseActivity implements EasyPermissions.PermissionCallbacks ,TempImgBottomDialogListener,ViewActInformationAuthenticationUnVerifiedI {

    @Bind(R.id.act_start_update_phone_after)FrameLayout act_start_update_phone_after;
    @Bind(R.id.act_start_update_phone)FrameLayout act_start_update_phone;
    @Bind(R.id.start_photo)ImageView start_photo;//图片
    @Bind(R.id.act_start_number)EditText act_start_number;//生猪头数
    private TempBottomDialogUtil dialogUtil;
    //检查权限
    String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final int PERSONINFO_CODE = 100;

    private String swayId;//运单编号
    private PreActInformationAuthenticationUnVerifiedI mPreI;
    private ResponseUploadPic mdata;
    private String carImage;
    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_start);
        swayId=getIntent().getStringExtra("swayId");
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== TempPKHelper.TEMP_REQUEST_CODE_CAMERA){
            dialogUtil.onResult(requestCode,resultCode,data);
        }
    }

    @Override
    @OnClick({R.id.toolbar_back,R.id.act_start_update_phone,R.id.reduce_img,R.id.act_start_sure})
    protected void OnViewClicked(View v) {
        switch (v.getId()){
            case R.id.toolbar_back://返回键
                finish();
                break;
            case R.id.act_start_update_phone://上传照片
                dialogUtil.showIconDialog("ActStart");
                break;
            case R.id.reduce_img://减号
                act_start_update_phone_after.setVisibility(View.GONE);
                act_start_update_phone.setVisibility(View.VISIBLE);
                start_photo.setImageURI(null);
                break;
            case R.id.act_start_sure://确认起运
                saveStartWaybill();
                break;
        }
    }

    /**
     * 上传资料
     */
    private void saveStartWaybill() {
        String number = act_start_number.getText().toString();
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).saveStartWaybill(TempLoginConfig.sf_getSueid(), "1", TempLoginConfig.sf_getPassWord(), carImage, number, swayId, TempLoginConfig.sf_getLat(), TempLoginConfig.sf_getLng()),
                new TempRemoteApiFactory.OnCallBack<TempResponse>() {
                    @Override
                    public void onSucceed(TempResponse data) {
                        if (data.getType()==1){
                            showToast(data.getMsg());
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
        toolbar_title.setText("起运");
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
    public void photoSuccess(Uri uri) {
        /*act_start_update_phone_after.setVisibility(View.VISIBLE);
        act_start_update_phone.setVisibility(View.GONE);
        start_photo.setImageURI(uri);*/
        mPreI.uploadImage(new String[]{uri.getPath()});
    }

    @Override
    public void photoFail(int flag) {
        showToast("已取消当前操作");

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
        carImage=mdata.getTitle();
        act_start_update_phone_after.setVisibility(View.VISIBLE);
        act_start_update_phone.setVisibility(View.GONE);
        ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(mdata.getTitle()), start_photo);
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
