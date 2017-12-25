package com.sk.sqhk.module.home.activity;

import android.view.View;

import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.library.base.view.MyRecyclerView;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/20.
 */

public class MyFenRunActivity extends BaseActivity {
    @BindView(R.id.rv_tixian_mingxi)
    MyRecyclerView rv_tixian_mingxi;
    LoadMoreAdapter adapter;
    @Override
    protected int getContentView() {
        setAppTitle("我的分润");
        setAppRightTitle("收益明细");
        return R.layout.act_my_fen_run;
    }

    @Override
    protected void initView() {
        adapter=new LoadMoreAdapter(mContext,R.layout.item_ti_xian_ming_xi,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, Object bean) {

            }
        };
        adapter.setOnLoadMoreListener(this);

        rv_tixian_mingxi.setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.app_right_tv})
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.app_right_tv:
                STActivity(ShouYiMingXiActivity.class);
            break;
        }
    }
}
