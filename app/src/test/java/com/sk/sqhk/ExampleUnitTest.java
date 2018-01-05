package com.sk.sqhk;

import android.text.TextUtils;

import org.junit.Test;

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
}