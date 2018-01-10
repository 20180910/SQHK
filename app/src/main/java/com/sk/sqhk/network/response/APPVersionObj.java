package com.sk.sqhk.network.response;

import com.library.base.BaseObj;

/**
 * Created by Administrator on 2018/1/10.
 */

public class APPVersionObj extends BaseObj {
    /**
     * version_code :
     * version_name : 1.1
     * version_url : http://121.40.186.118:6699/upload/app-release.apk
     */

    private String version_code;
    private String version_name;
    private String version_url;

    public String getVersion_code() {
        return version_code;
    }

    public void setVersion_code(String version_code) {
        this.version_code = version_code;
    }

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    public String getVersion_url() {
        return version_url;
    }

    public void setVersion_url(String version_url) {
        this.version_url = version_url;
    }
}
