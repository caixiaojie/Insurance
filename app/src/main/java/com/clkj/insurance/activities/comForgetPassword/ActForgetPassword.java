package com.clkj.insurance.activities.comForgetPassword;

import android.content.DialogInterface;
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
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempModule.tempUtils.TempRegexUtil;
import com.lf.tempcore.tempModule.tempUtils.TempTimeUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 忘记密码
 */
public class ActForgetPassword extends BaseActivity {

    @Bind(R.id.act_forget_password_send_code)TextView act_forget_password_send_code;//发送验证码
    @Bind(R.id.act_forget_password_phone)TextView act_forget_password_phone;//电话
    @Bind(R.id.act_forget_password_code)EditText act_forget_password_code;//验证码
    @Bind(R.id.act_forget_password_password)EditText act_forget_password_password;//密码
    @Bind(R.id.act_forget_password_password_again)EditText act_forget_password_password_again;//再次输入密码

    private TempTimeUtil tempTimeUtil;
    private TempRegexUtil tempRegexUtil;
    private String code;
    private String password;
    private String passwordAgain;
    private String phone;
    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_forget_password);
        if (TempLoginConfig.sf_getUserPhone()!=null){
            act_forget_password_phone.setText(TempLoginConfig.sf_getUserPhone());
        }
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {
        tempTimeUtil = new TempTimeUtil(60000, 1000, act_forget_password_send_code);
        tempRegexUtil=new TempRegexUtil();
    }

    @Override
    @OnClick({R.id.toolbar_back,R.id.act_forget_password_send_code,R.id.act_forget_password_sure})
    protected void OnViewClicked(View v) {
        switch (v.getId()){
            case R.id.toolbar_back://返回键
                finish();
                break;
            case R.id.act_forget_password_send_code://发送验证码
                String phone1 = act_forget_password_phone.getText().toString();
                /*if (TextUtils.isEmpty(phone1)){
                    showToast("请输入电话");
                    return;
                }
                if (!tempRegexUtil.checkMobile(phone1)){
                    showToast("请输入正确的电话号码");
                    return;
                }*/
                sendCode(phone1);
                break;
            case R.id.act_forget_password_sure://确认
                phone = act_forget_password_phone.getText().toString();
                code = act_forget_password_code.getText().toString();
                password = act_forget_password_password.getText().toString();
                passwordAgain = act_forget_password_password_again.getText().toString();
                /*if (TextUtils.isEmpty(phone)){
                    showToast("请输入电话");
                    return;
                }*/
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
                /*Intent intent=new Intent(this,ActLogin.class);
                intent.putExtra("phone",phone);//电话
                intent.putExtra("code",code);//验证码
                intent.putExtra("password",password);//密码
                startActivity(intent);*/
                break;
        }
    }

    /**
     * 验证码
     */
    private void sendCode(String phone1) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).forgetPwdCode(phone1), new TempRemoteApiFactory.OnCallBack<TempResponse>() {
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
        toolbar_title.setText("忘记密码");
    }
}
