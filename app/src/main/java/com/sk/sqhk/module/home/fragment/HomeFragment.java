package com.sk.sqhk.module.home.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.library.base.BaseObj;
import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseFragment;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.module.home.activity.FastRenZhengActivity;
import com.sk.sqhk.module.home.activity.JinRongChaoShiActivity;
import com.sk.sqhk.module.home.activity.MoreZiXunActivity;
import com.sk.sqhk.module.home.activity.MyFenRunActivity;
import com.sk.sqhk.module.home.activity.WenZhangDetailActivity;
import com.sk.sqhk.module.home.activity.WoDeYaoQingActivity;
import com.sk.sqhk.module.home.activity.YaoQingActivity;
import com.sk.sqhk.module.home.network.ApiRequest;
import com.sk.sqhk.module.home.network.response.BannerObj;
import com.sk.sqhk.module.home.network.response.HomeFenLeiObj;
import com.sk.sqhk.module.home.network.response.HomeImgObj;
import com.sk.sqhk.module.home.network.response.HomeJiSuObj;
import com.sk.sqhk.module.home.network.response.HomeZiXunDataObj;
import com.sk.sqhk.module.my.activity.LoginActivity;
import com.sk.sqhk.module.my.activity.MyMessageActivity;
import com.sk.sqhk.network.NetApiRequest;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/4.
 */

public class HomeFragment extends BaseFragment {


    @BindView(R.id.bn_home)
    Banner bn_home;
    @BindView(R.id.rv_home)
    RecyclerView rv_home;
    @BindView(R.id.iv_home_img)
    ImageView iv_home_img;
    @BindView(R.id.tv_home_has_msg)
    TextView tv_home_has_msg;

    @BindView(R.id.tv_home_jisu1)
    TextView tv_home_jisu1;
    @BindView(R.id.tv_home_jisu2)
    TextView tv_home_jisu2;
    @BindView(R.id.tv_home_jisu3)
    TextView tv_home_jisu3;
    @BindView(R.id.iv_home_jisu1)
    ImageView iv_home_jisu1;
    @BindView(R.id.iv_home_jisu2)
    ImageView iv_home_jisu2;

    @BindView(R.id.iv_home_jisu3)
    ImageView iv_home_jisu3;

    @BindView(R.id.iv_home_fenlei1)
    ImageView iv_home_fenlei1;
    @BindView(R.id.tv_home_fenlei1)
    TextView tv_home_fenlei1;

    @BindView(R.id.iv_home_fenlei2)
    ImageView iv_home_fenlei2;
    @BindView(R.id.tv_home_fenlei2)
    TextView tv_home_fenlei2;

    @BindView(R.id.iv_home_fenlei3)
    ImageView iv_home_fenlei3;
    @BindView(R.id.tv_home_fenlei3)
    TextView tv_home_fenlei3;

    @BindView(R.id.iv_home_fenlei4)
    ImageView iv_home_fenlei4;
    @BindView(R.id.tv_home_fenlei4)
    TextView tv_home_fenlei4;


    LoadMoreAdapter adapter;

    private List<String> bannerList = new ArrayList<String>();

    @Override
    protected int getContentView() {
        return R.layout.frag_home;
    }

    @Override
    protected void initView() {

        adapter = new LoadMoreAdapter<HomeZiXunDataObj>(mContext, R.layout.item_zui_xin_zixun, pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, HomeZiXunDataObj bean) {
                ImageView iv_home_zixun_img = holder.getImageView(R.id.iv_home_zixun_img);
                Glide.with(mContext).load(bean.getImage_url()).error(R.color.c_press).into(iv_home_zixun_img);
                holder.setText(R.id.tv_home_zixun_content, bean.getTitle())
                        .setText(R.id.tv_home_zixun_from, bean.getAuthor())
                        .setText(R.id.tv_home_zixun_time, bean.getAdd_time());

                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra(Constant.IParam.ziXunId, bean.getInformation_id() + "");
                        STActivity(intent, WenZhangDetailActivity.class);
                    }
                });
            }
        };
