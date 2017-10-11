package com.clkj.insurance.activities.comInformationAuthentication;

import com.clkj.insurance.response.ResponseUploadPic;
import com.lf.tempcore.tempModule.tempMVPCommI.TempViewI;

/**
 * Created by longf on 2016/11/4.
 */
public interface ViewActInformationAuthenticationUnVerifiedI extends TempViewI {
    void upLoadImageSuccess(ResponseUploadPic data);
    void upLoadVideoSuccess(ResponseUploadPic data);
}
