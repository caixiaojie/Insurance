package com.clkj.insurance.activities.comMessage;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.clkj.insurance.R;
import com.clkj.insurance.base.BaseActivity;

import butterknife.OnClick;

/**
 * 消息详情
 */
public class ActMessageDetail extends BaseActivity {
    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_message_detail);
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
    @OnClick({R.id.toolbar_back})
    protected void OnViewClicked(View v) {
        switch (v.getId()){
            case R.id.toolbar_back://返回键
                finish();
                break;
        }
    }

    @Override
    public void setToolbar(Toolbar toolbarTop) {
        TextView toolbar_title = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        toolbar_title.setText("消息详情");
    }
}
