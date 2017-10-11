package com.clkj.insurance.fragment;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.clkj.insurance.R;
import com.clkj.insurance.activities.comInformationAuthentication.PreActInformationAuthenticationUnVerifiedI;
import com.clkj.insurance.activities.comInformationAuthentication.PreActInformationAuthenticationUnVerifiedImpl;
import com.clkj.insurance.activities.comInformationAuthentication.ViewActInformationAuthenticationUnVerifiedI;
import com.clkj.insurance.api.TempAction;
import com.clkj.insurance.base.BaseFragment;
import com.clkj.insurance.config.BaseUriConfig;
import com.clkj.insurance.listener.TempImgBottomDialogListener;
import com.clkj.insurance.response.ResponseUploadPic;
import com.clkj.insurance.response.TempResponse;
import com.clkj.insurance.utils.TempBottomDialogUtil;
import com.lf.tempcore.tempActivity.TempActivity;
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
 * A simple {@link Fragment} subclass.
 * 报案 交通意外
 */
public class FragTrafficAccident extends BaseFragment implements EasyPermissions.PermissionCallbacks ,TempImgBottomDialogListener,ViewActInformationAuthenticationUnVerifiedI {

    //本车车牌号
    @Bind(R.id.frag_traffic_update_car_photo_before)FrameLayout frag_traffic_update_car_photo_before;
    @Bind(R.id.frag_traffic_update_car_photo_after)FrameLayout frag_traffic_update_car_photo_after;
    @Bind(R.id.car_photo)ImageView car_photo;
    //本车车辆全景
    @Bind(R.id.frag_traffic_update_car_photo1_before)FrameLayout frag_traffic_update_car_photo1_before;
    @Bind(R.id.frag_traffic_update_car_photo1_after)FrameLayout frag_traffic_update_car_photo1_after;
    @Bind(R.id.car_photo1)ImageView car_photo1;
    //受损部位近照
    @Bind(R.id.frag_traffic_update_shousun_photo_before)FrameLayout frag_traffic_update_shousun_photo_before;
    @Bind(R.id.frag_traffic_update_shousun_photo_after)FrameLayout frag_traffic_update_shousun_photo_after;
    @Bind(R.id.shousun_photo)ImageView shousun_photo;
    //事故现场全景
    @Bind(R.id.frag_traffic_update_shigu_photo_before)FrameLayout frag_traffic_update_shigu_photo_before;
    @Bind(R.id.frag_traffic_update_shigu_photo_after)FrameLayout frag_traffic_update_shigu_photo_after;
    @Bind(R.id.shigu_photo)ImageView shigu_photo;

    private TempBottomDialogUtil dialogUtil;
    //检查权限
    String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int PERSONINFO_CODE = 100;
    private int position=0;//用于区分
    private PreActInformationAuthenticationUnVerifiedI mPreI;
    private ResponseUploadPic mdata;
    private String img1,img2,img3,img4;

