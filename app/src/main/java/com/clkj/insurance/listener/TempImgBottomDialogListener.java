package com.clkj.insurance.listener;

import android.net.Uri;

/**
 * Created by caoyang on 2017/6/15.
 */

public interface TempImgBottomDialogListener extends TempBottomDialogBaseListener{
    void photoSuccess(Uri uri);
    void photoFail(int flag);//0取消 1：失败
}
