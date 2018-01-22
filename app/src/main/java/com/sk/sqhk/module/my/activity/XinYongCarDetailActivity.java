package com.sk.sqhk.module.my.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.library.base.BaseObj;
import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.module.my.network.ApiRequest;
import com.sk.sqhk.module.my.network.response.MyAllBankObj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/22.
 */

public class XinYongCarDetailActivity extends BaseActivity {
    @BindView(R.id.tv_xinyongka_detail_bank_name)
    TextView tv_xinyongka_detail_bank_name;
    @BindView(R.id.tv_xinyongka_detail_simple_name)
    TextView tv_xinyongka_detail_simple_name;
    @BindView(R.id.tv_xinyongka_detail_bank_code)
    TextView tv_xinyongka_detail_bank_code;
    @BindView(R.id.tv_xinyongka_detail_huankuanri)
    TextView tv_xinyongka_detail_huankuanri;
    @BindView(R.id.tv_xinyongka_detail_zhangdanri)
    TextView tv_xinyongka_detail_zhangdanri;
    @BindView(R.id.tv_xinyongka_detail_youxiaoqi)
    TextView tv_xinyongka_detail_youxiaoqi;
    @BindView(R.id.tv_xinyongka_detail_housanma)
    TextView tv_xinyongka_detail_housanma;
    @BindView(R.id.tv_xinyongka_detail_phone)
    TextView tv_xinyongka_detail_phone;
    @BindView(R.id.tv_xinyongka_detail_plan)
    TextView tv_xinyongka_detail_plan;
    private MyAllBankObj.CreditCardListBean cardBean;

    private String zhangDanRi, huanKuanRi;

    @Override
    protected int getContentView() {
        setAppTitle("银行卡详情");
        return R.layout.act_xinyongka_detail;
    }

    @Override
    protected void initView() {
        cardBean = (MyAllBankObj.CreditCardListBean) getIntent().getSerializableExtra(Constant.IParam.xinYongKaDetail);
        tv_xinyongka_detail_bank_name.setText(cardBean.getBankName());
        tv_xinyongka_detail_simple_name.setText(cardBean.getBankAbbr());
        tv_xinyongka_detail_bank_code.setText(cardBean.getCardNo());
        tv_xinyongka_detail_huankuanri.setText(cardBean.getPaymentDate() + "日");
        tv_xinyongka_detail_zhangdanri.setText(cardBean.getBillDate() + "日");
        tv_xinyongka_detail_youxiaoqi.setText(cardBean.getExpireMonth() + "/" + cardBean.getExpireYear());
        tv_xinyongka_detail_housanma.setText(cardBean.getCardCcv());
        tv_xinyongka_detail_phone.setText(cardBean.getPhone());
        tv_xinyongka_detail_plan.setText(cardBean.getPlanStatus() == 0 ? "否" : "是");
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.tv_xinyongka_detail_commit, R.id.tv_xinyongka_detail_huankuanri, R.id.tv_xinyongka_detail_zhangdanri})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.tv_xinyongka_detail_huankuanri:
                selectDate(1);
                break;
            case R.id.tv_xinyongka_detail_zhangdanri:
                selectDate(2);
                break;
            case R.id.tv_xinyongka_detail_commit:
                commitDetail();
                break;
        }
    }

    private void selectDate(int flag) {
        List<String> list=new ArrayList<>();
        OptionsPickerView  pickerView=new OptionsPickerView.Builder(mContext, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                Log.i(TAG + "===", options1+"===" + options2+"===" + options3);
                switch (flag) {
                    case 1://还款日
                        huanKuanRi=list.get(options1);
                        tv_xinyongka_detail_huankuanri.setText(list.get(options1)+"日");
                        break;
                    case 2://账单日
                        zhangDanRi=list.get(options1);
                        tv_xinyongka_detail_zhangdanri.setText(list.get(options1)+"日");
                        break;
                }
            }
        }).build();
        for (int i = 1; i <= 31; i++) {
            list.add(i+"");
        }
        pickerView.setPicker(list);
        pickerView.show();

        /*Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_MONTH, 1);
        TimePickerView pvTime = new TimePickerView.Builder(mContext, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                String day = calendar.get(Calendar.DATE) + "";
                Log.i(TAG + "===", "===" + day);
                switch (flag) {
                    case 1://还款日
                        tv_xinyongka_detail_huankuanri.setText(day);
                        break;
                    case 2://账单日
                        tv_xinyongka_detail_zhangdanri.setText(day);
                        break;
                }
            }
        }).setRangDate(Calendar.getInstance(), instance).setType(new boolean[]{true, true, true, false, false, false}).build();
        pvTime.show();*/
    }

    private void commitDetail() {
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("card_id", cardBean.getId() + "");
        map.put("billDate", TextUtils.isEmpty(zhangDanRi) ? cardBean.getBillDate() + "" : zhangDanRi);
        map.put("payDate", TextUtils.isEmpty(huanKuanRi) ? cardBean.getPaymentDate() + "" : huanKuanRi);
        map.put("sign", getSign(map));
        ApiRequest.updateXinYongCard(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                setResult(RESULT_OK);
                finish();
            }
        });

    }
}
