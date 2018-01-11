package com.sk.sqhk.module.home.activity;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;

import com.library.base.MyCallBack;
import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.module.my.network.ApiRequest;
import com.sk.sqhk.module.my.network.response.YaoQingObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/20.
 */

public class YaoQingActivity extends BaseActivity {
    @BindView(R.id.wv_yao_qing)
    WebView wv_yao_qing;
    private String activityRule;

    @Override
    protected int getContentView() {
        setAppTitle("邀请好友");
        setAppRightTitle("活动规则");
        return R.layout.act_yao_qing;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        showProgress();
        getData(1,false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("sign",getSign(map));
        ApiRequest.yaoQing(map, new MyCallBack<YaoQingObj>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(YaoQingObj obj) {
                activityRule = obj.getActivity_rule();
                initSimpleWebViewForUrl(wv_yao_qing,obj.getUrl());
            }
        });

    }

    @OnClick({R.id.tv_yao_qing,R.id.app_right_tv})
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.app_right_tv:
                Intent intent=new Intent();
                intent.putExtra(Constant.IParam.huoDongGuiZe,activityRule);
                STActivity(intent,HuoDongGuiZeActivity.class);
                break;
            case R.id.tv_yao_qing:

            break;
        }
    }
}
