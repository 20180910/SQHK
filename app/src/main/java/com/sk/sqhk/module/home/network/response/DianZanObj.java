package com.sk.sqhk.module.home.network.response;

import com.library.base.BaseObj;

/**
 * Created by Administrator on 2018/1/12.
 */

public class DianZanObj extends BaseObj {
    /**
     * is_thumbup : 1
     * thumbup_count : 3is_thumbup是否点赞(0否 1点过赞),thumbup_count点赞数
     */

    private int is_thumbup;
    private int thumbup_count;

    public int getIs_thumbup() {
        return is_thumbup;
    }

    public void setIs_thumbup(int is_thumbup) {
        this.is_thumbup = is_thumbup;
    }

    public int getThumbup_count() {
        return thumbup_count;
    }

    public void setThumbup_count(int thumbup_count) {
        this.thumbup_count = thumbup_count;
    }
}
