package com.sk.sqhk.module.home.activity;

import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.module.home.network.ApiRequest;
import com.sk.sqhk.module.home.network.response.DianZanObj;
import com.sk.sqhk.module.home.network.response.ZiXunDetailObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/20.
 */

public class WenZhangDetailActivity extends BaseActivity {

    @BindView(R.id.tv_zixun_detail_content)
    TextView tv_zixun_detail_content;
    @BindView(R.id.tv_zixun_detail_from)
    TextView tv_zixun_detail_from;
    @BindView(R.id.tv_zixun_detail_time)
    TextView tv_zixun_detail_time;
    @BindView(R.id.wv_zixun_detail)
    WebView wv_zixun_detail;
    @BindView(R.id.tv_zixun_detail_zan)
    TextView tv_zixun_detail_zan;
    @BindView(R.id.tv_zixun_detail_look)
    TextView tv_zixun_detail_look;

    private String ziXunId;

    @Override
    protected int getContentView() {
        setAppTitle("文章详情");
        return R.layout.act_wenzhang_detail;
    }

    @Override
    protected void initView() {
        ziXunId = getIntent().getStringExtra(Constant.IParam.ziXunId);
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
        map.put("information_id", ziXunId);
        map.put("sign", getSign(map));
        ApiRequest.getZiXunDetail(map, new MyCallBack<ZiXunDetailObj>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(ZiXunDetailObj obj) {
                tv_zixun_detail_content.setText(obj.getTitle());
                tv_zixun_detail_from.setText(obj.getAuthor());
                tv_zixun_detail_time.setText(obj.getAdd_time());

                initWebViewForContent(wv_zixun_detail, obj.getContent());

                tv_zixun_detail_zan.setText(obj.getThumbup_count() + "");
                tv_zixun_detail_look.setText(obj.getPage_view() + "");
            }
        });
    }

    @OnClick({R.id.tv_zixun_detail_zan})
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.tv_zixun_detail_zan:
                dianZan();
            break;
        }
    }

    private void dianZan() {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("information_id", ziXunId);
        map.put("sign",getSign(map));
        ApiRequest.ziXunDianZan(map, new MyCallBack<DianZanObj>(mContext) {
            @Override
            public void onSuccess(DianZanObj obj) {
                showMsg(obj.getMsg());
                tv_zixun_detail_zan.setText(obj.getThumbup_count() + "");
            }
        });

    }
}
