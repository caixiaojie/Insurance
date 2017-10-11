package com.clkj.insurance.activities.comReport;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.clkj.insurance.R;
import com.clkj.insurance.base.BaseActivity;
import com.clkj.insurance.fragment.FragEmergency;
import com.clkj.insurance.fragment.FragTrafficAccident;

import butterknife.OnClick;

/**
 * 报案
 */
public class ActReport extends BaseActivity  {

    private Fragment[] mFragments;
    private int mIndex=0;
    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_report);
    }

    @Override
    protected void findViews() {
        initFragment();
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {

    }

    @Override
    @OnClick({R.id.toolbar_back,R.id.act_report_yj,R.id.act_report_jtyw})
    protected void OnViewClicked(View v) {
        switch (v.getId()){
            case R.id.toolbar_back://返回键
                finish();
                break;
            case R.id.act_report_yj://应急
                setIndexSelected(0);
                break;
            case R.id.act_report_jtyw://交通意外
                setIndexSelected(1);
                break;
        }
    }

    private void initFragment() {
        //应急
        FragEmergency fragEmergency =new FragEmergency();
        //交通意外
        FragTrafficAccident fragTrafficAccident =new FragTrafficAccident();

        //添加到数组
        mFragments = new Fragment[]{fragEmergency,fragTrafficAccident};

        //开启事务

        FragmentTransaction ft =
                getSupportFragmentManager().beginTransaction();

        //添加外卖
        ft.replace(R.id.content,fragEmergency).commit();

        //默认设置为第0个
//        fragHome.changeStatusBar=true;
        setIndexSelected(1);


    }



    private void setIndexSelected(int index) {

        if(mIndex==index){
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft              = fragmentManager.beginTransaction();


        //隐藏
        ft.hide(mFragments[mIndex]);
        //判断是否添加
        if(!mFragments[index].isAdded()){
            ft.add(R.id.content,mFragments[index]).show(mFragments[index]);
        }else {
            ft.show(mFragments[index]);
        }

        ft.commit();
        //再次赋值
        mIndex=index;

    }




    @Override
    public void setToolbar(Toolbar toolbarTop) {
        TextView toolbar_title = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        toolbar_title.setText("报案");
    }

}
