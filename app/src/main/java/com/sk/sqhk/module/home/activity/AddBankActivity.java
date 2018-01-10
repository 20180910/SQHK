package com.sk.sqhk.module.home.activity;

import android.text.TextUtils;
import android.view.View;

import com.github.customview.MyEditText;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/20.
 */

public class AddBankActivity extends BaseActivity {
    @BindView(R.id.et_add_bank_account)
    MyEditText et_add_bank_account;
    @BindView(R.id.et_add_bank_name)
    MyEditText et_add_bank_name;
    String selectBank;
    @Override
    protected int getContentView() {
        setAppTitle("信用卡");
        return R.layout.act_add_bank;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_add_bank_select, R.id.tv_add_bank})
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_bank_select:
                STActivity(BankListActivity.class);
                break;
            case R.id.tv_add_bank:

                String account = getSStr(et_add_bank_account);
                String name =  getSStr(et_add_bank_name);
                if(TextUtils.isEmpty(account)){
                    showMsg("请输入银行卡号");
                    return;
                }else if(TextUtils.isEmpty(selectBank)){
                    showMsg("请选择发卡银行");
                    return;
                }else if(TextUtils.isEmpty(name)){
                    showMsg("请输入持卡人姓名");
                    return;
                }
                saveBank();
                break;
        }
    }

    private void saveBank() {
        showLoading();
        Map<String,String> map=new HashMap<String,String>();
        /*ApiRequest.method(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {

            }
        });*/
    }
}
