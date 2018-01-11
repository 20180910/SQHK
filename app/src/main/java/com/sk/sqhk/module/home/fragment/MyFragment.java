package com.sk.sqhk.module.home.fragment;

import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.SPUtils;
import com.github.customview.CircleImageView;
import com.library.base.MyCallBack;
import com.sk.sqhk.AppXml;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseFragment;
import com.sk.sqhk.module.home.activity.YaoQingActivity;
import com.sk.sqhk.module.my.activity.HelpCenterActivity;
import com.sk.sqhk.module.my.activity.MyAccountActivity;
import com.sk.sqhk.module.my.activity.SettingActivity;
import com.sk.sqhk.module.my.network.ApiRequest;
import com.sk.sqhk.module.my.network.response.LoginObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/4.
 */

public class MyFragment extends BaseFragment {

    @BindView(R.id.civ_my)
    CircleImageView civ_my;
    @BindView(R.id.tv_my_name)
    TextView tv_my_name;
    @BindView(R.id.tv_my_yue)
    TextView tv_my_yue;
    @BindView(R.id.tv_my_shouyi)
    TextView tv_my_shouyi;

    @Override
    protected int getContentView() {

        return R.layout.frag_my;
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
    protected void onMyReStart() {
        super.onMyReStart();
        getData(1, false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", getUserId());
        map.put("sign", getSign(map));
        ApiRequest.getUserInfo(map, new MyCallBack<LoginObj>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(LoginObj obj) {
                Glide.with(mContext).load(obj.getAvatar()).error(R.drawable.my_people).into(civ_my);
                tv_my_name.setText(obj.getUser_name());
                tv_my_yue.setText("账户余额:" + obj.getAmount());
                loginResult(obj);
            }
        });

    }

    private void loginResult(LoginObj obj) {
        tv_my_shouyi.setText("累计奖励: ¥"+obj.getCommission()+"元");

        SPUtils.setPrefString(mContext, AppXml.user_id, obj.getUser_id());
        SPUtils.setPrefString(mContext, AppXml.mobile, obj.getMobile());
        SPUtils.setPrefString(mContext, AppXml.sex, obj.getSex());
        SPUtils.setPrefString(mContext, AppXml.avatar, obj.getAvatar());
        SPUtils.setPrefString(mContext, AppXml.birthday, obj.getBirthday());
        SPUtils.setPrefString(mContext, AppXml.user_name, obj.getUser_name());
        SPUtils.setPrefString(mContext, AppXml.nick_name, obj.getNick_name());
        SPUtils.setPrefFloat(mContext, AppXml.amount, obj.getAmount());
        SPUtils.setPrefFloat(mContext, AppXml.commission, obj.getCommission());
        SPUtils.setPrefInt(mContext, AppXml.message_sink, obj.getMessage_sink());
        SPUtils.setPrefInt(mContext, AppXml.is_validation, obj.getIs_validation());
        SPUtils.setPrefInt(mContext, AppXml.cumulative_reward, obj.getCumulative_reward());

        SPUtils.setPrefString(mContext, AppXml.name, obj.getName());
        SPUtils.setPrefString(mContext, AppXml.email, obj.getEmail());
        SPUtils.setPrefString(mContext, AppXml.major, obj.getMajor());
    }

    @OnClick({R.id.iv_my_msg, R.id.tv_my_setting,R.id.tv_my_yinhangka, R.id.tv_my_zhangdan, R.id.tv_my_zhanghu, R.id.tv_my_bangzhu,R.id.tv_my_yaoqing})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_my_msg:
                break;
            case R.id.tv_my_setting:
                STActivity(SettingActivity.class);
                break;
            case R.id.tv_my_yinhangka:
                break;
            case R.id.tv_my_zhangdan:
                break;
            case R.id.tv_my_zhanghu:
                STActivity(MyAccountActivity.class);
                break;
            case R.id.tv_my_yaoqing:
                STActivity(YaoQingActivity.class);
                break;
            case R.id.tv_my_bangzhu:
                STActivity(HelpCenterActivity.class);
                break;
        }
    }
}
