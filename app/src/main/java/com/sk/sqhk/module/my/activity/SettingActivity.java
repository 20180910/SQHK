package com.sk.sqhk.module.my.activity;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.github.androidtools.SPUtils;
import com.library.base.BaseObj;
import com.library.base.MyCallBack;
import com.sk.sqhk.AppXml;
import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.module.home.activity.FastRenZhengActivity;
import com.sk.sqhk.module.my.network.ApiRequest;
import com.sk.sqhk.network.NetApiRequest;
import com.sk.sqhk.network.response.APPVersionObj;
import com.suke.widget.SwitchButton;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/25.
 */

public class SettingActivity extends BaseActivity {
    @BindView(R.id.tv_setting_renzheng)
    TextView tv_setting_renzheng;
    @BindView(R.id.tv_setting_phone)
    TextView tv_setting_phone;
    @BindView(R.id.tv_setting_updatepwd)
    TextView tv_setting_updatepwd;
    @BindView(R.id.tv_setting_version)
    TextView tv_setting_version;
    @BindView(R.id.sb_setting_tixing)
    SwitchButton sb_setting_tixing;

    @Override
    protected int getContentView() {
        setAppTitle("安全设置");
        return R.layout.act_setting;
    }

    @Override
    protected void initView() {
        String phone = SPUtils.getString(mContext, AppXml.mobile, null);
        tv_setting_phone.setText(phone);

        int isOpen = SPUtils.getInt(mContext, AppXml.message_sink, 0);
        sb_setting_tixing.setChecked(isOpen==1?true:false);

        sb_setting_tixing.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction()== MotionEvent.ACTION_UP){
                setOpen(!sb_setting_tixing.isChecked());
            }
            return true;
        });

    }
    private void setOpen(boolean isChecked) {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("message_sink",isChecked?"1":"0");
        map.put("sign",getSign(map));
        ApiRequest.setOpen(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                sb_setting_tixing.setChecked(isChecked);
            }
        });

    }
    @Override
    protected void onMyReStart() {
        super.onMyReStart();
        String phone = SPUtils.getString(mContext, AppXml.mobile, null);
        tv_setting_phone.setText(phone);
    }
    @Override
    protected void initData() {
        getVersionName();
    }

    private void getVersionName() {
        Map<String,String> map=new HashMap<String,String>();
        map.put("rnd",getRnd());
        map.put("sign",getSign(map));
        NetApiRequest.getAPPVersion(map, new MyCallBack<APPVersionObj>(mContext) {
            @Override
            public void onSuccess(APPVersionObj obj) {
                tv_setting_version.setText(obj.getVersion_name());
            }
        });

    }

    @OnClick({R.id.tv_setting_clear_cache,R.id.tv_setting_about_we,R.id.tv_setting_exit,R.id.tv_setting_renzheng, R.id.tv_setting_phone, R.id.tv_setting_updatepwd})
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.tv_setting_clear_cache:
                deleteCache();
                break;
            case R.id.tv_setting_about_we:
                STActivity(AboutWeActivity.class);
                break;
            case R.id.tv_setting_exit:
                SPUtils.removeKey(mContext, AppXml.user_id);

                Intent intent = new Intent(Constant.Bro.operation);
                intent.putExtra(Constant.Bro.flag, Constant.Bro.exit_login);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);

                STActivity(LoginActivity.class);

                finish();
                break;
            case R.id.tv_setting_renzheng:
                STActivity(FastRenZhengActivity.class);
                break;
            case R.id.tv_setting_phone:
                STActivity(UpdatePhoneActivity.class);
                break;
            case R.id.tv_setting_updatepwd:
                STActivity(UpdatePWDActivity.class);
                break;
        }
    }
}
