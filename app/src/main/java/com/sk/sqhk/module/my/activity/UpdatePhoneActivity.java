package com.sk.sqhk.module.my.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.androidtools.SPUtils;
import com.github.baseclass.rx.RxUtils;
import com.github.customview.MyEditText;
import com.github.customview.MyTextView;
import com.library.base.BaseObj;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.AppXml;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.module.my.network.ApiRequest;
import com.sk.sqhk.network.NetApiRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscription;

/**
 * Created by Administrator on 2017/12/25.
 */

public class UpdatePhoneActivity extends BaseActivity {
    @BindView(R.id.tv_update_phone_tell)
    TextView tv_update_phone_tell;
    @BindView(R.id.ll_update_phone_tell)
    LinearLayout ll_update_phone_tell;
    @BindView(R.id.et_update_phone_new_tell)
    MyEditText et_update_phone_new_tell;
    @BindView(R.id.ll_update_phone_new_tell)
    LinearLayout ll_update_phone_new_tell;
    @BindView(R.id.et_update_phone_code)
    MyEditText et_update_phone_code;
    @BindView(R.id.tv_update_phone_getcode)
    MyTextView tv_update_phone_getcode;
    @BindView(R.id.tv_update_phone_next)
    MyTextView tv_update_phone_next;

    private String smsCode;
    private boolean isFirst=true;
    private Subscription subscribe;

    @Override
    protected int getContentView() {
        setAppTitle("修改手机号");
        return R.layout.act_update_phone;
    }

    @Override
    protected void initView() {
        tv_update_phone_tell.setText(SPUtils.getString(mContext,AppXml.mobile,null));

        ll_update_phone_new_tell.setVisibility(View.GONE);

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_update_phone_getcode, R.id.tv_update_phone_next})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_update_phone_getcode:
                if(isFirst){
                    getCode(SPUtils.getString(mContext, AppXml.mobile,null));
                }else{
                    String newPhone = getSStr(et_update_phone_new_tell);
                    if(TextUtils.isEmpty(newPhone)){
                        showMsg("请输入手机号码");
                        return;
                    }else{
                        getCode(newPhone);
                    }
                }
                break;
            case R.id.tv_update_phone_next:
                if(TextUtils.isEmpty(getSStr(et_update_phone_code))||!TextUtils.equals(getSStr(et_update_phone_code),smsCode)){
                    showMsg("请输入正确验证码");
                    return;
                }
                if(isFirst){
                    subscribe.unsubscribe();
                    smsCode=null;
                    isFirst=false;
                    tv_update_phone_getcode.setEnabled(true);
                    tv_update_phone_getcode.setText("获取验证码");
                    et_update_phone_code.setText(null);

                    tv_update_phone_next.setText("完成");
                    ll_update_phone_tell.setVisibility(View.GONE);
                    ll_update_phone_new_tell.setVisibility(View.VISIBLE);
                    et_update_phone_new_tell.requestFocus();
                }else{
                    String phone = getSStr(et_update_phone_new_tell);
                    if(TextUtils.isEmpty(phone)){
                        showMsg("请输入手机号码");
                        return;
                    }
                    updatePhone(phone);
                }
                break;
        }
    }

    private void updatePhone(String phone) {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("userid",getUserId());
        map.put("phone",phone);
        map.put("sign",getSign(map));
        ApiRequest.updatePhone(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                SPUtils.setPrefString(mContext,AppXml.mobile,phone);
                finish();
            }
        });
    }

    private void getCode(String phone) {
        showLoading();
        Map<String,String> map=new HashMap<String,String>();
        map.put("mobile",phone);
        map.put("type","3");
        map.put("sign",getSign(map));
        NetApiRequest.getSMSCode(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                smsCode = obj.getSMSCode();
                countDown(tv_update_phone_getcode);
            }
        });
    }

    public void countDown(TextView textView) {
        textView.setEnabled(false);
        final long count = 30;
        //计时次数
        subscribe = Observable.interval(1, TimeUnit.SECONDS)
                .take(31)//计时次数
                .map(integer -> count - integer)
                .compose(RxUtils.appSchedulers())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        textView.setEnabled(true);
                        textView.setText("获取验证码");
                    }
                    @Override
                    public void onNext(Long aLong) {
                        textView.setText("剩下" + aLong + "s");
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                });
        addSubscription(subscribe);
    }
}
