package com.sk.sqhk.module.home.activity;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.library.base.view.MyRecyclerView;
import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.module.home.network.ApiRequest;
import com.sk.sqhk.module.home.network.response.XinYongDaiObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/21.
 */

public class XinYongDaiActivity extends BaseActivity {
    @BindView(R.id.rv_xinyongdai)
    MyRecyclerView rv_xinyongdai;

    LoadMoreAdapter adapter;
    private String typeId;

    @Override
    protected int getContentView() {
        return R.layout.act_xinyongdai;
    }

    @Override
    protected void initView() {
        typeId = getIntent().getStringExtra(Constant.IParam.typeId);
        String title = getIntent().getStringExtra(Constant.IParam.title);
        setAppTitle(title);


        adapter=new LoadMoreAdapter<XinYongDaiObj.HotCreditLoanBean>(mContext,R.layout.item_hot_dai,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, XinYongDaiObj.HotCreditLoanBean bean) {
                ImageView imageView = holder.getImageView(R.id.iv_jinrong_img);
                Glide.with(mContext).load(bean.getImage_url()).error(R.color.c_press).into(imageView);

                TextView tv_jinrong_lilv = holder.getTextView(R.id.tv_jinrong_lilv);
                tv_jinrong_lilv.setText(Html.fromHtml("日利率低至<font color='#5BA7F4'>" + bean.getDaily_rate() + "</font>%"));

                TextView tv_jinrong_money = holder.getTextView(R.id.tv_jinrong_money);
                tv_jinrong_money.setText(Html.fromHtml("可贷:<font color='#5BA7F4'>" + bean.getLoan_amount() + "</font>(元)"));

                holder.setText(R.id.tv_jinrong_title, bean.getTitle())
                        .setText(R.id.tv_jinrong_people, "已申请人数" + bean.getApplications());
            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_xinyongdai.setAdapter(adapter);

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
        map.put("typeid",typeId);
        map.put("pagesize",pagesize+"");
        map.put("page",page+"");
        map.put("sign",getSign(map));
        ApiRequest.getJinRongDaiList(map, new MyCallBack<XinYongDaiObj>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(XinYongDaiObj obj) {
                if(isLoad){
                    pageNum++;
                    adapter.addList(obj.getHot_credit_loan(),true);
                }else{
                    pageNum=2;
                    adapter.setList(obj.getHot_credit_loan(),true);
                }
            }
        });

    }

    @Override
    protected void onViewClick(View v) {

    }
}
