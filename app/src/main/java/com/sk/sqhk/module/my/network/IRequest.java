package com.sk.sqhk.module.my.network;

import com.library.base.BaseObj;
import com.library.base.ResponseObj;
import com.sk.sqhk.module.my.network.request.EditUserInfoBody;
import com.sk.sqhk.module.my.network.request.RegisterBody;
import com.sk.sqhk.module.my.network.response.LoginObj;

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

    //注册
    @POST("api/User/PostUserRegistration")
    Call<ResponseObj<BaseObj>> register(@QueryMap Map<String, String> map, @Body RegisterBody body);


    //修改信息（昵称，姓名，生日，性别，头像）
    @POST("api/UserBase/PostEditUserInfo")
    Call<ResponseObj<BaseObj>> editUserInfo(@QueryMap Map<String, String> map, @Body EditUserInfoBody body);


}
