package com.clkj.insurance.api;

import com.clkj.insurance.config.BaseUriConfig;
import com.clkj.insurance.response.ReponseCaseMessage;
import com.clkj.insurance.response.ReponseMessage;
import com.clkj.insurance.response.ReponseUserInfo;
import com.clkj.insurance.response.ResponseBillDetail;
import com.clkj.insurance.response.ResponeMemberUserCertification;
import com.clkj.insurance.response.ResponseCaseMessageList;
import com.clkj.insurance.response.ResponseHistoryBillList;
import com.clkj.insurance.response.ResponseSysMessageList;
import com.clkj.insurance.response.ResponseUploadPic;
import com.clkj.insurance.response.ResponseUserSwayBillList;
import com.clkj.insurance.response.ResponseWaterRemind;
import com.clkj.insurance.response.ResponseWaybillRecordList;
import com.clkj.insurance.response.TempResponse;
import com.clkj.insurance.response.TempResponseOther;
import com.squareup.okhttp.RequestBody;

import java.util.Map;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PartMap;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/18.
 * 接口地址
 */

public interface TempAction {
    /**
     * 图片上传
     *
     * @param params
     * @return
     */
    @Multipart
    @POST(BaseUriConfig.BASE_URL + "common/file/uploadUEImg.spm")
    Observable<ResponseUploadPic> uploadUEImg(@PartMap Map<String, RequestBody> params);

    /**
     * 视频上传
     * @param params
     * @return
     */
    @Multipart
    @POST(BaseUriConfig.BASE_URL + "common/file/uploadVideo.spm")
    Observable<ResponseUploadPic> uploadVideo(@PartMap Map<String, RequestBody> params);

    /**
     * 登录
     *
     * @param phone    电话
     * @param password 密码
     * @return
     */
    @GET(BaseUriConfig.BASE_URL + "app/public/system/appUserLogin.spm")
    Observable<com.lf.tempcore.tempResponse.ResponseLoginInfo> appUserLogin(@Query("password") String password,
                                                                            @Query("phone") String phone);

    /**
     * 注册获取验证码
     *
     * @param phone 电话
     * @return
     */
    @GET(BaseUriConfig.BASE_URL + "app/public/system/registerCode.spm")
    Observable<TempResponse> registerCode(@Query("phone") String phone);


    /**
     * 读取未读案件消息数量
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/system/queryUnreadCaseMessage.spm")
    Observable<ReponseCaseMessage> queryUnreadCaseMessage(@Field("museId") String museId,
                                                          @Field("museOnlineTag") String museOnlineTag,
                                                          @Field("musePwd") String musePwd);



    /**
     *
     *读取未读消息数量
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL + "app/private/mall/queryUnreadMallMessage.spm")
    Observable<ReponseMessage> queryUnreadMallMessage(@Field("museId") String museId, @Field("museOnlineTag") String museOnlineTag, @Field("musePwd") String musePwd);

    /**
     * 注册
     *
     * @param licensePaltes 车牌号 驾驶员必传 贸易商不用
     * @param code          验证码
     * @param flag          1：贸易商 2：驾驶员
     * @param areaId        区域编号
     * @param password      密码
     * @param phone         电话号码
     * @param userName      名字
     * @return
     */
    @GET(BaseUriConfig.BASE_URL + "app/public/system/appUserRegister.spm")
    Observable<TempResponse> appUserRegister(@Query("licensePaltes") String licensePaltes,
                                             @Query("code") String code,
                                             @Query("flag") int flag,
                                             @Query("areaId") String areaId,
                                             @Query("password") String password,
                                             @Query("phone") String phone,
                                             @Query("userName") String userName);

    /**
     * 忘记密码获取验证码
     *
     * @param phone 电话
     * @return
     */
    @GET(BaseUriConfig.BASE_URL + "app/public/system/forgetPwdCode.spm")
    Observable<TempResponse> forgetPwdCode(@Query("phone") String phone);

