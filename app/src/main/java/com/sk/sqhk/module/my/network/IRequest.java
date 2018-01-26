package com.sk.sqhk.module.my.network;

import com.library.base.BaseObj;
import com.library.base.ResponseObj;
import com.sk.sqhk.module.my.network.request.RegisterBody;
import com.sk.sqhk.module.my.network.response.AboutWeObj;
import com.sk.sqhk.module.my.network.response.DefaultBankObj;
import com.sk.sqhk.module.my.network.response.HelpCenterObj;
import com.sk.sqhk.module.my.network.response.LoginObj;
import com.sk.sqhk.module.my.network.response.MessageDetailObj;
import com.sk.sqhk.module.my.network.response.MyAccountObj;
import com.sk.sqhk.module.my.network.response.MyAllBankObj;
import com.sk.sqhk.module.my.network.response.MyMessageObj;
import com.sk.sqhk.module.my.network.response.RenZhengDataObj;
import com.sk.sqhk.module.my.network.response.TiXianAccountListObj;
import com.sk.sqhk.module.my.network.response.YaoQingObj;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/6/28.
 */

public interface IRequest {
    @GET("api/HomePage/GetProductGroupOrderShow")
    Call<ResponseObj<BaseObj>> tuanGouSureOrder(@QueryMap Map<String, String> map);

    //登录
    @GET("api/User/GetUserLogin")
    Call<ResponseObj<LoginObj>> userLogin(@QueryMap Map<String, String> map);

    //忘记密码
    @GET("api/User/GetSetPassword")
    Call<ResponseObj<BaseObj>> forgetPWD(@QueryMap Map<String, String> map);

    //注册
    @POST("api/User/PostUserRegistration")
    Call<ResponseObj<BaseObj>> register(@QueryMap Map<String, String> map, @Body RegisterBody body);

    //获取用户资料
    @GET("api/User/GetUserInfo")
    Call<ResponseObj<LoginObj>> getUserInfo(@QueryMap Map<String, String> map);


    //我的账户
    @GET("api/User/GetMyBalance")
    Call<ResponseObj<MyAccountObj>> getMyAccount(@QueryMap Map<String, String> map);


    //获取默认银行卡
    @GET("api/AccountWithdrawal/GetAccountDefault")
    Call<ResponseObj<DefaultBankObj>> getDefaultBank(@QueryMap Map<String, String> map);

    //获取银行卡账户列表
    @GET("api/AccountWithdrawal/GetAccount")
    Call<ResponseObj<List<TiXianAccountListObj>>> getTiXianAccountList(@QueryMap Map<String, String> map);

    //设置默认账户
    @GET("api/AccountWithdrawal/GetEditDefalut")
    Call<ResponseObj<BaseObj>> setDefaultBank(@QueryMap Map<String, String> map);

    //提现-删除账户
    @GET("api/AccountWithdrawal/GetDelAccount")
    Call<ResponseObj<BaseObj>> delBankAccount(@QueryMap Map<String, String> map);


    //提现申请
    @GET("api/AccountWithdrawal/GetWithdrawals")
    Call<ResponseObj<BaseObj>> tiXianCommit(@QueryMap Map<String, String> map);



    //保存银行卡
    @GET("api/User/GetMyBalance")
    Call<ResponseObj<BaseObj>> saveBank(@QueryMap Map<String, String> map);


    //添加银行卡
    @GET("api/User/GetMyBalance")
    Call<ResponseObj<BaseObj>> getHotBank(@QueryMap Map<String, String> map);


    //帮助中心列表
    @GET("api/User/GetHelpCenter")
    Call<ResponseObj<List<HelpCenterObj>>> getHelpCenter(@QueryMap Map<String, String> map);



    //意见反馈
    @GET("api/User/GetSubmitFeedback")
    Call<ResponseObj<BaseObj>> yiJianFanKui(@QueryMap Map<String, String> map);

    //修改手机号码
    @GET("api/User/GetEditPhone")
    Call<ResponseObj<BaseObj>> updatePhone(@QueryMap Map<String, String> map);

    //修改密码
    @GET("api/User/GetSetNewPassword")
    Call<ResponseObj<BaseObj>> updatePwd(@QueryMap Map<String, String> map);


    //关于我们
    @GET("api/Informations/GetPlatform_Instruction")
    Call<ResponseObj<AboutWeObj>> aboutWe(@QueryMap Map<String, String> map);


    //推送开关
    @GET("api/User/GetMessageSink")
    Call<ResponseObj<BaseObj>> setOpen(@QueryMap Map<String, String> map);


    //获取认证信息
    @GET("api/User/GetMemberAuthentication")
    Call<ResponseObj<RenZhengDataObj>> getRenZhengData(@QueryMap Map<String, String> map);


    //提交身份认证
    @GET("api/User/GetSetMemberAuthentication")
    Call<ResponseObj<BaseObj>> renZheng(@QueryMap Map<String, String> map);


    //邀请好友
    @GET("api/User/GetInviteFriend")
    Call<ResponseObj<YaoQingObj>> yaoQing(@QueryMap Map<String, String> map);


    //消息列表
    @GET("api/User/GetNewList")
    Call<ResponseObj<List<MyMessageObj>>> getMessageList(@QueryMap Map<String, String> map);

    //消息详情
    @GET("api/User/GetNewsDetail")
    Call<ResponseObj<MessageDetailObj>> getMessageDetail(@QueryMap Map<String, String> map);
    //修改头像
    @GET("api/User/GetSetUserAvatar")
    Call<ResponseObj<BaseObj>> updateUserImg(@QueryMap Map<String, String> map);

    //信用卡和银行卡列表
    @GET("api/AccountWithdrawal/GetMyBankCard")
    Call<ResponseObj<MyAllBankObj>> getXinYongCardList(@QueryMap Map<String, String> map);


    //删除储蓄卡
    @GET("api/AccountWithdrawal/GetDelAccount")
    Call<ResponseObj<BaseObj>> deleteBankCard(@QueryMap Map<String, String> map);


    //删除信用卡
    @GET("api/HandleCardOnline/GetCardDelete")
    Call<ResponseObj<BaseObj>> deleteXinYongCard(@QueryMap Map<String, String> map);


    //修改信用卡
    @GET("api/HandleCardOnline/GetCardModify")
    Call<ResponseObj<BaseObj>> updateXinYongCard(@QueryMap Map<String, String> map);




}
