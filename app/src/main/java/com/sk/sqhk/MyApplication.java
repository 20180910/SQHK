package com.sk.sqhk;


import android.app.Application;
import android.content.Context;

import com.aspsine.multithreaddownload.DownloadConfiguration;
import com.aspsine.multithreaddownload.DownloadManager;
import com.github.androidtools.SPUtils;
import com.github.baseclass.view.Loading;
import com.github.retrofitutil.NetWorkManager;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by administartor on 2017/8/8.
 */

public class MyApplication extends Application {
 /*   @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }*/
    @Override
    public void onCreate() {
        super.onCreate();
        NetWorkManager.getInstance(getApplicationContext(),"http://121.40.186.118:1145/",BuildConfig.DEBUG).complete();
        Loading.setLoadView(R.layout.app_loading_view);
//        ZXingLibrary.initDisplayOpinion(this);
        //二维码
//        SDKInitializer.initialize(getApplicationContext());
//        PlatformConfig.setWeixin(Config.weixing_id, Config.weixing_AppSecret );
//        PlatformConfig.setQQZone(Config.qq_id, Config.qq_key);
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
//        UMShareAPI.get(this);

        JPushInterface.setDebugMode(BuildConfig.DEBUG); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush

        DownloadConfiguration configuration = new DownloadConfiguration();
        configuration.setMaxThreadNum(10);
        configuration.setThreadNum(3);
        DownloadManager.getInstance().init(getApplicationContext(), configuration);
    }

   //经度
   public static double longitude;//=121.432986;
    //纬度
   public static double latitude;//=31.229504;

    /**
     * 经度
     * @param context
     * @return
     */
    public static double getJingDu(Context context){
        if(longitude==0){
            longitude= SPUtils.getFloat(context,Config.longitude,0);
            return longitude;
        }else{
            return longitude;
        }
    }

    /**
     * 纬度
     * @param context
     * @return
     */
    public static double getWeiDu(Context context){
        if(latitude==0){
            latitude= SPUtils.getFloat(context,Config.latitude,0);
            return latitude;
        }else{
            return latitude;
        }
    }
    /**
     * 经度
     * @param context
     * @return
     */
    public static double Lng(Context context){
        if(longitude==0){
            longitude= SPUtils.getFloat(context,Config.longitude,0);
            return longitude;
        }else{
            return longitude;
        }
    }
    /**
     * 纬度
     * @param context
     * @return
     */
    public static double Lat(Context context){
        if(latitude==0){
            latitude= SPUtils.getFloat(context,Config.latitude,0);
            return latitude;
        }else{
            return latitude;
        }
    }
}
