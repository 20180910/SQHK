package com.sk.sqhk.module.home.activity;

import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.github.customview.MyEditText;
import com.library.base.BaseObj;
import com.sk.sqhk.BuildConfig;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.module.home.network.ApiRequest;
import com.sk.sqhk.module.home.network.response.BankObj;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/22.
 */

public class AddXinYongKaActivity extends BaseActivity {
    @BindView(R.id.et_add_xyk_bankcode)
    MyEditText et_add_xyk_bankcode;
    @BindView(R.id.tv_add_xyk_bank)
    TextView tv_add_xyk_bank;
    @BindView(R.id.tv_add_xyk_youxiaoqi)
    TextView tv_add_xyk_youxiaoqi;
    @BindView(R.id.et_add_xyk_housanma)
    MyEditText et_add_xyk_housanma;
    @BindView(R.id.et_add_xyk_zhangdanri)
    MyEditText et_add_xyk_zhangdanri;
    @BindView(R.id.et_add_xyk_huankuanri)
    MyEditText et_add_xyk_huankuanri;
    @BindView(R.id.et_add_xyk_phone)
    MyEditText et_add_xyk_phone;
    @BindView(R.id.et_add_xyk_msgcode)
    MyEditText et_add_xyk_msgcode;
    @BindView(R.id.tv_add_xyk_getmsg)
    TextView tv_add_xyk_getmsg;


    private BottomSheetDialog bankDialog;
    private String bankId;
    private String smsCode;
    private String cardId;

