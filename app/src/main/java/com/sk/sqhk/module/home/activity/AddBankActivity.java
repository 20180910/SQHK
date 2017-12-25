package com.sk.sqhk.module.home.activity;

import android.view.View;

import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/20.
 */

public class AddBankActivity extends BaseActivity {
    @Override
    protected int getContentView() {
        setAppTitle("信用卡");
        return R.layout.act_add_bank;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_add_bank_select})
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.tv_add_bank_select:
                STActivity(BankListActivity.class);
            break;
        }
    }
}
