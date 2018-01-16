package com.sk.sqhk.module.home.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.library.base.view.MyRecyclerView;
import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.module.home.network.ApiRequest;
import com.sk.sqhk.module.home.network.response.JinRongChaoShiObj;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/21.
 */

public class JinRongChaoShiActivity extends BaseActivity {
    @BindView(R.id.rv_hot_dai)
    MyRecyclerView rv_hot_dai;

    @BindView(R.id.bn_home)
    Banner bn_home;
    @BindView(R.id.iv_jinrong_icon1)
    ImageView iv_jinrong_icon1;
    @BindView(R.id.tv_jinrong_dai1)
    TextView tv_jinrong_dai1;
    @BindView(R.id.tv_jinrong_shuoming1)
    TextView tv_jinrong_shuoming1;
    @BindView(R.id.iv_jinrong_icon2)
    ImageView iv_jinrong_icon2;
    @BindView(R.id.tv_jinrong_dai2)
    TextView tv_jinrong_dai2;
    @BindView(R.id.tv_jinrong_shuoming2)
    TextView tv_jinrong_shuoming2;
    @BindView(R.id.iv_jinrong_icon3)
    ImageView iv_jinrong_icon3;
    @BindView(R.id.tv_jinrong_dai3)
    TextView tv_jinrong_dai3;
    @BindView(R.id.tv_jinrong_shuoming3)
    TextView tv_jinrong_shuoming3;
    @BindView(R.id.iv_jinrong_icon4)
    ImageView iv_jinrong_icon4;
    @BindView(R.id.tv_jinrong_dai4)
    TextView tv_jinrong_dai4;
    @BindView(R.id.tv_jinrong_shuoming4)
    TextView tv_jinrong_shuoming4;

    @BindView(R.id.ll_jinrong_tab1)
    LinearLayout ll_jinrong_tab1;

    @BindView(R.id.ll_jinrong_tab2)
    LinearLayout ll_jinrong_tab2;

    @BindView(R.id.ll_jinrong_tab3)
    LinearLayout ll_jinrong_tab3;

    @BindView(R.id.ll_jinrong_tab4)
    LinearLayout ll_jinrong_tab4;


    private List<String> bannerList = new ArrayList<String>();

    LoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        setAppTitle("金融超市");
        return R.layout.act_jinrong_chaoshi;
    }

    @Override
    protected void initView() {
        adapter = new LoadMoreAdapter<JinRongChaoShiObj.HotCreditLoanBean>(mContext, R.layout.item_hot_dai, pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, JinRongChaoShiObj.HotCreditLoanBean bean) {
                ImageView imageView = holder.getImageView(R.id.iv_jinrong_img);
                Glide.with(mContext).load(bean.getImage_url()).error(R.color.c_press).into(imageView);

                TextView tv_jinrong_lilv = holder.getTextView(R.id.tv_jinrong_lilv);
                tv_jinrong_lilv.setText(Html.fromHtml("日利率低至<font color='#5BA7F4'>" + bean.getDaily_rate() + "</font>%"));

                TextView tv_jinrong_money = holder.getTextView(R.id.tv_jinrong_money);
                tv_jinrong_money.setText(Html.fromHtml("可贷:<font color='#5BA7F4'>" + bean.getLoan_amount() + "</font>(元)"));

                holder.setText(R.id.tv_jinrong_title, bean.getTitle())
                        .setText(R.id.tv_jinrong_people, "已申请人数" + bean.getApplications());

                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        Intent intent=new Intent();
                        intent.putExtra(Constant.IParam.creditId,bean.getCredit_id()+"");
                        STActivity(intent,DaiKuanDetailActivity.class);
                    }
                });

            }
        };
        adapter.setOnLoadMoreListener(this);

        rv_hot_dai.setAdapter(adapter);
        rv_hot_dai.setFocusable(false);
        rv_hot_dai.setNestedScrollingEnabled(false);

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
        map.put("page", page + "");
        map.put("pagesize", pagesize + "");
        map.put("sign", getSign(map));
        ApiRequest.getJinRongChaoShi(map, new MyCallBack<JinRongChaoShiObj>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(JinRongChaoShiObj obj) {
                if (notEmpty(obj.getFinancial_supermarket_image())) {
                    bannerList = new ArrayList<String>();
                    for (int i = 0; i < obj.getFinancial_supermarket_image().size(); i++) {
                        bannerList.add(obj.getFinancial_supermarket_image().get(i));
                    }
                    setBannerList(bn_home, bannerList);
                }

                setDaiKuan(obj);


                if (isLoad) {
                    pageNum++;
                    adapter.addList(obj.getHot_credit_loan(), true);
                } else {
                    pageNum = 2;
                    adapter.setList(obj.getHot_credit_loan(), true);
                }
            }
        });

    }

    private void setDaiKuan(JinRongChaoShiObj obj) {
        if (notEmpty(obj.getFs_type_list())) {
            List<JinRongChaoShiObj.FsTypeListBean> fsTypeList = obj.getFs_type_list();
            if (obj.getFs_type_list().size() > 0) {
                Glide.with(mContext).load(fsTypeList.get(0).getImages()).error(R.color.c_press).into(iv_jinrong_icon1);
                tv_jinrong_dai1.setText(fsTypeList.get(0).getTitle());
                tv_jinrong_shuoming1.setText(fsTypeList.get(0).getSubtitle());
                ll_jinrong_tab1.setOnClickListener(getClickListener(fsTypeList.get(0).getTypeid()+"",fsTypeList.get(0).getTitle()));
            }
            if (obj.getFs_type_list().size() > 1) {
                Glide.with(mContext).load(fsTypeList.get(1).getImages()).error(R.color.c_press).into(iv_jinrong_icon2);
                tv_jinrong_dai2.setText(fsTypeList.get(1).getTitle());
                tv_jinrong_shuoming2.setText(fsTypeList.get(1).getSubtitle());
                ll_jinrong_tab2.setOnClickListener(getClickListener(fsTypeList.get(1).getTypeid()+"",fsTypeList.get(1).getTitle()));
            }
            if (obj.getFs_type_list().size() > 2) {
                Glide.with(mContext).load(fsTypeList.get(2).getImages()).error(R.color.c_press).into(iv_jinrong_icon3);
                tv_jinrong_dai3.setText(fsTypeList.get(2).getTitle());
                tv_jinrong_shuoming3.setText(fsTypeList.get(2).getSubtitle());
                ll_jinrong_tab3.setOnClickListener(getClickListener(fsTypeList.get(2).getTypeid()+"" ,fsTypeList.get(2).getTitle()));
            }
            if (obj.getFs_type_list().size() > 3) {
                Glide.with(mContext).load(fsTypeList.get(3).getImages()).error(R.color.c_press).into(iv_jinrong_icon4);
                tv_jinrong_dai4.setText(fsTypeList.get(3).getTitle());
                tv_jinrong_shuoming4.setText(fsTypeList.get(3).getSubtitle());
                ll_jinrong_tab4.setOnClickListener(getClickListener(fsTypeList.get(3).getTypeid()+"" ,fsTypeList.get(3).getTitle()));
            }


        }

    }

    @NonNull
    private MyOnClickListener getClickListener(final String typeId,final String title) {
        return new MyOnClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                Intent intent=new Intent();
                intent.putExtra(Constant.IParam.typeId,typeId);
                intent.putExtra(Constant.IParam.title,title);
                STActivity(intent,XinYongDaiActivity.class);
            }
        };
    }
    @Override
    protected void onViewClick(View v) {

    }

    @Override
    public void onStop() {
        super.onStop();
        if (bn_home != null && bannerList != null) {
            bn_home.stopAutoPlay();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (bn_home != null && bannerList != null) {
            bn_home.startAutoPlay();
        }
    }
}
