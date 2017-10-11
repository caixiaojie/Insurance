package com.clkj.insurance.activities.comRecording;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.clkj.insurance.R;
import com.clkj.insurance.adapter.ImagePickerAdapter;
import com.clkj.insurance.api.TempAction;
import com.clkj.insurance.base.BaseActivity;
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
 * 记录
 */
public class ActRecording extends BaseActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener{

    @Bind(R.id.request_content)TextView request_content;
    @Bind(R.id.act_recording_recycler)RecyclerView act_recording_recycler;
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 1;

    private String image;//图片
    private String type;//类型 1：起运 2：冲水 3：堵车 4：到达 5：gps定位 6：过秤 7：收费站
    private String swayId;//运单编号
    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_recording);
        SharedPreferences sp=getSharedPreferences("report", Context.MODE_PRIVATE);
        swayId=sp.getString("mbimBillId","");
    }

    @Override
    protected void findViews() {
        initWidget();
        initImagePicker();
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {

    }

    @Override
    @OnClick({R.id.toolbar_back,R.id.rb_flush,R.id.rb_traffic_jam,R.id.rb_scale,R.id.rb_toll,R.id.actrecording_sure})
    protected void OnViewClicked(View v) {
        //类型 1：起运 2：冲水 3：堵车 4：到达 5：gps定位 6：过秤 7：收费站
        switch (v.getId()){
            case R.id.toolbar_back://返回键
                finish();
                break;
            case R.id.rb_flush://冲水
                type="2";
                request_content.setText("从车侧面拍摄正在冲水照片一张");
                break;
            case R.id.rb_traffic_jam://堵车
                type="3";
                request_content.setText("从驾驶室拍摄前方交通情况,拥堵状况须清晰可见照片一张");
                break;
            case R.id.rb_scale://过秤
                type="6";
                request_content.setText("称重重量清晰可见照片一张");
                break;
            case R.id.rb_toll://收费站
                type="7";
                request_content.setText("从驾驶室拍摄收费站,收费站名须清晰可见照片一张");
                break;
            case R.id.actrecording_sure://确认记录
                saveMemberBillRecord(type);
                break;
        }
    }

    /**
     * 添加报案记录
     * @param type
     */
    private void saveMemberBillRecord(String type) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).saveMemberBillRecord(TempLoginConfig.sf_getSueid(), "1", TempLoginConfig.sf_getPassWord(), image, TempLoginConfig.sf_getLat(), TempLoginConfig.sf_getLng(), swayId, type),
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
        toolbar_title.setText("记录");
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
                                Intent intent = new Intent(ActRecording.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(ActRecording.this, ImageGridActivity.class);
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
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }
    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!ActRecording.this.isFinishing()) {
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
    private void initWidget() {
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount,"report");
        adapter.setOnItemClickListener(this);
        act_recording_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        act_recording_recycler.addItemDecoration(new TempRVDividerDecoration(Color.parseColor("#ffffff"),20));
        act_recording_recycler.setHasFixedSize(true);
        act_recording_recycler.setAdapter(adapter);
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
                    image=selImageList.get(0).path;
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
}
