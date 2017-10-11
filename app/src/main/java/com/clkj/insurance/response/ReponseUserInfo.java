package com.clkj.insurance.response;

/**
 * Created by Administrator on 2017/7/19.
 * 用户信息实体
 */

public class ReponseUserInfo {

    /**
     * result : {"museId":38,"museName":"付","musePassword":"e10adc3949ba59abbe56e057f20f883e","musePhone":"18883880573","museAddress":"内蒙古呼和浩特市回民区","museOnlineTag":"","museRegisterTime":"1500454189000","museFirstFormTime":null,"museAreaName":"内蒙古呼和浩特市回民区","museAreaId":30352,"sysArea":{"sysaId":30352,"sysaParentId":20027,"sysArea":{"sysaId":20027,"sysaParentId":10005,"sysArea":{"sysaId":10005,"sysaParentId":2,"sysArea":{"sysaId":2,"sysaParentId":1,"sysArea":{"sysaId":1,"sysaParentId":null,"sysArea":null,"sysaName":"世界","sysaLevel":1,"sysaRelation":null,"sysaDescription":null,"sysaSort":null,"sysaLat":null,"sysaLng":null,"sysaPinyin":"sj"},"sysaName":"中国","sysaLevel":2,"sysaRelation":"|1|","sysaDescription":"中国","sysaSort":null,"sysaLat":null,"sysaLng":null,"sysaPinyin":"zg"},"sysaName":"内蒙古","sysaLevel":3,"sysaRelation":null,"sysaDescription":"内蒙古","sysaSort":null,"sysaLat":null,"sysaLng":null,"sysaPinyin":"nmg"},"sysaName":"呼和浩特市","sysaLevel":4,"sysaRelation":null,"sysaDescription":"呼和浩特市","sysaSort":null,"sysaLat":null,"sysaLng":null,"sysaPinyin":"hhhts"},"sysaName":"回民区","sysaLevel":5,"sysaRelation":null,"sysaDescription":"回民区","sysaSort":null,"sysaLat":null,"sysaLng":null,"sysaPinyin":"hmq"},"museCreateTime":"1500454189000","museStatus":0,"museType":2,"museImage":null,"museLicensePlates":"渝HA5888","museConfirmFlag":null}
     * httpStatus : 200
     * type : 1
     * msg : 查询成功
     */

    private ResultBean result;
    private String httpStatus;
    private String type;
    private String msg;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
         * museId : 38
         * museName : 付
         * musePassword : e10adc3949ba59abbe56e057f20f883e
         * musePhone : 18883880573
         * museAddress : 内蒙古呼和浩特市回民区
         * museOnlineTag : 
         * museRegisterTime : 1500454189000
         * museFirstFormTime : null
         * museAreaName : 内蒙古呼和浩特市回民区
         * museAreaId : 30352
         * sysArea : {"sysaId":30352,"sysaParentId":20027,"sysArea":{"sysaId":20027,"sysaParentId":10005,"sysArea":{"sysaId":10005,"sysaParentId":2,"sysArea":{"sysaId":2,"sysaParentId":1,"sysArea":{"sysaId":1,"sysaParentId":null,"sysArea":null,"sysaName":"世界","sysaLevel":1,"sysaRelation":null,"sysaDescription":null,"sysaSort":null,"sysaLat":null,"sysaLng":null,"sysaPinyin":"sj"},"sysaName":"中国","sysaLevel":2,"sysaRelation":"|1|","sysaDescription":"中国","sysaSort":null,"sysaLat":null,"sysaLng":null,"sysaPinyin":"zg"},"sysaName":"内蒙古","sysaLevel":3,"sysaRelation":null,"sysaDescription":"内蒙古","sysaSort":null,"sysaLat":null,"sysaLng":null,"sysaPinyin":"nmg"},"sysaName":"呼和浩特市","sysaLevel":4,"sysaRelation":null,"sysaDescription":"呼和浩特市","sysaSort":null,"sysaLat":null,"sysaLng":null,"sysaPinyin":"hhhts"},"sysaName":"回民区","sysaLevel":5,"sysaRelation":null,"sysaDescription":"回民区","sysaSort":null,"sysaLat":null,"sysaLng":null,"sysaPinyin":"hmq"}
         * museCreateTime : 1500454189000
         * museStatus : 0
         * museType : 2
         * museImage : null
         * museLicensePlates : 渝HA5888
         * museConfirmFlag : null
         */