    /**
     * 退出登录
     *
     * @param museId 用户编号
     * @return
     */
    @GET(BaseUriConfig.BASE_URL + "app/public/system/exitLogin.spm")
    Observable<TempResponse> exitLogin(@Query("museId") String museId);

    /**
     * 实名认证
     *
     * @param mcinBackIdcard         身份证反面照
     * @param mcinDriveLicenseCopy   驾驶照副本
     * @param mcinDriveLicenseIfront 驾驶证正本
     * @param mcinDriverPhotograph   与车合照
     * @param mcinIdcardNumber       身份证号
     * @param mcinIfrontIdcard       身份证正面照
     * @param mcinMoveLicenseCopy    行驶证副本
     * @param mcinMoveLicenseInfront 行驶证正本
     * @param mcinSelfImage          自拍照
     * @param museId                 用户编号
     * @param museOnlineTag          在线标识
     * @param musePwd                密码
     * @param mcinId                 认证编号
     * @return
     */
    @GET(BaseUriConfig.BASE_URL + " app/private/system/saveMemberUserCertification.spm")
    Observable<TempResponseOther>saveMemberUserCertification(@Query("mcinBackIdcard") String mcinBackIdcard,
                                                             @Query("mcinDriveLicenseCopy") String mcinDriveLicenseCopy,
                                                              @Query("mcinDriveLicenseIfront") String mcinDriveLicenseIfront,
                                                             @Query("mcinDriverPhotograph") String mcinDriverPhotograph,
                                                              @Query("mcinIdcardNumber") String mcinIdcardNumber,
                                                             @Query("mcinIfrontIdcard") String mcinIfrontIdcard,
                                                              @Query("mcinMoveLicenseCopy") String mcinMoveLicenseCopy,
                                                             @Query("mcinMoveLicenseInfront") String mcinMoveLicenseInfront,
                                                              @Query("mcinSelfImage") String mcinSelfImage,
                                                             @Query("museId") String museId,
                                                              @Query("museOnlineTag") String museOnlineTag,
                                                             @Query("musePwd") String musePwd,
                                                             @Query("mcinId")String mcinId);

    /**
     * 查询用户信息
     * @param museId 用户编号
     * @param museOnlineTag 在线标识
     * @param musePwd 密码
     * @return
     */
    @GET(BaseUriConfig.BASE_URL + "app/private/system/queryMemberUserInfo.spm")
    Observable<ReponseUserInfo>queryMemberUserInfo(@Query("museId")String museId,
                                                   @Query("museOnlineTag")String museOnlineTag,
                                                   @Query("musePwd")String musePwd);

    /**
     * 查询认证信息
     * @param museId
     * @param museOnlineTag
     * @param musePwd
     * @return
     */
    @GET(BaseUriConfig.BASE_URL+"app/private/system/queryMemberUserCertification.spm")
    Observable<ResponeMemberUserCertification>queryMemberUserCertification(@Query("museId")String museId,
                                                                           @Query("museOnlineTag")String museOnlineTag,
                                                                           @Query("musePwd")String musePwd
                                                                           );


    /**
     * 查询消息列表
     * @param museId
     * @param museOnlineTag
     * @param musePwd
     * @return
     */
    @GET(BaseUriConfig.BASE_URL+"app/private/mall/queryMallMessageCenter.spm")
    Observable<ResponseSysMessageList>queryMallMessageCenter(@Query("museId")String museId,
                                                             @Query("museOnlineTag")String museOnlineTag,
                                                             @Query("musePwd")String musePwd,
                                                             @Query("currentPage")String currentPage,
                                                             @Query("pageSize")String pageSize);


