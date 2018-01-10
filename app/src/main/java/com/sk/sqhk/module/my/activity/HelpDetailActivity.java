package com.sk.sqhk.module.my.activity;

import android.view.View;
import android.widget.TextView;

import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.module.my.network.response.HelpCenterObj;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/25.
 */

public class HelpDetailActivity extends BaseActivity {
    @BindView(R.id.tv_help_detail_title)
    TextView tv_help_detail_title;
    @BindView(R.id.tv_help_detail_content)
    TextView tv_help_detail_content;

    @Override
    protected int getContentView() {
        setAppTitle("帮助详情");
        return R.layout.act_help_detail;
    }

    @Override
    protected void initView() {
        HelpCenterObj helpCenterObj= (HelpCenterObj) getIntent().getSerializableExtra(Constant.IParam.helpCenter);
        tv_help_detail_title.setText(helpCenterObj.getTitle());
        tv_help_detail_content.setText(helpCenterObj.getContent());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}
