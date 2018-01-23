package com.sk.sqhk.module.home.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.github.androidtools.DateUtils;
import com.github.androidtools.PhoneUtils;
import com.github.customview.MyCheckBox;
import com.github.customview.MyEditText;
import com.github.customview.MyTextView;
import com.library.base.BaseObj;
import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.base.MyCallBack;
import com.sk.sqhk.module.home.bean.HuanKuanPlanBean;
import com.sk.sqhk.module.home.network.ApiRequest;
import com.sk.sqhk.module.my.network.response.MyAllBankObj;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    String cardId;

    Date startDate;
    Date endDate ;

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

    @OnClick({R.id.tv_jisuhuankuan_xinyongka, R.id.tv_jisuhuankuan_commit, R.id.cb_jisu_huankuan, R.id.tv_jisuhuankuan_starttime
            , R.id.tv_jisuhuankuan_endtime})
    public void onClick(View view) {
        String money;
        String bishu;
        String startTime;
        String endTime;
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_jisuhuankuan_starttime:
                PhoneUtils.hiddenKeyBoard(mContext,et_jisuhuankuan_bishu);
                selectDate(0);
                break;
            case R.id.tv_jisuhuankuan_endtime:
                PhoneUtils.hiddenKeyBoard(mContext,et_jisuhuankuan_bishu);
                selectDate(1);
                break;
            case R.id.tv_jisuhuankuan_xinyongka:
                  intent = new Intent();
                intent.putExtra(Constant.IParam.isJiSuHuanKuan, true);
                STActivityForResult(intent, SelectBankTypeActivity.class, 100);
                break;
            case R.id.tv_jisuhuankuan_commit:
                money = getSStr(et_jisuhuankuan_money);
                bishu = getSStr(et_jisuhuankuan_bishu);
                startTime = getSStr(tv_jisuhuankuan_starttime);
                endTime = getSStr(tv_jisuhuankuan_endtime);
                if (TextUtils.isEmpty(cardId)) {
                    showMsg("请选择信用卡");
                    return;
                } else if (TextUtils.isEmpty(money)) {
                    showMsg("请输入还款金额");
                    return;
                } else if (TextUtils.isEmpty(bishu)) {
                    showMsg("请输入笔数");
                    return;
                } else if (TextUtils.isEmpty(startTime)) {
                    showMsg("请选择还款开始时间");
                    return;
                } else if (TextUtils.isEmpty(endTime)) {
                    showMsg("请选择还款结束时间");
                    return;
                } else if (differentDaysByMillisecond(startDate,endDate)<=0) {
                    showMsg("还款开始时间必须小于结束时间");
                    return;
                }else if(Integer.parseInt(getSStr(et_jisuhuankuan_bishu))>differentDaysByMillisecond(startDate,endDate)){
                    showMsg("金额笔数不能超过"+differentDaysByMillisecond(startDate,endDate)+"笔");
                    return;
                }
                jiSuHuanKuanCommit(money,bishu);
                break;
            case R.id.cb_jisu_huankuan:
                  money = getSStr(et_jisuhuankuan_money);
                  bishu = getSStr(et_jisuhuankuan_bishu);
                  startTime = getSStr(tv_jisuhuankuan_starttime);
                  endTime = getSStr(tv_jisuhuankuan_endtime);
                if (TextUtils.isEmpty(cardId)) {
                    showMsg("请选择信用卡");
                    return;
                } else if (TextUtils.isEmpty(money)) {
                    showMsg("请输入还款金额");
                    return;
                } else if (TextUtils.isEmpty(bishu)) {
                    showMsg("请输入笔数");
                    return;
                } else if (TextUtils.isEmpty(startTime)) {
                    showMsg("请选择还款开始时间");
                    return;
                } else if (TextUtils.isEmpty(endTime)) {
                    showMsg("请选择还款结束时间");
                    return;
                } else if (differentDaysByMillisecond(startDate,endDate)<=0) {
                    showMsg("还款开始时间必须小于结束时间");
                    return;
                }else if(Integer.parseInt(getSStr(et_jisuhuankuan_bishu))>differentDaysByMillisecond(startDate,endDate)){
                    showMsg("金额笔数不能超过"+differentDaysByMillisecond(startDate,endDate)+"笔");
                    return;
                }
                HuanKuanPlanBean bean=new HuanKuanPlanBean();
                bean.setCard_id(cardId);
                bean.setMoney(money);
                bean.setBi_num(bishu);
                bean.setBegintime(startTime);
                bean.setEndtime(endTime);
                intent=new Intent();
                intent.putExtra(Constant.IParam.huanKuanBean,bean);
                STActivity(intent,HuanKuanJiHuaActivity.class);
                break;
        }
    }

    public int differentDaysByMillisecond(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    private void selectDate(int index) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.YEAR, 30);
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.add(Calendar.DAY_OF_MONTH, 1);
        TimePickerView pvTime = new TimePickerView.Builder(mContext, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                String dateStr = DateUtils.dateToString(date);
                if (index == 0) {
                    startDate = date ;
                    tv_jisuhuankuan_starttime.setText(dateStr);
                } else {
                    endDate = date ;
                    tv_jisuhuankuan_endtime.setText(dateStr);
                }
                if(startDate!=null&&endDate!=null){
                    Log.i(TAG+"===","==="+differentDaysByMillisecond(startDate,endDate));
                }
               /* Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                String year=calendar.get(Calendar.YEAR)+"";
                String month=(calendar.get(Calendar.MONTH)+1)+"";
                Log.i(TAG+"===",year+"==="+month);*/
            }
        }).setRangDate(startCalendar, instance).setType(new boolean[]{true, true, true, false, false, false}).build();
        pvTime.show();
    }

    private void jiSuHuanKuanCommit(String money, String bishu) {
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", getUserId());
        map.put("card_id",cardId);
        map.put("begintime", DateUtils.dateToString(startDate));
        map.put("endtime", DateUtils.dateToString(endDate));
        map.put("bi_num",bishu);
        map.put("money", money);
        map.put("sign", getSign(map));
        ApiRequest.addJiSuHuanKuan(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 100:
                    MyAllBankObj.CreditCardListBean bean = (MyAllBankObj.CreditCardListBean) data.getSerializableExtra(Constant.IParam.xinYongKaDetail);
                    cardId = bean.getId() + "";
                    tv_jisuhuankuan_xinyongka.setText(bean.getBankName() + "(" + bean.getCardNo() + ")");
                    Log.i(TAG+"===","==="+cardId);
                    break;
            }
        }
    }
}
