package com.sk.sqhk.module.home.network.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/23.
 */

public class SelectXinYongCardObj  implements Serializable{
    /**
     * id : 482
     * userId : 593
     * status : 1
     * cardNo : 6259********2877
     * cardType : 1
     * cardCcv : 461
     * expireYear : 25
     * expireMonth : 08
     * bankId : 4
     * bankName : 建设银行
     * bankAbbr : CCB
     * bankColor : #018ffd
     * phone : 18117352720
     * billDate : 4
     * paymentDate : 8
     * planStatus : 1
     */

    private int id;
    private int userId;
    private int status;
    private String cardNo;
    private int cardType;
    private String cardCcv;
    private String expireYear;
    private String expireMonth;
    private int bankId;
    private String bankName;
    private String bankAbbr;
    private String bankColor;
    private String phone;
    private int billDate;
    private int paymentDate;
    private int planStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public String getCardCcv() {
        return cardCcv;
    }

    public void setCardCcv(String cardCcv) {
        this.cardCcv = cardCcv;
    }

    public String getExpireYear() {
        return expireYear;
    }

    public void setExpireYear(String expireYear) {
        this.expireYear = expireYear;
    }

    public String getExpireMonth() {
        return expireMonth;
    }

    public void setExpireMonth(String expireMonth) {
        this.expireMonth = expireMonth;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAbbr() {
        return bankAbbr;
    }

    public void setBankAbbr(String bankAbbr) {
        this.bankAbbr = bankAbbr;
    }

    public String getBankColor() {
        return bankColor;
    }

    public void setBankColor(String bankColor) {
        this.bankColor = bankColor;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getBillDate() {
        return billDate;
    }

    public void setBillDate(int billDate) {
        this.billDate = billDate;
    }

    public int getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(int paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(int planStatus) {
        this.planStatus = planStatus;
    }
}
