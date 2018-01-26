package com.sk.sqhk.module.my.activity;

import android.view.View;
import android.widget.TextView;

import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.sk.sqhk.base.MyCallBack;
import com.library.base.view.MyRecyclerView;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.module.home.activity.TiXianActivity;
import com.sk.sqhk.module.my.network.ApiRequest;
import com.sk.sqhk.module.my.network.response.MyAccountObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/25.
 */

public class MyAccountActivity extends BaseActivity {
    @BindView(R.id.rv_my_shouyi)
    MyRecyclerView rv_my_shouyi;

    LoadMoreAdapter adapter;
    @BindView(R.id.tv_account_yue)
    TextView tv_account_yue;

    @Override
    protected int getContentView() {
        setAppTitle("我的账户");
        return R.layout.act_my_account;
    }

    @Override
    protected void initView() {

        adapter = new LoadMoreAdapter<MyAccountObj.BalanceDetailListBean>(mContext, R.layout.item_my_shouyi, pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, MyAccountObj.BalanceDetailListBean bean) {
                holder.setText(R.id.tv_shouyi_title,bean.getRemark())
                        .setText(R.id.tv_shouyi_money,bean.getValue()+"");
            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_my_shouyi.setAdapter(adapter);

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
        map.put("page",page+"");
        map.put("pagesize",pageSize+"");
        map.put("sign",getSign(map));
        ApiRequest.getMyAccount(map, new MyCallBack<MyAccountObj>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(MyAccountObj obj) {
                tv_account_yue.setText("¥"+obj.getBalance());
                if(isLoad){
                    pageNum++;
                    adapter.addList(obj.getBalance_detail_list(),true);
                }else{
                    pageNum=2;
                    adapter.setList(obj.getBalance_detail_list(),true);
                }
            }
        });

    }

    @OnClick(R.id.tv_account_tixian)
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.tv_account_tixian:
                STActivity(TiXianActivity.class);
            break;
        }
    }
}
