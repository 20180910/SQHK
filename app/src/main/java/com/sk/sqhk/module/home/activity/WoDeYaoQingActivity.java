package com.sk.sqhk.module.home.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.module.home.network.ApiRequest;
import com.sk.sqhk.module.home.network.response.MyXiaJiObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/15.
 */

public class WoDeYaoQingActivity extends BaseActivity {
    @BindView(R.id.rv_wode_yaoqing)
    RecyclerView rv_wode_yaoqing;

    LoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        setAppTitle("我的邀请");
        return R.layout.act_wode_yaoqing;
    }

    @Override
    protected void initView() {
        adapter=new LoadMoreAdapter<MyXiaJiObj>(mContext,R.layout.item_my_xiaji,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, MyXiaJiObj bean) {
                ImageView imageView = holder.getImageView(R.id.iv_my_xiaji);
                Glide.with(mContext).load(bean.getAvatar()).error(R.color.c_press).into(imageView);
                holder.setText(R.id.tv_my_xiaji_name,bean.getNick_name())
                        .setText(R.id.tv_my_xiaji_phone,bean.getMobile())
                        .setText(R.id.tv_my_xiaji_time,bean.getAdd_time());
            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_wode_yaoqing.setAdapter(adapter);
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
        map.put("page",page+"");
        map.put("pagesize",pagesize+"");
        map.put("sign",getSign(map));
        ApiRequest.getMyXiaJi(map, new MyCallBack<List<MyXiaJiObj>>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(List<MyXiaJiObj> list) {
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
