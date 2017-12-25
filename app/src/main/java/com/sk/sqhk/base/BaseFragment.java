package com.sk.sqhk.base;

import com.github.androidtools.SPUtils;
import com.library.base.MyBaseFragment;
import com.sk.sqhk.Config;
import com.sk.sqhk.GetSign;

/**
 * Created by Administrator on 2017/12/18.
 */

public abstract class BaseFragment extends MyBaseFragment {
    protected String getUserId() {
        return SPUtils.getString(mContext, Config.user_id, "0");
    }
    public boolean noLogin(){
        if("0".equals(getUserId())){
            return true;
        }else{
            return false;
        }
    }
    protected String getSign() {
        return getSign("user_id", getUserId());
    }

    protected String getSign(String key, String value) {
        return GetSign.getSign(key, value);
    }

}