//        adapter.setOnLoadMoreListener(this);
        rv_home.setFocusable(false);
        rv_home.setNestedScrollingEnabled(false);
        rv_home.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        showProgress();
        getHomeFenLei();
        getOtherData();
        getData(1, false);
    }

    @Override
    protected void onMyReStart() {
        super.onMyReStart();
        isHasMsg();
    }
    @Override
    protected void getOtherData() {
        super.getOtherData();
        getBanner();
        getHomeImg();
        isHasMsg();
        getJiSuData();
    }
    private void getBanner() {
        Map<String,String>map=new HashMap<String,String>();
        map.put("rnd",getRnd());
        map.put("sign",getSign(map));
        ApiRequest.getHomeBaner(map, new MyCallBack<BannerObj>(mContext) {
            @Override
            public void onSuccess(BannerObj obj) {
                if(notEmpty(obj.getRoasting_list())){
                    bannerList = new ArrayList<String>();
                    for (int i = 0; i <obj.getRoasting_list().size(); i++) {
                        bannerList.add(obj.getRoasting_list().get(i).getImg_url());
                    }
                    setBannerList(bn_home,bannerList);
                }
            }
        });

    }

    private void getHomeFenLei() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("rnd", getRnd());
        map.put("sign", getSign(map));
        ApiRequest.getHomeFenLei(map, new MyCallBack<List<HomeFenLeiObj>>(mContext) {
            @Override
            public void onSuccess(List<HomeFenLeiObj> list) {
                if (notEmpty(list)) {
                    if (list.size() > 0) {
                        Glide.with(mContext).load(list.get(0).getImg_url()).error(R.color.c_press).into(iv_home_fenlei1);
                        tv_home_fenlei1.setText(list.get(0).getTitle());
                    }
                    if (list.size() > 1) {
                        Glide.with(mContext).load(list.get(1).getImg_url()).error(R.color.c_press).into(iv_home_fenlei2);
                        tv_home_fenlei2.setText(list.get(1).getTitle());
                    }
                    if (list.size() > 2) {
                        Glide.with(mContext).load(list.get(2).getImg_url()).error(R.color.c_press).into(iv_home_fenlei3);
                        tv_home_fenlei3.setText(list.get(2).getTitle());
                    }
                    if (list.size() > 3) {
                        Glide.with(mContext).load(list.get(3).getImg_url()).error(R.color.c_press).into(iv_home_fenlei4);
                        tv_home_fenlei4.setText(list.get(3).getTitle());
                    }

                }
            }
        });

    }

    private void getHomeImg() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("rnd", getRnd());
        map.put("sign", getSign(map));
        ApiRequest.getHomeImg(map, new MyCallBack<HomeImgObj>(mContext) {
            @Override
            public void onSuccess(HomeImgObj obj) {
                Glide.with(mContext).load(obj.getImg_url()).error(R.color.c_press).into(iv_home_img);
            }
        });

    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String, String> map = new HashMap<String, String>();
        map.put("rnd", getRnd());
        map.put("sign", getSign(map));
        ApiRequest.getHomeZiXunData(map, new MyCallBack<List<HomeZiXunDataObj>>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(List<HomeZiXunDataObj> list) {
                if (isLoad) {
                    pageNum++;
                    adapter.addList(list, true);
                } else {
                    pageNum = 2;
                    adapter.setList(list, true);
                }
            }
        });

    }


    private void getJiSuData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("rnd", getRnd());
        map.put("sign", getSign(map));
        ApiRequest.getHomeJiSuData(map, new MyCallBack<List<HomeJiSuObj>>(mContext) {
            @Override
            public void onSuccess(List<HomeJiSuObj> list) {
                if (notEmpty(list)) {
                    if (list.size() > 0) {
                        tv_home_jisu1.setText(list.get(0).getTitle());
                        Glide.with(mContext).load(list.get(0).getImg_url()).error(R.drawable.home_icon02).into(iv_home_jisu1);
                    }
                    if (list.size() > 1) {
                        tv_home_jisu2.setText(list.get(1).getTitle());
                        Glide.with(mContext).load(list.get(1).getImg_url()).error(R.drawable.home_icon03).into(iv_home_jisu2);
                    }
                    if (list.size() > 2) {
                        tv_home_jisu3.setText(list.get(2).getTitle());
                        Glide.with(mContext).load(list.get(2).getImg_url()).error(R.drawable.home_icon04).into(iv_home_jisu3);
                    }
                }
            }
        });
    }

    public void isHasMsg() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", getUserId());
        map.put("sign", getSign(map));
        NetApiRequest.isHasNewMsg(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                if (obj.getIs_check() == 1) {
                    tv_home_has_msg.setVisibility(View.VISIBLE);
                } else {
                    tv_home_has_msg.setVisibility(View.GONE);
                }
            }
        });
    }

    @OnClick({R.id.ll_home_renzheng,
            R.id.ll_home_tab1,
            R.id.ll_home_tab2,
            R.id.ll_home_tab3,
            R.id.ll_home_fenxiang,
            R.id.ll_home_fenrun,
            R.id.tv_home_more_zixun,
            R.id.iv_home_news,
            R.id.ll_home_xiaji,})
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home_tab1:

                break;
            case R.id.ll_home_tab2:

                break;
            case R.id.ll_home_tab3:
                STActivity(JinRongChaoShiActivity.class);
                break;
            case R.id.iv_home_news:
                if(noLogin()){
                    STActivity(LoginActivity.class);
                }else{
                    STActivity(MyMessageActivity.class);
                }
                break;
            case R.id.tv_home_more_zixun:
                STActivity(MoreZiXunActivity.class);
                break;
            case R.id.ll_home_renzheng:
                if(noLogin()){
                    STActivity(LoginActivity.class);
                }else{
                    STActivity(FastRenZhengActivity.class);
                }
                break;
            case R.id.ll_home_fenxiang:
                if(noLogin()){
                    STActivity(LoginActivity.class);
                }else{
                    STActivity(YaoQingActivity.class);
                }
                break;
            case R.id.ll_home_fenrun:
                if(noLogin()){
                    STActivity(LoginActivity.class);
                }else{
                    STActivity(MyFenRunActivity.class);
                }
                break;
            case R.id.ll_home_xiaji:
                if(noLogin()){
                    STActivity(LoginActivity.class);
                }else{
                    STActivity(WoDeYaoQingActivity.class);
                }
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (bn_home != null && bannerList != null) {
            bn_home.stopAutoPlay();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (bn_home != null && bannerList != null) {
            bn_home.startAutoPlay();
        }
    }


}
