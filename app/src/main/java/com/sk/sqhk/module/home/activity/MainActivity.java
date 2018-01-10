package com.sk.sqhk.module.home.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.github.androidtools.SPUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.customview.MyRadioButton;
import com.library.base.BaseObj;
import com.library.base.MyCallBack;
import com.sk.sqhk.AppXml;
import com.sk.sqhk.Config;
import com.sk.sqhk.Constant;
import com.sk.sqhk.GetSign;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.broadcast.MyOperationBro;
import com.sk.sqhk.module.home.fragment.HomeFragment;
import com.sk.sqhk.module.home.fragment.MyFragment;
import com.sk.sqhk.module.home.fragment.SelectFragment;
import com.sk.sqhk.module.my.activity.LoginActivity;
import com.sk.sqhk.network.NetApiRequest;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/11/4.
 */

public class MainActivity extends BaseActivity {
//    @BindView(R.id.status_bar)
//    View status_bar;

    HomeFragment homeFragment;
    SelectFragment selectFragment;
    MyFragment myFragment;

    @BindView(R.id.fl_content)
    FrameLayout fl_content;
    @BindView(R.id.rb_home_tab1)
    MyRadioButton rb_home_tab1;

    @BindView(R.id.rb_home_tab2)
    MyRadioButton rb_home_tab2;

    @BindView(R.id.rb_home_tab3)
    MyRadioButton rb_home_tab3;
    private MyRadioButton selectView;


    private LocalBroadcastManager localBroadcastManager;
    private MyOperationBro myOperationBro;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        if(SPUtils.getBoolean(mContext, AppXml.needLogin,false)){
            SPUtils.removeKey(mContext,AppXml.needLogin);
            SPUtils.removeKey(mContext,AppXml.user_id);
        }

        homeFragment = new HomeFragment();
        addFragment(R.id.fl_content, homeFragment);

        setTabClickListener();

        setBroadcast();
    }

    private void setTabClickListener() {
        selectView = rb_home_tab1;
        rb_home_tab1.setOnClickListener(getTabClickListener(1));
        rb_home_tab2.setOnClickListener(getTabClickListener(2));
        rb_home_tab3.setOnClickListener(getTabClickListener(3));

    }

    @NonNull
    private MyOnClickListener getTabClickListener(final int index) {
        return new MyOnClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                switch (index) {
                    case 1:
                        selectHome();
                        break;
                    case 2:
                        selectChaXun();
                        break;
                    case 3:
                        if (TextUtils.equals(noLoginCode, getUserId())) {
                            STActivity(LoginActivity.class);
                            selectView.setChecked(true);
                        } else {
                            selectMy();
                        }
                        break;
                }
            }
        };
    }




    private void selectHome() {
        selectView = rb_home_tab1;
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            addFragment(R.id.fl_content, homeFragment);
        } else {
            showFragment(homeFragment);
        }
        hideFragment(selectFragment);
        hideFragment(myFragment);
    }

    private void selectChaXun() {
        selectView = rb_home_tab2;
        if (selectFragment == null) {
            selectFragment = new SelectFragment();
            addFragment(R.id.fl_content, selectFragment);
        } else {
            showFragment(selectFragment);
        }
        hideFragment(homeFragment);
        hideFragment(myFragment);
    }

    private void selectMy() {
        selectView = rb_home_tab3;
        if (myFragment == null) {
            myFragment = new MyFragment();
            addFragment(R.id.fl_content, myFragment);
        } else {
            showFragment(myFragment);
        }
        hideFragment(homeFragment);
        hideFragment(selectFragment);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (Config.exitAPP.equals(intent.getAction())) {
            finish();
        }
    }

    @Override
    protected void initData() {
        getPaymentURL(1);//获取支付宝回传地址
        getPaymentURL(2);//获取微信回传地址
    }

    private void getPaymentURL(int type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("payment_type", type + "");
        map.put("sign", GetSign.getSign(map));
        NetApiRequest.paymentURL(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
               /* if(obj.getPayment_type()==1){
                    SPUtils.setPrefString(mContext,Config.payType_ZFB,obj.getPayment_url());
                }else{
                    SPUtils.setPrefString(mContext,Config.payType_WX,obj.getPayment_url());
                }*/
            }
        });

    }

    @Override
    protected void initRxBus() {
        super.initRxBus();

    }

    protected void onViewClick(View v) {
        switch (v.getId()) {

        }
    }

    private void setBroadcast() {
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        myOperationBro = new MyOperationBro(new MyOperationBro.LoginBroInter() {
            @Override
            public void loginSuccess() {
                selectMy();
            }

            @Override
            public void exitLogin() {
                selectHome();
                selectView.setChecked(true);
            }
        });
        localBroadcastManager.registerReceiver(myOperationBro, new IntentFilter(Constant.Bro.operation));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (localBroadcastManager != null) {
            localBroadcastManager.unregisterReceiver(myOperationBro);
        }
    }


    private long mExitTime;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 1500) {
            showToastS("再按一次退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }
}
