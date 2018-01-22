package com.sk.sqhk.module.my.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.github.customview.MyLinearLayout;
import com.library.base.BaseObj;
import com.library.base.view.MyRecyclerView;
import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.module.home.activity.SelectBankTypeActivity;
import com.sk.sqhk.module.my.network.ApiRequest;
import com.sk.sqhk.module.my.network.response.MyAllBankObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/25.
 */

public class MyBankListActivity extends BaseActivity {

    @BindView(R.id.rv_my_xinyongka)
    MyRecyclerView rv_my_xinyongka;

    @BindView(R.id.rv_my_yinhangka)
    MyRecyclerView rv_my_yinhangka;

    LoadMoreAdapter xinYongAdapter,chuXuAdapter;

    @Override
    protected int getContentView() {
        setAppTitle("我的银行卡");
        setAppRightImg(R.drawable.add_bank);
        return R.layout.act_my_bank_list;
    }

    @Override
    protected void initView() {
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

                holder.getView(R.id.tv_xinyong_ka_delete).setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        deleteXinYongCard(bean.getId()+"");
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
                holder.getView(R.id.tv_chuxuka_ka_delete).setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        deleteCard(bean.getId()+"");
                    }
                });

                holder.setText(R.id.tv_chuxuka_bank_name,bean.getBank_name())
                .setText(R.id.tv_chuxuka_type,bean.getCard_type())
                .setText(R.id.tv_chuxuka_code,"******"+bean.getBank_card());
            }
        };
        rv_my_yinhangka.setAdapter(chuXuAdapter);

    }

    private void deleteXinYongCard(String cardId) {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("card_id",cardId);
        map.put("sign",getSign(map));
        ApiRequest.deleteXinYongCard(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                getData(1,false);
            }
        });

    }

    private void deleteCard(String cardId) {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("account_id",cardId);
        map.put("sign",getSign(map));
        ApiRequest.deleteBankCard(map, new MyCallBack<BaseObj>(mContext,true) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                getData(1,false);
            }
        });

    }

    @Override
    protected void initData() {
        showProgress();
        getData(1,false);
    }

    @Override
    protected void onMyReStart() {
        super.onMyReStart();
        getData(1,false);
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
                xinYongAdapter.setList(obj.getCredit_card_list(),true);
                chuXuAdapter.setList(obj.getCash_card_list(),true);
            }
        });

    }

    @OnClick({R.id.app_right_iv})
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.app_right_iv:
                STActivity(SelectBankTypeActivity.class);
            break;
        }
    }
}
