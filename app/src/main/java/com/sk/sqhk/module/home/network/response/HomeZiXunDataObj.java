package com.sk.sqhk.module.home.network.response;

/**
 * Created by Administrator on 2018/1/12.
 */

public class HomeZiXunDataObj  {
    /**
     * information_id : 3
     * title : 农业部第五督导组赴广东、广西两省（区）开展《条例》落实情况和农药大检查专项督查
     * image_url : http://121.40.186.118:1145/upload/zixun.png
     * author : 世界农化网世界农化网世界农化网世界农化网世界农化网世界农化网世界农化网
     * add_time : 2017/11/30
     */

    private int information_id;
    private String title;
    private String image_url;
    private String author;
    private String add_time;

    public int getInformation_id() {
        return information_id;
    }

    public void setInformation_id(int information_id) {
        this.information_id = information_id;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }
}
