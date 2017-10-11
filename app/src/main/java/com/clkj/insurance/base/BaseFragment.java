package com.clkj.insurance.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.clkj.insurance.R;
import com.lf.tempcore.tempFragment.TempFragment;
import com.lf.tempcore.tempUtil.statusUtil.StatusBarCompat2;

/**
 * Created by caoyang on 2017/6/29.
 */

public abstract class BaseFragment extends TempFragment {
    public abstract void setToolbar(Toolbar toolbar);


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar_top = (Toolbar) view.findViewById(R.id.toolbar_top);
        if (toolbar_top!=null){
            toolbar_top.findViewById(R.id.toolbar_back).setVisibility(View.GONE);
            TextView toolbar_title = (TextView) toolbar_top.findViewById(R.id.toolbar_title);
            FrameLayout toolbar_mess = (FrameLayout) toolbar_top.findViewById(R.id.toolbar_mess);
            toolbar_title.setText("");
            setToolbar(toolbar_top);
            if (toolbar_mess != null && toolbar_mess.getVisibility() == View.VISIBLE) {
                toolbar_mess.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        startActivity(new Intent(getActivity(), ActMessageCenter.class));
                    }
                });
            }
        }
    }
    protected void setStatusBar(Activity context, int type, Toolbar toolbarTop){
        switch (type){
            case 1:
                StatusBarCompat2.setStatusBarColor(context,toolbarTop,Color.parseColor("#00000000"));//只改变颜色
                break;
            case 2:
                StatusBarCompat2.translucentStatusBar(context,toolbarTop);//整体布局向上移 toolbar不上移 沉浸式
                break;
            case 3:
                StatusBarCompat2.translucentStatusBar(context);//整体布局向上移 沉浸式
                break;
        }

    }
    public void initData() {

    }

}
