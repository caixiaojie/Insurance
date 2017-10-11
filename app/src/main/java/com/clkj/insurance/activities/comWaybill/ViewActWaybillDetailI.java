package com.clkj.insurance.activities.comWaybill;

import com.clkj.insurance.interf.BaseViewI;
import com.clkj.insurance.response.ResponseBillDetail;

/**
 * Created by Administrator on 2017/7/28.
 */

public interface ViewActWaybillDetailI extends BaseViewI{
    void getBillDetailSuccess(ResponseBillDetail data);
}
