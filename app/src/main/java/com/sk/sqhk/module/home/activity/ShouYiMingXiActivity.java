package com.sk.sqhk.module.home.activity;

import android.view.View;

import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.library.base.view.MyRecyclerView;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.module.home.network.ApiRequest;
import com.sk.sqhk.module.home.network.response.ShouYiDetailObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/20.
 */

public class ShouYiMingXiActivity extends BaseActivity {
    @BindView(R.id.rv_shouyi_mingxi)
    MyRecyclerView rv_shouyi_mingxi;

    LoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        setAppTitle("收益明细");
        return R.layout.act_shouyi_mingxi;
    }

    @Override
    protected void initView() {
        adapter=new LoadMoreAdapter<ShouYiDetailObj>(mContext,R.layout.item_shouyi_mingxi,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, ShouYiDetailObj bean) {
                holder.setText(R.id.tv_shouyi_detail_title,bean.getRemark())
                        .setText(R.id.tv_shouyi_detail_time,bean.getAdd_time())
                        .setText(R.id.tv_shouyi_detail_money,bean.getValue()+"");
            }
        };
        adapter.setOnLoadMoreListener(this);

        rv_shouyi_mingxi.setAdapter(adapter);
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
        map.put("pagesize",pagesize+"");
        map.put("page",page+"");
        map.put("sign",getSign(map));
        ApiRequest.getShouYiDetail(map, new MyCallBack<List<ShouYiDetailObj>>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(List<ShouYiDetailObj> list) {
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

    @Override
    protected void onViewClick(View v) {

    }
}
