package com.sk.sqhk.module.my.activity;

import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.customview.MyImageView;
import com.library.base.MyCallBack;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.module.my.network.ApiRequest;
import com.sk.sqhk.module.my.network.response.AboutWeObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/25.
 */

public class AboutWeActivity extends BaseActivity {
    @BindView(R.id.iv_about_me_image)
    MyImageView iv_about_me_image;
    @BindView(R.id.tv_about_we_tell)
    TextView tv_about_we_tell;
    @BindView(R.id.tv_about_we_qq)
    TextView tv_about_we_qq;
    @BindView(R.id.tv_about_we_email)
    TextView tv_about_we_email;
    @BindView(R.id.tv_about_we_edition)
    TextView tv_about_we_edition;

    @Override
    protected int getContentView() {
        setAppTitle("关于我们");
        return R.layout.act_about_we;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        showProgress();
        getData(1, false);
    }
    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String, String> map = new HashMap<String, String>();
        map.put("rnd", getRnd());
        map.put("sign", getSign(map));
        ApiRequest.aboutWe(map, new MyCallBack<AboutWeObj>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(AboutWeObj obj) {
                Glide.with(mContext).load(obj.getImage()).error(R.color.c_press).into(iv_about_me_image);
                tv_about_we_tell.setText(obj.getTel_wechat());
                tv_about_we_qq.setText(obj.getQQ());
                tv_about_we_email.setText(obj.getEmail());
                tv_about_we_edition.setText(obj.getEdition());
            }
        });

    }
    @Override
    protected void onViewClick(View v) {

    }
}
