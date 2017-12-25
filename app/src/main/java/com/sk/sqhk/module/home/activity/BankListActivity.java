package com.sk.sqhk.module.home.activity;

import android.view.View;

import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.library.base.view.MyRecyclerView;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/20.
 */

public class BankListActivity extends BaseActivity {
    @BindView(R.id.rv_bank)
    MyRecyclerView rv_bank;

    LoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        setAppTitle("选择银行卡");
        setAppRightImg(R.drawable.add_bank);
        return R.layout.act_bank_list;
    }

    @Override
    protected void initView() {
        adapter=new LoadMoreAdapter(mContext,R.layout.item_bank,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, Object bean) {

            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_bank.setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}