        private String museId;
        private String museName;
        private String musePassword;
        private String musePhone;
        private String museAddress;
        private String museOnlineTag;
        private String museRegisterTime;
        private String museFirstFormTime;
        private String museAreaName;
        private String museAreaId;
        private SysAreaBeanXXXX sysArea;
        private String museCreateTime;
        private String museStatus;
        private String museType;
        private String museImage;
        private String museLicensePlates;
        private String museConfirmFlag;

        public String getMuseId() {
            return museId;
        }

        public void setMuseId(String museId) {
            this.museId = museId;
        }

        public String getMuseName() {
            return museName;
        }

        public void setMuseName(String museName) {
            this.museName = museName;
        }

        public String getMusePassword() {
            return musePassword;
        }

        public void setMusePassword(String musePassword) {
            this.musePassword = musePassword;
        }

        public String getMusePhone() {
            return musePhone;
        }

        public void setMusePhone(String musePhone) {
            this.musePhone = musePhone;
        }

        public String getMuseAddress() {
            return museAddress;
        }

        public void setMuseAddress(String museAddress) {
            this.museAddress = museAddress;
        }

        public String getMuseOnlineTag() {
            return museOnlineTag;
        }

        public void setMuseOnlineTag(String museOnlineTag) {
            this.museOnlineTag = museOnlineTag;
        }

        public String getMuseRegisterTime() {
            return museRegisterTime;
        }

        public void setMuseRegisterTime(String museRegisterTime) {
            this.museRegisterTime = museRegisterTime;
        }

        public String getMuseFirstFormTime() {
            return museFirstFormTime;
        }

        public void setMuseFirstFormTime(String museFirstFormTime) {
            this.museFirstFormTime = museFirstFormTime;
        }

        public String getMuseAreaName() {
            return museAreaName;
        }

        public void setMuseAreaName(String museAreaName) {
            this.museAreaName = museAreaName;
        }

        public String getMuseAreaId() {
            return museAreaId;
        }

        public void setMuseAreaId(String museAreaId) {
            this.museAreaId = museAreaId;
        }

        public SysAreaBeanXXXX getSysArea() {
            return sysArea;
        }

        public void setSysArea(SysAreaBeanXXXX sysArea) {
            this.sysArea = sysArea;
        }

        public String getMuseCreateTime() {
            return museCreateTime;
        }

        public void setMuseCreateTime(String museCreateTime) {
            this.museCreateTime = museCreateTime;
        }

        public String getMuseStatus() {
            return museStatus;
        }

        public void setMuseStatus(String museStatus) {
            this.museStatus = museStatus;
        }

        public String getMuseType() {
            return museType;
        }

        public void setMuseType(String museType) {
            this.museType = museType;
        }

        public String getMuseImage() {
            return museImage;
        }

        public void setMuseImage(String museImage) {
            this.museImage = museImage;
        }

        public String getMuseLicensePlates() {
            return museLicensePlates;
        }

        public void setMuseLicensePlates(String museLicensePlates) {
            this.museLicensePlates = museLicensePlates;
        }

        public String getMuseConfirmFlag() {
            return museConfirmFlag;
        }

        public void setMuseConfirmFlag(String museConfirmFlag) {
            this.museConfirmFlag = museConfirmFlag;
        }

