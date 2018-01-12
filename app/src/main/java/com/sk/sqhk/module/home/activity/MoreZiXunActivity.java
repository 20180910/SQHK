package com.sk.sqhk.module.home.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.module.home.network.ApiRequest;
import com.sk.sqhk.module.home.network.response.HomeZiXunDataObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/12.
 */

public class MoreZiXunActivity extends BaseActivity {
    @BindView(R.id.rv_more_zixun)
    RecyclerView rv_more_zixun;

    LoadMoreAdapter adapter;
    @Override
    protected int getContentView() {
        setAppTitle("资讯");
        return R.layout.act_more_zixun;
    }

    @Override
    protected void initView() {
        adapter=new LoadMoreAdapter<HomeZiXunDataObj>(mContext,R.layout.item_zui_xin_zixun,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, HomeZiXunDataObj bean) {
                ImageView iv_home_zixun_img = holder.getImageView(R.id.iv_home_zixun_img);
                Glide.with(mContext).load(bean.getImage_url()).error(R.color.c_press).into(iv_home_zixun_img);
                holder.setText(R.id.tv_home_zixun_content,bean.getTitle())
                        .setText(R.id.tv_home_zixun_from,bean.getAuthor())
                        .setText(R.id.tv_home_zixun_time,bean.getAdd_time());

                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        Intent intent=new Intent();
                        intent.putExtra(Constant.IParam.ziXunId,bean.getInformation_id()+"");
                        STActivity(intent,WenZhangDetailActivity.class);
                    }
                });
            }
        };
        adapter.setOnLoadMoreListener(this);

        rv_more_zixun.setAdapter(adapter);

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
        map.put("page",page+"");
        map.put("pagesize",pageSize+"");
        map.put("sign",getSign(map));
        ApiRequest.getMoreZiXunData(map, new MyCallBack<List<HomeZiXunDataObj>>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(List<HomeZiXunDataObj> list) {
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
