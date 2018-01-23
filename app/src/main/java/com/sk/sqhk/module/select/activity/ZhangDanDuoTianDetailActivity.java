package com.sk.sqhk.module.select.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.library.base.tools.has.AndroidUtils;
import com.library.base.view.MyRecyclerView;
import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.module.select.network.ApiRequest;
import com.sk.sqhk.module.select.network.response.HuanKuanPlanDuoTianDetailObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/25.
 */

public class ZhangDanDuoTianDetailActivity extends BaseActivity {

    @BindView(R.id.tv_duotian_plan_detail_time)
    TextView tv_duotian_plan_detail_time;
    @BindView(R.id.tv_duotian_plan_detail_bishu)
    TextView tv_duotian_plan_detail_bishu;
    @BindView(R.id.tv_duotian_plan_detail_total)
    TextView tv_duotian_plan_detail_total;
    @BindView(R.id.tv_duotian_plan_detail_shouxufei)
    TextView tv_duotian_plan_detail_shouxufei;
    @BindView(R.id.rv_duotian_plan)
    MyRecyclerView rv_duotian_plan;

    private String planId;
    private LoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        setAppTitle("计划详情");//多天
        return R.layout.act_zhangdan_duotian_detail;
    }

    @Override
    protected void initView() {
        planId = getIntent().getStringExtra(Constant.IParam.planId);

        adapter=new LoadMoreAdapter<HuanKuanPlanDuoTianDetailObj.PlanIdBean.ListBean>(mContext,R.layout.item_huankuan_plan_duotian_detial,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, HuanKuanPlanDuoTianDetailObj.PlanIdBean.ListBean bean) {
                holder.setText(R.id.tv_huankuan_plan_duotian_date,bean.getDay())
                        .setText(R.id.tv_huankuan_plan_duotian_money,AndroidUtils.chuFa(bean.getRefundAmount(),100)+"元");
                holder.getView(R.id.tv_huankuan_plan_duotian_date).setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        Intent intent=new Intent();
                        intent.putExtra(Constant.IParam.planId,planId);
                        intent.putExtra(Constant.IParam.day,bean.getDay());
                        STActivity(intent,ZhangDanYiTianDetailActivity.class);
                    }
                });
            }
        };
        rv_duotian_plan.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        showProgress();
        getData(1, false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", getUserId());
        map.put("plan_id", planId + "");
        map.put("sign", getSign(map));
        ApiRequest.getHuanKuanPlanDuoTianDetail(map, new MyCallBack<HuanKuanPlanDuoTianDetailObj>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(HuanKuanPlanDuoTianDetailObj obj) {
                tv_duotian_plan_detail_time.setText(obj.getPlan_id().getDay());
                tv_duotian_plan_detail_bishu.setText(obj.getPlan_id().getCount() + "笔");
                tv_duotian_plan_detail_total.setText(AndroidUtils.chuFa(obj.getPlan_id().getAmount(), 100) + "元");
                tv_duotian_plan_detail_shouxufei.setText(AndroidUtils.chuFa(obj.getPlan_id().getFee(), 100) + "元");
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
