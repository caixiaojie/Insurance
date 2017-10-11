package com.clkj.insurance.fragment;

import com.clkj.insurance.interf.BaseViewI;
import com.clkj.insurance.response.ResponseUserSwayBillList;

/**
 * Created by Administrator on 2017/7/21.
 */

public interface ViewFragHomeI extends BaseViewI{
    void queryUserSwayBillListSuccess(ResponseUserSwayBillList data);
}
