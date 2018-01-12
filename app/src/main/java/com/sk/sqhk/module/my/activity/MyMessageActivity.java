package com.sk.sqhk.module.my.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.module.my.network.ApiRequest;
import com.sk.sqhk.module.my.network.response.MyMessageObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/20.
 */

public class MyMessageActivity extends BaseActivity {
    @BindView(R.id.rv_my_message)
    RecyclerView rv_my_message;

    LoadMoreAdapter adapter;
    @Override
    protected int getContentView() {
        setAppTitle("我的消息");
        return R.layout.act_my_message;
    }

    @Override
    protected void initView() {
        adapter=new LoadMoreAdapter<MyMessageObj>(mContext,R.layout.item_my_message,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, MyMessageObj bean) {
                ImageView imageView = holder.getImageView(R.id.iv_my_message_img);
                Glide.with(mContext).load(bean.getImage()).error(R.color.c_press).into(imageView);
                holder.setText(R.id.tv_my_message_title,bean.getTitle())
                        .setText(R.id.tv_my_message_content,bean.getContent())
                        .setText(R.id.tv_my_message_time,bean.getAdd_time());

                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        Intent intent=new Intent();
                        intent.putExtra(Constant.IParam.messageId,bean.getId()+"");
                        STActivity(intent,TongZhiActivity.class);
                    }
                });
            }
        };
        adapter.setOnLoadMoreListener(this);

        rv_my_message.setAdapter(adapter);
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
        map.put("pagesize",pageSize+"");
        map.put("page",page+"");
        map.put("sign",getSign(map));
        ApiRequest.getMessageList(map, new MyCallBack<List<MyMessageObj>>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(List<MyMessageObj> list) {
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
