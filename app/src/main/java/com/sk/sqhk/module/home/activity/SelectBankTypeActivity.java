package com.sk.sqhk.module.home.activity;

import android.content.Intent;
import android.view.View;

import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/20.
 */

public class SelectBankTypeActivity extends BaseActivity {
    @Override
    protected int getContentView() {
        setAppTitle("添加银行卡");
        return R.layout.act_select_bank_type;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.ll_add_bank_chuxu,R.id.ll_add_bank_xinyong})
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.ll_add_bank_chuxu:
                STActivityForResult(AddChuXuKaActivity.class,100);
            break;
            case R.id.ll_add_bank_xinyong:
                STActivity(AddChuXuKaActivity.class);
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case 100:
                    finish();
                break;
            }
        }
    }
}
