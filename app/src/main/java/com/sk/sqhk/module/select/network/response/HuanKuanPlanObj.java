package com.sk.sqhk.module.select.network.response;

import com.library.base.BaseObj;

import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */

public class HuanKuanPlanObj extends BaseObj {
    /**
     * plan_id : {"pageNum":1,"pageSize":21,"size":1,"startRow":1,"endRow":1,"total":1,"pages":1,"list":[{"id":691,"no":"2018012300000744","creditChId":396,"amount":580782,"actualAmount":576696,"totalFee":4086,"count":6,"status":0,"feeRate":0.006,"fee":600,"billDate":1517673600000,"paymentDate":1518019200000,"createTime":"2018-01-23 11:07:55","updateTime":"2018-01-23 11:07:55","planDate":"2018-01-24,2018-07-25,2019-01-24,2019-07-26,2020-01-24,2020-07-25","platformId":3}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
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
         * pageNum : 1
         * pageSize : 21
         * size : 1
         * startRow : 1
         * endRow : 1
         * total : 1
         * pages : 1
         * list : [{"id":691,"no":"2018012300000744","creditChId":396,"amount":580782,"actualAmount":576696,"totalFee":4086,"count":6,"status":0,"feeRate":0.006,"fee":600,"billDate":1517673600000,"paymentDate":1518019200000,"createTime":"2018-01-23 11:07:55","updateTime":"2018-01-23 11:07:55","planDate":"2018-01-24,2018-07-25,2019-01-24,2019-07-26,2020-01-24,2020-07-25","platformId":3}]
         * prePage : 0
         * nextPage : 0
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         * firstPage : 1
         * lastPage : 1
         */

        private int pageNum;
        private int pageSize;
        private int size;
        private int startRow;
        private int endRow;
        private int total;
        private int pages;
        private int prePage;
        private int nextPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private int navigatePages;
        private int navigateFirstPage;
        private int navigateLastPage;
        private int firstPage;
        private int lastPage;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * id : 691
             * no : 2018012300000744
             * creditChId : 396
             * amount : 580782
             * actualAmount : 576696
             * totalFee : 4086
             * count : 6
             * status : 0
             * feeRate : 0.006
             * fee : 600
             * billDate : 1517673600000
             * paymentDate : 1518019200000
             * createTime : 2018-01-23 11:07:55
             * updateTime : 2018-01-23 11:07:55
             * planDate : 2018-01-24,2018-07-25,2019-01-24,2019-07-26,2020-01-24,2020-07-25
             * platformId : 3
             */

            private int id;
            private String no;
            private int creditChId;
            private int amount;
            private long actualAmount;
            private double totalFee;
            private int count;
            private int status;
            private double feeRate;
            private double fee;
            private long billDate;
            private long paymentDate;
            private String createTime;
            private String updateTime;
            private String planDate;
            private int platformId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNo() {
                return no;
            }

            public void setNo(String no) {
                this.no = no;
            }

            public int getCreditChId() {
                return creditChId;
            }

            public void setCreditChId(int creditChId) {
                this.creditChId = creditChId;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public long getActualAmount() {
                return actualAmount;
            }

            public void setActualAmount(long actualAmount) {
                this.actualAmount = actualAmount;
            }

            public double getTotalFee() {
                return totalFee;
            }

            public void setTotalFee(double totalFee) {
                this.totalFee = totalFee;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public double getFeeRate() {
                return feeRate;
            }

            public void setFeeRate(double feeRate) {
                this.feeRate = feeRate;
            }

            public double getFee() {
                return fee;
            }

            public void setFee(double fee) {
                this.fee = fee;
            }

            public long getBillDate() {
                return billDate;
            }

            public void setBillDate(long billDate) {
                this.billDate = billDate;
            }

            public long getPaymentDate() {
                return paymentDate;
            }

            public void setPaymentDate(long paymentDate) {
                this.paymentDate = paymentDate;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getPlanDate() {
                return planDate;
            }

            public void setPlanDate(String planDate) {
                this.planDate = planDate;
            }

            public int getPlatformId() {
                return platformId;
            }

            public void setPlatformId(int platformId) {
                this.platformId = platformId;
            }
        }
    }
}
