package com.clkj.insurance.response;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public class ResponseSysMessageList extends TempResponse{


    /**
     * result : {"currentPage":1,"pageSize":10,"totalPages":1,"totalResults":1,"orderProperty":null,"orderType":" asc ","pageRecord":[{"mmesId":1,"mmesContent":"test112","mmesCreateTime":"1500521689000","mmesObjectId":1,"mmTypeId":1,"mmIsReaded":1,"dataStr":null,"mmTypeTwo":1,"mmss":null,"mmmss":null,"mmmeReadTime":null}],"getPropertyMethods":null}
     * httpStatus : 200
     */

    private ResultBean result;
    private int httpStatus;

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

    public static class ResultBean {
        /**
         * currentPage : 1
         * pageSize : 10
         * totalPages : 1
         * totalResults : 1
         * orderProperty : null
         * orderType :  asc
         * pageRecord : [{"mmesId":1,"mmesContent":"test112","mmesCreateTime":"1500521689000","mmesObjectId":1,"mmTypeId":1,"mmIsReaded":1,"dataStr":null,"mmTypeTwo":1,"mmss":null,"mmmss":null,"mmmeReadTime":null}]
         * getPropertyMethods : null
         */

        private int currentPage;
        private int pageSize;
        private int totalPages;
        private int totalResults;
        private String orderProperty;
        private String orderType;
        private String getPropertyMethods;
        private List<PageRecordBean> pageRecord;

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(int totalResults) {
            this.totalResults = totalResults;
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

        public String getGetPropertyMethods() {
            return getPropertyMethods;
        }

        public void setGetPropertyMethods(String getPropertyMethods) {
            this.getPropertyMethods = getPropertyMethods;
        }

        public List<PageRecordBean> getPageRecord() {
            return pageRecord;
        }

        public void setPageRecord(List<PageRecordBean> pageRecord) {
            this.pageRecord = pageRecord;
        }

        public static class PageRecordBean {
            /**
             * mmesId : 1
             * mmesContent : test112
             * mmesCreateTime : 1500521689000
             * mmesObjectId : 1
             * mmTypeId : 1
             * mmIsReaded : 1
             * dataStr : null
             * mmTypeTwo : 1
             * mmss : null
             * mmmss : null
             * mmmeReadTime : null
             */

            private String mmesId;
            private String mmesContent;
            private String mmesCreateTime;
            private String mmesObjectId;
            private String mmTypeId;
            private String mmIsReaded;
            private String dataStr;
            private String mmTypeTwo;
            private String mmss;
            private String mmmss;
            private String mmmeReadTime;

            public String getMmesId() {
                return mmesId;
            }

            public void setMmesId(String mmesId) {
                this.mmesId = mmesId;
            }

            public String getMmesContent() {
                return mmesContent;
            }

            public void setMmesContent(String mmesContent) {
                this.mmesContent = mmesContent;
            }

            public String getMmesCreateTime() {
                return mmesCreateTime;
            }

            public void setMmesCreateTime(String mmesCreateTime) {
                this.mmesCreateTime = mmesCreateTime;
            }

            public String getMmesObjectId() {
                return mmesObjectId;
            }

            public void setMmesObjectId(String mmesObjectId) {
                this.mmesObjectId = mmesObjectId;
            }

            public String getMmTypeId() {
                return mmTypeId;
            }

            public void setMmTypeId(String mmTypeId) {
                this.mmTypeId = mmTypeId;
            }

            public String getMmIsReaded() {
                return mmIsReaded;
            }

            public void setMmIsReaded(String mmIsReaded) {
                this.mmIsReaded = mmIsReaded;
            }

            public String getDataStr() {
                return dataStr;
            }

            public void setDataStr(String dataStr) {
                this.dataStr = dataStr;
            }

            public String getMmTypeTwo() {
                return mmTypeTwo;
            }

            public void setMmTypeTwo(String mmTypeTwo) {
                this.mmTypeTwo = mmTypeTwo;
            }

            public String getMmss() {
                return mmss;
            }

            public void setMmss(String mmss) {
                this.mmss = mmss;
            }

            public String getMmmss() {
                return mmmss;
            }

            public void setMmmss(String mmmss) {
                this.mmmss = mmmss;
            }

            public String getMmmeReadTime() {
                return mmmeReadTime;
            }

            public void setMmmeReadTime(String mmmeReadTime) {
                this.mmmeReadTime = mmmeReadTime;
            }
        }
    }
}
