package com.sk.sqhk.module.my.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.library.base.MyCallBack;
import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.module.my.network.ApiRequest;
import com.sk.sqhk.module.my.network.response.HelpCenterObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/25.
 */

public class HelpCenterActivity extends BaseActivity {
    @BindView(R.id.rv_question)
    RecyclerView rv_question;

    LoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        setAppTitle("帮助中心");
        return R.layout.act_help_center;
    }

    @Override
    protected void initView() {
        adapter=new LoadMoreAdapter<HelpCenterObj>(mContext,R.layout.item_question,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, HelpCenterObj bean) {
                holder.setText(R.id.tv_help_center_title,bean.getTitle());
                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        Intent intent=new Intent();
                        intent.putExtra(Constant.IParam.helpCenter,bean);
                        STActivity(intent,HelpDetailActivity.class);
                    }
                });
            }
        };
        adapter.setOnLoadMoreListener(this);
        adapter.setHiddenPromptView();

        rv_question.setAdapter(adapter);

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
        ApiRequest.getHelpCenter(map, new MyCallBack<List<HelpCenterObj>>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(List<HelpCenterObj> list) {
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

    @OnClick({R.id.tv_help_center_yijian})
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.tv_help_center_yijian:
                STActivity(YiJianFanKuiActivity.class);
            break;
        }
    }
}
