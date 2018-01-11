package com.sk.sqhk.module.my.activity;

import android.view.View;
import android.widget.TextView;

import com.github.customview.MyTextView;
import com.library.base.MyCallBack;
import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.module.my.network.ApiRequest;
import com.sk.sqhk.module.my.network.response.MessageDetailObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/20.
 */

public class TongZhiActivity extends BaseActivity {

    @BindView(R.id.tv_xitong_tongzhi_date)
    MyTextView tv_xitong_tongzhi_date;
    @BindView(R.id.tv_xitong_tongzhi_title)
    TextView tv_xitong_tongzhi_title;
    @BindView(R.id.tv_xitong_tongzhi_time)
    TextView tv_xitong_tongzhi_time;
    @BindView(R.id.tv_xitong_tongzhi_content)
    TextView tv_xitong_tongzhi_content;
    private String messageId;

    @Override
    protected int getContentView() {
        setAppTitle("系统通知");
        return R.layout.act_xitong_tong_zhi;
    }

    @Override
    protected void initView() {
        messageId = getIntent().getStringExtra(Constant.IParam.messageId);

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
        map.put("news_id", messageId);
        map.put("sign", getSign(map));
        ApiRequest.getMessageDetail(map, new MyCallBack<MessageDetailObj>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(MessageDetailObj obj) {
                tv_xitong_tongzhi_date.setText(obj.getAdd_time());
                tv_xitong_tongzhi_title.setText(obj.getTitle());
                tv_xitong_tongzhi_time.setText(obj.getAdd_time());
                tv_xitong_tongzhi_content.setText(obj.getContent());
            }
        });

    }

    @Override
    protected void onViewClick(View v) {

    }
}
