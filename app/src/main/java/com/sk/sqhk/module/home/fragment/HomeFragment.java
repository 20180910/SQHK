package com.sk.sqhk.module.home.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.library.base.BaseObj;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseFragment;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.module.home.activity.MyFenRunActivity;
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

    LoadMoreAdapter adapter;

    private List<String> bannerList = new ArrayList<String>();

    @Override
    protected int getContentView() {
        return R.layout.frag_home;
    }

    @Override
    protected void initView() {

        adapter = new LoadMoreAdapter(mContext, R.layout.item_zui_xin_zixun, pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, Object bean) {

            }
        };
        adapter.setOnLoadMoreListener(this);
        adapter.setTestListSize(3);

        rv_home.setNestedScrollingEnabled(false);
        rv_home.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        showProgress();
        getOtherData();
        getData(1,false);
    }

    @Override
    protected void getOtherData() {
        super.getOtherData();
        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("sign",getSign(map));
        NetApiRequest.isHasNewMsg(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {

            }
        });

    }

    @OnClick({R.id.ll_home_renzheng,
            R.id.ll_home_fenxiang,
            R.id.ll_home_fenrun,
            R.id.ll_home_xiaji,})
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.ll_home_renzheng:

            break;
            case R.id.ll_home_fenxiang:

            break;
            case R.id.ll_home_fenrun:
                STActivity(MyFenRunActivity.class);
            break;
            case R.id.ll_home_xiaji:

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
