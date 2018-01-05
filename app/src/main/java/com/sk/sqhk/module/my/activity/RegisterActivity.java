package com.sk.sqhk.module.my.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.github.baseclass.rx.RxUtils;
import com.github.customview.MyEditText;
import com.library.base.BaseObj;
import com.library.base.MyCallBack;
import com.library.base.tools.ZhengZeUtils;
import com.sk.sqhk.Constant;
import com.sk.sqhk.GetSign;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.module.my.network.ApiRequest;
import com.sk.sqhk.module.my.network.request.RegisterBody;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscription;

/**
 * Created by Administrator on 2017/12/4.
 */

public class RegisterActivity extends BaseActivity {


    @BindView(R.id.et_register_phone)
    MyEditText et_register_phone;
    @BindView(R.id.et_register_pwd)
    MyEditText et_register_pwd;
    @BindView(R.id.et_register_msgcode)
    MyEditText et_register_msgcode;
    @BindView(R.id.et_register_code)
    MyEditText et_register_code;
    @BindView(R.id.tv_register_getmsg)
    TextView tv_register_getmsg;
    private String smsCode;
    @Override
    protected int getContentView() {
        setAppTitle("注册");
        return R.layout.act_register;
    }

    @Override
    protected void initView() {

    }
    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_register_commit, R.id.tv_register_xieyi,R.id.tv_register_getmsg})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_register_commit:
                String phone = getSStr(et_register_phone);
                String pwd = getSStr(et_register_pwd);
                String msg = getSStr(et_register_msgcode);
                String yaoQingCode = getSStr(et_register_code);
                if(TextUtils.isEmpty(phone)){
                    showMsg("请输入手机号");
                    return;
                }else if(TextUtils.isEmpty(pwd)){
                    showMsg("请输入密码");
                    return;
                }else if(!ZhengZeUtils.isShuZiAndZiMu(pwd)||pwd.length()>12||pwd.length()<6){
                    showMsg("请输入6-12位数字加字母的密码");
                    return;
                }else if(TextUtils.isEmpty(msg)||!TextUtils.equals(smsCode,msg)){
                    showMsg("请输入正确验证码");
                    return;
                }
                register(phone,pwd,yaoQingCode);
                break;
            case R.id.tv_register_xieyi:

                break;
            case R.id.tv_register_getmsg:
                getMsgCode();
                break;
        }
    }

    private void register(final String phone, String pwd, String yaoQingCode) {
        showLoading();
        Map<String,String> map=new HashMap<String,String>();
        RegisterBody body=new RegisterBody();
        body.setUsername(phone);
        body.setPassword(pwd);
        body.setDistribution_yard(yaoQingCode);
        ApiRequest.register(map,null, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                Intent intent=new Intent();
                intent.putExtra(Constant.IParam.userName,phone);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private void getMsgCode() {
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mobile",getSStr(et_register_phone));
        map.put("rnd",getRnd());
        String sign = GetSign.getSign(map);
        map.put("sign", sign);
        showLoading();
        com.sk.sqhk.network.ApiRequest.getSMSCode(map,new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                smsCode = obj.getSMSCode();
                countDown();
            }
        });
    }

    public void countDown() {
        tv_register_getmsg.setEnabled(false);
        final long count = 30;
        Subscription subscribe = Observable.interval(1, TimeUnit.SECONDS)
                .take(31)//计时次数
                .map(integer -> count - integer)
                .compose(RxUtils.appSchedulers())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        tv_register_getmsg.setEnabled(true);
                        tv_register_getmsg.setText("获取验证码");
                    }
                    @Override
                    public void onNext(Long aLong) {
                        tv_register_getmsg.setText("剩下" + aLong + "s");
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                });
        addSubscription(subscribe);
    }

}
