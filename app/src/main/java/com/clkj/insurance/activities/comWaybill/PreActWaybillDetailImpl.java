package com.clkj.insurance.activities.comWaybill;

import com.clkj.insurance.api.TempAction;
import com.clkj.insurance.response.ResponseBillDetail;
import com.clkj.insurance.throwable.ExceptionEngine;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

/**
 * Created by Administrator on 2017/7/28.
 */

public class PreActWaybillDetailImpl implements PreActWaybillDetailI {
    ViewActWaybillDetailI mViewI;
    public PreActWaybillDetailImpl(ViewActWaybillDetailI mViewI){
        this.mViewI=mViewI;
    }
    /**
     * 运单详情
     * @param museId
     * @param museOnlineTag
     * @param musePwd
     * @param swayId
     */
    @Override
    public void getBillDetailSuccess(String museId, String museOnlineTag, String musePwd, String swayId) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).getBillDetail(museId, museOnlineTag, musePwd, swayId),
                new TempRemoteApiFactory.OnCallBack<ResponseBillDetail>() {
                    @Override
                    public void onSucceed(ResponseBillDetail data) {
                        if (data.getType().equals("1")){
                            if (mViewI!=null){
                                mViewI.getBillDetailSuccess(data);
                            }
                        }else {
                            if (mViewI!=null){
                                mViewI.toast(data.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mViewI!=null){
                            mViewI.showConntectError(ExceptionEngine.handleException(e));
                        }
                    }
                });
    }
}
