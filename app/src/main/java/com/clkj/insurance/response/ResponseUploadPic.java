package com.clkj.insurance.response;

/**
 * Created by Administrator on 2016/5/17.
 */
public class ResponseUploadPic {

    /**
     * title : 0201605071857484321.jpg
     * original : forTest.jpg
     * state : SUCCESS
     * url : http://localhost:8080/housekeeping/common/file/download.do?storeFileName=0201605071857484321.jpg
     */

    private String title;
    private String original;
    private String state;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
