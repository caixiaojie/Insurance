package com.clkj.insurance.listener;

import android.view.View;

/**
 * Created by caoyang on 2017/6/15.
 */

public interface TempBottomDialogRcvListener extends TempBottomDialogBaseListener{
    void onBottomDialogItemClick(View v, int position, String selectV, String tag);
}
