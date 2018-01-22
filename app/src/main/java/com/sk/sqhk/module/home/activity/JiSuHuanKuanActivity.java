package com.sk.sqhk.module.home.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.github.customview.MyCheckBox;
import com.github.customview.MyEditText;
import com.github.customview.MyTextView;
import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/20.
 */

public class JiSuHuanKuanActivity extends BaseActivity {
    @BindView(R.id.tv_jisuhuankuan_xinyongka)
    TextView tv_jisuhuankuan_xinyongka;
    @BindView(R.id.et_jisuhuankuan_money)
    MyEditText et_jisuhuankuan_money;
    @BindView(R.id.et_jisuhuankuan_bishu)
    MyEditText et_jisuhuankuan_bishu;
    @BindView(R.id.tv_jisuhuankuan_starttime)
    TextView tv_jisuhuankuan_starttime;
    @BindView(R.id.tv_jisuhuankuan_endtime)
    TextView tv_jisuhuankuan_endtime;
    @BindView(R.id.tv_jisuhuankuan_commit)
    MyTextView tv_jisuhuankuan_commit;
    @BindView(R.id.cb_jisu_huankuan)
    MyCheckBox cb_jisu_huankuan;

    @Override
    protected int getContentView() {
        setAppTitle("急速还款");
        return R.layout.act_ji_su_huan_kuan;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }

    @OnClick({R.id.tv_jisuhuankuan_xinyongka, R.id.tv_jisuhuankuan_commit, R.id.cb_jisu_huankuan,R.id.tv_jisuhuankuan_starttime
            ,R.id.tv_jisuhuankuan_endtime})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_jisuhuankuan_starttime:
                break;
            case R.id.tv_jisuhuankuan_endtime:
                break;
            case R.id.tv_jisuhuankuan_xinyongka:
                Intent intent=new Intent();
                intent.putExtra(Constant.IParam.isJiSuHuanKuan,true);
                STActivityForResult(intent,SelectBankTypeActivity.class,100);
                break;
            case R.id.tv_jisuhuankuan_commit:
                break;
            case R.id.cb_jisu_huankuan:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case 100:

                break;
            }
        }
    }
}
