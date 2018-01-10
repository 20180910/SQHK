package com.sk.sqhk.module.home.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.github.androidtools.SPUtils;
import com.library.base.MyCallBack;
import com.sk.sqhk.AppXml;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.module.my.network.ApiRequest;
import com.sk.sqhk.module.my.network.response.DefaultBankObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/20.
 */

public class TiXianActivity extends BaseActivity {
    @BindView(R.id.tv_tixian_bank_name)
    TextView tv_tixian_bank_name;
    @BindView(R.id.et_tx_money)
    EditText et_tx_money;
    @BindView(R.id.tv_ti_xian_max)
    TextView tv_ti_xian_max;
    private float amount;

    @Override
    protected int getContentView() {
        setAppTitle("提现");
        return R.layout.act_ti_xian;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        showProgress();
        getData(1,false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        amount = SPUtils.getFloat(mContext, AppXml.amount, 0);
        tv_ti_xian_max.setText(amount +"元");
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);

        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("sign",getSign(map));
        ApiRequest.getDefaultBank(map, new MyCallBack<DefaultBankObj>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(DefaultBankObj obj) {
                if(!TextUtils.isEmpty(obj.getBank_name())){
                    tv_tixian_bank_name.setText(obj.getBank_name());
                }
            }
        });

    }

    @OnClick({R.id.tv_tixian_bank_name, R.id.tv_tx_commit,R.id.tv_ti_xian_zhuanchu})
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.tv_tixian_bank_name:
                STActivity(AddBankActivity.class);
                break;
            case R.id.tv_tx_commit:
                break;
            case R.id.tv_ti_xian_zhuanchu:
                et_tx_money.setText(amount+"");
                break;
        }
    }

}
