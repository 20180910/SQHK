package com.sk.sqhk.module.home.network.response;

import com.library.base.BaseObj;

import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class XinYongDaiObj extends BaseObj {
    private List<HotCreditLoanBean> hot_credit_loan;

    public List<HotCreditLoanBean> getHot_credit_loan() {
        return hot_credit_loan;
    }

    public void setHot_credit_loan(List<HotCreditLoanBean> hot_credit_loan) {
        this.hot_credit_loan = hot_credit_loan;
    }

    public static class HotCreditLoanBean {
        /**
         * credit_id : 5
         * title : 测试
         * image_url : http://121.40.186.118:1145/upload/201801/11/201801111335387168.jpg
         * loan_amount : 2000-10000
         * daily_rate : 0.002
         * applications : 120
         */

        private int credit_id;
        private String title;
        private String image_url;
        private String loan_amount;
        private double daily_rate;
        private int applications;

        public int getCredit_id() {
            return credit_id;
        }

        public void setCredit_id(int credit_id) {
            this.credit_id = credit_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getLoan_amount() {
            return loan_amount;
        }

        public void setLoan_amount(String loan_amount) {
            this.loan_amount = loan_amount;
        }

        public double getDaily_rate() {
            return daily_rate;
        }

        public void setDaily_rate(double daily_rate) {
            this.daily_rate = daily_rate;
        }

        public int getApplications() {
            return applications;
        }

        public void setApplications(int applications) {
            this.applications = applications;
        }
    }
}
