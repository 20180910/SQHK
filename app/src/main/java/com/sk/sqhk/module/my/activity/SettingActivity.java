package com.sk.sqhk.module.my.activity;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.github.androidtools.SPUtils;
import com.github.baseclass.rx.IOCallBack;
import com.github.baseclass.view.MyDialog;
import com.library.base.BaseObj;
import com.library.base.tools.CacheUtils;
import com.sk.sqhk.AppXml;
import com.sk.sqhk.Config;
import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.module.home.activity.FastRenZhengActivity;
import com.sk.sqhk.module.home.bean.AppInfo;
import com.sk.sqhk.module.my.network.ApiRequest;
import com.sk.sqhk.module.my.network.response.LoginObj;
import com.sk.sqhk.network.NetApiRequest;
import com.sk.sqhk.network.response.APPVersionObj;
import com.sk.sqhk.service.MyAPPDownloadService;
import com.sk.sqhk.tools.FileUtils;
import com.suke.widget.SwitchButton;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/12/25.
 */

public class SettingActivity extends BaseActivity {
    @BindView(R.id.tv_setting_new_version)
    TextView tv_setting_new_version;
    @BindView(R.id.tv_setting_renzheng)
    TextView tv_setting_renzheng;
    @BindView(R.id.tv_setting_phone)
    TextView tv_setting_phone;
    @BindView(R.id.tv_setting_updatepwd)
    TextView tv_setting_updatepwd;
    @BindView(R.id.tv_setting_version)
    TextView tv_setting_version;
    @BindView(R.id.tv_setting_cachesize)
    TextView tv_setting_cachesize;
    @BindView(R.id.sb_setting_tixing)
    SwitchButton sb_setting_tixing;

    @Override
    protected int getContentView() {
        setAppTitle("安全设置");
        return R.layout.act_setting;
    }

    @Override
    protected void initView() {
        if(SPUtils.getBoolean(mContext,Config.appHasNewVersion,false)){
            tv_setting_new_version.setVisibility(View.VISIBLE);
        }else{
            tv_setting_new_version.setVisibility(View.INVISIBLE);
        }

        setRenZheng();
        tv_setting_version.setText("V"+getAppVersionName());

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

        RXStart(new IOCallBack<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    String cacheSize = CacheUtils.getExternalCacheSize(mContext);
                    subscriber.onNext(cacheSize);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onMyNext(String s) {
                tv_setting_cachesize.setText(s);
            }
        });
    }

    private void setRenZheng() {
        int renZhengFlag = SPUtils.getInt(mContext, AppXml.is_validation, 0);
        //身份认证状态(0未认证 1待审核 2审核通过 3审核未通过)
        String renZhengStr="未认证";
        switch (renZhengFlag){
            case 0:
                renZhengStr="未认证";
            break;
            case 1:
                renZhengStr="待审核";
            break;
            case 2:
                renZhengStr="审核通过";
            break;
            case 3:
                renZhengStr="审核未通过";
            break;
        }
        tv_setting_renzheng.setText(renZhengStr);
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
    }

    private void getVersionName() {
        Map<String,String> map=new HashMap<String,String>();
        map.put("rnd",getRnd());
        map.put("sign",getSign(map));
        NetApiRequest.getAPPVersion(map, new MyCallBack<APPVersionObj>(mContext) {
            @Override
            public void onSuccess(APPVersionObj obj) {
                if(obj.getAndroid_version()>getAppVersionCode()){
                    SPUtils.setPrefString(mContext,Config.appDownloadUrl,obj.getAndroid_vs_url());
                    SPUtils.setPrefBoolean(mContext, Config.appHasNewVersion,true);
                    MyDialog.Builder mDialog=new MyDialog.Builder(mContext);
                    mDialog.setMessage("检测到app有新版本是否更新?");
                    mDialog.setNegativeButton((dialog, which) -> dialog.dismiss());
                    mDialog.setPositiveButton((dialog, which) -> {
                        dialog.dismiss();
                        FileUtils.deleteFile("shenqihuanka.apk");
                        AppInfo info=new AppInfo();
                        info.setUrl(obj.getAndroid_vs_url());
                        info.setHouZhui(".apk");
                        info.setFileName("shenqihuanka");
                        info.setId(obj.getAndroid_version()+"");
                        MyAPPDownloadService.intentDownload(mContext, info);
                    });
                    mDialog.create().show();
                }else{
                    showMsg("当前app已是最新版本");
                    tv_setting_new_version.setVisibility(View.INVISIBLE);
                    SPUtils.setPrefBoolean(mContext,Config.appHasNewVersion,false);
                }
            }
        });
    }

    @OnClick({R.id.ll_setting_update,R.id.ll_setting_clear_cache,R.id.tv_setting_about_we,R.id.tv_setting_exit,R.id.tv_setting_renzheng, R.id.tv_setting_phone, R.id.tv_setting_updatepwd})
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.ll_setting_update:
                getVersionName();
                break;
            case R.id.ll_setting_clear_cache:
                deleteCache(tv_setting_cachesize,false);
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
                STActivityForResult(FastRenZhengActivity.class,2000);
                break;
            case R.id.tv_setting_phone:
                STActivity(UpdatePhoneActivity.class);
                break;
            case R.id.tv_setting_updatepwd:
                STActivity(UpdatePWDActivity.class);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case 2000:
                    getUserInfo();
                break;
            }
        }
    }

    private void getUserInfo() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", getUserId());
        map.put("sign", getSign(map));
        ApiRequest.getUserInfo(map, new MyCallBack<LoginObj>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(LoginObj obj) {
                loginResult(obj);
            }
        });
    }
    private void loginResult(LoginObj obj) {

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

        setRenZheng();
    }

}