        public static class SysAreaBeanXXXX {
            /**
             * sysaId : 30352
             * sysaParentId : 20027
             * sysArea : {"sysaId":20027,"sysaParentId":10005,"sysArea":{"sysaId":10005,"sysaParentId":2,"sysArea":{"sysaId":2,"sysaParentId":1,"sysArea":{"sysaId":1,"sysaParentId":null,"sysArea":null,"sysaName":"世界","sysaLevel":1,"sysaRelation":null,"sysaDescription":null,"sysaSort":null,"sysaLat":null,"sysaLng":null,"sysaPinyin":"sj"},"sysaName":"中国","sysaLevel":2,"sysaRelation":"|1|","sysaDescription":"中国","sysaSort":null,"sysaLat":null,"sysaLng":null,"sysaPinyin":"zg"},"sysaName":"内蒙古","sysaLevel":3,"sysaRelation":null,"sysaDescription":"内蒙古","sysaSort":null,"sysaLat":null,"sysaLng":null,"sysaPinyin":"nmg"},"sysaName":"呼和浩特市","sysaLevel":4,"sysaRelation":null,"sysaDescription":"呼和浩特市","sysaSort":null,"sysaLat":null,"sysaLng":null,"sysaPinyin":"hhhts"}
             * sysaName : 回民区
             * sysaLevel : 5
             * sysaRelation : null
             * sysaDescription : 回民区
             * sysaSort : null
             * sysaLat : null
             * sysaLng : null
             * sysaPinyin : hmq
             */

            private String sysaId;
            private String sysaParentId;
            private SysAreaBeanXXX sysArea;
            private String sysaName;
            private String sysaLevel;
            private String sysaRelation;
            private String sysaDescription;
            private String sysaSort;
            private String sysaLat;
            private String sysaLng;
            private String sysaPinyin;

            public String getSysaId() {
                return sysaId;
            }

            public void setSysaId(String sysaId) {
                this.sysaId = sysaId;
            }

            public String getSysaParentId() {
                return sysaParentId;
            }

            public void setSysaParentId(String sysaParentId) {
                this.sysaParentId = sysaParentId;
            }

            public SysAreaBeanXXX getSysArea() {
                return sysArea;
            }

            public void setSysArea(SysAreaBeanXXX sysArea) {
                this.sysArea = sysArea;
            }

            public String getSysaName() {
                return sysaName;
            }

            public void setSysaName(String sysaName) {
                this.sysaName = sysaName;
            }

            public String getSysaLevel() {
                return sysaLevel;
            }

            public void setSysaLevel(String sysaLevel) {
                this.sysaLevel = sysaLevel;
            }

            public String getSysaRelation() {
                return sysaRelation;
            }

            public void setSysaRelation(String sysaRelation) {
                this.sysaRelation = sysaRelation;
            }

            public String getSysaDescription() {
                return sysaDescription;
            }

            public void setSysaDescription(String sysaDescription) {
                this.sysaDescription = sysaDescription;
            }

            public String getSysaSort() {
                return sysaSort;
            }

            public void setSysaSort(String sysaSort) {
                this.sysaSort = sysaSort;
            }

            public String getSysaLat() {
                return sysaLat;
            }

            public void setSysaLat(String sysaLat) {
                this.sysaLat = sysaLat;
            }

            public String getSysaLng() {
                return sysaLng;
            }

            public void setSysaLng(String sysaLng) {
                this.sysaLng = sysaLng;
            }

            public String getSysaPinyin() {
                return sysaPinyin;
            }

            public void setSysaPinyin(String sysaPinyin) {
                this.sysaPinyin = sysaPinyin;
            }

