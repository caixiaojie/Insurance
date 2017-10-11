package com.clkj.insurance.response;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public class ResponseCaseMessageList extends TempResponse{

    /**
     * httpStatus : 200
     * result : {"currentPage":1,"getPropertyMethods":1,"orderProperty":1,"orderType":" asc ","pageRecord":[{"memberUser":1,"mfflReadTime":1,"mffoContent":"补传资料","mffoFormId":2,"mffoId":1,"mffoMemberId":1,"mffoSuggestedType":77407,"mffoSuggestlType":38145,"mffoTime":"1500880675000","sysCase":1}],"pageSize":10,"totalPages":1,"totalResults":1}
     */

    private String httpStatus;
    private ResultBean result;

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * currentPage : 1
         * getPropertyMethods : 1
         * orderProperty : 1
         * orderType :  asc 
         * pageRecord : [{"memberUser":1,"mfflReadTime":1,"mffoContent":"补传资料","mffoFormId":2,"mffoId":1,"mffoMemberId":1,"mffoSuggestedType":77407,"mffoSuggestlType":38145,"mffoTime":"1500880675000","sysCase":1}]
         * pageSize : 10
         * totalPages : 1
         * totalResults : 1
         */

        private String currentPage;
        private String getPropertyMethods;
        private String orderProperty;
        private String orderType;
        private String pageSize;
        private String totalPages;
        private String totalResults;
        private List<PageRecordBean> pageRecord;

        public String getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(String currentPage) {
            this.currentPage = currentPage;
        }

        public String getGetPropertyMethods() {
            return getPropertyMethods;
        }

        public void setGetPropertyMethods(String getPropertyMethods) {
            this.getPropertyMethods = getPropertyMethods;
        }

        public String getOrderProperty() {
            return orderProperty;
        }

        public void setOrderProperty(String orderProperty) {
            this.orderProperty = orderProperty;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public String getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(String totalPages) {
            this.totalPages = totalPages;
        }

        public String getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(String totalResults) {
            this.totalResults = totalResults;
        }

        public List<PageRecordBean> getPageRecord() {
            return pageRecord;
        }

        public void setPageRecord(List<PageRecordBean> pageRecord) {
            this.pageRecord = pageRecord;
        }

        public static class PageRecordBean {
            /**
             * memberUser : 1
             * mfflReadTime : 1
             * mffoContent : 补传资料
             * mffoFormId : 2
             * mffoId : 1
             * mffoMemberId : 1
             * mffoSuggestedType : 77407
             * mffoSuggestlType : 38145
             * mffoTime : 1500880675000
             * sysCase : 1
             */

            private String memberUser;
            private String mfflReadTime;
            private String mffoContent;
            private String mffoFormId;
            private String mffoId;
            private String mffoMemberId;
            private String mffoSuggestedType;
            private String mffoSuggestlType;
            private String mffoTime;
            private String sysCase;

            public String getMemberUser() {
                return memberUser;
            }

            public void setMemberUser(String memberUser) {
                this.memberUser = memberUser;
            }

            public String getMfflReadTime() {
                return mfflReadTime;
            }

            public void setMfflReadTime(String mfflReadTime) {
                this.mfflReadTime = mfflReadTime;
            }

            public String getMffoContent() {
                return mffoContent;
            }

            public void setMffoContent(String mffoContent) {
                this.mffoContent = mffoContent;
            }

            public String getMffoFormId() {
                return mffoFormId;
            }

            public void setMffoFormId(String mffoFormId) {
                this.mffoFormId = mffoFormId;
            }

            public String getMffoId() {
                return mffoId;
            }

            public void setMffoId(String mffoId) {
                this.mffoId = mffoId;
            }

            public String getMffoMemberId() {
                return mffoMemberId;
            }

            public void setMffoMemberId(String mffoMemberId) {
                this.mffoMemberId = mffoMemberId;
            }

            public String getMffoSuggestedType() {
                return mffoSuggestedType;
            }

            public void setMffoSuggestedType(String mffoSuggestedType) {
                this.mffoSuggestedType = mffoSuggestedType;
            }

            public String getMffoSuggestlType() {
                return mffoSuggestlType;
            }

            public void setMffoSuggestlType(String mffoSuggestlType) {
                this.mffoSuggestlType = mffoSuggestlType;
            }

            public String getMffoTime() {
                return mffoTime;
            }

            public void setMffoTime(String mffoTime) {
                this.mffoTime = mffoTime;
            }

            public String getSysCase() {
                return sysCase;
            }

            public void setSysCase(String sysCase) {
                this.sysCase = sysCase;
            }
        }
    }
}
