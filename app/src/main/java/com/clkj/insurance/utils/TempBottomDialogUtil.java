package com.clkj.insurance.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;

import com.clkj.insurance.R;
import com.clkj.insurance.listener.TempBottomDialogBaseListener;
import com.clkj.insurance.listener.TempImgBottomDialogListener;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempBase.adapter.ListBaseAdapter;
import com.lf.tempcore.tempModule.tempDebuger.Debug;
import com.lf.tempcore.tempModule.tempPhotoPick.TempPKHandler;
import com.lf.tempcore.tempModule.tempPhotoPick.TempPKHelper;
import com.lf.tempcore.tempModule.tempPhotoPick.TempPKParams;
import com.rey.material.app.BottomSheetDialog;



/**
 * Created by caoyang on 2017/6/15.
 * 所有的底部 BottomSheetDialog
 */

public class TempBottomDialogUtil implements TempPKHandler {
    private BottomSheetDialog mDialog;//头像 日期 男女 上传底部弹出框
    private TempPKParams params;
    private TempActivity mContext;
    private int mMaxHeight=0;

    public void setBaseListener(TempBottomDialogBaseListener baseListener) {
        mBaseListener = baseListener;
    }

    private TempBottomDialogBaseListener mBaseListener;
    public String[] putStyles = {"配送员配送", "用户自取", "用户到店里吃"};
    public String[] eatNums = {"1人","2人","3人","4人","5人","6人","7人","8人","9人","10人"};

//    public void setListener(TempBottomDialogListener listener) {
//        mListener = listener;
//    }

//    private TempBottomDialogListener mListener;
    public String museBirthday, mData, museSex, putStyle;

    public TempBottomDialogUtil(TempActivity context) {
        mContext = context;
        params = new TempPKParams();
        params.compress = true;
    }
    public TempBottomDialogUtil(TempActivity context, int mMaxHeight) {
       this(context);
        this.mMaxHeight = mMaxHeight;
    }

