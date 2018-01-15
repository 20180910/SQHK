package com.sk.sqhk.module.home.activity;

import android.view.View;
import android.widget.TextView;

import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.library.base.BaseObj;
import com.library.base.view.MyRecyclerView;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.module.home.network.ApiRequest;
import com.sk.sqhk.module.home.network.response.MyFenRunObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/20.
 */

public class MyFenRunActivity extends BaseActivity {
    @BindView(R.id.rv_tixian_mingxi)
    MyRecyclerView rv_tixian_mingxi;
    LoadMoreAdapter adapter;
    @BindView(R.id.tv_fenrun_yestoday)
    TextView tv_fenrun_yestoday;
    @BindView(R.id.tv_fenrun_today)
    TextView tv_fenrun_today;
    @BindView(R.id.tv_fenrun_total)
    TextView tv_fenrun_total;
    @BindView(R.id.tv_fenrun_keyitixian)
    TextView tv_fenrun_keyitixian;
    private double commission;

    @Override
    protected int getContentView() {
        setAppTitle("我的分润");
        setAppRightTitle("收益明细");
        return R.layout.act_my_fen_run;
    }

    @Override
    protected void initView() {
        adapter = new LoadMoreAdapter<MyFenRunObj.CashWithdrawalDetailListBean>(mContext, R.layout.item_ti_xian_ming_xi, pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, MyFenRunObj.CashWithdrawalDetailListBean bean) {
                holder.setText(R.id.tv_tixian_detail_money, bean.getValue() + "")
                        .setText(R.id.tv_tixian_detail_title, bean.getRemark() + "")
                        .setText(R.id.tv_tixian_detail_time, bean.getAdd_time() + "");
            }
        };
        adapter.setOnLoadMoreListener(this);

        rv_tixian_mingxi.setAdapter(adapter);

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
        map.put("pagesize", pagesize + "");
        map.put("page", page + "");
        map.put("sign", getSign(map));
        ApiRequest.getFenRun(map, new MyCallBack<MyFenRunObj>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(MyFenRunObj obj) {
                commission = obj.getCommission();
                tv_fenrun_yestoday.setText(obj.getYesterday_commission() + "");
                tv_fenrun_today.setText(obj.getToday_commission() + "");
                tv_fenrun_total.setText("累计分润:" + obj.getHistory_commission() + "元");
                tv_fenrun_keyitixian.setText(obj.getCommission() + "");
                if (isLoad) {
                    pageNum++;
                    adapter.addList(obj.getCash_withdrawal_detail_list(), true);
                } else {
                    pageNum = 2;
                    adapter.setList(obj.getCash_withdrawal_detail_list(), true);
                }
            }
        });

    }

    @OnClick({R.id.app_right_tv, R.id.tv_fenrun_tixian})
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.tv_fenrun_tixian:
                tiXian();
                break;
            case R.id.app_right_tv:
                STActivity(ShouYiMingXiActivity.class);
                break;
        }
    }

    private void tiXian() {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("amount",commission+"");
        map.put("sign",getSign(map));
        ApiRequest.fenRunTiXian(map, new MyCallBack<BaseObj>(mContext,true) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                getData(1,false);
            }
        });

    }
}
