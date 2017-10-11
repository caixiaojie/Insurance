package com.clkj.insurance.response;

/**
 * Created by Administrator on 2017/7/27.
 */

public class ReponseMessage {


    /**
     * result : {"count":1}
     * httpStatus : 200
     * type : 1
     * msg : 查询成功
     */

    private ResultBean result;
    private int httpStatus;
    private int type;
    private String msg;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

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

    public static class ResultBean {
        /**
         * count : 1
         */

        private String count;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }
}
