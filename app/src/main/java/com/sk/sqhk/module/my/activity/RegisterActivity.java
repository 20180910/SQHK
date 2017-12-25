package com.sk.sqhk.module.my.activity;

import android.content.Intent;
import android.view.View;

import com.github.androidtools.SPUtils;
import com.sk.sqhk.Config;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.module.home.activity.MainActivity;
import com.sk.sqhk.module.my.network.response.LoginObj;

/**
 * Created by Administrator on 2017/12/4.
 */

public class RegisterActivity extends BaseActivity {


    @Override
    protected int getContentView() {
        setAppTitle("注册");
        return R.layout.act_register;
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }


    public void onClick(View view) {

    }
    private void login() {
        showLoading();
       /* Map<String,String> map=new HashMap<String,String>();
        map.put("username",xuehao);
        map.put("password",pwd);
        map.put("type","1");
        map.put("RegistrationID", SPUtils.getPrefString(mContext, Config.jiguangRegistrationId,""));
        map.put("sign", GetSign.getSign(map));
        ApiRequest.userLogin(map, new MyCallBack<LoginObj>(mContext) {
            @Override
            public void onSuccess(LoginObj obj) {
                loginResult(obj);

            }
        });
*/

    }
    private void loginResult(LoginObj obj) {
        SPUtils.setPrefString(mContext, Config.user_id,obj.getUser_id());
        SPUtils.setPrefString(mContext, Config.mobile,obj.getMobile());
        SPUtils.setPrefString(mContext, Config.sex,obj.getSex());
        SPUtils.setPrefString(mContext, Config.avatar,obj.getAvatar());
        SPUtils.setPrefString(mContext, Config.user_name,obj.getUser_name());
        SPUtils.setPrefString(mContext, Config.class_name,obj.getClass_name());
        SPUtils.setPrefString(mContext, Config.name,obj.getName());
        SPUtils.setPrefString(mContext, Config.email,obj.getEmail());
        SPUtils.setPrefBoolean(mContext, Config.user_switch, obj.getMessage_sink()==1?true:false);
//        LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent(Config.Bro.operation));

        Intent intent=new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        STActivity(intent,MainActivity.class);

        finish();

    }
    private long mExitTime;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 1500) {
            showToastS("再按一次退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
            if(Config.exitAPP.equals(1)){
                Intent intent=new Intent(Config.exitAPP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                STActivity(intent,MainActivity.class);
            }
            super.onBackPressed();
        }
    }


}
