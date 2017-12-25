package com.sk.sqhk.module.my.activity;

import android.view.View;

import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;

/**
 * Created by Administrator on 2017/12/25.
 */

public class MyBankListActivity extends BaseActivity {
    @Override
    protected int getContentView() {
        setAppTitle("我的银行卡");
        setAppRightImg(R.drawable.add_bank);
        return R.layout.act_my_bank_list;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}
