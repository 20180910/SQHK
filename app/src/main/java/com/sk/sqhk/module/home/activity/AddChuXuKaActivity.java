package com.sk.sqhk.module.home.activity;

import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

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
import com.sk.sqhk.module.home.network.request.AddChuXunBody;
import com.sk.sqhk.module.home.network.response.BankObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/20.
 */

public class AddChuXuKaActivity extends BaseActivity {
    @BindView(R.id.et_add_bank_account)
    MyEditText et_add_bank_account;
    @BindView(R.id.et_add_bank_name)
    MyEditText et_add_bank_name;
    @BindView(R.id.et_add_bank_zhihang)
    MyEditText et_add_bank_zhihang;
    @BindView(R.id.tv_add_bank_select)
    TextView tv_add_bank_select;
    private BottomSheetDialog bankDialog;
    private String bankId;

    @Override
    protected int getContentView() {
        setAppTitle("储蓄卡");
        return R.layout.act_add_chuxuka;
    }

    @Override
    protected void initView() {
        if(BuildConfig.DEBUG){
            findViewById(R.id.tv_add_bank_zhanghao).setOnClickListener(new MyOnClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    et_add_bank_account.setText("6217880800008464499");
                }
            });
        }
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_add_bank_select, R.id.tv_add_bank})
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_bank_select:
                selectBank();
                break;
            case R.id.tv_add_bank:

                String account = getSStr(et_add_bank_account);
                String name =  getSStr(et_add_bank_name);
                String zhihang =  getSStr(et_add_bank_zhihang);
                if(TextUtils.isEmpty(account)){
                    showMsg("请输入银行卡号");
                    return;
                }else if(TextUtils.isEmpty(bankId)){
                    showMsg("请选择发卡银行");
                    return;
                }else if(TextUtils.isEmpty(name)){
                    showMsg("请输入持卡人姓名");
                    return;
                }else if(TextUtils.isEmpty(zhihang)){
                    showMsg("请输入开户行");
                    return;
                }
                saveBank(account,bankId,name,zhihang);
                break;
        }
    }

    private void selectBank() {
        if(bankDialog==null){
            getBankList();
        }else{
            bankDialog.show();
        }
    }

    private void getBankList() {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("rnd",getRnd());
        map.put("sign",getSign(map));
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
                BaseRecyclerAdapter adapter=new BaseRecyclerAdapter<BankObj>(mContext,R.layout.item_bank_for_addcard) {
                    @Override
                    public void bindData(RecyclerViewHolder holder, int i, BankObj bean) {
                        holder.setText(R.id.tv_addcard_bank_name,bean.getBank_name());
                        holder.itemView.setOnClickListener(new MyOnClickListener() {
                            @Override
                            protected void onNoDoubleClick(View v) {
                                bankId=bean.getBank_id()+"";
                                tv_add_bank_select.setText(bean.getBank_name());
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

    private void saveBank(String account, String bankId, String name,String zhihang) {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("sign",getSign(map));
        AddChuXunBody body=new AddChuXunBody();
        body.setBank_card_num(account);
        body.setRealname(name);
        body.setCard_type(1);
        body.setBank_id(bankId);
        body.setOpening_bank(zhihang);
        ApiRequest.addChuXu(map,body, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                setResult(RESULT_OK);
                finish();
            }
        });

    }
}
