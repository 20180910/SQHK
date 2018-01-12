package com.sk.sqhk.module.select.network;

import com.github.retrofitutil.NoNetworkException;
import com.library.base.BaseApiRequest;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.Config;
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






    /********************************分割线**********************************************/


    //注册
    public static void register(Map map, RegisterBody body, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(com.sk.sqhk.module.my.network.IRequest.class).register(map, body).enqueue(callBack);
    }






}
