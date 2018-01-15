package com.sk.sqhk.module.home.network.response;

import com.library.base.BaseObj;

/**
 * Created by Administrator on 2018/1/15.
 */

public class DaiKuanDetailObj extends BaseObj {
    /**
     * credit_id : 2
     * title : 急速借-你我金融
     * image_url : http://121.40.186.118:1145/upload/2222.png
     * loan_amount : 500-100000
     * daily_rate : 0.052
     * time_limit_range : 14
     * applications : 2430
     * audit_instructions : 身份证基本信息/三步审核,极速审核
     * application_requirements : 芝麻信用分700以上
     * add_time : 2017-12-07 18:31:49
     */

    private int credit_id;
    private String title;
    private String image_url;
    private String loan_amount;
    private double daily_rate;
    private int time_limit_range;
    private int applications;
    private String audit_instructions;
    private String application_requirements;
    private String add_time;

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

    public int getTime_limit_range() {
        return time_limit_range;
    }

    public void setTime_limit_range(int time_limit_range) {
        this.time_limit_range = time_limit_range;
    }

    public int getApplications() {
        return applications;
    }

    public void setApplications(int applications) {
        this.applications = applications;
    }

    public String getAudit_instructions() {
        return audit_instructions;
    }

    public void setAudit_instructions(String audit_instructions) {
        this.audit_instructions = audit_instructions;
    }

    public String getApplication_requirements() {
        return application_requirements;
    }

    public void setApplication_requirements(String application_requirements) {
        this.application_requirements = application_requirements;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }
}
