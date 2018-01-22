package com.sk.sqhk.module.home.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.github.customview.MyLinearLayout;
import com.library.base.view.MyRecyclerView;
import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.module.my.activity.XinYongCarDetailActivity;
import com.sk.sqhk.module.my.network.ApiRequest;
import com.sk.sqhk.module.my.network.response.MyAllBankObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/20.
 */

public class SelectBankTypeActivity extends BaseActivity {


    @BindView(R.id.rv_my_xinyongka)
    MyRecyclerView rv_my_xinyongka;

    @BindView(R.id.rv_my_yinhangka)
    MyRecyclerView rv_my_yinhangka;

    @BindView(R.id.tv_select_bank_type_title)
    TextView tv_select_bank_type_title;

    @BindView(R.id.tv_select_bank_type_xytitle)
    TextView tv_select_bank_type_xytitle;

    @BindView(R.id.ll_add_bank_chuxu)
    LinearLayout ll_add_bank_chuxu;

    @BindView(R.id.ll_add_bank_xinyong)
    LinearLayout ll_add_bank_xinyong;


    LoadMoreAdapter xinYongAdapter,chuXuAdapter;
    private boolean isJiSuHuanKuan;

    @Override
    protected int getContentView() {
        setAppTitle("添加银行卡");
        return R.layout.act_select_bank_type;
    }

    @Override
    protected void initView() {
        isJiSuHuanKuan = getIntent().getBooleanExtra(Constant.IParam.isJiSuHuanKuan, false);
        rv_my_xinyongka.setVisibility(View.GONE);
        rv_my_yinhangka.setVisibility(View.GONE);
        if(isJiSuHuanKuan){
            xinYongAdapter=new LoadMoreAdapter<MyAllBankObj.CreditCardListBean>(mContext,R.layout.item_my_xinyong_card,pageSize) {
                @Override
                public void bindData(LoadMoreViewHolder holder, int position, MyAllBankObj.CreditCardListBean bean) {
                    holder.setText(R.id.tv_xinyongka_bank_name,bean.getBankName()+"\t\t")
                            .setText(R.id.tv_xinyongka_plan,bean.getPlanStatus()==0?"否":"是")
                            .setText(R.id.tv_xinyongka_code,bean.getCardNo())
                            .setText(R.id.tv_xinyongka_zhangdan,bean.getBillDate()+"日")
                            .setText(R.id.tv_xinyongka_huankuan,bean.getPaymentDate()+"日")
                            .setText(R.id.tv_xinyongka_bank_simple_name,bean.getBankAbbr());
                    MyLinearLayout ll_xinyong_ka = (MyLinearLayout) holder.getView(R.id.ll_xinyong_ka);
                    ll_xinyong_ka.setSolidColor(Color.parseColor(bean.getBankColor()));
                    ll_xinyong_ka.complete();
                    ll_xinyong_ka.setOnClickListener(new MyOnClickListener() {
                        @Override
                        protected void onNoDoubleClick(View v) {
                            Intent intent=new Intent();
                            intent.putExtra(Constant.IParam.xinYongKaDetail,bean);
                            STActivity(intent,XinYongCarDetailActivity.class);
                        }
                    });


                }
            };
            rv_my_xinyongka.setAdapter(xinYongAdapter);


            chuXuAdapter=new LoadMoreAdapter<MyAllBankObj.CashCardListBean>(mContext,R.layout.item_my_chuyu_card,pageSize) {
                @Override
                public void bindData(LoadMoreViewHolder holder, int position, MyAllBankObj.CashCardListBean bean) {
                    ImageView imageView = holder.getImageView(R.id.iv_chuxuka_ka);
                    Glide.with(mContext).load(bean.getBank_image()).asBitmap().encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG,90)).error(R.color.c_press).into(imageView);
//                MyLinearLayout ll_chuxuka_ka = (MyLinearLayout) holder.getView(R.id.ll_chuxuka_ka);
//                ll_chuxuka_ka.setSolidColor(Color.parseColor(bean.get()));
//                ll_chuxuka_ka.complete();


                    holder.setText(R.id.tv_chuxuka_bank_name,bean.getBank_name())
                            .setText(R.id.tv_chuxuka_type,bean.getCard_type())
                            .setText(R.id.tv_chuxuka_code,"******"+bean.getBank_card());

                    holder.getView(R.id.ll_chuxuka_ka).setOnClickListener(new MyOnClickListener() {
                        @Override
                        protected void onNoDoubleClick(View v) {
                            showMsg("请选择信用卡");
                        }
                    });
                }
            };
            rv_my_yinhangka.setAdapter(chuXuAdapter);
        }
    }

    @Override
    protected void initData() {
        if(isJiSuHuanKuan){
            showProgress();
            getData(1,false);
        }
    }
    @Override
    protected void onMyReStart() {
        super.onMyReStart();
        if(isJiSuHuanKuan){
            getData(1,false);
        }
    }
    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("sign",getSign(map));
        ApiRequest.getXinYongCardList(map, new MyCallBack<MyAllBankObj>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(MyAllBankObj obj) {
                if(isJiSuHuanKuan){
                    if(notEmpty(obj.getCredit_card_list())){//有信用卡
                        ll_add_bank_xinyong.setVisibility(View.GONE);
                        rv_my_xinyongka.setVisibility(View.VISIBLE);
                        tv_select_bank_type_xytitle.setText("代还信用卡(最多绑定5张)");
                    }else{
                        rv_my_xinyongka.setVisibility(View.GONE);
                        ll_add_bank_xinyong.setVisibility(View.VISIBLE);
                        tv_select_bank_type_xytitle.setText("代还信用卡(最多绑定5张)");
                    }
                    if(notEmpty(obj.getCash_card_list())){//有储蓄卡
                        ll_add_bank_chuxu.setVisibility(View.GONE);
                        rv_my_yinhangka.setVisibility(View.VISIBLE);
                        tv_select_bank_type_title.setText("储蓄卡");
                    }else{
                        ll_add_bank_chuxu.setVisibility(View.VISIBLE);
                        rv_my_yinhangka.setVisibility(View.GONE);
                        tv_select_bank_type_title.setText("添加储蓄卡");
                    }
                }
                xinYongAdapter.setList(obj.getCredit_card_list(),true);
                chuXuAdapter.setList(obj.getCash_card_list(),true);
            }
        });

    }

    @OnClick({R.id.ll_add_bank_chuxu,R.id.ll_add_bank_xinyong})
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.ll_add_bank_chuxu:
                STActivityForResult(AddChuXuKaActivity.class,100);
            break;
            case R.id.ll_add_bank_xinyong:
                STActivityForResult(AddXinYongKaActivity.class,100);
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
