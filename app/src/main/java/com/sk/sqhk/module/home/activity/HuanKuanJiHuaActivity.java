package com.sk.sqhk.module.home.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.module.home.bean.HuanKuanPlanBean;
import com.sk.sqhk.module.home.network.ApiRequest;
import com.sk.sqhk.module.home.network.response.JiSuHuanKuanPlanObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/22.
 */

public class HuanKuanJiHuaActivity extends BaseActivity {
    @BindView(R.id.rv_huankuan_plan)
    RecyclerView rv_huankuan_plan;

    @BindView(R.id.tv_huankuan_shouxufei)
    TextView tv_huankuan_shouxufei;

    @BindView(R.id.tv_huankuan_feilv)
    TextView tv_huankuan_feilv;

    LoadMoreAdapter adapter;
    private HuanKuanPlanBean huanKuanPlanBean;

    @Override
    protected int getContentView() {
        setAppTitle("还款计划");
        return R.layout.act_huankuan_jihua;
    }

    @Override
    protected void initView() {
        huanKuanPlanBean = (HuanKuanPlanBean) getIntent().getSerializableExtra(Constant.IParam.huanKuanBean);

        adapter = new LoadMoreAdapter<JiSuHuanKuanPlanObj.PlanBean>(mContext, R.layout.item_huan_kuan_plan, pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, JiSuHuanKuanPlanObj.PlanBean bean) {
                //绑定数据
                View ll_huankuan_plan_content = holder.getView(R.id.ll_huankuan_plan_content);
                ImageView iv_huankuan_plan = holder.getImageView(R.id.iv_huankuan_plan);
                if (bean.isZhanKai()) {
                    ll_huankuan_plan_content.setVisibility(View.VISIBLE);
                    iv_huankuan_plan.setImageResource(R.drawable.zaixianbanka_icon05);
                } else {
                    ll_huankuan_plan_content.setVisibility(View.GONE);
                    iv_huankuan_plan.setImageResource(R.drawable.zaixianbanka_icon06);
                }
                View ll_huankuan_plan_title = holder.getView(R.id.ll_huankuan_plan_title);
                ll_huankuan_plan_title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bean.setZhanKai(!bean.isZhanKai());
                        if (bean.isZhanKai()) {
                            ll_huankuan_plan_content.setVisibility(View.VISIBLE);
                            iv_huankuan_plan.setImageResource(R.drawable.zaixianbanka_icon05);
                        } else {
                            ll_huankuan_plan_content.setVisibility(View.GONE);
                            iv_huankuan_plan.setImageResource(R.drawable.zaixianbanka_icon06);
                        }
                    }
                });

                holder.setText(R.id.tv_huankuan_plan_date, bean.getDay())
                        .setText(R.id.tv_huankuan_plan_bishu, bean.getRefundCount() + "笔")//还款笔数
                        .setText(R.id.tv_huankuan_plan_xiaofei_bishu, bean.getExpenseCount() + "笔")//消费笔数
                        .setText(R.id.tv_huankuan_plan_xiaofei_money, isEmpty(bean.getExpense()) ? "0元" : bean.getExpense().get(0).getExpenseMoney() + "元")//消费金额
                        .setText(R.id.tv_huankuan_plan_total_money, bean.getTotalRefund() + "元")//当日还款总额
                        //当日消费总额
                        .setText(R.id.tv_huankuan_plan_xiaofei_total_money, bean.getTotalExpense() + "0元")
                        //实际消费金额
                        .setText(R.id.tv_huankuan_plan_shijixiaofei_total_money, isEmpty(bean.getExpense()) ? "0元" : bean.getExpense().get(0).getExpenseFee() + "元")
                        .setText(R.id.tv_huankuan_plan_money, isEmpty(bean.getRefund()) ? "0元" : bean.getRefund().get(0).getRefundMoney() + "元")//还款金额
                        .setText(R.id.tv_huankuan_plan_shiji_money, isEmpty(bean.getRefund()) ? "0元" : bean.getRefund().get(0).getRefundAcMoney() + "元");//实际还款金额
                /*tv_huankuan_plan_date
ll_huankuan_plan_content
tv_huankuan_plan_bishu
tv_huankuan_plan_xiaofei_bishu
tv_huankuan_plan_xiaofei_money
tv_huankuan_plan_total_money
tv_huankuan_plan_xiaofei_total_money
tv_huankuan_plan_shijixiaofei_total_money
tv_huankuan_plan_money
tv_huankuan_plan_shiji_money*/
            }
        };

        rv_huankuan_plan.setLayoutManager(new LinearLayoutManager(mContext));
        rv_huankuan_plan.setAdapter(adapter);

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
        map.put("card_id", huanKuanPlanBean.getCard_id());
        map.put("begintime", huanKuanPlanBean.getBegintime());
        map.put("endtime", huanKuanPlanBean.getEndtime());
        map.put("bi_num", huanKuanPlanBean.getBi_num());
        map.put("money", huanKuanPlanBean.getMoney());
        map.put("sign", getSign(map));
        ApiRequest.jiSuHuanKuanPlanList(map, new MyCallBack<JiSuHuanKuanPlanObj>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(JiSuHuanKuanPlanObj obj) {
                tv_huankuan_shouxufei.setText(obj.getFee() + "元");
                tv_huankuan_feilv.setText(obj.getFeeRate() + "%");
                if (isLoad) {
                    pageNum++;
                    adapter.addList(obj.getPlan(), true);
                } else {
                    pageNum = 2;
                    adapter.setList(obj.getPlan(), true);
                }
            }
        });

    }

    @Override
    protected void onViewClick(View v) {

    }
}
