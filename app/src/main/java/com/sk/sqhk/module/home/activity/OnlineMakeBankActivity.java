package com.sk.sqhk.module.home.activity;

import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.library.base.view.MyRecyclerView;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.module.home.network.ApiRequest;
import com.sk.sqhk.module.home.network.response.HotBankObj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/20.
 */

public class OnlineMakeBankActivity extends BaseActivity {
    @BindView(R.id.rv_hot_bank)
    RecyclerView rv_hot_bank;
    @BindView(R.id.rv_hot_xinyongka)
    MyRecyclerView rv_hot_xinyongka;
    @BindView(R.id.tv_online_zhankai)
    TextView tv_online_zhankai;

    LoadMoreAdapter bankAdapter,xinyongKaAdapter;

    List<HotBankObj> allList=new ArrayList<>();
    List<HotBankObj> littleList=new ArrayList<>();

    private boolean lookAll;

    @Override
    protected int getContentView() {
        setAppTitle("在线办卡");
        return R.layout.act_online_make_bank;
    }

    @Override
    protected void initView() {
        bankAdapter=new LoadMoreAdapter<HotBankObj>(mContext,R.layout.item_hot_bank,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, HotBankObj bean) {
                ImageView imageView = holder.getImageView(R.id.iv_hot_bank);
                Glide.with(mContext).load(bean.getImage_url()).asBitmap().encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG,90)).error(R.color.c_press).into(imageView);
                holder.setText(R.id.tv_hot_bank_name,bean.getBank_name());
            }
        };
        rv_hot_bank.setAdapter(bankAdapter);
        rv_hot_bank.setLayoutManager(new GridLayoutManager(mContext,3));
        rv_hot_bank.setNestedScrollingEnabled(false);
//        rv_hot_bank.addItemDecoration(getItemDivider());


        xinyongKaAdapter=new LoadMoreAdapter(mContext,R.layout.item_hot_xinyongka,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, Object bean) {

            }
        };
        xinyongKaAdapter.setOnLoadMoreListener(this);
        rv_hot_xinyongka.setAdapter(xinyongKaAdapter);
        rv_hot_xinyongka.setNestedScrollingEnabled(false);

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
        map.put("rnd",getRnd());
        map.put("sign",getSign(map));
        ApiRequest.getHotBankList(map, new MyCallBack<List<HotBankObj>>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(List<HotBankObj> list) {
                allList=list;
                littleList.clear();
                if(notEmpty(list)){
                    if(list.size()>=6){
                        littleList.add(list.get(0));
                        littleList.add(list.get(1));
                        littleList.add(list.get(2));
                        littleList.add(list.get(3));
                        littleList.add(list.get(4));
                        littleList.add(list.get(5));
                    }else{
                        littleList.addAll(list);
                    }
                    bankAdapter.setList(littleList,true);
                }
            }
        });

    }

    @OnClick({R.id.tv_online_zhankai})
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.tv_online_zhankai:
                lookAll=!lookAll;
                if(lookAll){
                    tv_online_zhankai.setText("收起更多");
                    bankAdapter.setList(allList,true);
                }else{
                    tv_online_zhankai.setText("展开更多");
                    bankAdapter.setList(littleList,true);
                }
            break;
        }
    }
}
