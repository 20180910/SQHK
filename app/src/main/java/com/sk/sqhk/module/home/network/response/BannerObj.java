package com.sk.sqhk.module.home.network.response;

import com.library.base.BaseObj;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class BannerObj extends BaseObj {
    private List<RoastingListBean> roasting_list;

    public List<RoastingListBean> getRoasting_list() {
        return roasting_list;
    }

    public void setRoasting_list(List<RoastingListBean> roasting_list) {
        this.roasting_list = roasting_list;
    }

    public static class RoastingListBean {
        /**
         * img_url : http://121.40.186.118:1145/upload/123.png
         * merchant_id : 0
         */

        private String img_url;
        private int merchant_id;

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public int getMerchant_id() {
            return merchant_id;
        }

        public void setMerchant_id(int merchant_id) {
            this.merchant_id = merchant_id;
        }
    }
}