    private String caseBillId;//运单编号

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_frag_traffic_accident, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp=getContext().getSharedPreferences("report", Context.MODE_PRIVATE);
        caseBillId=sp.getString("mbimBillId","");
    }

    @Override
    protected void setListeners() {
        Log.e("==caseBillId==",caseBillId);
        dialogUtil=new TempBottomDialogUtil((TempActivity)getContext());
        dialogUtil.setBaseListener(this);
        mPreI = new PreActInformationAuthenticationUnVerifiedImpl(this);
    }

    @Override
    protected void bundleValues() {

    }

    @Override
    @OnClick({R.id.frag_traffic_update_car_photo_before,R.id.frag_traffic_update_car_photo1_before,
              R.id.frag_traffic_update_shousun_photo_before,R.id.frag_traffic_update_shigu_photo_before,
              R.id.car_photo_reduce_img,R.id.car_photo1_reduce_img,R.id.shousun_photo_reduce_img,R.id.shigu_photo_reduce_img,
              R.id.frag_traffic_sure})
    protected void OnViewClicked(View v) {
        switch (v.getId()){
            case R.id.frag_traffic_update_car_photo_before://本车车牌号
                position=1;
                dialogUtil.showIconDialog("ActComplementary");
                break;
            case R.id.frag_traffic_update_car_photo1_before://车辆全景
                position=2;
                dialogUtil.showIconDialog("ActComplementary");
                break;
            case R.id.frag_traffic_update_shousun_photo_before://受损部位近照
                position=3;
                dialogUtil.showIconDialog("ActComplementary");
                break;
            case R.id.frag_traffic_update_shigu_photo_before://事故现场全景
                position=4;
                dialogUtil.showIconDialog("ActComplementary");
                break;
            case R.id.car_photo_reduce_img://本车车牌号减号
                car_photo.setImageURI(null);
                frag_traffic_update_car_photo_before.setVisibility(View.VISIBLE);
                frag_traffic_update_car_photo_after.setVisibility(View.GONE);
                break;
            case R.id.car_photo1_reduce_img://本车全景减号
                car_photo1.setImageURI(null);
                frag_traffic_update_car_photo1_before.setVisibility(View.VISIBLE);
                frag_traffic_update_car_photo1_after.setVisibility(View.GONE);
                break;
            case R.id.shousun_photo_reduce_img://受损部位近照减号
                shousun_photo.setImageURI(null);
                frag_traffic_update_shousun_photo_before.setVisibility(View.VISIBLE);
                frag_traffic_update_shousun_photo_after.setVisibility(View.GONE);
                break;
            case R.id.shigu_photo_reduce_img://事故现场全景减号
                shigu_photo.setImageURI(null);
                frag_traffic_update_shigu_photo_before.setVisibility(View.VISIBLE);
                frag_traffic_update_shigu_photo_after.setVisibility(View.GONE);
                break;
            case R.id.frag_traffic_sure://确认报案
                saveMemberBillCase();
                break;
        }
    }
    @Override
    public void photoSuccess(Uri uri) {
        mPreI.uploadImage(new String[]{uri.getPath()});
    }

    /**
     * 报案
     */
    private void saveMemberBillCase() {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).saveMemberBillCase(TempLoginConfig.sf_getSueid(), "1", TempLoginConfig.sf_getPassWord(), img3, caseBillId, img2, "2", "", "",
                img1, img4, ""), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
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
    public void setToolbar(Toolbar toolbar) {

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
            case 1://车牌号
                img1 = mdata.getTitle();
                frag_traffic_update_car_photo_before.setVisibility(View.GONE);
                frag_traffic_update_car_photo_after.setVisibility(View.VISIBLE);
                ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(mdata.getTitle()), car_photo);
                break;
            case 2://车辆全景
                img2 = mdata.getTitle();
                frag_traffic_update_car_photo1_before.setVisibility(View.GONE);
                frag_traffic_update_car_photo1_after.setVisibility(View.VISIBLE);
                ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(mdata.getTitle()), car_photo1);
                break;
            case 3://受损部位近照
                img3 = mdata.getTitle();
                frag_traffic_update_shousun_photo_before.setVisibility(View.GONE);
                frag_traffic_update_shousun_photo_after.setVisibility(View.VISIBLE);
                ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(mdata.getTitle()), shousun_photo);
                break;
            case 4://事故现场全景
                img4 = mdata.getTitle();
                frag_traffic_update_shigu_photo_before.setVisibility(View.GONE);
                frag_traffic_update_shigu_photo_after.setVisibility(View.VISIBLE);
                ImageLoader.getInstance().displayImage(BaseUriConfig.makeImageUrl(mdata.getTitle()), shigu_photo);
                break;
        }
    }


    @Override
    public void photoFail(int flag) {
        showToast("已取消当前操作");
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== TempPKHelper.TEMP_REQUEST_CODE_CAMERA){
            dialogUtil.onResult(requestCode,resultCode,data);
        }else if (requestCode==TempPKHelper.TEMP_REQUEST_CODE_GALLERY){
            dialogUtil.onResult(requestCode,resultCode,data);
        }

    }
}
