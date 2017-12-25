package com.sk.sqhk.module.home.activity;

import android.view.View;

import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/20.
 */

public class TiXianResultActivity extends BaseActivity {
    @Override
    protected int getContentView() {
        setAppTitle("提现详情");
        return R.layout.act_ti_xian_result;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.ll_tixian_bank})
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.ll_tixian_bank:
                STActivity(BankListActivity.class);
            break;
        }
    }
}
