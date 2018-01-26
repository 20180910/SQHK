package com.sk.sqhk.module.home.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.github.androidtools.SPUtils;
import com.library.base.BaseObj;
import com.sk.sqhk.AppXml;
import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.module.my.network.ApiRequest;
import com.sk.sqhk.module.my.network.response.DefaultBankObj;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private String bankId;
    @Override
    protected int getContentView() {
        setAppTitle("提现");
        return R.layout.act_ti_xian;
    }

    @Override
    protected void initView() {
        et_tx_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int selectionStart = et_tx_money.getSelectionStart();
                int selectionEnd = et_tx_money.getSelectionEnd();
                if (!isOnlyPointNumber(et_tx_money.getText().toString())){
                    s.delete(selectionStart - 1, selectionEnd);
                }
            }
        });
    }
    public boolean isOnlyPointNumber(String number) {//保留两位小数正则
        if(number.indexOf(".")==-1){
            return true;
        }else if(number.indexOf(".")==0){
            number="0"+number;
        }
        Pattern pattern = Pattern.compile("^\\d+\\.?\\d{0,2}$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
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
                if(!TextUtils.isEmpty(obj.getId()+"")){
                    tv_tixian_bank_name.setText(obj.getBank_name());
                    bankId=obj.getId()+"";
                }
            }
        });
    }

    @OnClick({R.id.tv_tixian_bank_name, R.id.tv_tx_commit,R.id.tv_ti_xian_zhuanchu})
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.tv_tixian_bank_name:
                STActivityForResult(BankListActivity.class,200);
                break;
            case R.id.tv_tx_commit:
                String money = getSStr(et_tx_money);
                if(TextUtils.isEmpty(bankId)){
                    showMsg("请选择到账银行卡");
                    return;
                }else if(TextUtils.isEmpty(money)){
                    showMsg("请输入金额");
                    return;
                }else if(money.length()==1&&money.indexOf(".")==0){
                    showMsg("请输入金额");
                    return;
                }
                if(money.indexOf(".")==0){
                    money="0"+money;
                }
                if(money.indexOf(".")==money.length()-1){
                    money=money.replace(".","");
                }
                double resultMoney = Double.parseDouble(money);
                if(resultMoney<=0){
                    showMsg("请输入金额");
                    return;
                }
                commit(money);
                break;
            case R.id.tv_ti_xian_zhuanchu:
                et_tx_money.setText(amount+"");
                break;
        }
    }

    private void commit(String money) {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("account_id",bankId);
        map.put("amount",money);
        map.put("sign",getSign(map));
        ApiRequest.tiXianCommit(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case 200:
                    boolean noDefault = data.getBooleanExtra(Constant.IParam.noDefault, false);
                    if(noDefault){
                        bankId=null;
                        tv_tixian_bank_name.setText(null);
                    }else{
                        bankId = data.getStringExtra(Constant.IParam.bankCardId);
                        String bankCardContent = data.getStringExtra(Constant.IParam.bankCardContent);
                        tv_tixian_bank_name.setText(bankCardContent);
                    }
                    break;
            }
        }
    }
}
