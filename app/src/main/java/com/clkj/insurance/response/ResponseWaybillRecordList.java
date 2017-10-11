package com.clkj.insurance.response;

import java.util.List;

/**
 * Created by Administrator on 2017/7/28.
 * 运单过程记录
 */

public class ResponseWaybillRecordList {
    /**
     * httpStatus : 200
     * msg : 查询成功
     * result : [{"mbimAddress":"dddd","mbimBillId":"1","mbimContent":"起运","mbimId":"1","mbimImage":"","mbimTime":"1499934717000","mbimType":"测试内容w6wz"}]
     * type : 1
     */

    private String httpStatus;
    private String msg;
    private String type;
    private List<ResultBean> result;

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * mbimAddress : dddd
         * mbimBillId : 1
         * mbimContent : 起运
         * mbimId : 1
         * mbimImage : 
         * mbimTime : 1499934717000
         * mbimType : 测试内容w6wz
         */

        private String mbimAddress;
        private String mbimBillId;
        private String mbimContent;
        private String mbimId;
        private String mbimImage;
        private String mbimTime;
        private String mbimType;

        public String getMbimAddress() {
            return mbimAddress;
        }

        public void setMbimAddress(String mbimAddress) {
            this.mbimAddress = mbimAddress;
        }

        public String getMbimBillId() {
            return mbimBillId;
        }

        public void setMbimBillId(String mbimBillId) {
            this.mbimBillId = mbimBillId;
        }

        public String getMbimContent() {
            return mbimContent;
        }

        public void setMbimContent(String mbimContent) {
            this.mbimContent = mbimContent;
        }

        public String getMbimId() {
            return mbimId;
        }

        public void setMbimId(String mbimId) {
            this.mbimId = mbimId;
        }

        public String getMbimImage() {
            return mbimImage;
        }

        public void setMbimImage(String mbimImage) {
            this.mbimImage = mbimImage;
        }

        public String getMbimTime() {
            return mbimTime;
        }

        public void setMbimTime(String mbimTime) {
            this.mbimTime = mbimTime;
        }

        public String getMbimType() {
            return mbimType;
        }

        public void setMbimType(String mbimType) {
            this.mbimType = mbimType;
        }
    }
}
