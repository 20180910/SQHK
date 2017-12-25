package com.sk.sqhk.module.home.fragment;

import android.text.TextUtils;
import android.view.View;

import com.library.base.MyCallBack;
import com.sk.sqhk.GetSign;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseFragment;
import com.sk.sqhk.module.my.network.ApiRequest;
import com.sk.sqhk.module.my.network.response.UserInfoObj;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/4.
 */

public class MyFragment extends BaseFragment {

    @Override
    protected int getContentView() {

        return R.layout.frag_my;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void initData() {
        showProgress();
        getUserInfo();

    }

    private void getUserInfo() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", getUserId());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getUserInfo(map, new MyCallBack<UserInfoObj>(mContext, pcfl, pl_load) {
            @Override
            public void onSuccess(UserInfoObj obj) {
                if (TextUtils.isEmpty(obj.getAvatar())) {
                } else {
                }
            }
        });

    }


    public void onViewClick(View view) {
        switch (view.getId()) {
            case 1:

                break;

        }
    }

}