    @Override
    protected int getContentView() {
        setAppTitle("添加信用卡");
        return R.layout.act_add_xinyongka;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_add_xyk_bankcode, R.id.tv_add_xyk_getmsg, R.id.tv_add_xyk_commit, R.id.tv_add_xyk_bank,R.id.tv_add_xyk_youxiaoqi})
    public void onViewClick(View view) {
        String cardCode = getSStr(et_add_xyk_bankcode);
        String youXiaoQi = getSStr(tv_add_xyk_youxiaoqi);
        String houSanMa = getSStr(et_add_xyk_housanma);
        String zhangDanRi = getSStr(et_add_xyk_zhangdanri);
        String huanKuanRi = getSStr(et_add_xyk_huankuanri);
        String phone = getSStr(et_add_xyk_phone);
        switch (view.getId()) {
            case R.id.tv_add_xyk_bankcode:
                if (BuildConfig.DEBUG) {
                    et_add_xyk_bankcode.setText("");
                }
                break;
            case R.id.tv_add_xyk_youxiaoqi:
                selectDate();
                break;
            case R.id.tv_add_xyk_bank:
                selectBank();
                break;
            case R.id.tv_add_xyk_getmsg:

                if (TextUtils.isEmpty(cardCode)) {
                    showMsg("请输入信用卡号");
                    return;
                } else if (TextUtils.isEmpty(bankId)) {
                    showMsg("请选择银行");
                    return;
                } else if (TextUtils.isEmpty(youXiaoQi)) {
                    showMsg("请填写有效期");
                    return;
                } else if (TextUtils.isEmpty(houSanMa)) {
                    showMsg("请填写后三码");
                    return;
                } else if (TextUtils.isEmpty(zhangDanRi)) {
                    showMsg("请填写账单日");
                    return;
                } else if (TextUtils.isEmpty(huanKuanRi)) {
                    showMsg("请填写还款日");
                    return;
                } else if (TextUtils.isEmpty(phone)) {
                    showMsg("请填写手机号");
                    return;
                }
                getMsgCode(cardCode,
                        youXiaoQi,
                        houSanMa,
                        zhangDanRi,
                        huanKuanRi,
                        phone);
                break;
            case R.id.tv_add_xyk_commit:


                if (TextUtils.isEmpty(cardCode)) {
                    showMsg("请输入信用卡号");
                    return;
                } else if (TextUtils.isEmpty(bankId)) {
                    showMsg("请选择银行");
                    return;
                } else if (TextUtils.isEmpty(youXiaoQi)) {
                    showMsg("请填写有效期");
                    return;
                } else if (TextUtils.isEmpty(houSanMa)) {
                    showMsg("请填写后三码");
                    return;
                } else if (TextUtils.isEmpty(zhangDanRi)) {
                    showMsg("请填写账单日");
                    return;
                } else if (TextUtils.isEmpty(huanKuanRi)) {
                    showMsg("请填写还款日");
                    return;
                } else if (TextUtils.isEmpty(phone)) {
                    showMsg("请填写手机号");
                    return;
                }else if(TextUtils.isEmpty(getSStr(et_add_xyk_msgcode))){
                    showMsg("请输入验证码");
                    return;
                }
                addXinYongKa(cardCode,
                        youXiaoQi,
                        houSanMa,
                        zhangDanRi,
                        huanKuanRi,
                        phone);
                break;
        }
    }

    private void selectDate() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.YEAR,30);
        TimePickerView pvTime = new TimePickerView.Builder(mContext, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                String year=calendar.get(Calendar.YEAR)+"";
                String month;
                int monthParam = calendar.get(Calendar.MONTH) + 1;
                if(monthParam<10){
                    month="0"+monthParam;
                }else{
                    month=monthParam+"";
                }
                tv_add_xyk_youxiaoqi.setText(month+"/"+year.substring(2));
                Log.i(TAG+"===",year+"==="+month);
            }
        }).setRangDate(Calendar.getInstance(),instance).setType(new boolean[]{true, true, false, false, false, false}).build();
        pvTime.show();
    }

    private void addXinYongKa(String cardCode, String youXiaoQi, String houSanMa, String zhangDanRi, String huanKuanRi, String phone) {
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", getUserId());
        map.put("card_id", cardId);
        map.put("bankid", bankId);
        map.put("card_no", cardCode);
        map.put("expire", youXiaoQi);
        map.put("ccv", houSanMa);
        map.put("billDate", zhangDanRi);
        map.put("payDate", huanKuanRi);
        map.put("phone", phone);
        map.put("code", getSStr(et_add_xyk_msgcode));
        map.put("sign", getSign(map));
        ApiRequest.addXinYongKa(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                setResult(RESULT_OK);
                finish();
            }
        });

    }

    private void getMsgCode(String cardCode, String youXiaoQi, String houSanMa, String zhangDanRi, String huanKuanRi, String phone) {
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", getUserId());
        map.put("bankid", bankId);
        map.put("card_no", cardCode);
        map.put("expire", youXiaoQi);
        map.put("ccv", houSanMa);
        map.put("billDate", zhangDanRi);
        map.put("payDate", huanKuanRi);
        map.put("phone", phone);
        map.put("sign", getSign(map));
        ApiRequest.getMsgCodeForAddXinYongKa(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                smsCode = obj.getSMSCode();
                cardId=obj.getCard_id();
                countDown(tv_add_xyk_getmsg);
            }
        });

    }

    private void selectBank() {
        if (bankDialog == null) {
            getBankList();
        } else {
            bankDialog.show();
        }
    }

    private void getBankList() {
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("rnd", getRnd());
        map.put("sign", getSign(map));
        ApiRequest.getBankListForAddCard(map, new MyCallBack<List<BankObj>>(mContext) {
            @Override
            public void onSuccess(List<BankObj> list) {
                bankDialog = new BottomSheetDialog(mContext);
                bankDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                View dialogView = getLayoutInflater().inflate(R.layout.popu_bank_list, null);
                RecyclerView rv_bank_list = dialogView.findViewById(R.id.rv_bank_list);
                dialogView.findViewById(R.id.tv_addcard_cancel).setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        bankDialog.dismiss();
                    }
                });
                BaseRecyclerAdapter adapter = new BaseRecyclerAdapter<BankObj>(mContext, R.layout.item_bank_for_addcard) {
                    @Override
                    public void bindData(RecyclerViewHolder holder, int i, BankObj bean) {
                        holder.setText(R.id.tv_addcard_bank_name, bean.getBank_name());
                        holder.itemView.setOnClickListener(new MyOnClickListener() {
                            @Override
                            protected void onNoDoubleClick(View v) {
                                bankId = bean.getBank_id() + "";
                                tv_add_xyk_bank.setText(bean.getBank_name());
                                bankDialog.dismiss();
                            }
                        });
                    }
                };
                adapter.setList(list);
                rv_bank_list.setAdapter(adapter);

                bankDialog.setContentView(dialogView);
                bankDialog.show();
            }
        });

    }
}
