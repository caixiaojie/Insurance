package com.clkj.insurance.base;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.clkj.insurance.R;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempUtil.statusUtil.StatusBarCompat;
import com.lf.tempcore.tempUtil.statusUtil.StatusBarCompat2;


/**
 * Created by caoyang on 2017/6/22.
 */

public abstract class BaseActivity extends TempActivity {


    private Toolbar toolbarTop;

    public abstract void setToolbar(Toolbar toolbarTop);

    public Toolbar getToolbarTop() {
        return toolbarTop;
    }

    @Override
    protected void returnBack() {
        super.returnBack();
        Toolbar toolbarTop = getViewById(R.id.toolbar_top);
        if (toolbarTop != null) {
            toolbarTop.setTitle("");
            toolbarTop.setBackgroundColor(getResources().getColor(R.color.color_toolbar));
            toolbarTop.setNavigationIcon(null);

            TextView mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
            ImageView mBack = (ImageView) toolbarTop.findViewById(R.id.toolbar_back);
            FrameLayout toolbar_mess = (FrameLayout) toolbarTop.findViewById(R.id.toolbar_mess);
            if (mTitle != null) {
                mTitle.setText("");
                mTitle.setTextColor(getResources().getColor(R.color.color_white));
            }
            setSupportActionBar(toolbarTop);
            if (mBack!=null){
                mBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }

            this.toolbarTop = toolbarTop;
            setToolbar(toolbarTop);
            if (toolbar_mess != null && toolbar_mess.getVisibility() == View.VISIBLE) {
                toolbar_mess.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        startActivity(new Intent(getBaseContext(), ActMessageCenter.class));
                    }
                });
            }
        }
    }

    /**
     * 查找View
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) findViewById(id);
    }


    protected int getColorById(@ColorRes int id) {
        return ActivityCompat.getColor(getBaseContext(), id);

    }
    protected Drawable getDrawableById(@DrawableRes int id) {
        return ActivityCompat.getDrawable(getBaseContext(), id);

    }
    protected void setStatusBar(int type){
       switch (type){
           case 1:
               StatusBarCompat.setStatusBarColor(this,getColorById(R.color.white));//只改变颜色
               break;
           case 2:
               StatusBarCompat2.setStatusBarColor(this,toolbarTop, getColorById(R.color.temp_transparent));//整体布局向上移 toolbar不上移 沉浸式
               break;
           case 3:
               StatusBarCompat2.translucentStatusBar(this);//整体布局向上移 沉浸式
               break;
       }

    }

}
