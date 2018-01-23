package com.sk.sqhk.module.select.network.response;

import com.library.base.BaseObj;

import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */

public class HuanKuanPlanDuoTianDetailObj extends BaseObj {
    /**
     * plan_id : {"planId":482,"day":"2018-01-16 11:21:02","amount":846100,"count":3,"fee":5925,"list":[{"day":"2018-01-17","refundAmount":270752,"status":2},{"day":"2018-01-19","refundAmount":296135,"status":0},{"day":"2018-01-21","refundAmount":279213,"status":0}]}
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
         * planId : 482
         * day : 2018-01-16 11:21:02
         * amount : 846100
         * count : 3
         * fee : 5925
         * list : [{"day":"2018-01-17","refundAmount":270752,"status":2},{"day":"2018-01-19","refundAmount":296135,"status":0},{"day":"2018-01-21","refundAmount":279213,"status":0}]
         */

        private int planId;
        private String day;
        private long amount;
        private int count;
        private int fee;
        private List<ListBean> list;

        public int getPlanId() {
            return planId;
        }

        public void setPlanId(int planId) {
            this.planId = planId;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public long getAmount() {
            return amount;
        }

        public void setAmount(long amount) {
            this.amount = amount;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * day : 2018-01-17
             * refundAmount : 270752
             * status : 2
             */

            private String day;
            private long refundAmount;
            private int status;

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public long getRefundAmount() {
                return refundAmount;
            }

            public void setRefundAmount(long refundAmount) {
                this.refundAmount = refundAmount;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
