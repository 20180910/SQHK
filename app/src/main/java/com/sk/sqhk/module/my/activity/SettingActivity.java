package com.sk.sqhk.module.my.activity;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;

import com.github.androidtools.SPUtils;
import com.sk.sqhk.AppXml;
import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/25.
 */

public class SettingActivity extends BaseActivity {
    @Override
    protected int getContentView() {
        setAppTitle("安全设置");
        return R.layout.act_setting;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_setting_exit})
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.tv_setting_exit:
                SPUtils.removeKey(mContext, AppXml.user_id);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent(Constant.Bro.operation));

                STActivity(LoginActivity.class);

                finish();
            break;
        }
    }
}
