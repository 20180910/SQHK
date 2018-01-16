package com.sk.sqhk;

import android.text.TextUtils;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void afds() throws Exception {
        boolean a1 = TextUtils.equals(null, "a");
        boolean a2 =TextUtils.equals(null,null);
        boolean a3 =TextUtils.equals("a",null);
        boolean a4 =TextUtils.equals("a","a");
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3);
        System.out.println(a4);
    }
    @Test
    public void asfd() throws Exception {
        String reg = "(?i)^(?!([a-z]*|\\d*)$)[a-z\\d]+$";
        String str = "asfd";
        System.out.println(str.matches(reg));
    }
    @Test
    public void asfdd() throws Exception {
        String reg = "^[A-Za-z0-9]+$";
        String str = "13d12ddd2$";
        System.out.println(str.matches(reg));
        System.out.println(isMobile("15312312312"));
    }
    public static boolean isMobile(String str){
        String reg = "^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$";
        return str.matches(reg.trim());
    }

    @Test
    public void calculateMethodNum() throws Exception {
        Method[] declaredMethods1 = com.sk.sqhk.module.select.network.ApiRequest.class.getDeclaredMethods();
        Method[] declaredMethods2 = com.sk.sqhk.module.home.network.ApiRequest.class.getDeclaredMethods();
        Method[] declaredMethods3 = com.sk.sqhk.module.my.network.ApiRequest.class.getDeclaredMethods();
        Method[] declaredMethods4 = com.sk.sqhk.network.NetApiRequest.class.getDeclaredMethods();
        int methodSize = declaredMethods1.length + declaredMethods2.length + declaredMethods3.length + declaredMethods4.length  ;
        System.out.println("方法数量:"+methodSize);
    }
}