            public static class SysAreaBeanXXX {
                /**
                 * sysaId : 20027
                 * sysaParentId : 10005
                 * sysArea : {"sysaId":10005,"sysaParentId":2,"sysArea":{"sysaId":2,"sysaParentId":1,"sysArea":{"sysaId":1,"sysaParentId":null,"sysArea":null,"sysaName":"世界","sysaLevel":1,"sysaRelation":null,"sysaDescription":null,"sysaSort":null,"sysaLat":null,"sysaLng":null,"sysaPinyin":"sj"},"sysaName":"中国","sysaLevel":2,"sysaRelation":"|1|","sysaDescription":"中国","sysaSort":null,"sysaLat":null,"sysaLng":null,"sysaPinyin":"zg"},"sysaName":"内蒙古","sysaLevel":3,"sysaRelation":null,"sysaDescription":"内蒙古","sysaSort":null,"sysaLat":null,"sysaLng":null,"sysaPinyin":"nmg"}
                 * sysaName : 呼和浩特市
                 * sysaLevel : 4
                 * sysaRelation : null
                 * sysaDescription : 呼和浩特市
                 * sysaSort : null
                 * sysaLat : null
                 * sysaLng : null
                 * sysaPinyin : hhhts
                 */

                private String sysaId;
                private String sysaParentId;
                private SysAreaBeanXX sysArea;
                private String sysaName;
                private String sysaLevel;
                private String sysaRelation;
                private String sysaDescription;
                private String sysaSort;
                private String sysaLat;
                private String sysaLng;
                private String sysaPinyin;

                public String getSysaId() {
                    return sysaId;
                }

                public void setSysaId(String sysaId) {
                    this.sysaId = sysaId;
                }

                public String getSysaParentId() {
                    return sysaParentId;
                }

                public void setSysaParentId(String sysaParentId) {
                    this.sysaParentId = sysaParentId;
                }

                public SysAreaBeanXX getSysArea() {
                    return sysArea;
                }

                public void setSysArea(SysAreaBeanXX sysArea) {
                    this.sysArea = sysArea;
                }

                public String getSysaName() {
                    return sysaName;
                }

                public void setSysaName(String sysaName) {
                    this.sysaName = sysaName;
                }

                public String getSysaLevel() {
                    return sysaLevel;
                }

                public void setSysaLevel(String sysaLevel) {
                    this.sysaLevel = sysaLevel;
                }

                public String getSysaRelation() {
                    return sysaRelation;
                }

                public void setSysaRelation(String sysaRelation) {
                    this.sysaRelation = sysaRelation;
                }

                public String getSysaDescription() {
                    return sysaDescription;
                }

                public void setSysaDescription(String sysaDescription) {
                    this.sysaDescription = sysaDescription;
                }

                public String getSysaSort() {
                    return sysaSort;
                }

                public void setSysaSort(String sysaSort) {
                    this.sysaSort = sysaSort;
                }

                public String getSysaLat() {
                    return sysaLat;
                }

                public void setSysaLat(String sysaLat) {
                    this.sysaLat = sysaLat;
                }

                public String getSysaLng() {
                    return sysaLng;
                }

                public void setSysaLng(String sysaLng) {
                    this.sysaLng = sysaLng;
                }

                public String getSysaPinyin() {
                    return sysaPinyin;
                }

                public void setSysaPinyin(String sysaPinyin) {
                    this.sysaPinyin = sysaPinyin;
                }

                public static class SysAreaBeanXX {
                    /**
                     * sysaId : 10005
                     * sysaParentId : 2
                     * sysArea : {"sysaId":2,"sysaParentId":1,"sysArea":{"sysaId":1,"sysaParentId":null,"sysArea":null,"sysaName":"世界","sysaLevel":1,"sysaRelation":null,"sysaDescription":null,"sysaSort":null,"sysaLat":null,"sysaLng":null,"sysaPinyin":"sj"},"sysaName":"中国","sysaLevel":2,"sysaRelation":"|1|","sysaDescription":"中国","sysaSort":null,"sysaLat":null,"sysaLng":null,"sysaPinyin":"zg"}
                     * sysaName : 内蒙古
                     * sysaLevel : 3
                     * sysaRelation : null
                     * sysaDescription : 内蒙古
                     * sysaSort : null
                     * sysaLat : null
                     * sysaLng : null
                     * sysaPinyin : nmg
                     */

