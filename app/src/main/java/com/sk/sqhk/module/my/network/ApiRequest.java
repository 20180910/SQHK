package com.sk.sqhk.module.my.network;

import com.github.retrofitutil.NoNetworkException;
import com.library.base.BaseApiRequest;
import com.library.base.MyCallBack;
import com.sk.sqhk.Config;
import com.sk.sqhk.module.my.network.request.EditUserInfoBody;
import com.sk.sqhk.module.my.network.request.RegisterBody;
import com.sk.sqhk.network.NetIRequest;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/28.
 */

public class ApiRequest extends BaseApiRequest {


    public static void tuanGouSureOrder(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(NetIRequest.class).tuanGouSureOrder(map).enqueue(callBack);
    }

    //登录
    public static void userLogin(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).userLogin(map).enqueue(callBack);
    }
    //登录
    public static void forgetPWD(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).forgetPWD(map).enqueue(callBack);
    }




    /********************************分割线**********************************************/


    //注册
    public static void register(Map map, RegisterBody body, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).register(map, body).enqueue(callBack);
    }




    //修改信息（昵称，姓名，生日，性别，头像）
    public static void editUserInfo(Map map, EditUserInfoBody body, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).editUserInfo(map, body).enqueue(callBack);
    }


}
