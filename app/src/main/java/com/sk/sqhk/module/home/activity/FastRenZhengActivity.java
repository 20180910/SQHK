package com.sk.sqhk.module.home.activity;

import android.view.View;

import com.github.customview.MyEditText;
import com.github.customview.MyImageView;
import com.github.customview.MyRadioButton;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/20.
 */

public class FastRenZhengActivity extends BaseActivity {

    @BindView(R.id.iv_renzheng_img)
    MyImageView iv_renzheng_img;
    @BindView(R.id.iv_renzheng_second_img)
    MyImageView iv_renzheng_second_img;
    @BindView(R.id.et_renzheng_name)
    MyEditText et_renzheng_name;
    @BindView(R.id.rb_renzheng_nan)
    MyRadioButton rb_renzheng_nan;
    @BindView(R.id.et_renzheng_eid)
    MyEditText et_renzheng_eid;
    @BindView(R.id.et_renzheng_phone)
    MyEditText et_renzheng_phone;
    @BindView(R.id.et_renzheng_address)
    MyEditText et_renzheng_address;

    @Override
    protected int getContentView() {
        setAppTitle("快速认证");
        return R.layout.act_fast_ren_zheng;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.ll_renzheng, R.id.ll_renzheng_second, R.id.tx_renzheng_commit})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_renzheng:
                break;
            case R.id.ll_renzheng_second:
                break;
            case R.id.tx_renzheng_commit:
                break;
        }
    }
}
