package com.clkj.insurance.activities.comChangePassword;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.clkj.insurance.R;
import com.clkj.insurance.base.BaseActivity;

import butterknife.OnClick;

/**
 * 修改密码
 */
public class ActChangePassword extends BaseActivity {

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_change_password);
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {

    }

    @Override
    @OnClick({R.id.toolbar_back,R.id.act_change_password_sure})
    protected void OnViewClicked(View v) {
        switch (v.getId()){
            case R.id.toolbar_back://返回键
                finish();
                break;
            case R.id.act_change_password_sure://确认
                break;
        }
    }

    @Override
    public void setToolbar(Toolbar toolbarTop) {
        TextView toolbar_title = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        toolbar_title.setText("修改密码");
    }
}
