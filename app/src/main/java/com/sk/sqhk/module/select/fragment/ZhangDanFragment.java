package com.sk.sqhk.module.select.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.androidtools.DateUtils;
import com.github.androidtools.PhoneUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.library.base.tools.has.AndroidUtils;
import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseFragment;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.module.select.activity.ZhangDanDuoTianDetailActivity;
import com.sk.sqhk.module.select.network.ApiRequest;
import com.sk.sqhk.module.select.network.response.HuanKuanPlanObj;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/23.
 */

public class ZhangDanFragment extends BaseFragment {
    @BindView(R.id.rv_huankuan_plan)
    RecyclerView rv_huankuan_plan;

    LoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        return R.layout.frag_zhangdan;
    }

    public static ZhangDanFragment newInstance(String flag,String cardId) {
        Bundle args = new Bundle();
        args.putString(Constant.zhangDanFlag,flag);
        args.putString(Constant.cardId,cardId);
        ZhangDanFragment fragment = new ZhangDanFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void initView() {
        adapter=new LoadMoreAdapter<HuanKuanPlanObj.PlanIdBean.ListBean>(mContext,R.layout.item_zhangdan,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, HuanKuanPlanObj.PlanIdBean.ListBean bean) {
                holder.getView(R.id.tv_zhangdan_detail).setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        Intent intent=new Intent();
                        intent.putExtra(Constant.IParam.planId,bean.getId()+"");
                        STActivity(intent,ZhangDanDuoTianDetailActivity.class);
                    }
                });
                holder.setText(R.id.tv_zhangdan_no,"编号:"+bean.getNo())
                        .setText(R.id.tv_zhangdan_date,bean.getCreateTime())
                        .setText(R.id.tv_zhangdan_bishu,bean.getCount()+"笔")
                        .setText(R.id.tv_zhangdan_shouxufei,AndroidUtils.chuFa(bean.getFee(),100)+"元")
                        .setText(R.id.tv_zhangdan_huankuan_money, AndroidUtils.chuFa(bean.getActualAmount(),100)+"元")
                        .setText(R.id.tv_zhangdan_ri, DateUtils.dateToString(new Date(bean.getBillDate())))
                        .setText(R.id.tv_zhangdan_huankuan_ri,DateUtils.dateToString(new Date(bean.getPaymentDate())));
            }
        };
        adapter.setOnLoadMoreListener(this);

        rv_huankuan_plan.setLayoutManager(new LinearLayoutManager(mContext));
        rv_huankuan_plan.addItemDecoration(getItemDivider(PhoneUtils.dip2px(mContext,5)));
        rv_huankuan_plan.setAdapter(adapter);


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
        map.put("card_id",getArguments().getString(Constant.cardId));
        map.put("status",getArguments().getString(Constant.zhangDanFlag));
        map.put("pagesize",pagesize+"");
        map.put("page",page+"");
        map.put("sign",getSign(map));
        ApiRequest.getHuanKuanPlan(map, new MyCallBack<HuanKuanPlanObj>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(HuanKuanPlanObj obj) {
                if(isLoad){
                    pageNum++;
                    adapter.addList(obj.getPlan_id().getList(),true);
                }else{
                    pageNum=2;
                    adapter.setList(obj.getPlan_id().getList(),true);
                }
            }
        });

    }

    @Override
    protected void onViewClick(View v) {

    }
}
