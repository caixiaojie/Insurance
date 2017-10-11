package com.clkj.insurance;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;

import com.clkj.insurance.base.BaseActivity;
import com.clkj.insurance.fragment.FragHistory;
import com.clkj.insurance.fragment.FragHome;
import com.clkj.insurance.fragment.FragMessage;
import com.clkj.insurance.fragment.FragMine;
import com.clkj.insurance.fragment.FragTransport;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 主界面
 */
public class ActIndex extends BaseActivity {
    @Bind(R.id.rbHome)RadioButton rbHome;
    @Bind(R.id.rbMessage)RadioButton rbMessage;
    @Bind(R.id.rbHistory)RadioButton rbHistory;
    @Bind(R.id.rbMine)RadioButton rbMine;
    private Fragment[] mFragments;
    private int mIndex=0;
    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_index);
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setListeners() {
        initFragment();
    }

    @Override
    protected void bindValues() {

    }
    private void initFragment() {
        //首页
        FragHome fragHome =new FragHome();
        //消息
        FragMessage fragMessage =new FragMessage();
        //历史
        FragHistory fragHistory =new FragHistory();
        //我的
        FragMine fragMine =new FragMine();
        //运输
        FragTransport fragTransport=new FragTransport();

        //添加到数组
        mFragments = new Fragment[]{fragHome,fragMessage,fragHistory,fragMine,fragTransport};

        //开启事务

        FragmentTransaction ft =
                getSupportFragmentManager().beginTransaction();

        //添加外卖
        ft.replace(R.id.content,fragHome).commit();

        //默认设置为第0个
//        fragHome.changeStatusBar=true;
        setIndexSelected(0);


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
    @OnClick({R.id.rbHome,R.id.rbMessage,R.id.rbHistory,R.id.rbMine,R.id.transportImg})
    protected void OnViewClicked(View v) {
        switch (v.getId()){
            case R.id.rbHome://首页
                setIndexSelected(0);
                break;
            case R.id.rbMessage://消息
                setIndexSelected(1);
                break;
            case R.id.rbHistory://历史
                setIndexSelected(2);
                break;
            case R.id.rbMine://我的
                setIndexSelected(3);
                break;
            case R.id.transportImg://运输
                setIndexSelected(4);
                rbHome.setChecked(false);
                rbMessage.setChecked(false);
                rbHistory.setChecked(false);
                rbMine.setChecked(false);
                break;
        }
    }

    @Override
    public void setToolbar(Toolbar toolbarTop) {

    }
}
