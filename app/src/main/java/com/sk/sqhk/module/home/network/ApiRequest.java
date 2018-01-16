package com.sk.sqhk.module.home.network;

import com.github.retrofitutil.NoNetworkException;
import com.library.base.BaseApiRequest;
import com.sk.sqhk.Config;
import com.sk.sqhk.base.MyCallBack;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/28.
 */

public class ApiRequest extends BaseApiRequest {

    /*public static Observable getRegisterXieYi(String rnd, String sign){
        return getCommonClient(com.sk.yangyu.module.home.network.NetIRequest.class).getPayNotifyUrl(rnd,sign).compose(RxResult.appSchedulers()).compose(RxResult.handleResult());
    }*/

    public static void getHomeJiSuData(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getHomeJiSuData(map).enqueue(callBack);
    }


    public static void getHomeBaner(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getHomeBaner(map).enqueue(callBack);
    }
    public static void getHomeZiXunData(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getHomeZiXunData(map).enqueue(callBack);
    }
    public static void getMoreZiXunData(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getMoreZiXunData(map).enqueue(callBack);
    }
    public static void getZiXunDetail(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getZiXunDetail(map).enqueue(callBack);
    }
    public static void ziXunDianZan(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).ziXunDianZan(map).enqueue(callBack);
    }
    public static void getHomeImg(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getHomeImg(map).enqueue(callBack);
    }
    public static void getHomeFenLei(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getHomeFenLei(map).enqueue(callBack);
    }
    public static void getFenRun(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getFenRun(map).enqueue(callBack);
    }
    public static void fenRunTiXian(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).fenRunTiXian(map).enqueue(callBack);
    }
    public static void getShouYiDetail(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getShouYiDetail(map).enqueue(callBack);
    }
    public static void getMyXiaJi(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getMyXiaJi(map).enqueue(callBack);
    }
    public static void getJinRongChaoShi(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getJinRongChaoShi(map).enqueue(callBack);
    }
    public static void getJinRongDaiList(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getJinRongDaiList(map).enqueue(callBack);
    }
    public static void getDaiKuanDetail(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getDaiKuanDetail(map).enqueue(callBack);
    }


}
