package com.sk.sqhk.module.home.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.library.base.BaseObj;
import com.library.base.view.MyRecyclerView;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.module.my.network.ApiRequest;
import com.sk.sqhk.module.my.network.response.TiXianAccountListObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/20.
 */

public class BankListActivity extends BaseActivity {
    @BindView(R.id.rv_bank)
    MyRecyclerView rv_bank;

    LoadMoreAdapter adapter;
    private boolean noDefault;

    @Override
    protected int getContentView() {
        setAppTitle("选择银行卡");
        setAppRightImg(R.drawable.add_bank);
        return R.layout.act_bank_list;
    }

    @Override
    protected void onMyReStart() {
        super.onMyReStart();
        getData(1,false);
    }

    @Override
    protected void initView() {
        adapter=new LoadMoreAdapter<TiXianAccountListObj>(mContext,R.layout.item_bank,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, TiXianAccountListObj bean) {
                SwipeMenuLayout sml_account_bank= (SwipeMenuLayout) holder.getView(R.id.sml_account_bank);
                sml_account_bank.setSwipeEnable(false);
                holder.getView(R.id.ll_account_bank).setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        setDefaultBank(bean);
                    }
                });
                holder.setText(R.id.tv_account_bank_name,bean.getBank_name())
                        .setText(R.id.tv_account_bank_code,bean.getBank_card());
                ImageView imageView = holder.getImageView(R.id.iv_account_bank);
                Glide.with(mContext).load(bean.getBank_image()).asBitmap().encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG,90)).error(R.color.c_press).into(imageView);
                holder.getView(R.id.tv_account_bank_delete).setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        if(bean.getIs_default()==1){
                            noDefault=true;
                        }
                        deleteAccount(bean.getId()+"");
                    }
                });
            }
        };
        rv_bank.setAdapter(adapter);
    }
    private void deleteAccount(String accountId){
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("account_id",accountId);
        map.put("sign",getSign(map));
        ApiRequest.delBankAccount(map, new MyCallBack<BaseObj>(mContext,true) {
            @Override
            public void onSuccess(BaseObj obj) {
                getData(1,false);
            }
        });

    }
    private void setDefaultBank(TiXianAccountListObj bean) {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("account_id",bean.getId()+"");
        map.put("sign",getSign(map));
        ApiRequest.setDefaultBank(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                noDefault=false;
                Intent intent=new Intent();
                intent.putExtra(Constant.IParam.noDefault,noDefault);
                intent.putExtra(Constant.IParam.bankCardId,bean.getId()+"");
                intent.putExtra(Constant.IParam.bankCardContent,bean.getBank_name()+"  "+bean.getBank_card());
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }

    @Override
    protected void initData() {
        showProgress();
        getData(1,false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("sign",getSign(map));
        ApiRequest.getTiXianAccountList(map, new MyCallBack<List<TiXianAccountListObj>>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(List<TiXianAccountListObj> list) {
                if(isLoad){
                    pageNum++;
                    adapter.addList(list,true);
                }else{
                    pageNum=2;
                    adapter.setList(list,true);
                }
            }
        });

    }

    @OnClick({R.id.app_right_iv})
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.app_right_iv:
                STActivity(AddChuXuKaActivity.class);
            break;
        }
    }
}
