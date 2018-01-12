package com.sk.sqhk.module.my.activity;

import android.text.TextUtils;
import android.view.View;

import com.github.androidtools.SPUtils;
import com.github.customview.MyEditText;
import com.library.base.BaseObj;
import com.sk.sqhk.base.MyCallBack;
import com.library.base.tools.ZhengZeUtils;
import com.sk.sqhk.AppXml;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.module.my.network.ApiRequest;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/25.
 */

public class UpdatePWDActivity extends BaseActivity {
    @BindView(R.id.et_update_pwd_old)
    MyEditText et_update_pwd_old;
    @BindView(R.id.et_update_pwd_new)
    MyEditText et_update_pwd_new;
    @BindView(R.id.et_update_pwd_re)
    MyEditText et_update_pwd_re;

    @Override
    protected int getContentView() {
        setAppTitle("修改登录密码");
        return R.layout.act_update_pwd;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.tv_update_pwd_commit)
    public void onViewClick(View v) {
        switch (v.getId()){
            case R.id.tv_update_pwd_commit:
                String oldPwd=getSStr(et_update_pwd_old);
                String newPwd=getSStr(et_update_pwd_new);
                String rePwd=getSStr(et_update_pwd_re);
                if(TextUtils.isEmpty(oldPwd)){
                    showMsg("请输入当前密码");
                    return;
                }else if(!ZhengZeUtils.isShuZiAndZiMu(newPwd)||newPwd.length()>12||newPwd.length()<6){
                    showMsg("请输入6-12位数字加字母的密码");
                    return;
                }else if(!TextUtils.equals(newPwd,rePwd)){
                    showMsg("两次密码不一样");
                    return;
                }
                updatePwd(oldPwd,newPwd);
            break;
        }
    }

    private void updatePwd(String oldPwd, String newPwd) {
        showLoading();
        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("oldPassword",oldPwd);
        map.put("newPassword",newPwd);
        map.put("sign",getSign(map));
        ApiRequest.updatePwd(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                SPUtils.setPrefBoolean(mContext, AppXml.needLogin,true);
                showMsg(obj.getMsg());
                finish();
            }
        });

    }
}
