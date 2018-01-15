package com.sk.sqhk.module.home.activity;

import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.customview.MyImageView;
import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.module.home.network.ApiRequest;
import com.sk.sqhk.module.home.network.response.DaiKuanDetailObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/21.
 */

public class DaiKuanDetailActivity extends BaseActivity {

    @BindView(R.id.iv_daikuan_detail)
    MyImageView iv_daikuan_detail;
    @BindView(R.id.tv_daikuan_detail_title)
    TextView tv_daikuan_detail_title;
    @BindView(R.id.tv_daikuan_detail_people)
    TextView tv_daikuan_detail_people;
    @BindView(R.id.tv_daikuan_detail_edu)
    TextView tv_daikuan_detail_edu;
    @BindView(R.id.tv_daikuan_detail_qx)
    TextView tv_daikuan_detail_qx;
    @BindView(R.id.tv_daikuan_detail_lilv)
    TextView tv_daikuan_detail_lilv;
    @BindView(R.id.tv_daikuan_detail_tj)
    TextView tv_daikuan_detail_tj;
    @BindView(R.id.tv_daikuan_detail_sm1)
    TextView tv_daikuan_detail_sm1;
    @BindView(R.id.tv_daikuan_detail_sm2)
    TextView tv_daikuan_detail_sm2;
    private String creditId;

    @Override
    protected int getContentView() {
        setAppTitle("贷款详情");
        return R.layout.act_daikuan_detail;
    }

    @Override
    protected void initView() {
        creditId = getIntent().getStringExtra(Constant.IParam.creditId);

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
        map.put("credit_id", creditId);
        map.put("sign", getSign(map));
        ApiRequest.getDaiKuanDetail(map, new MyCallBack<DaiKuanDetailObj>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(DaiKuanDetailObj obj) {
                Glide.with(mContext).load(obj.getImage_url()).error(R.color.c_press).into(iv_daikuan_detail);
                tv_daikuan_detail_title.setText(obj.getTitle());
                tv_daikuan_detail_people.setText("累计"+obj.getApplications()+"人贷款成功");
                tv_daikuan_detail_edu.setText(obj.getLoan_amount()+"元");
                tv_daikuan_detail_qx.setText(obj.getTime_limit_range()+"天");
                tv_daikuan_detail_lilv.setText(obj.getDaily_rate()+"%");
                tv_daikuan_detail_tj.setText(obj.getApplication_requirements()+"%");
                tv_daikuan_detail_sm1.setText(obj.getAudit_instructions()+"%");

            }
        });

    }

    @Override
    protected void onViewClick(View v) {

    }

    @OnClick(R.id.tv_daikuan_shenqing)
    public void onClick() {
    }
}
