package com.library.base;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/20.
 */

public class BaseObj implements Serializable {
    private String msg;
    private String SMSCode;//获取短信验证码
    private String url;//上传文件返回路径
    private String img;//上传图片返回路径

    public String getUrl() {
        return url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getSMSCode() {
        return SMSCode;
    }
    public void setSMSCode(String SMSCode) {
        this.SMSCode = SMSCode;
    }
}
