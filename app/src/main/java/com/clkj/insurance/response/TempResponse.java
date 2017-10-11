package com.clkj.insurance.response;

/**
 * Created by longf on 2016/1/25.
 */
public class TempResponse {

    /**
     * flag : 1
     * msg : 查询个人资料成功
     * result : {"museId":"5","museSex":"2","museImage":"0201605171408038261.jpg","musePhone":"13983574928","museAddress":"","museEmail":"","museNickName":"从你的全世界路过_甜甜","museTrueName":"","museBirthday":"2016-05-20 00:00:00.0"}
     */

    private int type;
    private String msg;
    private int flag;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
