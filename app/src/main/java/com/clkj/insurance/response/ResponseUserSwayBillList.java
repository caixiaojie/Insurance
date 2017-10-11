package com.clkj.insurance.response;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public class ResponseUserSwayBillList extends TempResponse{


    /**
     * result : {"currentPage":1,"pageSize":10,"totalPages":1,"totalResults":2,"orderProperty":null,"orderType":" asc ","pageRecord":[{"swayId":3,"swayFormId":3,"sysDeclarationForm":{"sdfoId":3,"sdfoMechanismId":50012600,"sysMechanism":{"smecId":50012600,"smecTypeId":1,"sysMechanismType":{"smtyId":1,"smtyParentId":null,"sysMechanismType":null,"smtyName":"1","smtyLevel":1},"smecMechainismId":null,"sysMechanism":null,"smecCode":null,"smecName":null},"sdfoNumber":"PYDB20175001Q000G00164","sdfoUserName":"买家二号","sdfoUserPhone":"18500000006","sdfoBeenUserName":"买家二号","sdfoSignBillTime":"2017-06-29 00:00:00","sdfoFromPaulTime":"2017-10-29","sdfoEndPaulTime":"2017-10-29","sdfoFromAddress":"北京市朝阳区","sdfoEndAddress":"北京市西城区","sdfoTransDistance":null,"sdfoPigNumber":null,"sdfoPrice":90,"sdfoMoney":26400,"sdfoSingleIndemnityLimit":null,"sdfoCode":"YDB1000001","sdfoSpecialAppoString":null,"adfoCreateTime":"1500544483000","sdfoStatus":0},"swayBillCode":null,"swayActualFromlLng":null,"swayActualFromLat":null,"swayActualFromAddress":"北京市朝阳区","swayActualEndLng":null,"swayActualEndLat":null,"swayActualEndAdress":"北京市西城区","swayFromTime":"1500541604000","swayEndTime":"1500519496000","swayActualDriver":"测试车主五","swayActualLicensePlate":"京A99999","swayActualDriverPhone":"18883880573","swayPigNumber":null,"swayBillStatus":1,"swayCreateTime":"1500519427000","swayStatus":0,"swayDriverPhone":"18883880573","swayLicensePlate":"京A99999","swayDriverName":"测试车主五","swayDistance":null},{"swayId":4,"swayFormId":3,"sysDeclarationForm":{"sdfoId":3,"sdfoMechanismId":50012600,"sysMechanism":{"smecId":50012600,"smecTypeId":1,"sysMechanismType":{"smtyId":1,"smtyParentId":null,"sysMechanismType":null,"smtyName":"1","smtyLevel":1},"smecMechainismId":null,"sysMechanism":null,"smecCode":null,"smecName":null},"sdfoNumber":"PYDB20175001Q000G00164","sdfoUserName":"买家二号","sdfoUserPhone":"18500000006","sdfoBeenUserName":"买家二号","sdfoSignBillTime":"2017-06-29 00:00:00","sdfoFromPaulTime":"2017-10-29","sdfoEndPaulTime":"2017-10-29","sdfoFromAddress":"北京市朝阳区","sdfoEndAddress":"北京市西城区","sdfoTransDistance":null,"sdfoPigNumber":null,"sdfoPrice":90,"sdfoMoney":26400,"sdfoSingleIndemnityLimit":null,"sdfoCode":"YDB1000001","sdfoSpecialAppoString":null,"adfoCreateTime":"1500544483000","sdfoStatus":0},"swayBillCode":null,"swayActualFromlLng":null,"swayActualFromLat":null,"swayActualFromAddress":"北京市朝阳区","swayActualEndLng":null,"swayActualEndLat":null,"swayActualEndAdress":"北京市西城区","swayFromTime":"1500541605000","swayEndTime":"1500519527000","swayActualDriver":"张洪岳","swayActualLicensePlate":"京A12345","swayActualDriverPhone":"18883880573","swayPigNumber":null,"swayBillStatus":1,"swayCreateTime":"1500519563000","swayStatus":0,"swayDriverPhone":"18883880573","swayLicensePlate":"京A12345","swayDriverName":"张洪岳","swayDistance":null}],"getPropertyMethods":null}
     * httpStatus : 200
     */

    private ResultBean result;
    private String httpStatus;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    public static class ResultBean {
        /**
         * currentPage : 1
         * pageSize : 10
         * totalPages : 1
         * totalResults : 2
         * orderProperty : null
         * orderType :  asc 
         * pageRecord : [{"swayId":3,"swayFormId":3,"sysDeclarationForm":{"sdfoId":3,"sdfoMechanismId":50012600,"sysMechanism":{"smecId":50012600,"smecTypeId":1,"sysMechanismType":{"smtyId":1,"smtyParentId":null,"sysMechanismType":null,"smtyName":"1","smtyLevel":1},"smecMechainismId":null,"sysMechanism":null,"smecCode":null,"smecName":null},"sdfoNumber":"PYDB20175001Q000G00164","sdfoUserName":"买家二号","sdfoUserPhone":"18500000006","sdfoBeenUserName":"买家二号","sdfoSignBillTime":"2017-06-29 00:00:00","sdfoFromPaulTime":"2017-10-29","sdfoEndPaulTime":"2017-10-29","sdfoFromAddress":"北京市朝阳区","sdfoEndAddress":"北京市西城区","sdfoTransDistance":null,"sdfoPigNumber":null,"sdfoPrice":90,"sdfoMoney":26400,"sdfoSingleIndemnityLimit":null,"sdfoCode":"YDB1000001","sdfoSpecialAppoString":null,"adfoCreateTime":"1500544483000","sdfoStatus":0},"swayBillCode":null,"swayActualFromlLng":null,"swayActualFromLat":null,"swayActualFromAddress":"北京市朝阳区","swayActualEndLng":null,"swayActualEndLat":null,"swayActualEndAdress":"北京市西城区","swayFromTime":"1500541604000","swayEndTime":"1500519496000","swayActualDriver":"测试车主五","swayActualLicensePlate":"京A99999","swayActualDriverPhone":"18883880573","swayPigNumber":null,"swayBillStatus":1,"swayCreateTime":"1500519427000","swayStatus":0,"swayDriverPhone":"18883880573","swayLicensePlate":"京A99999","swayDriverName":"测试车主五","swayDistance":null},{"swayId":4,"swayFormId":3,"sysDeclarationForm":{"sdfoId":3,"sdfoMechanismId":50012600,"sysMechanism":{"smecId":50012600,"smecTypeId":1,"sysMechanismType":{"smtyId":1,"smtyParentId":null,"sysMechanismType":null,"smtyName":"1","smtyLevel":1},"smecMechainismId":null,"sysMechanism":null,"smecCode":null,"smecName":null},"sdfoNumber":"PYDB20175001Q000G00164","sdfoUserName":"买家二号","sdfoUserPhone":"18500000006","sdfoBeenUserName":"买家二号","sdfoSignBillTime":"2017-06-29 00:00:00","sdfoFromPaulTime":"2017-10-29","sdfoEndPaulTime":"2017-10-29","sdfoFromAddress":"北京市朝阳区","sdfoEndAddress":"北京市西城区","sdfoTransDistance":null,"sdfoPigNumber":null,"sdfoPrice":90,"sdfoMoney":26400,"sdfoSingleIndemnityLimit":null,"sdfoCode":"YDB1000001","sdfoSpecialAppoString":null,"adfoCreateTime":"1500544483000","sdfoStatus":0},"swayBillCode":null,"swayActualFromlLng":null,"swayActualFromLat":null,"swayActualFromAddress":"北京市朝阳区","swayActualEndLng":null,"swayActualEndLat":null,"swayActualEndAdress":"北京市西城区","swayFromTime":"1500541605000","swayEndTime":"1500519527000","swayActualDriver":"张洪岳","swayActualLicensePlate":"京A12345","swayActualDriverPhone":"18883880573","swayPigNumber":null,"swayBillStatus":1,"swayCreateTime":"1500519563000","swayStatus":0,"swayDriverPhone":"18883880573","swayLicensePlate":"京A12345","swayDriverName":"张洪岳","swayDistance":null}]
         * getPropertyMethods : null
         */

        private String currentPage;
        private String pageSize;
        private String totalPages;
        private String totalResults;
        private String orderProperty;
        private String orderType;
        private String getPropertyMethods;
        private List<PageRecordBean> pageRecord;

        public String getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(String currentPage) {
            this.currentPage = currentPage;
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
             * swayId : 3
             * swayFormId : 3
             * sysDeclarationForm : {"sdfoId":3,"sdfoMechanismId":50012600,"sysMechanism":{"smecId":50012600,"smecTypeId":1,"sysMechanismType":{"smtyId":1,"smtyParentId":null,"sysMechanismType":null,"smtyName":"1","smtyLevel":1},"smecMechainismId":null,"sysMechanism":null,"smecCode":null,"smecName":null},"sdfoNumber":"PYDB20175001Q000G00164","sdfoUserName":"买家二号","sdfoUserPhone":"18500000006","sdfoBeenUserName":"买家二号","sdfoSignBillTime":"2017-06-29 00:00:00","sdfoFromPaulTime":"2017-10-29","sdfoEndPaulTime":"2017-10-29","sdfoFromAddress":"北京市朝阳区","sdfoEndAddress":"北京市西城区","sdfoTransDistance":null,"sdfoPigNumber":null,"sdfoPrice":90,"sdfoMoney":26400,"sdfoSingleIndemnityLimit":null,"sdfoCode":"YDB1000001","sdfoSpecialAppoString":null,"adfoCreateTime":"1500544483000","sdfoStatus":0}
             * swayBillCode : null
             * swayActualFromlLng : null
             * swayActualFromLat : null
             * swayActualFromAddress : 北京市朝阳区
             * swayActualEndLng : null
             * swayActualEndLat : null
             * swayActualEndAdress : 北京市西城区
             * swayFromTime : 1500541604000
             * swayEndTime : 1500519496000
             * swayActualDriver : 测试车主五
             * swayActualLicensePlate : 京A99999
             * swayActualDriverPhone : 18883880573
             * swayPigNumber : null
             * swayBillStatus : 1
             * swayCreateTime : 1500519427000
             * swayStatus : 0
             * swayDriverPhone : 18883880573
             * swayLicensePlate : 京A99999
             * swayDriverName : 测试车主五
             * swayDistance : null
             */

            private String swayId;
            private String swayFormId;
            private SysDeclarationFormBean sysDeclarationForm;
            private String swayBillCode;
            private String swayActualFromlLng;
            private String swayActualFromLat;
            private String swayActualFromAddress;
            private String swayActualEndLng;
            private String swayActualEndLat;
            private String swayActualEndAdress;
            private String swayFromTime;
            private String swayEndTime;
            private String swayActualDriver;
            private String swayActualLicensePlate;
            private String swayActualDriverPhone;
            private String swayPigNumber;
            private String swayBillStatus;
            private String swayCreateTime;
            private String swayStatus;
            private String swayDriverPhone;
            private String swayLicensePlate;
            private String swayDriverName;
            private String swayDistance;

            public String getSwayId() {
                return swayId;
            }

            public void setSwayId(String swayId) {
                this.swayId = swayId;
            }

            public String getSwayFormId() {
                return swayFormId;
            }

            public void setSwayFormId(String swayFormId) {
                this.swayFormId = swayFormId;
            }

            public SysDeclarationFormBean getSysDeclarationForm() {
                return sysDeclarationForm;
            }

            public void setSysDeclarationForm(SysDeclarationFormBean sysDeclarationForm) {
                this.sysDeclarationForm = sysDeclarationForm;
            }

            public String getSwayBillCode() {
                return swayBillCode;
            }

            public void setSwayBillCode(String swayBillCode) {
                this.swayBillCode = swayBillCode;
            }

            public String getSwayActualFromlLng() {
                return swayActualFromlLng;
            }

            public void setSwayActualFromlLng(String swayActualFromlLng) {
                this.swayActualFromlLng = swayActualFromlLng;
            }

            public String getSwayActualFromLat() {
                return swayActualFromLat;
            }

            public void setSwayActualFromLat(String swayActualFromLat) {
                this.swayActualFromLat = swayActualFromLat;
            }

            public String getSwayActualFromAddress() {
                return swayActualFromAddress;
            }

            public void setSwayActualFromAddress(String swayActualFromAddress) {
                this.swayActualFromAddress = swayActualFromAddress;
            }

            public String getSwayActualEndLng() {
                return swayActualEndLng;
            }

            public void setSwayActualEndLng(String swayActualEndLng) {
                this.swayActualEndLng = swayActualEndLng;
            }

            public String getSwayActualEndLat() {
                return swayActualEndLat;
            }

            public void setSwayActualEndLat(String swayActualEndLat) {
                this.swayActualEndLat = swayActualEndLat;
            }

            public String getSwayActualEndAdress() {
                return swayActualEndAdress;
            }

            public void setSwayActualEndAdress(String swayActualEndAdress) {
                this.swayActualEndAdress = swayActualEndAdress;
            }

            public String getSwayFromTime() {
                return swayFromTime;
            }

            public void setSwayFromTime(String swayFromTime) {
                this.swayFromTime = swayFromTime;
            }

            public String getSwayEndTime() {
                return swayEndTime;
            }

            public void setSwayEndTime(String swayEndTime) {
                this.swayEndTime = swayEndTime;
            }

            public String getSwayActualDriver() {
                return swayActualDriver;
            }

            public void setSwayActualDriver(String swayActualDriver) {
                this.swayActualDriver = swayActualDriver;
            }

            public String getSwayActualLicensePlate() {
                return swayActualLicensePlate;
            }

            public void setSwayActualLicensePlate(String swayActualLicensePlate) {
                this.swayActualLicensePlate = swayActualLicensePlate;
            }

            public String getSwayActualDriverPhone() {
                return swayActualDriverPhone;
            }

            public void setSwayActualDriverPhone(String swayActualDriverPhone) {
                this.swayActualDriverPhone = swayActualDriverPhone;
            }

            public String getSwayPigNumber() {
                return swayPigNumber;
            }

            public void setSwayPigNumber(String swayPigNumber) {
                this.swayPigNumber = swayPigNumber;
            }

            public String getSwayBillStatus() {
                return swayBillStatus;
            }

            public void setSwayBillStatus(String swayBillStatus) {
                this.swayBillStatus = swayBillStatus;
            }

            public String getSwayCreateTime() {
                return swayCreateTime;
            }

            public void setSwayCreateTime(String swayCreateTime) {
                this.swayCreateTime = swayCreateTime;
            }

            public String getSwayStatus() {
                return swayStatus;
            }

            public void setSwayStatus(String swayStatus) {
                this.swayStatus = swayStatus;
            }

            public String getSwayDriverPhone() {
                return swayDriverPhone;
            }

            public void setSwayDriverPhone(String swayDriverPhone) {
                this.swayDriverPhone = swayDriverPhone;
            }

            public String getSwayLicensePlate() {
                return swayLicensePlate;
            }

            public void setSwayLicensePlate(String swayLicensePlate) {
                this.swayLicensePlate = swayLicensePlate;
            }

            public String getSwayDriverName() {
                return swayDriverName;
            }

            public void setSwayDriverName(String swayDriverName) {
                this.swayDriverName = swayDriverName;
            }

            public String getSwayDistance() {
                return swayDistance;
            }

            public void setSwayDistance(String swayDistance) {
                this.swayDistance = swayDistance;
            }

            public static class SysDeclarationFormBean {
                /**
                 * sdfoId : 3
                 * sdfoMechanismId : 50012600
                 * sysMechanism : {"smecId":50012600,"smecTypeId":1,"sysMechanismType":{"smtyId":1,"smtyParentId":null,"sysMechanismType":null,"smtyName":"1","smtyLevel":1},"smecMechainismId":null,"sysMechanism":null,"smecCode":null,"smecName":null}
                 * sdfoNumber : PYDB20175001Q000G00164
                 * sdfoUserName : 买家二号
                 * sdfoUserPhone : 18500000006
                 * sdfoBeenUserName : 买家二号
                 * sdfoSignBillTime : 2017-06-29 00:00:00
                 * sdfoFromPaulTime : 2017-10-29
                 * sdfoEndPaulTime : 2017-10-29
                 * sdfoFromAddress : 北京市朝阳区
                 * sdfoEndAddress : 北京市西城区
                 * sdfoTransDistance : null
                 * sdfoPigNumber : null
                 * sdfoPrice : 90.0
                 * sdfoMoney : 26400.0
                 * sdfoSingleIndemnityLimit : null
                 * sdfoCode : YDB1000001
                 * sdfoSpecialAppoString : null
                 * adfoCreateTime : 1500544483000
                 * sdfoStatus : 0
                 */

                private String sdfoId;
                private String sdfoMechanismId;
                private SysMechanismBean sysMechanism;
                private String sdfoNumber;
                private String sdfoUserName;
                private String sdfoUserPhone;
                private String sdfoBeenUserName;
                private String sdfoSignBillTime;
                private String sdfoFromPaulTime;
                private String sdfoEndPaulTime;
                private String sdfoFromAddress;
                private String sdfoEndAddress;
                private String sdfoTransDistance;
                private String sdfoPigNumber;
                private String sdfoPrice;
                private String sdfoMoney;
                private String sdfoSingleIndemnityLimit;
                private String sdfoCode;
                private String sdfoSpecialAppoString;
                private String adfoCreateTime;
                private String sdfoStatus;

                public String getSdfoId() {
                    return sdfoId;
                }

                public void setSdfoId(String sdfoId) {
                    this.sdfoId = sdfoId;
                }

                public String getSdfoMechanismId() {
                    return sdfoMechanismId;
                }

                public void setSdfoMechanismId(String sdfoMechanismId) {
                    this.sdfoMechanismId = sdfoMechanismId;
                }

                public SysMechanismBean getSysMechanism() {
                    return sysMechanism;
                }

                public void setSysMechanism(SysMechanismBean sysMechanism) {
                    this.sysMechanism = sysMechanism;
                }

                public String getSdfoNumber() {
                    return sdfoNumber;
                }

                public void setSdfoNumber(String sdfoNumber) {
                    this.sdfoNumber = sdfoNumber;
                }

                public String getSdfoUserName() {
                    return sdfoUserName;
                }

                public void setSdfoUserName(String sdfoUserName) {
                    this.sdfoUserName = sdfoUserName;
                }

                public String getSdfoUserPhone() {
                    return sdfoUserPhone;
                }

                public void setSdfoUserPhone(String sdfoUserPhone) {
                    this.sdfoUserPhone = sdfoUserPhone;
                }

                public String getSdfoBeenUserName() {
                    return sdfoBeenUserName;
                }

                public void setSdfoBeenUserName(String sdfoBeenUserName) {
                    this.sdfoBeenUserName = sdfoBeenUserName;
                }

                public String getSdfoSignBillTime() {
                    return sdfoSignBillTime;
                }

                public void setSdfoSignBillTime(String sdfoSignBillTime) {
                    this.sdfoSignBillTime = sdfoSignBillTime;
                }

                public String getSdfoFromPaulTime() {
                    return sdfoFromPaulTime;
                }

                public void setSdfoFromPaulTime(String sdfoFromPaulTime) {
                    this.sdfoFromPaulTime = sdfoFromPaulTime;
                }

                public String getSdfoEndPaulTime() {
                    return sdfoEndPaulTime;
                }

                public void setSdfoEndPaulTime(String sdfoEndPaulTime) {
                    this.sdfoEndPaulTime = sdfoEndPaulTime;
                }

                public String getSdfoFromAddress() {
                    return sdfoFromAddress;
                }

                public void setSdfoFromAddress(String sdfoFromAddress) {
                    this.sdfoFromAddress = sdfoFromAddress;
                }

                public String getSdfoEndAddress() {
                    return sdfoEndAddress;
                }

                public void setSdfoEndAddress(String sdfoEndAddress) {
                    this.sdfoEndAddress = sdfoEndAddress;
                }

                public String getSdfoTransDistance() {
                    return sdfoTransDistance;
                }

                public void setSdfoTransDistance(String sdfoTransDistance) {
                    this.sdfoTransDistance = sdfoTransDistance;
                }

                public String getSdfoPigNumber() {
                    return sdfoPigNumber;
                }

                public void setSdfoPigNumber(String sdfoPigNumber) {
                    this.sdfoPigNumber = sdfoPigNumber;
                }

                public String getSdfoPrice() {
                    return sdfoPrice;
                }

                public void setSdfoPrice(String sdfoPrice) {
                    this.sdfoPrice = sdfoPrice;
                }

                public String getSdfoMoney() {
                    return sdfoMoney;
                }

                public void setSdfoMoney(String sdfoMoney) {
                    this.sdfoMoney = sdfoMoney;
                }

                public String getSdfoSingleIndemnityLimit() {
                    return sdfoSingleIndemnityLimit;
                }

                public void setSdfoSingleIndemnityLimit(String sdfoSingleIndemnityLimit) {
                    this.sdfoSingleIndemnityLimit = sdfoSingleIndemnityLimit;
                }

                public String getSdfoCode() {
                    return sdfoCode;
                }

                public void setSdfoCode(String sdfoCode) {
                    this.sdfoCode = sdfoCode;
                }

                public String getSdfoSpecialAppoString() {
                    return sdfoSpecialAppoString;
                }

                public void setSdfoSpecialAppoString(String sdfoSpecialAppoString) {
                    this.sdfoSpecialAppoString = sdfoSpecialAppoString;
                }

                public String getAdfoCreateTime() {
                    return adfoCreateTime;
                }

                public void setAdfoCreateTime(String adfoCreateTime) {
                    this.adfoCreateTime = adfoCreateTime;
                }

                public String getSdfoStatus() {
                    return sdfoStatus;
                }

                public void setSdfoStatus(String sdfoStatus) {
                    this.sdfoStatus = sdfoStatus;
                }

                public static class SysMechanismBean {
                    /**
                     * smecId : 50012600
                     * smecTypeId : 1
                     * sysMechanismType : {"smtyId":1,"smtyParentId":null,"sysMechanismType":null,"smtyName":"1","smtyLevel":1}
                     * smecMechainismId : null
                     * sysMechanism : null
                     * smecCode : null
                     * smecName : null
                     */

                    private String smecId;
                    private String smecTypeId;
                    private SysMechanismTypeBean sysMechanismType;
                    private String smecMechainismId;
                    private String sysMechanism;
                    private String smecCode;
                    private String smecName;

                    public String getSmecId() {
                        return smecId;
                    }

                    public void setSmecId(String smecId) {
                        this.smecId = smecId;
                    }

                    public String getSmecTypeId() {
                        return smecTypeId;
                    }

                    public void setSmecTypeId(String smecTypeId) {
                        this.smecTypeId = smecTypeId;
                    }

                    public SysMechanismTypeBean getSysMechanismType() {
                        return sysMechanismType;
                    }

                    public void setSysMechanismType(SysMechanismTypeBean sysMechanismType) {
                        this.sysMechanismType = sysMechanismType;
                    }

                    public String getSmecMechainismId() {
                        return smecMechainismId;
                    }

                    public void setSmecMechainismId(String smecMechainismId) {
                        this.smecMechainismId = smecMechainismId;
                    }

                    public String getSysMechanism() {
                        return sysMechanism;
                    }

                    public void setSysMechanism(String sysMechanism) {
                        this.sysMechanism = sysMechanism;
                    }

                    public String getSmecCode() {
                        return smecCode;
                    }

                    public void setSmecCode(String smecCode) {
                        this.smecCode = smecCode;
                    }

                    public String getSmecName() {
                        return smecName;
                    }

                    public void setSmecName(String smecName) {
                        this.smecName = smecName;
                    }

                    public static class SysMechanismTypeBean {
                        /**
                         * smtyId : 1
                         * smtyParentId : null
                         * sysMechanismType : null
                         * smtyName : 1
                         * smtyLevel : 1
                         */

                        private String smtyId;
                        private String smtyParentId;
                        private String sysMechanismType;
                        private String smtyName;
                        private String smtyLevel;

                        public String getSmtyId() {
                            return smtyId;
                        }

                        public void setSmtyId(String smtyId) {
                            this.smtyId = smtyId;
                        }

                        public String getSmtyParentId() {
                            return smtyParentId;
                        }

                        public void setSmtyParentId(String smtyParentId) {
                            this.smtyParentId = smtyParentId;
                        }

                        public String getSysMechanismType() {
                            return sysMechanismType;
                        }

                        public void setSysMechanismType(String sysMechanismType) {
                            this.sysMechanismType = sysMechanismType;
                        }

                        public String getSmtyName() {
                            return smtyName;
                        }

                        public void setSmtyName(String smtyName) {
                            this.smtyName = smtyName;
                        }

                        public String getSmtyLevel() {
                            return smtyLevel;
                        }

                        public void setSmtyLevel(String smtyLevel) {
                            this.smtyLevel = smtyLevel;
                        }
                    }
                }
            }
        }
    }
}
