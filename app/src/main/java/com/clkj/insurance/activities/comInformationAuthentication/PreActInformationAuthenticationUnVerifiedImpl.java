package com.clkj.insurance.activities.comInformationAuthentication;

import com.clkj.insurance.api.TempAction;
import com.clkj.insurance.response.ResponseUploadPic;
import com.lf.tempcore.tempModule.tempDebuger.Debug;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by longf on 2016/11/4.
 */
public class PreActInformationAuthenticationUnVerifiedImpl implements PreActInformationAuthenticationUnVerifiedI {

    private ViewActInformationAuthenticationUnVerifiedI mActHomeDetailsI;

    public PreActInformationAuthenticationUnVerifiedImpl(ViewActInformationAuthenticationUnVerifiedI actHomeDetailsI) {
        mActHomeDetailsI = actHomeDetailsI;
    }


    /**
     * 图片上传
     *
     * @param filePath
     */
    @Override
    public void uploadImage(String[] filePath) {

        Map<String, RequestBody> map = new HashMap<>();
        for (int i = 0; i < filePath.length; i++) {
            Debug.error("filePath" + i + " :" + filePath[i]);
            File currentFile = new File(filePath[i]);
            if (currentFile.exists()) {
                RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), currentFile);
                String[] arraypath = filePath[i].split("/");
                String name = arraypath[arraypath.length - 1];
                Debug.info("file name=" + name);
                map.put("image\"; filename=\"" + name, fileBody);
            } else {
                if (mActHomeDetailsI != null) {
                    mActHomeDetailsI.toast(filePath + "文件不存在");
                }
            }
        }

        if (mActHomeDetailsI != null) {
            mActHomeDetailsI.showPro();
        }

        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).uploadUEImg(map), new TempRemoteApiFactory.OnCallBack<ResponseUploadPic>() {
            @Override
            public void onSucceed(ResponseUploadPic data) {

                if (data.getState().equals("SUCCESS")) {
                    if (mActHomeDetailsI != null) {
                        mActHomeDetailsI.upLoadImageSuccess(data);
                    }
                } else {
                    if (mActHomeDetailsI != null) {
                        mActHomeDetailsI.toast("操作失败，请重试！");
                    }
                }
            }

            @Override
            public void onCompleted() {
                if (mActHomeDetailsI != null) {
                    mActHomeDetailsI.dismissPro();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mActHomeDetailsI != null) {
                    mActHomeDetailsI.dismissPro();
                }
            }
        });
    }

    /**
     * 视频上传
     * @param list
     */
    @Override
    public void uploadVideo(String[] list) {

    }

}
