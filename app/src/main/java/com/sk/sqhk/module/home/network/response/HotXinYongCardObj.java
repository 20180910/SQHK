package com.sk.sqhk.module.home.network.response;

/**
 * Created by Administrator on 2018/1/26.
 */

public class HotXinYongCardObj {
    /**
     * id : 5
     * title : 建设银行标准信用卡四大金卡
     * img_url : http://121.40.186.118:1145/upload/201801/26/201801260927095934.png
     * zhaiyao : 店内周六-周四想5%刷卡金?
     * link : http://creditcard.ccb.com/cn/creditcard/apply/apply.html
     * add_time : 2018-01-26 09:27:10
     */

    private int id;
    private String title;
    private String img_url;
    private String zhaiyao;
    private String link;
    private String add_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getZhaiyao() {
        return zhaiyao;
    }

    public void setZhaiyao(String zhaiyao) {
        this.zhaiyao = zhaiyao;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }
}