                    private String sysaId;
                    private String sysaParentId;
                    private SysAreaBeanX sysArea;
                    private String sysaName;
                    private String sysaLevel;
                    private String sysaRelation;
                    private String sysaDescription;
                    private String sysaSort;
                    private String sysaLat;
                    private String sysaLng;
                    private String sysaPinyin;

                    public String getSysaId() {
                        return sysaId;
                    }

                    public void setSysaId(String sysaId) {
                        this.sysaId = sysaId;
                    }

                    public String getSysaParentId() {
                        return sysaParentId;
                    }

                    public void setSysaParentId(String sysaParentId) {
                        this.sysaParentId = sysaParentId;
                    }

                    public SysAreaBeanX getSysArea() {
                        return sysArea;
                    }

                    public void setSysArea(SysAreaBeanX sysArea) {
                        this.sysArea = sysArea;
                    }

                    public String getSysaName() {
                        return sysaName;
                    }

                    public void setSysaName(String sysaName) {
                        this.sysaName = sysaName;
                    }

                    public String getSysaLevel() {
                        return sysaLevel;
                    }

                    public void setSysaLevel(String sysaLevel) {
                        this.sysaLevel = sysaLevel;
                    }

                    public String getSysaRelation() {
                        return sysaRelation;
                    }

                    public void setSysaRelation(String sysaRelation) {
                        this.sysaRelation = sysaRelation;
                    }

                    public String getSysaDescription() {
                        return sysaDescription;
                    }

                    public void setSysaDescription(String sysaDescription) {
                        this.sysaDescription = sysaDescription;
                    }

                    public String getSysaSort() {
                        return sysaSort;
                    }

                    public void setSysaSort(String sysaSort) {
                        this.sysaSort = sysaSort;
                    }

                    public String getSysaLat() {
                        return sysaLat;
                    }

                    public void setSysaLat(String sysaLat) {
                        this.sysaLat = sysaLat;
                    }

                    public String getSysaLng() {
                        return sysaLng;
                    }

                    public void setSysaLng(String sysaLng) {
                        this.sysaLng = sysaLng;
                    }

                    public String getSysaPinyin() {
                        return sysaPinyin;
                    }

                    public void setSysaPinyin(String sysaPinyin) {
                        this.sysaPinyin = sysaPinyin;
                    }

                    public static class SysAreaBeanX {
                        /**
                         * sysaId : 2
                         * sysaParentId : 1
                         * sysArea : {"sysaId":1,"sysaParentId":null,"sysArea":null,"sysaName":"世界","sysaLevel":1,"sysaRelation":null,"sysaDescription":null,"sysaSort":null,"sysaLat":null,"sysaLng":null,"sysaPinyin":"sj"}
                         * sysaName : 中国
                         * sysaLevel : 2
                         * sysaRelation : |1|
                         * sysaDescription : 中国
                         * sysaSort : null
                         * sysaLat : null
                         * sysaLng : null
                         * sysaPinyin : zg
                         */

                        private String sysaId;
                        private String sysaParentId;
                        private SysAreaBean sysArea;
                        private String sysaName;
                        private String sysaLevel;
                        private String sysaRelation;
                        private String sysaDescription;
                        private String sysaSort;
                        private String sysaLat;
                        private String sysaLng;
                        private String sysaPinyin;

                        public String getSysaId() {
                            return sysaId;
                        }

                        public void setSysaId(String sysaId) {
                            this.sysaId = sysaId;
                        }

                        public String getSysaParentId() {
                            return sysaParentId;
                        }

                        public void setSysaParentId(String sysaParentId) {
                            this.sysaParentId = sysaParentId;
                        }

                        public SysAreaBean getSysArea() {
                            return sysArea;
                        }

                        public void setSysArea(SysAreaBean sysArea) {
                            this.sysArea = sysArea;
                        }

                        public String getSysaName() {
                            return sysaName;
                        }

                        public void setSysaName(String sysaName) {
                            this.sysaName = sysaName;
                        }

