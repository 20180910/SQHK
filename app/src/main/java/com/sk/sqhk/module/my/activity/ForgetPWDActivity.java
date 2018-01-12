package com.sk.sqhk.module.my.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.github.customview.MyEditText;
import com.library.base.BaseObj;
import com.sk.sqhk.base.MyCallBack;
import com.library.base.tools.ZhengZeUtils;
import com.sk.sqhk.GetSign;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.module.my.network.ApiRequest;
import com.sk.sqhk.network.NetApiRequest;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/25.
 */

public class ForgetPWDActivity extends BaseActivity {
    @BindView(R.id.et_forget_phone)
    MyEditText et_forget_phone;
    @BindView(R.id.et_forget_code)
    MyEditText et_forget_code;
    @BindView(R.id.et_forget_pwd)
    MyEditText et_forget_pwd;
    @BindView(R.id.tv_forget_getcode)
    TextView tv_forget_getcode;
    private String smsCode;

    @Override
    protected int getContentView() {
        setAppTitle("忘记密码");
        return R.layout.act_forget_pwd;
    }
    @Override
    protected void initView() {

    }
    @Override
    protected void initData() {

    }
    @OnClick({R.id.tv_forget_getcode, R.id.tv_forget_commit })
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_forget_getcode:
                String phone=getSStr(et_forget_phone);
                if(TextUtils.isEmpty(phone)||!ZhengZeUtils.isMobile(phone)){
                    showMsg("请输入正确手机号");
                    return;
                }
                getMsgCode(phone);
                break;
            case R.id.tv_forget_commit:
                String tell = getSStr(et_forget_phone);
                String pwd = getSStr(et_forget_pwd);
                if(TextUtils.isEmpty(tell)||!ZhengZeUtils.isMobile(tell)){
                    showMsg("请输入正确手机号");
                    return;
                }else if(TextUtils.isEmpty(pwd)){
                    showMsg("请输入密码");
                    return;
                }else if(!ZhengZeUtils.isShuZiAndZiMu(pwd)||pwd.length()>12||pwd.length()<6){
                    showMsg("请输入6-12位数字加字母的密码");
                    return;
                }
                updatePWD(tell,pwd);
                break;
        }
    }

    private void updatePWD(String tell, String pwd) {
        Map<String,String>map=new HashMap<String,String>();
        map.put("username",tell);
        map.put("newPassword",pwd);
        map.put("sign",getSign(map));
        ApiRequest.forgetPWD(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                finish();
            }
        });

    }

    private void getMsgCode(String phone) {
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mobile",phone);
        map.put("type","1");
        map.put("sign", GetSign.getSign(map));
        NetApiRequest.getSMSCode(map,new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                smsCode = obj.getSMSCode();
                countDown(tv_forget_getcode);
            }
        });
    }
}
