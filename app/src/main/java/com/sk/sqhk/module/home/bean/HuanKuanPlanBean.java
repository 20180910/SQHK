package com.sk.sqhk.module.home.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/23.
 */

public class HuanKuanPlanBean implements Serializable{
    private String card_id;
    private String begintime;
    private String endtime;
    private String bi_num;
    private String money;

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getBi_num() {
        return bi_num;
    }

    public void setBi_num(String bi_num) {
        this.bi_num = bi_num;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
