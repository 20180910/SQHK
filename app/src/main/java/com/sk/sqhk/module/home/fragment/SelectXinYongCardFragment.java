package com.sk.sqhk.module.home.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.github.customview.MyLinearLayout;
import com.library.base.view.MyRecyclerView;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseFragment;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.module.home.network.ApiRequest;
import com.sk.sqhk.module.home.network.response.SelectXinYongCardObj;
import com.sk.sqhk.module.select.activity.WoDeZhangDanActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/23.
 */

public class SelectXinYongCardFragment extends BaseFragment {
    @BindView(R.id.rv_my_xinyongka)
    MyRecyclerView rv_my_xinyongka;

    LoadMoreAdapter xinYongAdapter;

    @Override
    protected int getContentView() {
        return R.layout.act_select_xinyong_card;
    }

    @Override
    protected void initView() {
        xinYongAdapter=new LoadMoreAdapter<SelectXinYongCardObj>(mContext,R.layout.item_my_xinyong_card,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, SelectXinYongCardObj bean) {
                SwipeMenuLayout sml_xinyong = (SwipeMenuLayout) holder.getView(R.id.sml_xinyong);
                sml_xinyong.setSwipeEnable(false);
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
                        intent.putExtra(Constant.IParam.selectXinYongCard,bean);
                        STActivity(intent,WoDeZhangDanActivity.class);
                    }
                });


            }
        };
        rv_my_xinyongka.setAdapter(xinYongAdapter);
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
        ApiRequest.getSelectXinYongCard(map, new MyCallBack<List<SelectXinYongCardObj>>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(List<SelectXinYongCardObj> list) {
                if(isLoad){
                    pageNum++;
                    xinYongAdapter.addList(list,true);
                }else{
                    pageNum=2;
                    xinYongAdapter.setList(list,true);
                }
            }
        });

    }

    @Override
    protected void onViewClick(View v) {

    }
}
