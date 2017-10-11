package com.clkj.insurance.response;

/**
 * Created by Administrator on 2017/7/28.
 * 冲水提醒
 */

public class ResponseWaterRemind {
    /**
     * httpStatus : 200
     * msg : 保存成功
     * result : {"memberUser":1,"mmIsReaded":51734,"mmmeContent":"冲水时间到了","mmmeCreateTime":"1501119716160","mmmeId":2,"mmmeMemberId":2,"mmmeObjectId":1,"mmmeReadTime":1,"mmmeTypeId":1}
     * type : 1
     */

    private String httpStatus;
    private String msg;
    private ResultBean result;
    private String type;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class ResultBean {
        /**
         * memberUser : 1
         * mmIsReaded : 51734
         * mmmeContent : 冲水时间到了
         * mmmeCreateTime : 1501119716160
         * mmmeId : 2
         * mmmeMemberId : 2
         * mmmeObjectId : 1
         * mmmeReadTime : 1
         * mmmeTypeId : 1
         */

        private String memberUser;
        private String mmIsReaded;
        private String mmmeContent;
        private String mmmeCreateTime;
        private String mmmeId;
        private String mmmeMemberId;
        private String mmmeObjectId;
        private String mmmeReadTime;
        private String mmmeTypeId;

        public String getMemberUser() {
            return memberUser;
        }

        public void setMemberUser(String memberUser) {
            this.memberUser = memberUser;
        }

        public String getMmIsReaded() {
            return mmIsReaded;
        }

        public void setMmIsReaded(String mmIsReaded) {
            this.mmIsReaded = mmIsReaded;
        }

        public String getMmmeContent() {
            return mmmeContent;
        }

        public void setMmmeContent(String mmmeContent) {
            this.mmmeContent = mmmeContent;
        }

        public String getMmmeCreateTime() {
            return mmmeCreateTime;
        }

        public void setMmmeCreateTime(String mmmeCreateTime) {
            this.mmmeCreateTime = mmmeCreateTime;
        }

        public String getMmmeId() {
            return mmmeId;
        }

        public void setMmmeId(String mmmeId) {
            this.mmmeId = mmmeId;
        }

        public String getMmmeMemberId() {
            return mmmeMemberId;
        }

        public void setMmmeMemberId(String mmmeMemberId) {
            this.mmmeMemberId = mmmeMemberId;
        }

        public String getMmmeObjectId() {
            return mmmeObjectId;
        }

        public void setMmmeObjectId(String mmmeObjectId) {
            this.mmmeObjectId = mmmeObjectId;
        }

        public String getMmmeReadTime() {
            return mmmeReadTime;
        }

        public void setMmmeReadTime(String mmmeReadTime) {
            this.mmmeReadTime = mmmeReadTime;
        }

        public String getMmmeTypeId() {
            return mmmeTypeId;
        }

        public void setMmmeTypeId(String mmmeTypeId) {
            this.mmmeTypeId = mmmeTypeId;
        }
    }
}
