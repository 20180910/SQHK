package com.sk.sqhk.module.home.network.response;

import com.library.base.BaseObj;

import java.util.List;

/**
 * Created by Administrator on 2018/1/22.
 */

public class JiSuHuanKuanPlanObj extends BaseObj {



    /**
     * cardId : 482
     * fee : 400
     * feeRate : 0.006
     * plan : [{"day":"2018-01-24","expenseCount":1,"refundCount":1,"totalRefund":5850,"totalExpense":5986,"expense":[{"expenseMoney":5950,"expenseFee":5986,"expenseTimeStamp":"2018-01-24 06:02:00"}],"refund":[{"refundMoney":5950,"refundAcMoney":5850,"refundTimeStamp":"2018-01-24 06:04:00"}]},{"day":"2018-04-25","expenseCount":1,"refundCount":1,"totalRefund":5850,"totalExpense":5986,"expense":[{"expenseMoney":5950,"expenseFee":5986,"expenseTimeStamp":"2018-04-25 18:02:00"}],"refund":[{"refundMoney":5950,"refundAcMoney":5850,"refundTimeStamp":"2018-04-25 18:04:00"}]},{"day":"2018-07-26","expenseCount":1,"refundCount":1,"totalRefund":5850,"totalExpense":5986,"expense":[{"expenseMoney":5950,"expenseFee":5986,"expenseTimeStamp":"2018-07-26 12:02:00"}],"refund":[{"refundMoney":5950,"refundAcMoney":5850,"refundTimeStamp":"2018-07-26 12:04:00"}]},{"day":"2018-10-26","expenseCount":1,"refundCount":1,"totalRefund":5850,"totalExpense":5986,"expense":[{"expenseMoney":5950,"expenseFee":5986,"expenseTimeStamp":"2018-10-26 06:02:00"}],"refund":[{"refundMoney":5950,"refundAcMoney":5850,"refundTimeStamp":"2018-10-26 06:04:00"}]}]
     */

    private int cardId;
    private int fee;
    private double feeRate;
    private List<PlanBean> plan;



    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public double getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(double feeRate) {
        this.feeRate = feeRate;
    }

    public List<PlanBean> getPlan() {
        return plan;
    }

    public void setPlan(List<PlanBean> plan) {
        this.plan = plan;
    }


    public static class PlanBean {
        /**
         * day : 2018-01-24
         * expenseCount : 1
         * refundCount : 1
         * totalRefund : 5850
         * totalExpense : 5986
         * expense : [{"expenseMoney":5950,"expenseFee":5986,"expenseTimeStamp":"2018-01-24 06:02:00"}]
         * refund : [{"refundMoney":5950,"refundAcMoney":5850,"refundTimeStamp":"2018-01-24 06:04:00"}]
         */

        private String day;
        private int expenseCount;
        private int refundCount;
        private double totalRefund;
        private double totalExpense;
        private List<ExpenseBean> expense;
        private List<RefundBean> refund;

        private boolean zhanKai;

        public boolean isZhanKai() {
            return zhanKai;
        }

        public void setZhanKai(boolean zhanKai) {
            this.zhanKai = zhanKai;
        }
        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public int getExpenseCount() {
            return expenseCount;
        }

        public void setExpenseCount(int expenseCount) {
            this.expenseCount = expenseCount;
        }

        public int getRefundCount() {
            return refundCount;
        }

        public void setRefundCount(int refundCount) {
            this.refundCount = refundCount;
        }

        public double getTotalRefund() {
            return totalRefund;
        }

        public void setTotalRefund(double totalRefund) {
            this.totalRefund = totalRefund;
        }

        public double getTotalExpense() {
            return totalExpense;
        }

        public void setTotalExpense(double totalExpense) {
            this.totalExpense = totalExpense;
        }

        public List<ExpenseBean> getExpense() {
            return expense;
        }

        public void setExpense(List<ExpenseBean> expense) {
            this.expense = expense;
        }

        public List<RefundBean> getRefund() {
            return refund;
        }

        public void setRefund(List<RefundBean> refund) {
            this.refund = refund;
        }

        public static class ExpenseBean {
            /**
             * expenseMoney : 5950
             * expenseFee : 5986
             * expenseTimeStamp : 2018-01-24 06:02:00
             */

            private double expenseMoney;
            private double expenseFee;
            private String expenseTimeStamp;

            public double getExpenseMoney() {
                return expenseMoney;
            }

            public void setExpenseMoney(double expenseMoney) {
                this.expenseMoney = expenseMoney;
            }

            public double getExpenseFee() {
                return expenseFee;
            }

            public void setExpenseFee(double expenseFee) {
                this.expenseFee = expenseFee;
            }

            public String getExpenseTimeStamp() {
                return expenseTimeStamp;
            }

            public void setExpenseTimeStamp(String expenseTimeStamp) {
                this.expenseTimeStamp = expenseTimeStamp;
            }
        }

        public static class RefundBean {
            /**
             * refundMoney : 5950
             * refundAcMoney : 5850
             * refundTimeStamp : 2018-01-24 06:04:00
             */

            private double refundMoney;
            private double refundAcMoney;
            private String refundTimeStamp;

            public double getRefundMoney() {
                return refundMoney;
            }

            public void setRefundMoney(double refundMoney) {
                this.refundMoney = refundMoney;
            }

            public double getRefundAcMoney() {
                return refundAcMoney;
            }

            public void setRefundAcMoney(double refundAcMoney) {
                this.refundAcMoney = refundAcMoney;
            }

            public String getRefundTimeStamp() {
                return refundTimeStamp;
            }

            public void setRefundTimeStamp(String refundTimeStamp) {
                this.refundTimeStamp = refundTimeStamp;
            }
        }
    }
}
