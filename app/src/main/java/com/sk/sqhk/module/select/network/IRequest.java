package com.sk.sqhk.module.select.network;

import com.library.base.ResponseObj;
import com.sk.sqhk.module.select.network.response.HuanKuanPlanDuoTianDetailObj;
import com.sk.sqhk.module.select.network.response.HuanKuanPlanObj;
import com.sk.sqhk.module.select.network.response.HuanKuanPlanYiTianDetailObj;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/6/28.
 */

public interface IRequest {

    //查询模块-获取信用卡列表
    @GET("api/HandleCardOnline/GetPlanList")
    Call<ResponseObj<HuanKuanPlanObj>> getHuanKuanPlan(@QueryMap Map<String, String> map);

    //还款计划-还款计划详情(多天)
    @GET("api/HandleCardOnline/GetPlanSummary")
    Call<ResponseObj<HuanKuanPlanDuoTianDetailObj>> getHuanKuanPlanDuoTianDetail(@QueryMap Map<String, String> map);

    //还款计划-还款计划详情(一天)
    @GET("api/HandleCardOnline/GetPlanDetail")
    Call<ResponseObj<HuanKuanPlanYiTianDetailObj>> getHuanKuanPlanYiTianDetail(@QueryMap Map<String, String> map);



}
