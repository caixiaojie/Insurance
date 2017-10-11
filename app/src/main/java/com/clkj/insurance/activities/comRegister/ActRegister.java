package com.clkj.insurance.activities.comRegister;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.clkj.insurance.R;
import com.clkj.insurance.api.TempAction;
import com.clkj.insurance.base.BaseActivity;
import com.clkj.insurance.response.TempResponse;
import com.lf.tempcore.tempApplication.TempApplication;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempModule.tempUtils.TempRegexUtil;
import com.lf.tempcore.tempModule.tempUtils.TempTimeUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 注册
 */
public class ActRegister extends BaseActivity {
    @Bind(R.id.act_register_phone)EditText act_register_phone;
    @Bind(R.id.code_text)EditText code_text;
    @Bind(R.id.act_register_password)EditText act_register_password;
    @Bind(R.id.act_register_password_again)EditText act_register_password_again;
    @Bind(R.id.send_code)TextView send_code;

    private String code;
    private String password;
    private String passwordAgain;
    private String phone;

    private TempTimeUtil tempTimeUtil;
    private TempRegexUtil tempRegexUtil;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_register);
    }

    @Override
    protected void findViews() {


    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {
        tempTimeUtil = new TempTimeUtil(60000, 1000, send_code);
        tempRegexUtil=new TempRegexUtil();
    }

    @Override
    @OnClick({R.id.toolbar_back,R.id.act_register_next,R.id.send_code})
    protected void OnViewClicked(View v) {
        switch (v.getId()){
            case R.id.toolbar_back://返回键
                finish();
                break;
            case R.id.send_code://发送验证码
                String phone1 = act_register_phone.getText().toString();
                if (TextUtils.isEmpty(phone1)){
                    showToast("请输入电话");
                    return;
                }
                if (!tempRegexUtil.checkMobile(phone1)){
                    showToast("请输入正确的电话号码");
                    return;
                }if (!tempRegexUtil.checkMobile(phone1)){
                showToast("请输入正确的电话号码");
                return;
            }
                sendCode(phone1);
                break;
            case R.id.act_register_next://下一步
                phone = act_register_phone.getText().toString().trim();
                code = code_text.getText().toString();
                password = act_register_password.getText().toString();
                passwordAgain = act_register_password_again.getText().toString();
                if (TextUtils.isEmpty(phone)){
                    showToast("请输入电话");
                    return;
                }
                if (TextUtils.isEmpty(code)){
                    showToast("请输入验证码");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    showToast("请输入密码");
                    return;
                }
                if (password.length()<6){
                    showToast("密码不能小于六位数");
                    return;
                }
                if (TextUtils.isEmpty(passwordAgain)){
                    showToast("请再次输入密码");
                    return;
                }
                if (!password.equals(passwordAgain)){
                    showToast("两次输入密码不一致,请重新输入");
                    return;
                }
                Intent intent=new Intent(this,ActRegisterNext.class);
                /*intent.putExtra("phone",phone);//电话
                intent.putExtra("code",code);//验证码
                intent.putExtra("password",password);//密码*/
                startActivity(intent);
                SharedPreferences sf = TempApplication.getInstance().getSharedPreferences("register", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sf.edit();
                editor.putString("phone", phone);
                editor.putString("code", code);
                editor.putString("password", password);
                editor.commit();
                break;
        }
    }

    /**
     * 验证码
     */
    private void sendCode(String phone1) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).registerCode(phone1), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
            @Override
            public void onSucceed(TempResponse data) {

                if (data.getType()==1){
                    showToast(data.getMsg());
                    showDialog();
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
    public void showDialog() {
        tempTimeUtil.start();
        new AlertDialog.Builder(this).setMessage("验证码已发送，请注意查收").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show();
    }

    @Override
    public void setToolbar(Toolbar toolbarTop) {
        TextView toolbar_title = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        toolbar_title.setText("注册");
    }
}
