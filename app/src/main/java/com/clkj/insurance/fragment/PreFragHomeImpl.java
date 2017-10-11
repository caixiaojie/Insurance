package com.clkj.insurance.fragment;

import com.clkj.insurance.api.TempAction;
import com.clkj.insurance.response.ResponseUserSwayBillList;
import com.clkj.insurance.throwable.ExceptionEngine;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

/**
 * Created by Administrator on 2017/7/21.
 */

public class PreFragHomeImpl implements PreFragHomeI {
    private ViewFragHomeI mViewI;
    public PreFragHomeImpl(ViewFragHomeI mViewI){
        this.mViewI=mViewI;
    }
    /**
     * 查询运单通知列表
     * @param museId
     * @param museOnlineTag
     * @param musePwd
     */
    @Override
    public void queryUserSwayBillList(String museId, String museOnlineTag, String musePwd,String currentPage,int pageSize) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).queryUserSwayBillList(museId, museOnlineTag, musePwd,currentPage,pageSize),
                new TempRemoteApiFactory.OnCallBack<ResponseUserSwayBillList>() {
                    @Override
                    public void onSucceed(ResponseUserSwayBillList data) {
                        if (data.getType()==1){
                            if (mViewI!=null){
                                mViewI.queryUserSwayBillListSuccess(data);
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
