package com.sk.sqhk.network;

import com.github.retrofitutil.NoNetworkException;
import com.library.base.BaseApiRequest;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.Config;
import com.sk.sqhk.network.request.UploadImgBody;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/28.
 */

public class NetApiRequest extends BaseApiRequest {

    public static void uploadImg(Map map, UploadImgBody body, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(NetIRequest.class).uploadImg(map, body).enqueue(callBack);
    }
    public static void getAPPVersion(Map map,  MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(NetIRequest.class).getAPPVersion(map ).enqueue(callBack);
    }
    public static void isHasNewMsg(Map map,  MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(NetIRequest.class).isHasNewMsg(map ).enqueue(callBack);
    }

    public static void getSMSCode(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(NetIRequest.class).getSMSCode(map).enqueue(callBack);
    }





    /**********************************分割线**************************************/





    public static void paymentURL(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(NetIRequest.class).paymentURL(map).enqueue(callBack);
    }

    //获取省份
    public static void getProvince(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(NetIRequest.class).getProvince(map).enqueue(callBack);
    }

    //获取城市
    public static void getCity(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(NetIRequest.class).getCity(map).enqueue(callBack);
    }

    //获取区/县
    public static void getArea(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(NetIRequest.class).getArea(map).enqueue(callBack);
    }

    //申请合伙人
    public static void getApplyForPartner(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(NetIRequest.class).getApplyForPartner(map).enqueue(callBack);
    }

    //获取支付信息
    public static void PayInfo(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(NetIRequest.class).PayInfo(map).enqueue(callBack);
    }
    public static void getAllCity(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(NetIRequest.class).getAllCity(map).enqueue(callBack);
    }


    //保存定位信息
    public static void getLatLng(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(NetIRequest.class).getLatLng(map).enqueue(callBack);
    }
    //保存定位信息
    public static void fenXiang(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(NetIRequest.class).fenXiang(map).enqueue(callBack);
    }

}
