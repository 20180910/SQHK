package com.sk.sqhk.module.my.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.library.base.BaseObj;
import com.library.base.MyCallBack;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.module.my.network.ApiRequest;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/25.
 */

public class YiJianFanKuiActivity extends BaseActivity {
    @BindView(R.id.et_fankui_content)
    EditText et_fankui_content;

    @Override
    protected int getContentView() {
        setAppTitle("意见反馈");
        return R.layout.act_yijian_fankui;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_yijian_fankui_commit})
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.tv_yijian_fankui_commit:
                String content=getSStr(et_fankui_content);
                if(TextUtils.isEmpty(content)){
                    showMsg("请输入反馈内容");
                    return;
                }
                commitContent(content);
            break;
        }
    }

    private void commitContent(String content) {
        showLoading();
        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("content",content);
        map.put("sign",getSign(map));
        ApiRequest.yiJianFanKui(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                finish();
            }
        });

    }
}