    /**
     * 查询案件意见列表
     * @param museId
     * @param museOnlineTag
     * @param musePwd
     * @return
     */
    @GET(BaseUriConfig.BASE_URL+"app/private/system/getCaseMessageList.spm")
    Observable<ResponseCaseMessageList>getCaseMessageList(@Query("museId")String museId,
                                                          @Query("museOnlineTag")String museOnlineTag,
                                                          @Query("musePwd")String musePwd,
                                                          @Query("currentPage")String currentPage,
                                                          @Query("pageSize")String pageSize);

    /**
     * 获取运单通知列表
     * @param museId
     * @param museOnlineTag
     * @param musePwd
     * @return
     */
    @GET(BaseUriConfig.BASE_URL+"app/private/system/queryUserSwayBillList.spm")
    Observable<ResponseUserSwayBillList>queryUserSwayBillList(@Query("museId")String museId,
                                                              @Query("museOnlineTag")String museOnlineTag,
                                                              @Query("musePwd")String musePwd,
                                                              @Query("currentPage")String currentPage,
                                                              @Query("pagesize") int pagesize);

    /**
     * 冲水提醒
     * @param museId
     * @param museOnlineTag
     * @param musePwd
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL+"app/private/system/saveWaterRemind.spm")
    Observable<ResponseWaterRemind>saveWaterRemind(@Field("museId") String museId,
                                                   @Field("museOnlineTag") String museOnlineTag,
                                                   @Field("musePwd") String musePwd);

    /**
     * 运单到达
     * @param museId
     * @param museOnlineTag
     * @param musePwd
     * @param swayId 运单编号
     * @param lat 纬度
     * @param lng 经度
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL+"app/private/system/saveMemberBillArrive.spm")
    Observable<TempResponse>saveMemberBillArrive(@Field("museId") String museId,
                                           @Field("museOnlineTag") String museOnlineTag,
                                           @Field("musePwd") String musePwd,
                                           @Field("swayId")String swayId,
                                           @Field("lat")String lat,
                                           @Field("lng")String lng);

    /**
     * 查询运单过程记录
     * @param museId
     * @param museOnlineTag
     * @param musePwd
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL+"app/private/system/getWaybillRecordList.spm")
    Observable<ResponseWaybillRecordList>getWaybillRecordList(@Field("museId") String museId,
                                                              @Field("museOnlineTag") String museOnlineTag,
                                                              @Field("musePwd") String musePwd);

    /**
     * 历史运单记录列表
     * @param museId
     * @param museOnlineTag
     * @param musePwd
     * @param keyWord 搜索关键字
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL+"app/private/system/queryHistoryBillList.spm")
    Observable<ResponseHistoryBillList>queryHistoryBillList(@Field("museId") String museId,
                                                            @Field("museOnlineTag") String museOnlineTag,
                                                            @Field("musePwd") String musePwd,
                                                            @Field("keyWord")String keyWord,
                                                            @Field("currentPage") String currentPage,
                                                            @Field("pagesize") int pagesize);

    /**
     * 起运上传资料
     * @param museId
     * @param museOnlineTag
     * @param musePwd
     * @param carImage 图片
     * @param number 生猪头数
     * @param swayId 运单编号
     * @param lat 纬度
     * @param lng 经度
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL+"app/private/system/saveStartWaybill.spm")
    Observable<TempResponse>saveStartWaybill(@Field("museId") String museId,
                                             @Field("museOnlineTag") String museOnlineTag,
                                             @Field("musePwd") String musePwd,
                                             @Field("carImage")String carImage,
                                             @Field("number")String number,
                                             @Field("swayId")String swayId,
                                             @Field("lat")String lat,
                                             @Field("lng")String lng);

    /**
     * 运单详情
     * @param museId
     * @param museOnlineTag
     * @param musePwd
     * @param swayId 运单编号
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL+"app/private/system/getBillDetail.spm")
    Observable<ResponseBillDetail>getBillDetail(@Field("museId") String museId,
                                                @Field("museOnlineTag") String museOnlineTag,
                                                @Field("musePwd") String musePwd,
                                                @Field("swayId")String swayId);

    /**
     * 给出案件处理意见
     * @param museId
     * @param museOnlineTag
     * @param musePwd
     * @param mffoContent 意见内容
     * @param mffoFormId 案件编号
     * @param mffoSuggestedType 接收意见方类型 1：驾驶员 2：贸易商 3：人保专员
     * @param mffoSuggestlType 谁给出的意见类型 1：驾驶员 2：贸易商 3：人保专员
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL+"app/private/system/saveCaseFeedback.spm")
    Observable<TempResponse>saveCaseFeedback(@Field("museId") String museId,
                                             @Field("museOnlineTag") String museOnlineTag,
                                             @Field("musePwd") String musePwd,
                                             @Field("mffoContent")String mffoContent,
                                             @Field("mffoFormId")String mffoFormId,
                                             @Field("mffoSuggestedType")String mffoSuggestedType,
                                             @Field("mffoSuggestlType")String mffoSuggestlType);

    /**
     * 报案
     * @param museId
     * @param museOnlineTag
     * @param musePwd
     * @param scasBadImage 受损部位近照（交通意外）
     * @param scasBillId 运单号
     * @param scasCarImage 本车车辆全景（交通意外）
     * @param scasCaseType 报案类型	1：应急 2：交通意外
     * @param scasDeathNumber 应急死亡头数（应急）
     * @param scasImage 照片资料(应急)
     * @param scasLicensePlateImage 车牌照片（交通意外）
     * @param scasLiveImage 事故现场全景(交通意外)
     * @param scasVideo 视频资料(应急)
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL+"app/private/system/saveMemberBillCase.spm")
    Observable<TempResponse>saveMemberBillCase(@Field("museId") String museId,
                                               @Field("museOnlineTag") String museOnlineTag,
                                               @Field("musePwd") String musePwd,
                                               @Field("scasBadImage")String scasBadImage,
                                               @Field("scasBillId")String scasBillId,
                                               @Field("scasCarImage")String scasCarImage,
                                               @Field("scasCaseType")String scasCaseType,
                                               @Field("scasDeathNumber")String scasDeathNumber,
                                               @Field("scasImage")String scasImage,
                                               @Field("scasLicensePlateImage")String scasLicensePlateImage,
                                               @Field("scasLiveImage")String scasLiveImage,
                                               @Field("scasVideo")String scasVideo);

    /**
     * 案件补传资料
     * @param museId
     * @param museOnlineTag
     * @param musePwd
     * @param scsuCaselId 案件编号
     * @param scsuImage 图片
     * @param scsuVideoName 视频名称
     * @param scsuVideoUrl 视频路径
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL+"app/private/system/saveCaseSupple.spm")
    Observable<TempResponse>saveCaseSupple(@Field("museId") String museId,
                                           @Field("museOnlineTag") String museOnlineTag,
                                           @Field("musePwd") String musePwd,
                                           @Field("scsuCaselId")String scsuCaselId,
                                           @Field("scsuImage")String scsuImage,
                                           @Field("scsuVideoName")String scsuVideoName,
                                           @Field("scsuVideoUrl")String scsuVideoUrl);

    /**
     * 添加运单记录
     * @param museId
     * @param museOnlineTag
     * @param musePwd
     * @param image 图片
     * @param lat 纬度
     * @param lng 经度
     * @param swayId 运单编号
     * @param type 类型 1：起运 2：冲水 3：堵车 4：到达 5：gps定位 6：过秤 7：收费站
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUriConfig.BASE_URL+"app/private/system/saveMemberBillRecord.spm")
    Observable<TempResponse>saveMemberBillRecord(@Field("museId") String museId,
                                                 @Field("museOnlineTag") String museOnlineTag,
                                                 @Field("musePwd") String musePwd,
                                                 @Field("image")String image,
                                                 @Field("lat")String lat,
                                                 @Field("lng")String lng,
                                                 @Field("swayId")String swayId,
                                                 @Field("type")String type);
}