    @Override
    public void onSucceed(Uri uri) {
        if (mBaseListener != null)
            ((TempImgBottomDialogListener)mBaseListener).photoSuccess(uri);
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void onCancel() {
//        showToast("已取消当前操作！");
        if (mBaseListener != null)
            ((TempImgBottomDialogListener)mBaseListener).photoFail(0);
        Debug.info("ActPersonalInfo", "onCancel");
        mDialog.dismiss();
    }

    @Override
    public void onFailed(String message) {
//        showToast("已取消当前操作！");
        if (mBaseListener != null)
            ((TempImgBottomDialogListener)mBaseListener).photoFail(1);
        Debug.info("ActPersonalInfo", message);
        mDialog.dismiss();
    }

    @Override
    public TempPKParams getPkParams() {
        return params;
    }

    @Override
    public Activity getContext() {
        return mContext;
    }

    public void onDestroy() {
        if (getPkParams() != null)
            TempPKHelper.clearCachedByUri(getPkParams().uri);
    }

    public void onResult(int requestCode, int resultCode, Intent data) {
        TempPKHelper.onResult(this, requestCode, resultCode, data);
    }
  /*选择头像*/

    public void showIconDialog(String tog) {

        mDialog = new BottomSheetDialog(mContext);
        View view = mContext.getLayoutInflater().inflate(R.layout.pop_act_peraonal_info_pics_layout, null);
        view.findViewById(R.id.pop_quit_layout).setOnClickListener(clickListener);
        view.findViewById(R.id.pop_take_pic_layout).setOnClickListener(clickListener);
        LinearLayout pop_choose_pic_layout = (LinearLayout) view.findViewById(R.id.pop_choose_pic_layout);
        pop_choose_pic_layout.setOnClickListener(clickListener);
        switch (tog){
            case "ActComplementary"://补传
                pop_choose_pic_layout.setVisibility(View.VISIBLE);

                break;
            case "ActStart"://起运
                pop_choose_pic_layout.setVisibility(View.GONE);
                view.findViewById(R.id.view).setVisibility(View.GONE);
                break;
        }
        mDialog.contentView(view).cancelable(true).canceledOnTouchOutside(true).show();
    }

    /*选择时间*/
    /*public void showDateDialog() {
        if (mContext == null) {
            return;
        }
        Calendar mCalendar = Calendar.getInstance();
        String birthday = TempLoginConfig.sf_getBirthday();
        if (birthday != null && !birthday.equals("")) {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            try {
                Date date = formater.parse(birthday);
                mCalendar.setTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        View view = mContext.getLayoutInflater().inflate(R.layout.pop_data_layout, null);

        final TimePicker datePicker = (TimePicker) view.findViewById(R.id.pop_choose_date_datepicker);
        view.findViewById(R.id.pop_choose_date_quit).setOnClickListener(clickListener);
        view.findViewById(R.id.pop_choose_date_ok).setOnClickListener(clickListener);

//        setDatePickerDividerColor(datePicker);
        datePicker.setIs24HourView(true);
        mDialog = new BottomSheetDialog(mContext);
        mDialog.contentView(view).cancelable(true).canceledOnTouchOutside(true).show();
    }*/


    /**
     * 选择配送方式 其他Rcv
     *
     * @param defaultV 默认值
     */
    /*public void showRcv(String defaultV ,List<String> data,String tag) {
        if (mContext == null) {
            return;
        }
        mDialog = new BottomSheetDialog(mContext);
        View view = mContext.getLayoutInflater().inflate(R.layout.pop_rcv, null);
        view.findViewById(R.id.pop_choose_rcv_quit).setOnClickListener(clickListener);
        view.findViewById(R.id.pop_choose_rcv_ok).setOnClickListener(clickListener);
        LinearLayout pop_bottom_layout = (LinearLayout) view.findViewById(R.id.pop_bottom_layout);
        initRcv(data, (RecyclerView) view.findViewById(R.id.pop_rcv), defaultV,tag);
        mDialog.contentView(view).cancelable(true).canceledOnTouchOutside(true).show();
        if (((RecyclerView) view.findViewById(R.id.pop_rcv)).getAdapter().getItemCount()>5){
            mDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, mMaxHeight);
            mDialog.getWindow().setGravity(Gravity.BOTTOM);
        }

    }*/

    ListBaseAdapter<String> adapter;

    /*private void initRcv(final List<String> data, RecyclerView lv, final String defaultV, final String tag) {

        lv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        adapter = new ListBaseAdapter<String>(mContext) {

            @Override
            public int getLayoutId() {
                return R.layout.item_pop_rcv;
            }

            @Override
            public void onBindItemHolder(SuperViewHolder holder, int position) {
                TextView pop_rcv_item_text = holder.getView(R.id.pop_rcv_item_text);

                if (adapter.getDataList().contains(defaultV)) {
                    if (defaultV != null && adapter.getDataList().get(position).equals(defaultV)) {
                        pop_rcv_item_text.setTextColor(Color.parseColor("#e6003f"));
                    }
                } else if (position == 0) {
                    pop_rcv_item_text.setTextColor(Color.parseColor("#e6003f"));
                }
                pop_rcv_item_text.setText(adapter.getDataList().get(position));
            }
        };
        adapter.setDataList(data);
        lv.setAdapter(adapter);
        adapter.setOnItemClickListener(new ListBaseAdapter.OnItemClickListener<String>() {
            @Override
            public void OnItemClick(View itemView, int position, String s) {
                putStyle = s;
                if (mBaseListener != null) {
                    ((TempBottomDialogRcvListener) mBaseListener).onBottomDialogItemClick(itemView, position, s,tag);
                }
                if (mDialog != null && mDialog.isShowing())
                    mDialog.dismiss();
            }
        });
    }*/

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                /*选择配送方式*/
                /*case R.id.pop_choose_rcv_ok:
                    break;
                case R.id.pop_choose_rcv_quit:
                    if (mDialog != null && mDialog.isShowing()) {
                        mDialog.dismiss();
                    }
                    break;*/
                /*选择配送方式*/


                //选择图片开始
                case R.id.pop_quit_layout:
                    if (mBaseListener != null)
                        ((TempImgBottomDialogListener)mBaseListener).photoFail(0);
                    if (mDialog != null && mDialog.isShowing()) {
                        mDialog.dismiss();
                    }
                    break;
                case R.id.pop_take_pic_layout:
                    mContext.startActivityForResult(TempPKHelper.makeCaptureIntent(params), TempPKHelper.TEMP_REQUEST_CODE_CAMERA);
                    break;
                case R.id.pop_choose_pic_layout:
                    mContext.startActivityForResult(TempPKHelper.makeGalleryIntent(params), TempPKHelper.TEMP_REQUEST_CODE_GALLERY);
                    break;
                //选择图片结束

            }
        }
    };

}
