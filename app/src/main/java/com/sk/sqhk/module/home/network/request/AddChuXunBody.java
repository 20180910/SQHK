package com.sk.sqhk.module.home.network.request;

/**
 * Created by Administrator on 2018/1/19.
 */

public class AddChuXunBody {
    /**
     * realname : sample string 1
     * bank_card_num : sample string 2
     * id_number : sample string 3
     * card_type : 4
     * opening_bank : sample string 5
     * bank_id : 6
     */

    private String realname;
    private String bank_card_num;
    private String id_number;
    private int card_type;
    private String opening_bank;
    private String bank_id;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getBank_card_num() {
        return bank_card_num;
    }

    public void setBank_card_num(String bank_card_num) {
        this.bank_card_num = bank_card_num;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public int getCard_type() {
        return card_type;
    }

    public void setCard_type(int card_type) {
        this.card_type = card_type;
    }

    public String getOpening_bank() {
        return opening_bank;
    }

    public void setOpening_bank(String opening_bank) {
        this.opening_bank = opening_bank;
    }

    public String getBank_id() {
        return bank_id;
    }

    public void setBank_id(String bank_id) {
        this.bank_id = bank_id;
    }
}
