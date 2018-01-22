package com.sk.sqhk.module.home.network;

import com.library.base.BaseObj;
import com.library.base.ResponseObj;
import com.sk.sqhk.module.home.network.request.AddChuXunBody;
import com.sk.sqhk.module.home.network.request.HomeTypeMerchantListBody;
import com.sk.sqhk.module.home.network.request.SearchResultBody;
import com.sk.sqhk.module.home.network.response.AreaBusinessCircleObj;
import com.sk.sqhk.module.home.network.response.BankObj;
import com.sk.sqhk.module.home.network.response.BannerObj;
import com.sk.sqhk.module.home.network.response.CityIdObj;
import com.sk.sqhk.module.home.network.response.DaiKuanDetailObj;
import com.sk.sqhk.module.home.network.response.DianZanObj;
import com.sk.sqhk.module.home.network.response.HomeAnnouncementObj;
import com.sk.sqhk.module.home.network.response.HomeDailybestObj;
import com.sk.sqhk.module.home.network.response.HomeFenLeiObj;
import com.sk.sqhk.module.home.network.response.HomeImgObj;
import com.sk.sqhk.module.home.network.response.HomeJiSuObj;
import com.sk.sqhk.module.home.network.response.HomeLikeObj;
import com.sk.sqhk.module.home.network.response.HomePageImageObj;
import com.sk.sqhk.module.home.network.response.HomeTypeAssemblageObj;
import com.sk.sqhk.module.home.network.response.HomeTypeMerchantListObj;
import com.sk.sqhk.module.home.network.response.HomeZiXunDataObj;
import com.sk.sqhk.module.home.network.response.JinRongChaoShiObj;
import com.sk.sqhk.module.home.network.response.MyFenRunObj;
import com.sk.sqhk.module.home.network.response.MyXiaJiObj;
import com.sk.sqhk.module.home.network.response.SearchObj;
import com.sk.sqhk.module.home.network.response.SearchResultObj;
import com.sk.sqhk.module.home.network.response.ShouYiDetailObj;
import com.sk.sqhk.module.home.network.response.XinYongDaiObj;
import com.sk.sqhk.module.home.network.response.ZiXunDetailObj;

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
    @GET("api/Informations/GetTypeAssemBlages")
    Call<ResponseObj<List<HomeJiSuObj>>> getHomeJiSuData(@QueryMap Map<String, String> map);

    //首页轮播图信息
    @POST("api/Informations/PostRoastingChart")
    Call<ResponseObj<BannerObj>> getHomeBaner(@QueryMap Map<String, String> map );
    //首页类别集合信息
    @GET("api/Information/GetTypeAssemblage")
    Call<ResponseObj<HomeTypeAssemblageObj>> getTypeAssemblage(@QueryMap Map<String, String> map);

    //首页中部图片信息
    @GET("api/Information/GetHomePageImage")
    Call<ResponseObj<HomePageImageObj>> getHomePageImage(@QueryMap Map<String, String> map);

    //首页每日精选
    @GET("api/Information/GetDailybest")
    Call<ResponseObj<HomeDailybestObj>> getDailybest(@QueryMap Map<String, String> map);

    //首页咨询列表数据
    @GET("api/Informations/GetInformationList")
    Call<ResponseObj<List<HomeZiXunDataObj>>> getHomeZiXunData(@QueryMap Map<String, String> map);

    //more咨询列表数据
    @GET("api/Informations/GetInformationMoreList")
    Call<ResponseObj<List<HomeZiXunDataObj>>> getMoreZiXunData(@QueryMap Map<String, String> map);

    //咨询详情数据
    @GET("api/Informations/GetInformationMore")
    Call<ResponseObj<ZiXunDetailObj>> getZiXunDetail(@QueryMap Map<String, String> map);


    //咨询详情点赞
    @GET("api/Informations/GetThumbupForum")
    Call<ResponseObj<DianZanObj>> ziXunDianZan(@QueryMap Map<String, String> map);


    //首页中部图片
    @GET("api/Informations/GetHomePageCenterImage")
    Call<ResponseObj<HomeImgObj>> getHomeImg(@QueryMap Map<String, String> map);


    //首页快速认证，分享推荐等
    @GET("api/Informations/GetHomePageClass")
    Call<ResponseObj<List<HomeFenLeiObj>>> getHomeFenLei(@QueryMap Map<String, String> map);


    //我的分润
    @GET("api/AccountWithdrawal/GetMyCommission")
    Call<ResponseObj<MyFenRunObj>> getFenRun(@QueryMap Map<String, String> map);

    //提现
    @GET("api/AccountWithdrawal/GetCommissionWithdrawals")
    Call<ResponseObj<BaseObj>> fenRunTiXian(@QueryMap Map<String, String> map);

    //收益明细
    @GET("api/AccountWithdrawal/GetCommissionDetail")
    Call<ResponseObj<List<ShouYiDetailObj>>> getShouYiDetail(@QueryMap Map<String, String> map);


    //我的下级-我的邀请
    @GET("api/User/GetMyLowerLevel")
    Call<ResponseObj<List<MyXiaJiObj>>> getMyXiaJi(@QueryMap Map<String, String> map);



    //金融超市
    @GET("api/FinancialSupermarket/GetFinancialSupermarket")
    Call<ResponseObj<JinRongChaoShiObj>> getJinRongChaoShi(@QueryMap Map<String, String> map);

    //金融贷列表
    @GET("api/FinancialSupermarket/GetCreditLoanList")
    Call<ResponseObj<XinYongDaiObj>> getJinRongDaiList(@QueryMap Map<String, String> map);

    //金融贷详情
    @GET("api/FinancialSupermarket/GetCreditLoanDetail")
    Call<ResponseObj<DaiKuanDetailObj>> getDaiKuanDetail(@QueryMap Map<String, String> map);



    //添加银行卡-银行卡列表
    @GET("api/AccountWithdrawal/GetBankList")
    Call<ResponseObj<List<BankObj>>> getBankListForAddCard(@QueryMap Map<String, String> map);


    //添加储蓄卡
    @POST("api/AccountWithdrawal/PostAddAccountS")
    Call<ResponseObj<BaseObj>> addChuXu(@QueryMap Map<String, String> map,@Body AddChuXunBody body);


    //添加信用卡-发短信
    @GET("api/HandleCardOnline/GetCreateCardSMS")
    Call<ResponseObj<BaseObj>> getMsgCodeForAddXinYongKa(@QueryMap Map<String, String> map);


    //添加信用卡
    @GET("api/HandleCardOnline/GetCreateCard")
    Call<ResponseObj<BaseObj>> addXinYongKa(@QueryMap Map<String, String> map);











    //根据城市名获取ID
    @GET("api/Lib/GetCityID")
    Call<ResponseObj<CityIdObj>> getCityId(@QueryMap Map<String, String> map);

    //获取全部区/县商业圈
    @GET("api/Lib/GetAreaBusinessCircle")
    Call<ResponseObj<List<AreaBusinessCircleObj>>> getAreaBusinessCircle(@QueryMap Map<String, String> map);
    //商家列表(分类)
    @POST("api/MerchantCenter/PostMerchantList")
    Call<ResponseObj<HomeTypeMerchantListObj>> postMerchantList(@QueryMap Map<String, String> map, @Body HomeTypeMerchantListBody body);

    //首页公告信息
    @GET("api/Information/GetAnnouncement")
    Call<ResponseObj<List<HomeAnnouncementObj>>> getAnnouncement(@QueryMap Map<String, String> map);



    //热门搜索词、历史搜索词
    @GET("api/MerchantCenter/GetHottestSearch")
    Call<ResponseObj<SearchObj>> getHottestSearch(@QueryMap Map<String, String> map);

    //删除历史搜索词
    @GET("api/MerchantCenter/GetDelRecentlySearch")
    Call<ResponseObj<BaseObj>> getDelRecentlySearch(@QueryMap Map<String, String> map);

    //搜索商家
    @POST("api/MerchantCenter/PostSearchMerchant")
    Call<ResponseObj<SearchResultObj>>postSearchMerchant(@QueryMap Map<String, String> map, @Body SearchResultBody body);

    //猜你喜欢
    @GET("api/Information/GetGuessYouLike")
    Call<ResponseObj<HomeLikeObj>>getGuessYouLike(@QueryMap Map<String, String> map);



}
