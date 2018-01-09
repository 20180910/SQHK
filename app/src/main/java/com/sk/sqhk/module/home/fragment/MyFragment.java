package com.sk.sqhk.module.home.fragment;

import android.view.View;

import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseFragment;
import com.sk.sqhk.module.my.activity.SettingActivity;

import butterknife.OnClick;

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
//        getUserInfo();

    }

    @Override
    protected void onMyReStart() {
        super.onMyReStart();
    }
    /*private void getUserInfo() {
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

    }*/

    @OnClick({R.id.tv_my_setting})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_my_setting:
                STActivity(SettingActivity.class);
                break;

        }
    }

}
