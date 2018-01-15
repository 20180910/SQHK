package com.sk.sqhk.module.home.network.response;

import com.library.base.BaseObj;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class JinRongChaoShiObj extends BaseObj {
    private List<String> financial_supermarket_image;
    private List<FsTypeListBean> fs_type_list;
    private List<HotCreditLoanBean> hot_credit_loan;

    public List<String> getFinancial_supermarket_image() {
        return financial_supermarket_image;
    }

    public void setFinancial_supermarket_image(List<String> financial_supermarket_image) {
        this.financial_supermarket_image = financial_supermarket_image;
    }

    public List<FsTypeListBean> getFs_type_list() {
        return fs_type_list;
    }

    public void setFs_type_list(List<FsTypeListBean> fs_type_list) {
        this.fs_type_list = fs_type_list;
    }

    public List<HotCreditLoanBean> getHot_credit_loan() {
        return hot_credit_loan;
    }

    public void setHot_credit_loan(List<HotCreditLoanBean> hot_credit_loan) {
        this.hot_credit_loan = hot_credit_loan;
    }

    public static class FsTypeListBean implements Serializable {
        /**
         * typeid : 1
         * images : http://121.40.186.118:1145/upload/111.png
         * title : 急速小微贷
         * subtitle : 小于2000 放款快
         */

        private int typeid;
        private String images;
        private String title;
        private String subtitle;

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }
    }

    public static class HotCreditLoanBean implements Serializable{
        /**
         * credit_id : 4
         * title : 急速借-你我金融
         * image_url : http://121.40.186.118:1145/upload/4444.png
         * loan_amount : 500-100000
         * daily_rate : 0.052
         * applications : 4457
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
