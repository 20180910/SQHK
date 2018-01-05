package com.library.base.tools;

/**
 * Created by Administrator on 2018/1/5.
 */

public class ZhengZeUtils {
    public static boolean isShuZiAndZiMu(String str){
        String reg = "(?i)^(?!([a-z]*|\\d*)$)[a-z\\d]+$";
        return str.matches(reg);
    }
}
