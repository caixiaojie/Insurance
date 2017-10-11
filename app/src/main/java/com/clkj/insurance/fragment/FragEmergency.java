package com.clkj.insurance.fragment;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.clkj.insurance.R;
import com.clkj.insurance.adapter.ImagePickerAdapter;
import com.clkj.insurance.api.TempAction;
import com.clkj.insurance.base.BaseFragment;
import com.clkj.insurance.response.TempResponse;
import com.clkj.insurance.widget.GlideImageLoader;
import com.clkj.insurance.widget.SelectDialog;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempRecyclerView.TempRVDividerDecoration;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * 报案 应急
 */
public class FragEmergency extends BaseFragment implements ImagePickerAdapter.OnRecyclerViewItemClickListener{
    @Bind(R.id.act_report_photo_recycler)RecyclerView act_report_photo_recycler;
    @Bind(R.id.act_report_second_hint)EditText act_report_second_hint;//死猪头数
    private AlertDialog mDailog;
    private TextView frag_order_call_phone;


    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 2;               //允许选择图片最大数
    private int tag=0;//审核成功 审核失败 审核中
    private String caseBillId;//运单编号
    private String scasImage;//照片
    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frag_emergency, container, false);
    }

    @Override
    protected void setListeners() {
        SharedPreferences sp=getContext().getSharedPreferences("report", Context.MODE_PRIVATE);
        caseBillId=sp.getString("mbimBillId","");
    }

    @Override
    protected void bundleValues() {
        initWidget();
        initImagePicker();
    }

    @Override
    @OnClick({R.id.sure_report})
    protected void OnViewClicked(View v) {
        switch (v.getId()){
            case R.id.sure_report://确认报案
//                showDialog();
                saveMemberBillCase();
                break;
        }
    }

    @Override
    public void setToolbar(Toolbar toolbar) {

    }
    /**
     * 报案
     */
    private void saveMemberBillCase() {
        String pigCount = act_report_second_hint.getText().toString();
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).saveMemberBillCase(TempLoginConfig.sf_getSueid(), "1", TempLoginConfig.sf_getPassWord(), "", caseBillId, "", "1", pigCount, scasImage,
                "", "", ""), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
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

    /**
     * 拨打电话的弹窗
     */
    private void showDialog() {
        View v = getActivity().getLayoutInflater().inflate(R.layout.making_a_call_dailog_layout,null);
        frag_order_call_phone = (TextView) v.findViewById(R.id.frag_order_call_phone);
        mDailog = new AlertDialog.Builder(getContext()).setView(v).create();
        mDailog.getWindow().setBackgroundDrawableResource(R.color.color_1);
        mDailog.show();
        TextView phone_title = (TextView) v.findViewById(R.id.phone_title);
        phone_title.setText("请电话报案");
        TextView frag_order_store_name = (TextView) v.findViewById(R.id.frag_order_store_name);
        frag_order_store_name.setText("报案号码:");
        android.widget.TextView cancel = (android.widget.TextView) v.findViewById(R.id.making_a_call_cancel);
        android.widget.TextView sure = (android.widget.TextView) v.findViewById(R.id.making_a_call_sure);
        MyListener listener=new MyListener();
        cancel.setOnClickListener(listener);
        sure.setOnClickListener(listener);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                    scasImage=selImageList.get(0).path;
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }
    }

    @Override
    public void onItemClick(View view, int position) {

        switch (position) {
            case IMAGE_ITEM_ADD:
                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0: // 直接调起相机
                                /**
                                 * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
                                 *
                                 * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
                                 *
                                 * 如果实在有所需要，请直接下载源码引用。
                                 */
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent = new Intent(getActivity(), ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(getActivity(), ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                break;
                            default:
                                break;
                        }

                    }
                }, names);


                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(getActivity(), ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(getActivity(), R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!getActivity().isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    ArrayList<ImageItem> images = new ArrayList<>();
    @Override
    public void ondeteleChange(ArrayList<ImageItem> mimages) {

        if (images != null) {
            for (int i = 0; i < mimages.size(); i++) {
                if (TextUtils.isEmpty(mimages.get(i).path)) {
                    mimages.remove(i);
                }
            }
            images = mimages;
            selImageList.clear();
            selImageList.addAll(images);
            adapter.setImages(selImageList);
        }
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
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((getActivity()), new String[]{Manifest.permission.CALL_PHONE}, 100);
                    }
                    startActivity(i);
                    break;
            }
        }
    }
    private void initWidget() {
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(getContext(), selImageList, maxImgCount,"report");
        adapter.setOnItemClickListener(this);
        act_report_photo_recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        act_report_photo_recycler.addItemDecoration(new TempRVDividerDecoration(Color.parseColor("#ffffff"),20));
        act_report_photo_recycler.setHasFixedSize(true);
        act_report_photo_recycler.setAdapter(adapter);
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                            //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }
}
