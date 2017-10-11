package com.clkj.insurance.activities.comRegister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.clkj.insurance.ActLogin;
import com.clkj.insurance.R;
import com.clkj.insurance.activities.comSelectedAddress.ActSelectedAddressFirst;
import com.clkj.insurance.api.TempAction;
import com.clkj.insurance.base.BaseActivity;
import com.clkj.insurance.response.TempResponse;
import com.lf.tempcore.tempApplication.TempApplication;
import com.lf.tempcore.tempBase.adapter.ListBaseAdapter;
import com.lf.tempcore.tempBase.adapter.SuperViewHolder;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 注册 下一步
 */
public class ActRegisterNext extends BaseActivity {

    @Bind(R.id.act_register_next_name)EditText act_register_next_name;//名字
    @Bind(R.id.act_register_next_licensePaltes)EditText act_register_next_licensePaltes;//车牌号
    @Bind(R.id.act_register_next_address_name)TextView act_register_next_address_name;//区域简称
    @Bind(R.id.address_view_line)View address_view_line;
    private String name;//名字
    private String licensePaltes;//车牌号
    private String licensePaltes1;//区域简称
    private String licensePaltes2;//车牌号
    private String phone;//电话
    private String code;//验证码
    private String password;//密码
    private int museAreaId1;//区域编号

    //区域简称
    private String[] addressReferred={"渝","川","鄂","湘","贵","黔","京","津","沪","蒙","新","藏","宁","桂","陕","滇","甘",
                                      "琼","粤","赣","闽","浙","皖","苏","豫","鲁","青","冀","晋","辽","吉","黑","澳","港"};
    private ListBaseAdapter<String> baseAdapter;
    private List<String> data=new ArrayList<>();
    private PopupWindow popupWindow;


    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_register_next);
        SharedPreferences sf = TempApplication.getInstance().getSharedPreferences("register", Context.MODE_PRIVATE);
        phone = sf.getString("phone", "");
        code = sf.getString("code", "");
        password = sf.getString("password", "");

        SharedPreferences sf1 = TempApplication.getInstance().getSharedPreferences("address", Context.MODE_PRIVATE);
        museAreaId1 = sf1.getInt("museAreaId", 0);
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("======phone=====",phone);
        Log.e("======code=====",code);
        Log.e("======password=====", password);

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {

    }

    @Override
    @OnClick({R.id.toolbar_back,R.id.selected_address_layout,R.id.act_register,R.id.select_address_img})
    protected void OnViewClicked(View v) {
        switch (v.getId()){
            case R.id.toolbar_back://返回键
                finish();
                break;
            case R.id.selected_address_layout://地区
                startActivity(new Intent(this, ActSelectedAddressFirst.class));
                break;
            case R.id.act_register://立即注册
                name = act_register_next_name.getText().toString();
                licensePaltes1=act_register_next_address_name.getText().toString();
                licensePaltes2 = act_register_next_licensePaltes.getText().toString();
                licensePaltes=licensePaltes1+licensePaltes2;
                if (TextUtils.isEmpty(name)){
                    showToast("请输入姓名");
                    return;
                }
                if (TextUtils.isEmpty(licensePaltes)){
                    showToast("请输入车牌号");
                    return;
                }
                Log.e("======museAreaId=====",museAreaId1+"");
                Log.e("======name=====",name);
                Log.e("====licensePaltes====",licensePaltes);
                register();
                break;
            case R.id.select_address_img://选择区域简称 弹窗
                showPopupWindow();
                break;
        }
    }

    /**
     * 展示区域简称弹窗
     */
    private void showPopupWindow() {
        View popupView = getLayoutInflater().inflate(R.layout.address_referred_layout, null);

        popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(address_view_line);
        RecyclerView addressReferred_recycler = (RecyclerView) popupView.findViewById(R.id.addressReferred_recycler);
        for (int i = 0; i <addressReferred.length ; i++) {
            data.add(addressReferred[i]);
        }
        addressReferred_recycler.setLayoutManager(new LinearLayoutManager(ActRegisterNext.this,LinearLayoutManager.VERTICAL,false));
        baseAdapter=new ListBaseAdapter<String>(ActRegisterNext.this) {
            @Override
            public int getLayoutId() {
                return R.layout.address_referred_recycler_layout;
            }

            @Override
            public void onBindItemHolder(SuperViewHolder holder, int position) {
                holder.setText(R.id.addressReferred_name,data.get(position));
            }
        };
        baseAdapter.setDataList(data);
        addressReferred_recycler.setAdapter(baseAdapter);
        baseAdapter.setOnItemClickListener(new ListBaseAdapter.OnItemClickListener<String>() {
            @Override
            public void OnItemClick(View itemView, int position, String s) {
                act_register_next_address_name.setText(s);
                popupWindow.dismiss();
            }
        });

    }

    /**
     * 注册
     */
    private void register() {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).appUserRegister(licensePaltes,code,2,museAreaId1+"", password,phone,name), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {

                if (data.getType()==1){
                    showToast(data.getMsg());
                    startActivity(new Intent(ActRegisterNext.this, ActLogin.class));
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
        toolbar_title.setText("注册");
    }
}
