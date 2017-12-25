package com.sk.sqhk.module.my.network;

import com.library.base.BaseObj;
import com.library.base.ResponseObj;
import com.sk.sqhk.module.my.network.request.EditUserInfoBody;
import com.sk.sqhk.module.my.network.request.RegisterBody;
import com.sk.sqhk.module.my.network.response.LoginObj;
import com.sk.sqhk.module.my.network.response.UserInfoObj;

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
    @GET("api/SHLGUser/GetUserLogins")
    Call<ResponseObj<LoginObj>> userLogin(@QueryMap Map<String, String> map);

    //获取用户资料
    @GET("api/SHLGUser/GetUserInfo")
    Call<ResponseObj<UserInfoObj>> getUserInfo(@QueryMap Map<String, String> map);

    //单独修改用户头像
    @GET("api/SHLGUser/GetSetUserAvatar")
    Call<ResponseObj<BaseObj>> updateUserImg(@QueryMap Map<String, String> map);

    //修改手机号
    @GET("api/SHLGUser/GetEditPhone")
    Call<ResponseObj<BaseObj>> getEditPhone(@QueryMap Map<String, String> map);

    //修改邮箱
    @GET("api/SHLGUser/GetEditEmail")
    Call<ResponseObj<BaseObj>> getEditEmail(@QueryMap Map<String, String> map);

    //提交-意见反馈
    @GET("api/SHLGUser/GetSubmitFeedback")
    Call<ResponseObj<BaseObj>> getSubmitFeedback(@QueryMap Map<String, String> map);

    //是否接受消息推送设置
    @GET("api/UserBase/GetMessageSink")
    Call<ResponseObj<BaseObj>> getMessageSink(@QueryMap Map<String, String> map);

    //修改密码
    @GET("api/SHLGUser/GetSetNewPassword")
    Call<ResponseObj<BaseObj>> setNewPassword(@QueryMap Map<String, String> map);

    //重置密码(忘记密码)
    @GET("api/SHLGUser/GetSetPassword")
    Call<ResponseObj<LoginObj>> forgetPWD(@QueryMap Map<String, String> map);


    /***************************************分割线***************************************/


    //注册
    @POST("api/UserBase/PostUserRegister")
    Call<ResponseObj<BaseObj>> register(@QueryMap Map<String, String> map, @Body RegisterBody body);


    //修改信息（昵称，姓名，生日，性别，头像）
    @POST("api/UserBase/PostEditUserInfo")
    Call<ResponseObj<BaseObj>> editUserInfo(@QueryMap Map<String, String> map, @Body EditUserInfoBody body);


}
