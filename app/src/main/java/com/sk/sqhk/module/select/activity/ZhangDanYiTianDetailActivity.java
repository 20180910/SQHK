package com.sk.sqhk.module.select.activity;

import android.view.View;
import android.widget.TextView;

import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.library.base.tools.has.AndroidUtils;
import com.library.base.view.MyRecyclerView;
import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.module.select.network.ApiRequest;
import com.sk.sqhk.module.select.network.response.HuanKuanPlanYiTianDetailObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/25.
 */

public class ZhangDanYiTianDetailActivity extends BaseActivity {


    @BindView(R.id.tv_yitian_plan_detail_xiaofei_total)
    TextView tv_yitian_plan_detail_xiaofei_total;
    @BindView(R.id.tv_yitian_plan_detail_xiaofei_bishu)
    TextView tv_yitian_plan_detail_xiaofei_bishu;
    @BindView(R.id.tv_yitian_plan_detail_hk_total)
    TextView tv_yitian_plan_detail_hk_total;
    @BindView(R.id.tv_yitian_plan_detail_hk_bishu)
    TextView tv_yitian_plan_detail_hk_bishu;

    @BindView(R.id.rv_yitian_plan_xiaofei)
    MyRecyclerView rv_yitian_plan_xiaofei;

    @BindView(R.id.rv_yitian_plan_hk)
    MyRecyclerView rv_yitian_plan_hk;
    private LoadMoreAdapter xfAdapter, hkAdapter;
    private String planId;
    private String day;

    @Override
    protected int getContentView() {
        setAppTitle("账单详情");
        return R.layout.act_zhangdan_yitian_detail;
    }

    @Override
    protected void initView() {
        planId = getIntent().getStringExtra(Constant.IParam.planId);
        day = getIntent().getStringExtra(Constant.IParam.day);

        xfAdapter = new LoadMoreAdapter<HuanKuanPlanYiTianDetailObj.PlanIdBean.ExpenseListBean>(mContext, R.layout.item_huankuan_plan_yitian_detial_xf, pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, HuanKuanPlanYiTianDetailObj.PlanIdBean.ExpenseListBean bean) {
                String str = "未执行";
                //0未执行 1进行中 2成功 3失败 4中断
                switch (bean.getStatus()) {
                    case 0:
                        str = "未执行";
                        break;
                    case 1:
                        str = "进行中";
                        break;
                    case 2:
                        str = "成功";
                        break;
                    case 3:
                        str = "失败";
                        break;
                    case 4:
                        str = "中断";
                        break;
                }
                holder.setText(R.id.tv_huankuan_plan_yitian_xiaofei, AndroidUtils.chuFa(bean.getAmount(), 100) + "元")
                        .setText(R.id.tv_huankuan_plan_yitian_status, str)
                        .setText(R.id.tv_huankuan_plan_yitian_date, bean.getDay());

                View ll_huankuan_plan_yitian_reason = holder.getView(R.id.ll_huankuan_plan_yitian_reason);
                TextView tv_huankuan_plan_yitian_reason = holder.getTextView(R.id.tv_huankuan_plan_yitian_reason);
                if (bean.getStatus() == 3) {//如果失败就显示
                    ll_huankuan_plan_yitian_reason.setVisibility(View.VISIBLE);
                    tv_huankuan_plan_yitian_reason.setText("暂无数据");
                } else {
                    ll_huankuan_plan_yitian_reason.setVisibility(View.GONE);
                }
            }
        };
        rv_yitian_plan_xiaofei.setAdapter(xfAdapter);

        hkAdapter = new LoadMoreAdapter<HuanKuanPlanYiTianDetailObj.PlanIdBean.RefundListBean>(mContext, R.layout.item_huankuan_plan_yitian_detial_hk, pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, HuanKuanPlanYiTianDetailObj.PlanIdBean.RefundListBean bean) {
                //0初始 1进行 2成功 3失败 4中断
                String str = "初始";
                //0未执行 1进行中 2成功 3失败 4中断
                switch (bean.getStatus()) {
                    case 0:
                        str = "初始";
                        break;
                    case 1:
                        str = "进行";
                        break;
                    case 2:
                        str = "成功";
                        break;
                    case 3:
                        str = "失败";
                        break;
                    case 4:
                        str = "中断";
                        break;
                }
                holder.setText(R.id.tv_huankuan_plan_yitian_hk, AndroidUtils.chuFa(bean.getAmount(), 100) + "元")
                        .setText(R.id.tv_huankuan_plan_yitian_status, str)
                        .setText(R.id.tv_huankuan_plan_yitian_date, bean.getDay());

                View ll_huankuan_plan_yitian_reason = holder.getView(R.id.ll_huankuan_plan_yitian_reason);
                TextView tv_huankuan_plan_yitian_reason = holder.getTextView(R.id.tv_huankuan_plan_yitian_reason);
                if (bean.getStatus() == 3) {//如果失败就显示
                    ll_huankuan_plan_yitian_reason.setVisibility(View.VISIBLE);
                    tv_huankuan_plan_yitian_reason.setText("暂无数据");
                } else {
                    ll_huankuan_plan_yitian_reason.setVisibility(View.GONE);
                }
            }
        };
        rv_yitian_plan_hk.setAdapter(hkAdapter);


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
        map.put("plan_id", planId);
        map.put("day", day);
        map.put("sign", getSign(map));
        ApiRequest.getHuanKuanPlanYiTianDetail(map, new MyCallBack<HuanKuanPlanYiTianDetailObj>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(HuanKuanPlanYiTianDetailObj obj) {
                xfAdapter.setList(obj.getPlan_id().getExpenseList(), true);
                hkAdapter.setList(obj.getPlan_id().getRefundList(), true);

                tv_yitian_plan_detail_xiaofei_total.setText(AndroidUtils.chuFa(obj.getPlan_id().getExpenseAmount(), 100) + "元");
                tv_yitian_plan_detail_xiaofei_bishu.setText(obj.getPlan_id().getExpenseCount() + "笔");
                tv_yitian_plan_detail_hk_total.setText(AndroidUtils.chuFa(obj.getPlan_id().getRefundAmount(), 100) + "元");
                tv_yitian_plan_detail_hk_bishu.setText(obj.getPlan_id().getRefundCount() + "笔");

            }
        });

    }

    @Override
    protected void onViewClick(View v) {

    }
}
