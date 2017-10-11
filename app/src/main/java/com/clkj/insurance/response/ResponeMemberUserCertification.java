package com.clkj.insurance.response;

/**
 * Created by Administrator on 2017/7/20.
 */

public class ResponeMemberUserCertification {
    /**
     * httpStatus : 200
     * msg : 查询成功
     * result : {"mcinBackIdcard":1,"mcinConfirmTime":"1500432484000","mcinCreateTime":"1500432384000","mcinDetailAddress":1,"mcinDriveLicenseCopy":1,"mcinDriveLicenseIfront":1,"mcinDriverPhotograph":1,"mcinId":1,"mcinIdcardNumber":1,"mcinIfrontIdcard":1,"mcinMemberId":2,"mcinMoveLicenseCopy":1,"mcinMoveLicenseInfront":1,"mcinSelfImage":1,"mcinStatus":35030,"mcinSysuserId":1,"mcinUserName":1,"memberUser":1,"sysUser":1}
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
         * mcinBackIdcard : 1
         * mcinConfirmTime : 1500432484000
         * mcinCreateTime : 1500432384000
         * mcinDetailAddress : 1
         * mcinDriveLicenseCopy : 1
         * mcinDriveLicenseIfront : 1
         * mcinDriverPhotograph : 1
         * mcinId : 1
         * mcinIdcardNumber : 1
         * mcinIfrontIdcard : 1
         * mcinMemberId : 2
         * mcinMoveLicenseCopy : 1
         * mcinMoveLicenseInfront : 1
         * mcinSelfImage : 1
         * mcinStatus : 35030
         * mcinSysuserId : 1
         * mcinUserName : 1
         * memberUser : 1
         * sysUser : 1
         */

        private String mcinBackIdcard;
        private String mcinConfirmTime;
        private String mcinCreateTime;
        private String mcinDetailAddress;
        private String mcinDriveLicenseCopy;
        private String mcinDriveLicenseIfront;
        private String mcinDriverPhotograph;
        private String mcinId;
        private String mcinIdcardNumber;
        private String mcinIfrontIdcard;
        private String mcinMemberId;
        private String mcinMoveLicenseCopy;
        private String mcinMoveLicenseInfront;
        private String mcinSelfImage;
        private String mcinStatus;
        private String mcinSysuserId;
        private String mcinUserName;
        private String memberUser;
        private String sysUser;

        public String getMcinBackIdcard() {
            return mcinBackIdcard;
        }

        public void setMcinBackIdcard(String mcinBackIdcard) {
            this.mcinBackIdcard = mcinBackIdcard;
        }

        public String getMcinConfirmTime() {
            return mcinConfirmTime;
        }

        public void setMcinConfirmTime(String mcinConfirmTime) {
            this.mcinConfirmTime = mcinConfirmTime;
        }

        public String getMcinCreateTime() {
            return mcinCreateTime;
        }

        public void setMcinCreateTime(String mcinCreateTime) {
            this.mcinCreateTime = mcinCreateTime;
        }

        public String getMcinDetailAddress() {
            return mcinDetailAddress;
        }

        public void setMcinDetailAddress(String mcinDetailAddress) {
            this.mcinDetailAddress = mcinDetailAddress;
        }

        public String getMcinDriveLicenseCopy() {
            return mcinDriveLicenseCopy;
        }

        public void setMcinDriveLicenseCopy(String mcinDriveLicenseCopy) {
            this.mcinDriveLicenseCopy = mcinDriveLicenseCopy;
        }

        public String getMcinDriveLicenseIfront() {
            return mcinDriveLicenseIfront;
        }

        public void setMcinDriveLicenseIfront(String mcinDriveLicenseIfront) {
            this.mcinDriveLicenseIfront = mcinDriveLicenseIfront;
        }

        public String getMcinDriverPhotograph() {
            return mcinDriverPhotograph;
        }

        public void setMcinDriverPhotograph(String mcinDriverPhotograph) {
            this.mcinDriverPhotograph = mcinDriverPhotograph;
        }

        public String getMcinId() {
            return mcinId;
        }

        public void setMcinId(String mcinId) {
            this.mcinId = mcinId;
        }

        public String getMcinIdcardNumber() {
            return mcinIdcardNumber;
        }

        public void setMcinIdcardNumber(String mcinIdcardNumber) {
            this.mcinIdcardNumber = mcinIdcardNumber;
        }

        public String getMcinIfrontIdcard() {
            return mcinIfrontIdcard;
        }

        public void setMcinIfrontIdcard(String mcinIfrontIdcard) {
            this.mcinIfrontIdcard = mcinIfrontIdcard;
        }

        public String getMcinMemberId() {
            return mcinMemberId;
        }

        public void setMcinMemberId(String mcinMemberId) {
            this.mcinMemberId = mcinMemberId;
        }

        public String getMcinMoveLicenseCopy() {
            return mcinMoveLicenseCopy;
        }

        public void setMcinMoveLicenseCopy(String mcinMoveLicenseCopy) {
            this.mcinMoveLicenseCopy = mcinMoveLicenseCopy;
        }

        public String getMcinMoveLicenseInfront() {
            return mcinMoveLicenseInfront;
        }

        public void setMcinMoveLicenseInfront(String mcinMoveLicenseInfront) {
            this.mcinMoveLicenseInfront = mcinMoveLicenseInfront;
        }

        public String getMcinSelfImage() {
            return mcinSelfImage;
        }

        public void setMcinSelfImage(String mcinSelfImage) {
            this.mcinSelfImage = mcinSelfImage;
        }

        public String getMcinStatus() {
            return mcinStatus;
        }

        public void setMcinStatus(String mcinStatus) {
            this.mcinStatus = mcinStatus;
        }

        public String getMcinSysuserId() {
            return mcinSysuserId;
        }

        public void setMcinSysuserId(String mcinSysuserId) {
            this.mcinSysuserId = mcinSysuserId;
        }

        public String getMcinUserName() {
            return mcinUserName;
        }

        public void setMcinUserName(String mcinUserName) {
            this.mcinUserName = mcinUserName;
        }

        public String getMemberUser() {
            return memberUser;
        }

        public void setMemberUser(String memberUser) {
            this.memberUser = memberUser;
        }

        public String getSysUser() {
            return sysUser;
        }

        public void setSysUser(String sysUser) {
            this.sysUser = sysUser;
        }
    }
}
