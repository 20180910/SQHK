package com.sk.sqhk;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.sk.sqhk", appContext.getPackageName());
    }
    @Test
    public void asasdfdf() throws Exception {
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