                        public String getSysaLevel() {
                            return sysaLevel;
                        }

                        public void setSysaLevel(String sysaLevel) {
                            this.sysaLevel = sysaLevel;
                        }

                        public String getSysaRelation() {
                            return sysaRelation;
                        }

                        public void setSysaRelation(String sysaRelation) {
                            this.sysaRelation = sysaRelation;
                        }

                        public String getSysaDescription() {
                            return sysaDescription;
                        }

                        public void setSysaDescription(String sysaDescription) {
                            this.sysaDescription = sysaDescription;
                        }

                        public String getSysaSort() {
                            return sysaSort;
                        }

                        public void setSysaSort(String sysaSort) {
                            this.sysaSort = sysaSort;
                        }

                        public String getSysaLat() {
                            return sysaLat;
                        }

                        public void setSysaLat(String sysaLat) {
                            this.sysaLat = sysaLat;
                        }

                        public String getSysaLng() {
                            return sysaLng;
                        }

                        public void setSysaLng(String sysaLng) {
                            this.sysaLng = sysaLng;
                        }

                        public String getSysaPinyin() {
                            return sysaPinyin;
                        }

                        public void setSysaPinyin(String sysaPinyin) {
                            this.sysaPinyin = sysaPinyin;
                        }

                        public static class SysAreaBean {
                            /**
                             * sysaId : 1
                             * sysaParentId : null
                             * sysArea : null
                             * sysaName : 世界
                             * sysaLevel : 1
                             * sysaRelation : null
                             * sysaDescription : null
                             * sysaSort : null
                             * sysaLat : null
                             * sysaLng : null
                             * sysaPinyin : sj
                             */

                            private String sysaId;
                            private String sysaParentId;
                            private String sysArea;
                            private String sysaName;
                            private String sysaLevel;
                            private String sysaRelation;
                            private String sysaDescription;
                            private String sysaSort;
                            private String sysaLat;
                            private String sysaLng;
                            private String sysaPinyin;

                            public String getSysaId() {
                                return sysaId;
                            }

                            public void setSysaId(String sysaId) {
                                this.sysaId = sysaId;
                            }

                            public String getSysaParentId() {
                                return sysaParentId;
                            }

                            public void setSysaParentId(String sysaParentId) {
                                this.sysaParentId = sysaParentId;
                            }

                            public String getSysArea() {
                                return sysArea;
                            }

                            public void setSysArea(String sysArea) {
                                this.sysArea = sysArea;
                            }

                            public String getSysaName() {
                                return sysaName;
                            }

                            public void setSysaName(String sysaName) {
                                this.sysaName = sysaName;
                            }

                            public String getSysaLevel() {
                                return sysaLevel;
                            }

                            public void setSysaLevel(String sysaLevel) {
                                this.sysaLevel = sysaLevel;
                            }

                            public String getSysaRelation() {
                                return sysaRelation;
                            }

                            public void setSysaRelation(String sysaRelation) {
                                this.sysaRelation = sysaRelation;
                            }

                            public String getSysaDescription() {
                                return sysaDescription;
                            }

                            public void setSysaDescription(String sysaDescription) {
                                this.sysaDescription = sysaDescription;
                            }

                            public String getSysaSort() {
                                return sysaSort;
                            }

                            public void setSysaSort(String sysaSort) {
                                this.sysaSort = sysaSort;
                            }

                            public String getSysaLat() {
                                return sysaLat;
                            }

                            public void setSysaLat(String sysaLat) {
                                this.sysaLat = sysaLat;
                            }

                            public String getSysaLng() {
                                return sysaLng;
                            }

                            public void setSysaLng(String sysaLng) {
                                this.sysaLng = sysaLng;
                            }

                            public String getSysaPinyin() {
                                return sysaPinyin;
                            }

                            public void setSysaPinyin(String sysaPinyin) {
                                this.sysaPinyin = sysaPinyin;
                            }
                        }
                    }
                }
            }
        }
    }
}
