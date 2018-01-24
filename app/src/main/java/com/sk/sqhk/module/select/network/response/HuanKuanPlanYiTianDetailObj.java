package com.sk.sqhk.module.select.network.response;

import com.library.base.BaseObj;

import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */

public class HuanKuanPlanYiTianDetailObj extends BaseObj {

    /**
     * plan_id : {"expenseAmount":100705,"expenseCount":1,"refundAmount":100000,"refundCount":1,"expenseList":[{"detailId":8793,"amount":100705,"type":0,"status":2,"day":"2018-01-23 06:30"}],"refundList":[{"detailId":8794,"amount":100000,"type":1,"status":2,"day":"2018-01-23 06:32"}]}
     */

    private PlanIdBean plan_id;

    public PlanIdBean getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(PlanIdBean plan_id) {
        this.plan_id = plan_id;
    }

    public static class PlanIdBean {
        /**
         * expenseAmount : 100705
         * expenseCount : 1
         * refundAmount : 100000
         * refundCount : 1
         * expenseList : [{"detailId":8793,"amount":100705,"type":0,"status":2,"day":"2018-01-23 06:30"}]
         * refundList : [{"detailId":8794,"amount":100000,"type":1,"status":2,"day":"2018-01-23 06:32"}]
         */

        private long expenseAmount;
        private int expenseCount;
        private long refundAmount;
        private int refundCount;
        private List<ExpenseListBean> expenseList;
        private List<RefundListBean> refundList;

        public long getExpenseAmount() {
            return expenseAmount;
        }

        public void setExpenseAmount(long expenseAmount) {
            this.expenseAmount = expenseAmount;
        }

        public int getExpenseCount() {
            return expenseCount;
        }

        public void setExpenseCount(int expenseCount) {
            this.expenseCount = expenseCount;
        }

        public long getRefundAmount() {
            return refundAmount;
        }

        public void setRefundAmount(long refundAmount) {
            this.refundAmount = refundAmount;
        }

        public int getRefundCount() {
            return refundCount;
        }

        public void setRefundCount(int refundCount) {
            this.refundCount = refundCount;
        }

        public List<ExpenseListBean> getExpenseList() {
            return expenseList;
        }

        public void setExpenseList(List<ExpenseListBean> expenseList) {
            this.expenseList = expenseList;
        }

        public List<RefundListBean> getRefundList() {
            return refundList;
        }

        public void setRefundList(List<RefundListBean> refundList) {
            this.refundList = refundList;
        }

        public static class ExpenseListBean {
            /**
             * detailId : 8793
             * amount : 100705
             * type : 0
             * status : 2
             * day : 2018-01-23 06:30
             */

            private int detailId;
            private long amount;
            private int type;
            private int status;
            private String day;

            private String message;

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }
            public int getDetailId() {
                return detailId;
            }

            public void setDetailId(int detailId) {
                this.detailId = detailId;
            }

            public long getAmount() {
                return amount;
            }

            public void setAmount(long amount) {
                this.amount = amount;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }
        }

        public static class RefundListBean {
            /**
             * detailId : 8794
             * amount : 100000
             * type : 1
             * status : 2
             * day : 2018-01-23 06:32
             */

            private int detailId;
            private long amount;
            private int type;
            private int status;
            private String day;
            private String message;

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public int getDetailId() {
                return detailId;
            }

            public void setDetailId(int detailId) {
                this.detailId = detailId;
            }

            public long getAmount() {
                return amount;
            }

            public void setAmount(long amount) {
                this.amount = amount;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }
        }
    }
}
