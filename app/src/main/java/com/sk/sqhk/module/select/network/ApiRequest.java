package com.sk.sqhk.module.select.network;

import com.github.retrofitutil.NoNetworkException;
import com.library.base.BaseApiRequest;
import com.library.base.MyCallBack;
import com.sk.sqhk.Config;
import com.sk.sqhk.module.my.network.request.EditUserInfoBody;
import com.sk.sqhk.module.my.network.request.RegisterBody;

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
        getGeneralClient(com.sk.sqhk.network.IRequest.class).tuanGouSureOrder(map).enqueue(callBack);
    }

    //登录
    public static void userLogin(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(com.sk.sqhk.module.my.network.IRequest.class).userLogin(map).enqueue(callBack);
    }

    //获取用户资料
    public static void getUserInfo(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(com.sk.sqhk.module.my.network.IRequest.class).getUserInfo(map).enqueue(callBack);
    }

    //单独修改用户头像
    public static void updateUserImg(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(com.sk.sqhk.module.my.network.IRequest.class).updateUserImg(map).enqueue(callBack);
    }

    //修改手机号
    public static void getEditPhone(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(com.sk.sqhk.module.my.network.IRequest.class).getEditPhone(map).enqueue(callBack);
    }

    //修改邮箱
    public static void getEditEmail(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(com.sk.sqhk.module.my.network.IRequest.class).getEditEmail(map).enqueue(callBack);
    }

    //提交-意见反馈
    public static void getSubmitFeedback(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(com.sk.sqhk.module.my.network.IRequest.class).getSubmitFeedback(map).enqueue(callBack);
    }


    //是否接受消息推送设置
    public static void getMessageSink(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(com.sk.sqhk.module.my.network.IRequest.class).getMessageSink(map).enqueue(callBack);
    }

    //修改密码
    public static void setNewPassword(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(com.sk.sqhk.module.my.network.IRequest.class).setNewPassword(map).enqueue(callBack);
    }
    //重置密码(忘记密码)
    public static void forgetPWD(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(com.sk.sqhk.module.my.network.IRequest.class).forgetPWD(map).enqueue(callBack);
    }



    /********************************分割线**********************************************/


    //注册
    public static void register(Map map, RegisterBody body, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(com.sk.sqhk.module.my.network.IRequest.class).register(map, body).enqueue(callBack);
    }




    //修改信息（昵称，姓名，生日，性别，头像）
    public static void editUserInfo(Map map, EditUserInfoBody body, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(com.sk.sqhk.module.my.network.IRequest.class).editUserInfo(map, body).enqueue(callBack);
    }




}
