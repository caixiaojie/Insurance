package com.clkj.insurance.activities.comReview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.clkj.insurance.R;
import com.clkj.insurance.base.BaseActivity;

import butterknife.OnClick;

/**
 * 认证信息 审核失败
 */
public class ActReviewFail extends BaseActivity {
    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_review_fail);
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
    @OnClick({R.id.toolbar_back,R.id.act_review_fail_reapply})
    protected void OnViewClicked(View v) {
        switch (v.getId()){
            case R.id.toolbar_back://返回键
                finish();
                break;
            case R.id.act_review_fail_reapply://重新认证
                startActivity(new Intent(this,ActUnderReview.class));
                break;
        }
    }

    @Override
    public void setToolbar(Toolbar toolbarTop) {
        TextView toolbar_title = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        toolbar_title.setText("认证信息");
    }
}
