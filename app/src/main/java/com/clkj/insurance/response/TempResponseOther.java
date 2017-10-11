package com.clkj.insurance.response;

/**
 * Created by Administrator on 2017/7/19.
 */

public class TempResponseOther {
    /**
     * flag : 1
     * msg : 登录成功
     * result : 1
     */

    private int flag;
    private String msg;
    private int result;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
