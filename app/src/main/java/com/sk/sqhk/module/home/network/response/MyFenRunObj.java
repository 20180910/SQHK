package com.sk.sqhk.module.home.network.response;

import com.library.base.BaseObj;

import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class MyFenRunObj extends BaseObj {
    /**
     * commission : 0.0
     * history_commission : 0.0
     * yesterday_commission : 0
     * today_commission : 0
     * cash_withdrawal_detail_list : [{"remark":"收益-转出到余额","value":-50,"add_time":"2018-1-15 11:8"}]
     */

    private double commission;
    private double history_commission;
    private double yesterday_commission;
    private double today_commission;
    private List<CashWithdrawalDetailListBean> cash_withdrawal_detail_list;

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public double getHistory_commission() {
        return history_commission;
    }

    public void setHistory_commission(double history_commission) {
        this.history_commission = history_commission;
    }

    public double getYesterday_commission() {
        return yesterday_commission;
    }

    public void setYesterday_commission(double yesterday_commission) {
        this.yesterday_commission = yesterday_commission;
    }

    public double getToday_commission() {
        return today_commission;
    }

    public void setToday_commission(double today_commission) {
        this.today_commission = today_commission;
    }

    public List<CashWithdrawalDetailListBean> getCash_withdrawal_detail_list() {
        return cash_withdrawal_detail_list;
    }

    public void setCash_withdrawal_detail_list(List<CashWithdrawalDetailListBean> cash_withdrawal_detail_list) {
        this.cash_withdrawal_detail_list = cash_withdrawal_detail_list;
    }

    public static class CashWithdrawalDetailListBean {
        /**
         * remark : 收益-转出到余额
         * value : -50.0
         * add_time : 2018-1-15 11:8
         */

        private String remark;
        private double value;
        private String add_time;

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }
    }
}
