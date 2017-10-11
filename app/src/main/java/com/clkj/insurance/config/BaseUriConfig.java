package com.clkj.insurance.config;

import android.text.TextUtils;

/**
 * Created by Administrator on 2017/7/18.
 */

public class BaseUriConfig {
    public final static String BASE_URL = "http://192.168.0.7:8092/pigInsurance/";
    public final static String BASE_IMG_URL = BASE_URL+"/common/file/download.spm?storeFileName=";
    public static String makeImageUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return "";
        }
        return BASE_IMG_URL + url;
    }

    public static String makeImageUrl(String url, int width, int height) {
        if (TextUtils.isEmpty(url))
            return "";
        if (TextUtils.isEmpty(width + "") || TextUtils.isEmpty(height + ""))
            return BASE_IMG_URL + url + "&imgwidth=" + width + "&imgheight=" + height;
        return BASE_IMG_URL + url;
    }
    public static String makeFilesUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return "";
        }
        return BASE_IMG_URL + url;
    }
}
